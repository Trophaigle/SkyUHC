package fr.lasercraft;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import fr.lasercraft.listeners.BreakBlocListener;
import fr.lasercraft.listeners.JumpListener;
import fr.lasercraft.listeners.PlayJoinListener;
import fr.lasercraft.listeners.PlayerChatListener;
import fr.lasercraft.listeners.PlayerCraftListener;
import fr.lasercraft.listeners.PlayerDeathListener;
import fr.lasercraft.listeners.PlayerInteractItem;
import fr.lasercraft.listeners.PlayerQuitListener;
import fr.lasercraft.ping.ServerPingListener;

public class ListenerManager {
	
	Plugin plugin;
	
	public ListenerManager(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(new JumpListener(), plugin);
		Bukkit.getPluginManager().registerEvents(new PlayJoinListener(), plugin);
		Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), plugin);
		Bukkit.getPluginManager().registerEvents(new BreakBlocListener(), plugin);
		Bukkit.getPluginManager().registerEvents(new PlayerInteractItem(), plugin);
		Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), plugin);
		Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), plugin);
		Bukkit.getPluginManager().registerEvents(new ServerPingListener(), plugin);
		Bukkit.getPluginManager().registerEvents(new PlayerCraftListener(), plugin);
		
	}
	
}
