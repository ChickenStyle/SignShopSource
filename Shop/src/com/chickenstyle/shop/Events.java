package com.chickenstyle.shop;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;


public class Events implements Listener {
	
	public static String getFriendlyName(Material mat) {
		StringBuilder sb = new StringBuilder();
		for (String str : mat.name().split("_"))
		sb.append(" ").append(Character.toUpperCase(str.charAt(0))).append(str.substring(1).toLowerCase());
		return sb.toString().trim().replace("Diode", "Redstone Repeater").replace("Thin Glass", "Glass Pane").replace("Wood ", "Wooden ");
		}
	
	
	FileConfiguration config = Main.getPlugin(Main.class).getConfig();
	HashMap<Player,Location> blockLocation = new HashMap<>();
	HashMap<Player,Location> openedShop = new HashMap<>();
	//Adding new sign
	@EventHandler
	public void blockPlaceEvent(SignChangeEvent e) {
		Player player = e.getPlayer();
		if (player.hasPermission("shopplus.createsign") || player.hasPermission("shopplus.admin")) {
			if (e.getLine(0).toLowerCase().equals("[newshop]")) {
				e.setLine(0, null);
				e.setLine(1, ChatColor.translateAlternateColorCodes('&', e.getLine(1)));
				e.setLine(2, ChatColor.translateAlternateColorCodes('&', e.getLine(2)));
				e.setLine(3, ChatColor.translateAlternateColorCodes('&', e.getLine(3)));
				blockLocation.put(player, e.getBlock().getLocation());
				Gui.addNewShop(player);
			}
		}
		
	}
	
