package com.chickenstyle.shop;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Gui {
	public static void addNewShop(Player player) {
		
		//Black Glass
		ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta glassMeta = glass.getItemMeta();
		glassMeta.setDisplayName(" ");
		glass.setItemMeta(glassMeta);
		
		Inventory gui = Bukkit.createInventory(null, 45 , ChatColor.BLACK + "" + ChatColor.UNDERLINE + "Add New Shop");
		ArrayList<Integer> emptySlot = new ArrayList<Integer>();
		emptySlot.add(16);
		emptySlot.add(10);
		emptySlot.add(11);
		emptySlot.add(12);
		emptySlot.add(13);
		emptySlot.add(14);
		emptySlot.add(15);
		emptySlot.add(25);
		emptySlot.add(19);
		emptySlot.add(20);
		emptySlot.add(21);
		emptySlot.add(22);
		emptySlot.add(23);
		emptySlot.add(24);
		emptySlot.add(34);
		emptySlot.add(28);
		emptySlot.add(29);
		emptySlot.add(30);
		emptySlot.add(31);
		emptySlot.add(32);
		emptySlot.add(33);
		for (int i = 0; i < gui.getSize(); i++) {
			if (emptySlot.contains(i)) {
				gui.setItem(i, null);
			} else {
				gui.setItem(i, glass);
			}
		}
		player.openInventory(gui);
	}
	
	public static void chooseItem(Player player,Location loc) {
		
		//Black Glass
		ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta glassMeta = glass.getItemMeta();
		glassMeta.setDisplayName(" ");
		glass.setItemMeta(glassMeta);
		
		Inventory gui = Bukkit.createInventory(null, 45 , ChatColor.BLACK + "" + ChatColor.UNDERLINE + "Shop");
		ArrayList<Integer> emptySlot = new ArrayList<Integer>();
		emptySlot.add(16);
		emptySlot.add(10);
		emptySlot.add(11);
		emptySlot.add(12);
		emptySlot.add(13);
		emptySlot.add(14);
		emptySlot.add(15);
		emptySlot.add(25);
		emptySlot.add(19);
		emptySlot.add(20);
		emptySlot.add(21);
		emptySlot.add(22);
		emptySlot.add(23);
		emptySlot.add(24);
		emptySlot.add(34);
		emptySlot.add(28);
		emptySlot.add(29);
		emptySlot.add(30);
		emptySlot.add(31);
		emptySlot.add(32);
		emptySlot.add(33);
		for (int i = 0; i < gui.getSize(); i++) {
			if (emptySlot.contains(i)) {
				gui.setItem(i, null);
			} else {
				gui.setItem(i, glass);
			}
		}
		for (String item: ShopsLocation.getItems(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ())) {
			Material mat = Material.valueOf(item.split(":")[0]);
			ItemStack newitem = new ItemStack(mat);
			ItemMeta meta = newitem.getItemMeta();
			int value = (int) BlockValue.getBuyPrice(newitem.getType());
	        if (newitem != null && newitem.getType() != Material.AIR) {
				meta.setDisplayName(ChatColor.GOLD + getFriendlyName(newitem.getType()));
	        }
		    ArrayList<String> lore = new ArrayList<>();
			lore.add(ChatColor.AQUA + "Buy 1 unit for: " + value + "$");
			lore.add(ChatColor.AQUA + "Sell 1 unit for: " + (int) BlockValue.getSellPrice(newitem.getType()) + "$");
			lore.add(ChatColor.GREEN + "Click left to buy!");
			lore.add(ChatColor.RED + "Click right to sell!");
			meta.setLore(lore);
			newitem.setItemMeta(meta);
			
			gui.setItem(Integer.valueOf(item.split(":")[1]), newitem);
			
		}
		player.openInventory(gui);
	}
	
	
	
	
	public static void buyItem(Player player,Material mat) {
		
		//Black Glass
		ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta glassMeta = glass.getItemMeta();
		glassMeta.setDisplayName(" ");
		glass.setItemMeta(glassMeta);
		
		//Back Button
		ItemStack back = new ItemStack(Material.ARROW);
		ItemMeta meta = back.getItemMeta();
		meta.setDisplayName(ChatColor.GRAY + "Go Back");
		back.setItemMeta(meta);
		
		//Buy Amount
		
		ItemStack buy = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
		ItemMeta buymeta = buy.getItemMeta();
		
		Inventory gui = Bukkit.createInventory(null, 45 , ChatColor.BLACK + "" + ChatColor.UNDERLINE + "Buy Item");

		for (int i = 0; i < gui.getSize(); i++) {
			if  (i == 22) {
				ItemStack newitem = new ItemStack(mat);
				ItemMeta newmeta = newitem.getItemMeta();
				int value = (int) BlockValue.getBuyPrice(mat);
		        if (newitem != null && newitem.getType() != Material.AIR) {
					newmeta.setDisplayName(ChatColor.GOLD + getFriendlyName(newitem.getType()));
		        }
			    ArrayList<String> lore = new ArrayList<>();
				lore.add(ChatColor.AQUA + "Buy 1 unit for: " + value + "$");
				newmeta.setLore(lore);
				newitem.setItemMeta(newmeta);
				gui.setItem(22, newitem);
		        
			} else if (i == 40) {
				gui.setItem(40, back);
			} else if (i == 29) {
	        	buymeta.setDisplayName(ChatColor.GREEN + "Buy 1");
	        	ArrayList<String> lore = new ArrayList<>();
	        	lore.add(ChatColor.AQUA + "Buy 1 unit for: " + ChatColor.GOLD + (int) BlockValue.getBuyPrice(mat) + "$");
	        	buymeta.setLore(lore);
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(29, buy);
	        } else if (i == 30) {
	        	buymeta.setDisplayName(ChatColor.GREEN + "Buy 4");
	        	ArrayList<String> lore = new ArrayList<>();
	        	lore.add(ChatColor.AQUA + "Buy 4 units for: " + ChatColor.GOLD + (int) BlockValue.getBuyPrice(mat) * 4 + "$");
	        	buymeta.setLore(lore);
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(30, buy);
	        } else if (i == 31) {
	        	buymeta.setDisplayName(ChatColor.GREEN + "Buy 16");
	        	ArrayList<String> lore = new ArrayList<>();
	        	lore.add(ChatColor.AQUA + "Buy 16 units for: " + ChatColor.GOLD + (int) BlockValue.getBuyPrice(mat) * 16 + "$");
	        	buymeta.setLore(lore);
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(31, buy);
	        } else if (i == 32) {
	        	buymeta.setDisplayName(ChatColor.GREEN + "Buy 32");
	        	ArrayList<String> lore = new ArrayList<>();
	        	lore.add(ChatColor.AQUA + "Buy 32 units for: " + ChatColor.GOLD + (int) BlockValue.getBuyPrice(mat) * 32 + "$");
	        	buymeta.setLore(lore);
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(32, buy);
	        } else if (i == 33) {
	        	buymeta.setDisplayName(ChatColor.GREEN + "Buy 64");
	        	ArrayList<String> lore = new ArrayList<>();
	        	lore.add(ChatColor.AQUA + "Buy 64 units for: " + ChatColor.GOLD + (int) BlockValue.getBuyPrice(mat) * 64 + "$");
	        	buymeta.setLore(lore);
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(33, buy);
	        } else {
				gui.setItem(i, glass);
			}
		}
		
		
		player.openInventory(gui);
	}
	
	public static void sellItem(Player player,Material mat) {
		
		//Black Glass
		ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta glassMeta = glass.getItemMeta();
		glassMeta.setDisplayName(" ");
		glass.setItemMeta(glassMeta);
		
		//Back Button
		ItemStack back = new ItemStack(Material.ARROW);
		ItemMeta meta = back.getItemMeta();
		meta.setDisplayName(ChatColor.GRAY + "Go Back");
		back.setItemMeta(meta);
		
		//Sell Amount
		
		ItemStack buy = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		ItemMeta buymeta = buy.getItemMeta();
		
		Inventory gui = Bukkit.createInventory(null, 45 , ChatColor.BLACK + "" + ChatColor.UNDERLINE + "Sell Item");

		for (int i = 0; i < gui.getSize(); i++) {
			if  (i == 22) {
				ItemStack newitem = new ItemStack(mat);
				ItemMeta newmeta = newitem.getItemMeta();
				int value = (int) BlockValue.getSellPrice(mat);
		        if (newitem != null && newitem.getType() != Material.AIR) {
					newmeta.setDisplayName(ChatColor.GOLD + getFriendlyName(newitem.getType()));
		        }
			    ArrayList<String> lore = new ArrayList<>();
				lore.add(ChatColor.AQUA + "Sell 1 unit for: " + value + "$");
				newmeta.setLore(lore);
				newitem.setItemMeta(newmeta);
				gui.setItem(22, newitem);
		        
			} else if (i == 40) {
				gui.setItem(40, back);
			} else if (i == 29) {
				
	        	buymeta.setDisplayName(ChatColor.RED + "Sell 1");
	        	ArrayList<String> lore = new ArrayList<>();
	        	lore.add(ChatColor.AQUA + "Sell 1 unit for: " + ChatColor.GOLD + (int) BlockValue.getSellPrice(mat) + "$");
	        	buymeta.setLore(lore);
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(29, buy);
	        	
	        } else if (i == 30) {
	        	
	        	buymeta.setDisplayName(ChatColor.RED + "Sell 16");
	        	ArrayList<String> lore = new ArrayList<>();
	        	lore.add(ChatColor.AQUA + "Sell 16 units for: " + ChatColor.GOLD + (int)BlockValue.getSellPrice(mat) * 16 + "$");
	        	buymeta.setLore(lore);
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(30, buy);
	        } else if (i == 31) {
	        	
	        	buymeta.setDisplayName(ChatColor.RED + "Sell 32");
	        	ArrayList<String> lore = new ArrayList<>();
	        	lore.add(ChatColor.AQUA + "Sell 32 units for: " + ChatColor.GOLD +(int) BlockValue.getSellPrice(mat) * 32 + "$");
	        	buymeta.setLore(lore);
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(31, buy);
	        	
	        } else if (i == 32) {
	        	
	        	buymeta.setDisplayName(ChatColor.RED + "Sell 64");
	        	ArrayList<String> lore = new ArrayList<>();
	        	lore.add(ChatColor.AQUA + "Sell 64 units for: " + ChatColor.GOLD +(int) BlockValue.getSellPrice(mat) * 64 + "$");
	        	buymeta.setLore(lore);
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(32, buy);
	        	
	        } else if (i == 33) {
	        	buymeta.setDisplayName(ChatColor.RED + "Sell All");
	        	ArrayList<String> lore = new ArrayList<>();
				int totalamount = 0;
				
				// Count The Amount Of Items
				for (ItemStack m: player.getInventory().getContents()) {
					if (m != null && m.getItemMeta() != null) {
						if (m.getType().equals(mat)) {
						totalamount = totalamount + m.getAmount();
						}
					}
				}
	        	if (totalamount == 0) {
	        		lore.add(ChatColor.RED + "You have dont items to sell!");
	        	} else {
	        		lore.add(ChatColor.AQUA + "Sell All units for: " + ChatColor.GOLD +(int) BlockValue.getSellPrice(mat) * totalamount + "$");
	        	}
	        	buymeta.setLore(lore);
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(33, buy);
	        } else {
				gui.setItem(i, glass);
			}
		}
		
		
		player.openInventory(gui);
	}
	
	

	public static String getFriendlyName(Material mat) {
		StringBuilder sb = new StringBuilder();
		for (String str : mat.name().split("_"))
		sb.append(" ").append(Character.toUpperCase(str.charAt(0))).append(str.substring(1).toLowerCase());
		return sb.toString().trim().replace("Diode", "Redstone Repeater").replace("Thin Glass", "Glass Pane").replace("Wood ", "Wooden ");
		}
}
