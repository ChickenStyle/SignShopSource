package com.chickenstyle.shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ShopPlus Has Been Enabled");
		Bukkit.getPluginManager().registerEvents(new Events(), this);
		this.getConfig().options().copyDefaults();
	    saveDefaultConfig();
	    getCommand("signshop").setExecutor(new ShopPlus());
	    new ShopsLocation(this);
	    new BlockValue(this);
	}
}
