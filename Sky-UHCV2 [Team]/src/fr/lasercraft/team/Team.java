package fr.lasercraft.team;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.lasercraft.SkyUHC;
import fr.lasercraft.inventory.MenuItemTeam;

public class Team {

	private String name;
	private String tag;
	private byte wooldata;
	private List<Player> players = new ArrayList<>();
	public static Team instance;
	public int teamNumber = 0;
	public Location location;
	
	public Team(String name, String tag, byte woolData, int i, Location loc) {
		this.name = name;
		this.tag = tag;
		this.wooldata = woolData;
		this.teamNumber = i;
		this.location = loc;
		String TeamNameWithTag = tag + name;
		SkyUHC.instance.teamsName.put(TeamNameWithTag, this);	
	}
	
	public List<Player> getPlayers(){
		return players;
	}
	
	public int getSize(){
		return players.size();
	}

	public String getName() {
		return name;
	}

	public String getTag() {
		return tag;
	}

	public byte getWooldata() {
		return wooldata;
	}
	
	public boolean contains(Player player){
		if(players.contains(player)) return true;
		return false;
	}
	
	public void addPlayers(Player player){
		if(players.size() != 2){
			players.add(player);
			player.sendMessage("§aVous rejoingnez l'équipe: " + tag + name);
			MenuItemTeam.refreshItemTeam(this);
			return;
		} else {
			player.sendMessage("§cCette équipe est pleine !");
			return;
		}
	}
}
