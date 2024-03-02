package fr.lasercraft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.lasercraft.command.StartExecutor;
import fr.lasercraft.inventory.VirtualInventoryTeam;
import fr.lasercraft.lasercraftapi.Cuboide;
import fr.lasercraft.lasercraftapi.scoreboard.ScoreboardSign;
import fr.lasercraft.manager.LocationManager;
import fr.lasercraft.team.Team;
import fr.lasercraft.team.TeamManager;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.WorldGenerator;


public class SkyUHC extends JavaPlugin{

	public static Map<Player, ScoreboardSign> cs = new HashMap<>();
	public static SkyUHC instance;
	public ArrayList<Player> playerInGame = new ArrayList<>();
	public HashMap<Player, Integer> kills = new HashMap<>();
	public HashMap<Player, Double> coins = new HashMap<>();
	public List<Team> teams = new ArrayList<>();
	public HashMap<String, Team> teamsName = new HashMap<>();
	public Cuboide waitingzone;
	public int teamNumber = 0;

	@Override
	public void onEnable() {
		instance = this;
		waitingzone = new Cuboide(new Location(Bukkit.getWorld("world"), -898, 168, -256), new Location(Bukkit.getWorld("world"), -951, 168, -310));
		saveDefaultConfig();
		new ListenerManager(this);
		Bukkit.getWorld("world").setPVP(false);
		TeamManager.getInstance().s = Bukkit.getScoreboardManager().getMainScoreboard();
		TeamManager.getInstance().registerKillTabList();
		GameState.setState(GameState.LOBBY);
		ChangeServeur.init(this);
		LocationManager.init();
		getCommand("start").setExecutor(new StartExecutor());
		
		teams.add(new Team("§7Les guerriers", "§7", (byte) 7, teamNumber++, new Location(Bukkit.getWorld("world"), -798, 87, -441)));
		teams.add(new Team("§7Les sorciers", "§8", (byte) 8, teamNumber++, new Location(Bukkit.getWorld("world"), -798, 87, -441)));
		teams.add(new Team("§7Les gladiateurs", "§9", (byte) 9, teamNumber++, new Location(Bukkit.getWorld("world"), -798, 87, -441)));
		teams.add(new Team("§7Les affolés", "§a", (byte) 10, teamNumber++, new Location(Bukkit.getWorld("world"), -798, 87, -441)));
		teams.add(new Team("§7Les résistants", "§b", (byte) 11, teamNumber++, new Location(Bukkit.getWorld("world"), -798, 87, -441)));
		teams.add(new Team("§7Les gardiens", "§c", (byte) 12, teamNumber++, new Location(Bukkit.getWorld("world"), -798, 87, -441)));
		teams.add(new Team("§7Les invincibles", "§d", (byte) 13, teamNumber++, new Location(Bukkit.getWorld("world"), -798, 87, -441)));
		teams.add(new Team("§7Les inhumains", "§e", (byte) 14, teamNumber++, new Location(Bukkit.getWorld("world"), -798, 87, -441)));
		
		new VirtualInventoryTeam();
		
		super.onEnable();
	}
	
	public ScoreboardSign get(Player pls) {
		return cs.get(pls);
	} 
	
	public Team getTeamByName(String name){
		Team team = teamsName.get(name);
		return team;
	}
}
