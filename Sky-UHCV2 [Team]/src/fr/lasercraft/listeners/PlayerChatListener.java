package fr.lasercraft.listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.lasercraft.SkyUHC;
import fr.lasercraft.lasercraftapi.BDD.MySQL;
import fr.lasercraft.lasercraftapi.BDD.RankUnit;

import fr.lasercraft.team.Team;
import fr.lasercraft.team.TeamManager;

public class PlayerChatListener implements Listener {

	private HashMap<UUID, Long> cooldown = new HashMap<>();
	
	@EventHandler
	public void onChat(PlayerChatEvent event){
		
		Player p = event.getPlayer();
		
		RankUnit rank = MySQL.getRank(p);
		String pref = rank.getPrefix();
	
		String s1 = event.getMessage();
		char s = s1.charAt(0);
		String s7 = String.valueOf(s);
		if(s7.equalsIgnoreCase("!")){
			
			StringBuffer buff = new StringBuffer (event.getMessage());
						
			for(Team team : SkyUHC.instance.teams){
				if(team.contains(event.getPlayer())){
					buff.deleteCharAt(0); 
					Bukkit.broadcastMessage("(All) " + (pref == "§7" ? "" + team.getTag() + event.getPlayer().getName() : team.getTag() + "[" + pref + "] " + event.getPlayer().getName()) + " §f" + event.getMessage());
					return;
					}
				}
			}else{
				for(Team team : SkyUHC.instance.teams){
				 if(team.contains(event.getPlayer())){
					Bukkit.broadcastMessage("(Team) " + (pref == "§7" ? "" + team.getTag() + event.getPlayer().getName() : team.getTag() + "[" + pref + "] " + event.getPlayer().getName()) + " §f" + event.getMessage());
				 }
				return;
			}
		}
	
	}
	
	@EventHandler
    public void onLeave(PlayerQuitEvent e) {
            cooldown.remove(e.getPlayer().getUniqueId());
    }
}
