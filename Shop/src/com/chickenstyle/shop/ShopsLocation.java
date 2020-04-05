package com.chickenstyle.shop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;



public class ShopsLocation {
	
	private static File file;
	private static YamlConfiguration config;
    public ShopsLocation(Main main) {
  	  file = new File(main.getDataFolder(), "ShopsLocation.yml");
  	 if (!file.exists()) {
  		 try {
				 file.createNewFile();
		    	 config = YamlConfiguration.loadConfiguration(file);;
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
	static public ArrayList<String> getItems(int locx,int locy,int locz) {
    	ArrayList<String> list = (ArrayList<String>) config.getList("ShopLocation." + locx + "a" + locy + "a" + locz);
    	return list;
    }
    
    static public void setItems(ArrayList<String> items,int locx,int locy,int locz) {
  	    config.set("ShopLocation." + locx + "a" + locy + "a" + locz, items);
  	try {
			config.save(file);
	    	config = YamlConfiguration.loadConfiguration(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
    static public void removeShop(int locx,int locy,int locz) {
    	config.set("ShopLocation." + locx + "a" + locy + "a" + locz, null);
		try {
			config.save(file);
			config = YamlConfiguration.loadConfiguration(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
  

	static public boolean checkLocation(Location loc) {
		if (config.get("ShopLocation." + loc.getBlockX() + "a" + loc.getBlockY() + "a" + loc.getBlockZ()) != null) {
			return true;
		}
  		return false;
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