	// Save Event
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		Player player = (Player) e.getPlayer();
		Inventory inv = e.getInventory();
		if (e.getView().getTitle().equals(ChatColor.BLACK + "" + ChatColor.UNDERLINE + "Add New Shop")) {
			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i < 45; i++) {
				if (inv.getItem(i) != null && inv.getItem(i).getType() != Material.BLACK_STAINED_GLASS_PANE) {
					list.add(inv.getItem(i).getType().toString() + ":" + i);
				}
			}
			if (list.isEmpty()) {
				player.sendMessage(ChatColor.RED + "Please Put Items Before Closing The GUI!");
			} else {
				Location loc = blockLocation.get(player);
				ShopsLocation.setItems(list,loc.getBlockX(),loc.getBlockY(),loc.getBlockZ());
				player.sendMessage(ChatColor.GREEN + "New Shop Was Added!");
			}
		}
	}
	
	//Open Shop Event
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
		if (e.getClickedBlock().getType().toString().endsWith("SIGN") && e.getClickedBlock() != null) {
			Sign sign = (Sign) e.getClickedBlock().getState();
			if (ShopsLocation.checkLocation(sign.getLocation())) {
				Gui.chooseItem(player, sign.getLocation());
				openedShop.put(player, e.getClickedBlock().getLocation());
			}
		}
      }
	}
	
	// Buy and Choose Item
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) throws UserDoesNotExistException, NoLoanPermittedException {
		if (e.getView().getTitle().equals(ChatColor.BLACK + "" + ChatColor.UNDERLINE + "Shop")) {
			e.setCancelled(true);
			int slot = e.getSlot();
			if (e.getClickedInventory() == null) {
				return;
			}
 			ItemStack item = e.getClickedInventory().getItem(slot);
			Player player = (Player) e.getWhoClicked();
			if (e.getClickedInventory().getType().toString() == "CHEST") {
				if (item != null && item.getType() != Material.BLACK_STAINED_GLASS_PANE) {
					if (e.getClick().equals(ClickType.LEFT)) {
						Gui.buyItem(player, item.getType());
					} else if (e.getClick().equals(ClickType.RIGHT)) {
						Gui.sellItem(player, item.getType());
					}
				}
			}
		} else if (e.getView().getTitle().equals(ChatColor.BLACK + "" + ChatColor.UNDERLINE + "Buy Item")) {
			e.setCancelled(true);
			int slot = e.getSlot();
			if (e.getClickedInventory() == null) {
				return;
			}
			ItemStack item = e.getClickedInventory().getItem(22);
			Player player = (Player) e.getWhoClicked();
			double money = Economy.getMoney(player.getName());
			double buyprice = BlockValue.getBuyPrice(item.getType());
			if (e.getClickedInventory().getType().toString() == "CHEST") {
				if (slot == 29) {
					if (money >= BlockValue.getBuyPrice(item.getType())) {
						money = money - buyprice;
						Economy.setMoney(player.getName(), money);
						player.sendMessage(ChatColor.GREEN + "You successfully bought 1 " + getFriendlyName(item.getType()) + "!");
						player.sendMessage(ChatColor.GRAY + "Your balance is: " + ChatColor.GOLD + money + "$");
						ItemStack buyitem = new ItemStack(item.getType(),1);
						player.getInventory().addItem(buyitem);
					} else {
						player.sendMessage(ChatColor.RED + "You dont have enough money to buy this!");
					}
				}
				if (slot == 30) {
					if (money >= buyprice * 4) {
						money = money - buyprice * 4;
						Economy.setMoney(player.getName(), money);
						player.sendMessage(ChatColor.GREEN + "You successfully bought 4 " + getFriendlyName(item.getType()) + "!");
						player.sendMessage(ChatColor.GRAY + "Your balance is: " + ChatColor.GOLD + money + "$");
						ItemStack buyitem = new ItemStack(item.getType(),4);
						player.getInventory().addItem(buyitem);
					} else {
						player.sendMessage(ChatColor.RED + "You dont have enough money to buy this!");
					}
				}
				if (slot == 31) {
					if (money >= buyprice * 16) {
						money = money - buyprice * 16;
						Economy.setMoney(player.getName(), money);
						player.sendMessage(ChatColor.GREEN + "You successfully bought 16 " + getFriendlyName(item.getType()) + "!");
						player.sendMessage(ChatColor.GRAY + "Your balance is: " + ChatColor.GOLD + money + "$");
						ItemStack buyitem = new ItemStack(item.getType(),16);
						player.getInventory().addItem(buyitem);
					} else {
						player.sendMessage(ChatColor.RED + "You dont have enough money to buy this!");
					}
				}
				if (slot == 32) {
					if (money >= buyprice * 32) {
						money = money - buyprice * 32;
						Economy.setMoney(player.getName(), money);
						player.sendMessage(ChatColor.GREEN + "You successfully bought 32 " + getFriendlyName(item.getType()) + "!");
						player.sendMessage(ChatColor.GRAY + "Your balance is: " + ChatColor.GOLD + money + "$");
						ItemStack buyitem = new ItemStack(item.getType(),32);
						player.getInventory().addItem(buyitem);
					} else {
						player.sendMessage(ChatColor.RED + "You dont have enough money to buy this!");
					}
				}
				if (slot == 33) {
					if (money >= buyprice * 64) {
						money = money - buyprice * 64;
						Economy.setMoney(player.getName(), money);
						player.sendMessage(ChatColor.GREEN + "You successfully bought 64 " + getFriendlyName(item.getType()) + "!");
						player.sendMessage(ChatColor.GRAY + "Your balance is: " + ChatColor.GOLD + money + "$");
						ItemStack buyitem = new ItemStack(item.getType(),64);
						player.getInventory().addItem(buyitem);
					} else {
						player.sendMessage(ChatColor.RED + "You dont have enough money to buy this!");
					}
				}
				if (slot == 40) {
					Gui.chooseItem(player, openedShop.get(player));
				}
			}
			
			
		} else if (e.getView().getTitle().equals(ChatColor.BLACK + "" + ChatColor.UNDERLINE + "Sell Item")) {
			e.setCancelled(true);
			int slot = e.getSlot();
			if (e.getClickedInventory() == null) {
				return;
			}
			ItemStack item = e.getClickedInventory().getItem(22);
			Player player = (Player) e.getWhoClicked();
			double money = Economy.getMoney(player.getName());
			double sellprice = BlockValue.getSellPrice(item.getType());
			if (e.getClickedInventory().getType().toString() == "CHEST") {
				if (item != null && item.getType() != Material.BLACK_STAINED_GLASS_PANE) {
					// Count The Amount Of Items
					int totalamount = 0;
					for (ItemStack m: player.getInventory().getContents()) {
						if (m != null && m.getItemMeta() != null) {
							if (m.getType().equals(item.getType())) {
							totalamount = totalamount + m.getAmount();
							}
						}
					}
					
					if (slot == 29) {
						if (totalamount >= 1) {
							removeItems(player.getInventory(), item.getType(), 1);
							Economy.setMoney(player.getName(), money + sellprice * 1);
							money = money + sellprice * 1;
							player.sendMessage(ChatColor.GREEN + "You successfully sold 1 " + item.getType() + "!");
							player.sendMessage(ChatColor.GRAY + "Your balance is: " + ChatColor.GOLD + money + "$");
						} else {
							player.sendMessage(ChatColor.RED + "You dont have enought items to sell!");
						}
					}
					if (slot == 30) {
						if (totalamount >= 16) {
							removeItems(player.getInventory(), item.getType(), 16);
							Economy.setMoney(player.getName(), money + sellprice * 16);
							money = money + sellprice * 16;
							player.sendMessage(ChatColor.GREEN + "You successfully sold 16 " + item.getType() + "!");
							player.sendMessage(ChatColor.GRAY + "Your balance is: " + ChatColor.GOLD + money + "$");
						} else {
							player.sendMessage(ChatColor.RED + "You dont have enought items to sell!");
						}
					}
					if (slot == 31) {
						if (totalamount >= 32) {
							removeItems(player.getInventory(), item.getType(), 32);
							Economy.setMoney(player.getName(), money + sellprice * 32);
							money = money + sellprice * 32;
							player.sendMessage(ChatColor.GREEN + "You successfully sold 32 " + item.getType() + "!");
							player.sendMessage(ChatColor.GRAY + "Your balance is: " + ChatColor.GOLD + money + "$");
						} else {
							player.sendMessage(ChatColor.RED + "You dont have enought items to sell!");
						}
					}
					if (slot == 32) {
						if (totalamount >= 64) {
							removeItems(player.getInventory(), item.getType(), 64);
							Economy.setMoney(player.getName(), money + sellprice * 64);
							money = money + sellprice * 64;
							player.sendMessage(ChatColor.GREEN + "You successfully sold 64 " + item.getType() + "!");
							player.sendMessage(ChatColor.GRAY + "Your balance is: " + ChatColor.GOLD + money + "$");
						} else {
							player.sendMessage(ChatColor.RED + "You dont have enought items to sell!");
						}
					}
					if (slot == 33) {
						if (totalamount != 0) {
							removeItems(player.getInventory(), item.getType(), totalamount);
							Economy.setMoney(player.getName(), money + sellprice * totalamount);
							money = money + sellprice * totalamount;
							player.sendMessage(ChatColor.GREEN + "You successfully sold " + totalamount + " " + item.getType() + "!");
							player.sendMessage(ChatColor.GRAY + "Your balance is: " + ChatColor.GOLD + money + "$");
						} else {
							player.sendMessage(ChatColor.RED + "You dont have enought items to sell!");
						}
					}
					if (slot == 40) {
						Gui.chooseItem(player, openedShop.get(player));
					}

					
				}
			}
		} else if (e.getView().getTitle().equals(ChatColor.BLACK + "" + ChatColor.UNDERLINE + "Add New Shop")) {
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
			if (!emptySlot.contains(e.getSlot())) {
				if (e.getClickedInventory().getType().toString() == "CHEST") {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		if (e.getBlock().getType().toString().endsWith("SIGN")) {
			Location loc = e.getBlock().getLocation();
			if (ShopsLocation.checkLocation(loc) == true) {
				if (e.getBlock().getType().toString().endsWith("SIGN")) {
				if (player.hasPermission("shopplus.removesign") || player.hasPermission("shopplus.admin")) {
					player.sendMessage(ChatColor.GREEN + "You successfully removed this shop!");
				} else {
					e.setCancelled(true);
					player.sendMessage(ChatColor.RED + "You dont have permission to remove this sign!");
					ShopsLocation.removeShop(loc.getBlockX(), loc.getBlockY(), loc.getBlockX());
				}
			  }
			}
		}
	}
	
    public static void removeItems(Inventory inventory, Material type, int amount) {
        if (amount <= 0) return;
        int size = inventory.getSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack is = inventory.getItem(slot);
            if (is == null) continue;
            if (type == is.getType()) {
                int newAmount = is.getAmount() - amount;
                if (newAmount > 0) {
                    is.setAmount(newAmount);
                    break;
                } else {
                    inventory.clear(slot);
                    amount = -newAmount;
                    if (amount == 0) break;
                }
            }
        }
    }
	
	
	
	
}
