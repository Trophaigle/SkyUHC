package fr.lasercraft.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import fr.lasercraft.SkyUHC;

public class LocationManager {
	
	public static ArrayList<Location> location = new ArrayList<>();
	public static ArrayList<Location> locationteleport = new ArrayList<>();
	
	public static void init(){
		
		addLocationSpawn(new Location(Bukkit.getWorld("world"), -608, 89, -460));
		addLocationSpawn(new Location(Bukkit.getWorld("world"), -733, 89, -244));
		addLocationSpawn(new Location(Bukkit.getWorld("world"), -820, 88, -50));
		addLocationSpawn(new Location(Bukkit.getWorld("world"), -985, 87, -88));
		addLocationSpawn(new Location(Bukkit.getWorld("world"),-1117, 87, -257));
		addLocationSpawn(new Location(Bukkit.getWorld("world"), -1019, 87, -453));
		addLocationSpawn(new Location(Bukkit.getWorld("world"), -927, 87, -668));
	
		addLocationDeathMatch(new Location(Bukkit.getWorld("world"), -867, 107, -282));
		addLocationDeathMatch(new Location(Bukkit.getWorld("world"), -880, 106, -317));
		addLocationDeathMatch(new Location(Bukkit.getWorld("world"), -920, 122, -337));
		addLocationDeathMatch(new Location(Bukkit.getWorld("world"), -970, 106, -317));
		addLocationDeathMatch(new Location(Bukkit.getWorld("world"), -965, 107, -283));
		addLocationDeathMatch(new Location(Bukkit.getWorld("world"), -964, 107, -244));
		addLocationDeathMatch(new Location(Bukkit.getWorld("world"), -915, 107, -223));
		addLocationDeathMatch(new Location(Bukkit.getWorld("world"), -884, 107, -242));
	}
	
	private static void addLocationSpawn(Location loc){
		location.add(loc);
	}
	
	private static void addLocationDeathMatch(Location loc){
		locationteleport.add(loc);
	}
	
	public static void teleport(List<Player> player){
		Random r = new Random();
		Location loc = location.get(r.nextInt(location.size()));
		for(Player players : player){
			players.teleport(loc);
		}
		location.remove(loc);
	}
	
	public static void teleportDeathMatch(Player p){
		Random r = new Random();
		Location loc = locationteleport.get(r.nextInt(locationteleport.size()));
		p.teleport(loc);
		location.remove(loc);
	}
	
}
