package com.chickenstyle.shop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;



public class BlockValue {
	
	private static File file;
	private static YamlConfiguration config;
    public BlockValue(Main main) {
  	  file = new File(main.getDataFolder(), "BlocksValue.yml");
  	 if (!file.exists()) {
  		 try {
				 file.createNewFile();
		    	 config = YamlConfiguration.loadConfiguration(file);
		    	 Material[] items = {};
		    	  	config.set("items", items);
		    	  	try {
		    				config.save(file);
		    		    	config = YamlConfiguration.loadConfiguration(file);
		    			} catch (IOException e) {
		    				e.printStackTrace();
		    			}
			} catch (IOException e) {
				e.printStackTrace();
			}
  		 
  	 }
    config = YamlConfiguration.loadConfiguration(file);
   }
    @SuppressWarnings("unchecked")
    static public void addBlockValue(Material material,double buyprice,double sellprice) {
	ArrayList<String> items = (ArrayList<String>) config.getList("items");
	items.add(material + ":" + buyprice + ":" + sellprice);
  	config.set("items", items);
  	try {
			config.save(file);
	    	config = YamlConfiguration.loadConfiguration(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
  

	@SuppressWarnings("unchecked")
	static public ArrayList<String> getBlocks() {
	ArrayList<String> items = (ArrayList<String>) config.getList("items");
  	return items;
  }
	
	@SuppressWarnings({ "unchecked"})
	static public double getBuyPrice(Material material) {
	ArrayList<String> items = (ArrayList<String>) config.getList("items");
	for (String item:items) {
		Material newitem = Material.valueOf(item.split(":")[0]);
		if (newitem.equals(material)) {
			return Double.valueOf(item.split(":")[1]);
		}
	}
	return 0.0;
	}
	
	@SuppressWarnings("unchecked")
	static public double getSellPrice(Material material) {
		ArrayList<String> items = (ArrayList<String>) config.getList("items");
		for (String item:items) {
			Material newitem = Material.valueOf(item.split(":")[0]);
			if (newitem.equals(material)) {
				return Double.valueOf(item.split(":")[2]);
			}
		}
		return 0.0;
	}
	
	static public void configReload() {
		try {
			config.save(file);
			config = YamlConfiguration.loadConfiguration(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
