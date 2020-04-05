package com.chickenstyle.shop;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ShopPlus implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length >= 1) {
					switch (args[0].toLowerCase()) {
					case "setprice":
						if (args.length == 3) {
						if (player.hasPermission("shopplus.admin") || player.hasPermission("shopplus." + args[0].toLowerCase())) {
						ItemStack iteminhand = player.getInventory().getItemInMainHand();
							if (iteminhand != null) {
								if (isInteger(args[1]) || isDouble(args[1]) ) {
									if (isInteger(args[2]) || isDouble(args[2])) {
									if (!BlockValue.getBlocks().isEmpty()) {
										boolean containsItem = false;
										for (String item: BlockValue.getBlocks()) {
											Material getitem = Material.valueOf(item.split(":")[0]);
											if (getitem.equals(iteminhand.getType())) {
												containsItem = true;
											}
										}
										if (containsItem == true) {
											player.sendMessage(ChatColor.RED + "This item already has price.");
										} else {
											BlockValue.addBlockValue(iteminhand.getType(), Double.valueOf(args[1]), Double.valueOf(args[2]));
											player.sendMessage(ChatColor.GREEN + "Item's price was set successfully!");
										}
									} else {
										BlockValue.addBlockValue(iteminhand.getType(), Double.valueOf(args[1]), Double.valueOf(args[2]));
										player.sendMessage(ChatColor.GREEN + "Item's price was set successfully!");
									}
								  } else {
										player.sendMessage(ChatColor.RED + "Invalid Number!");
									}
								} else {
									player.sendMessage(ChatColor.RED + "Invalid Number!");
								}
							} else {
								player.sendMessage(ChatColor.RED + "Please hold an item!");
							}
						}
					} else {
						player.sendMessage(ChatColor.RED + "Invalid Usage");
						player.sendMessage(ChatColor.GRAY + "/sp setprice [number]");
					}
					break;
					
					case "reload":
						if (player.hasPermission("shopplus.reload") || player.hasPermission("shopplus.admin")) {
							ShopsLocation.configReload();
							BlockValue.configReload();
							Main.getPlugin(Main.class).reloadConfig();
							player.sendMessage(ChatColor.GREEN + "Config has been reloaded!");
						}
					break;
					
					default:
						player.sendMessage(ChatColor.GRAY + "/sp [setprice/reload]");
					break;
					}
				
				} else {
					player.sendMessage(ChatColor.GRAY + "/sp [setprice/reload]");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You cannot use this command through console!");
			}
		return false;
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
	public static boolean isSound(String s) {
	    try { 
	       Sound.valueOf(s); 
	    } catch(Exception e) { 
	        return false; 
	    }
	    return true;
	}
	
	public static boolean isDouble(String s) {
	    try { 
	       Double.valueOf(s); 
	    } catch(Exception e) { 
	        return false; 
	    }
		return true;
	}
}
