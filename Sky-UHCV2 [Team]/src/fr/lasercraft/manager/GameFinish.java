package fr.lasercraft.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lasercraft.ChangeServeur;
import fr.lasercraft.GameState;
import fr.lasercraft.SkyUHC;

public class GameFinish {

	public int timereject = 15;
	
	public GameFinish() {
		Bukkit.getScheduler().cancelAllTasks();
		GameState.setState(GameState.FINISH);
		for(Player p : Bukkit.getOnlinePlayers()){
			p.setCustomNameVisible(true);
			//METTRE CE MESSAGE DANS L API
			p.sendMessage("§e--------------------------------------------");
			p.sendMessage("§6Fin de partie sur le serveur: §a"+Bukkit.getServerName());
			p.sendMessage("§7Total §aKill(s) §7sur la partie: §e"+SkyUHC.instance.kills.get(p));
			p.sendMessage("§7Total gain de §eCoins §7sur la partie: §e"+SkyUHC.instance.coins.get(p));
			p.sendMessage("§e--------------------------------------------");
			p.setAllowFlight(true);
			p.getWorld().setPVP(false);
		}
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				timereject--;
				if(timereject == 3){
					for(Player p : Bukkit.getOnlinePlayers()){
						ChangeServeur.changeServer(p, "hub01");
						//CHANGER DE SERVER
						//OU KICK OU RESTART
					}
				}
				if(timereject == 0){
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
					
					this.cancel();
		
					timereject = 15;
				}
			}
		}.runTaskTimer(SkyUHC.instance, 0, 20);	
	}
	
}
