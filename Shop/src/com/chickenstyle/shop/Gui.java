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
				meta.setDisplayName(ChatColor.YELLOW + getFriendlyName(newitem.getType()) + ":");
	        }
		    ArrayList<String> lore = new ArrayList<>();
		    lore.add(ChatColor.GRAY + "");
			lore.add(ChatColor.WHITE + "Purchase for: " + ChatColor.GREEN +  + value +"©");
			lore.add(ChatColor.WHITE + "Sell for: " + ChatColor.RED + (int) BlockValue.getSellPrice(newitem.getType()) + "©");
		    lore.add(ChatColor.GRAY + "");
			lore.add(ChatColor.WHITE + "Left click to " + ChatColor.YELLOW + "" + ChatColor.BOLD +"Purchase" + ChatColor.WHITE+ "!");
			lore.add(ChatColor.WHITE + "Right click to " + ChatColor.YELLOW + "" + ChatColor.BOLD +"Sell" + ChatColor.WHITE + "!");
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
		meta.setDisplayName(ChatColor.WHITE + "Go Back");
		back.setItemMeta(meta);
		
		//Buy Amount
		
		ItemStack buy = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
		ItemMeta buymeta = buy.getItemMeta();
		
		Inventory gui = Bukkit.createInventory(null, 45 , ChatColor.BLACK + "" + ChatColor.UNDERLINE + "Buy Item");

		for (int i = 0; i < gui.getSize(); i++) {
			if  (i == 22) {
				ItemStack newitem = new ItemStack(mat);
				ItemMeta newmeta = newitem.getItemMeta();
		        if (newitem != null && newitem.getType() != Material.AIR) {
					newmeta.setDisplayName(ChatColor.YELLOW + getFriendlyName(newitem.getType()));
					newitem.setItemMeta(newmeta);
		        }
				gui.setItem(22, newitem);
		        
			} else if (i == 40) {
				gui.setItem(40, back);
			} else if (i == 29) {
	        	buymeta.setDisplayName(ChatColor.WHITE + "Purchase 1: " + ChatColor.GREEN + (int) BlockValue.getBuyPrice(mat) + "©");
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(29, buy);
	        } else if (i == 30) {
	        	buymeta.setDisplayName(ChatColor.WHITE + "Purchase 4: " + ChatColor.GREEN + (int) BlockValue.getBuyPrice(mat) * 4 + "©");
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(30, buy);
	        } else if (i == 31) {
	        	buymeta.setDisplayName(ChatColor.WHITE + "Purchase 16: " + ChatColor.GREEN + (int) BlockValue.getBuyPrice(mat) * 16 + "©");
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(31, buy);
	        } else if (i == 32) {
	        	buymeta.setDisplayName(ChatColor.WHITE + "Purchase 32: " + ChatColor.GREEN + (int) BlockValue.getBuyPrice(mat) * 32 + "©");
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(32, buy);
	        } else if (i == 33) {
	        	buymeta.setDisplayName(ChatColor.WHITE + "Purchase 64: " + ChatColor.GREEN + (int) BlockValue.getBuyPrice(mat) * 64 + "©");
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
		meta.setDisplayName(ChatColor.WHITE + "Go Back");
		back.setItemMeta(meta);
		
		//Sell Amount
		
		ItemStack buy = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		ItemMeta buymeta = buy.getItemMeta();
		
		Inventory gui = Bukkit.createInventory(null, 45 , ChatColor.BLACK + "" + ChatColor.UNDERLINE + "Sell Item");

		for (int i = 0; i < gui.getSize(); i++) {
			if  (i == 22) {
				ItemStack newitem = new ItemStack(mat);
				ItemMeta newmeta = newitem.getItemMeta();
		        if (newitem != null && newitem.getType() != Material.AIR) {
					newmeta.setDisplayName(ChatColor.YELLOW + getFriendlyName(newitem.getType()));
					newitem.setItemMeta(newmeta);
		        }
				gui.setItem(22, newitem);
		        
			} else if (i == 40) {
				gui.setItem(40, back);
			} else if (i == 29) {
				buymeta.setDisplayName(ChatColor.WHITE + "Sell 1: " + ChatColor.RED + (int) BlockValue.getSellPrice(mat) + "©");
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(29, buy);
	        	
	        } else if (i == 30) {
	        	buymeta.setDisplayName(ChatColor.WHITE + "Sell 16: " + ChatColor.RED + (int)BlockValue.getSellPrice(mat) * 16 + "©");
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(30, buy);
	        } else if (i == 31) {
	        	buymeta.setDisplayName(ChatColor.WHITE + "Sell 32: " + ChatColor.RED +(int) BlockValue.getSellPrice(mat) * 32 + "©");
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(31, buy);
	        	
	        } else if (i == 32) {
	        	buymeta.setDisplayName(ChatColor.WHITE + "Sell 64: " + ChatColor.RED +(int) BlockValue.getSellPrice(mat) * 64 + "©");
	        	buy.setItemMeta(buymeta);
	        	gui.setItem(32, buy);
	        	
	        } else if (i == 33) {
				int totalamount = 0;
				
				// Count The Amount Of Items
				for (ItemStack m: player.getInventory().getContents()) {
					if (m != null && m.getItemMeta() != null) {
						if (m.getType().equals(mat)) {
						totalamount = totalamount + m.getAmount();
						}
					}
				}
	        	buymeta.setDisplayName(ChatColor.WHITE + "Sell all: " + ChatColor.RED +(int) BlockValue.getSellPrice(mat) * totalamount + "©");
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
