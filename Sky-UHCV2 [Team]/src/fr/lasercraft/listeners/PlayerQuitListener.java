package fr.lasercraft.listeners;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.lasercraft.GameState;
import fr.lasercraft.SkyUHC;
import fr.lasercraft.lasercraftapi.events.CommandHubEvent;
import fr.lasercraft.lasercraftapi.scoreboard.ScoreboardSign;
import fr.lasercraft.team.TeamManager;

public class PlayerQuitListener implements Listener {

	@EventHandler
	public void onQuit(CommandHubEvent e){
		final Player p = e.getPlayer();
		if(!GameState.isState(GameState.LOBBY)){
			if(SkyUHC.instance.playerInGame.contains(p.getUniqueId())){
				p.sendMessage("§e--------------------------------------------");
				p.sendMessage("§6Fin de partie sur le serveur: §a"+Bukkit.getServerName());
				p.sendMessage("§7Total Kill(s) sur la partie: §e"+SkyUHC.instance.kills.get(p));
				p.sendMessage("§7Total gain de §eCoins §7sur la partie: §e"+SkyUHC.instance.coins.get(p));
				p.sendMessage("§e--------------------------------------------");
		}
			
			Bukkit.getScheduler().runTaskLater(SkyUHC.instance, new Runnable() {
				
				@Override
				public void run() {
					ByteArrayDataOutput out = ByteStreams.newDataOutput();
					out.writeUTF("Connect");
					out.writeUTF("hub01");
					p.sendPluginMessage(SkyUHC.instance, "BungeeCord", out.toByteArray());
					SkyUHC.instance.playerInGame.remove(p.getUniqueId());
					updateScoreboard(p);
				}
			}, 5);
		
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		updateScoreboard(p);
		SkyUHC.instance.playerInGame.remove(e.getPlayer());
	}
	
	private void updateScoreboard(Player p){
		for(Entry<Player, ScoreboardSign> entry : SkyUHC.instance.cs.entrySet()){
			entry.getValue().setLine(7, "§cRouge §f"+TeamManager.getInstance().getRedSize());
        	entry.getValue().setLine(6, "§9Bleu §f"+TeamManager.getInstance().getBlueSize());
		}
	}
	
}
