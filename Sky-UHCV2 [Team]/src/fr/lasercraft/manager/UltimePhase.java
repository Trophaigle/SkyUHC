package fr.lasercraft.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.util.Vector;

import fr.lasercraft.SkyUHC;

public class UltimePhase {
	
	public UltimePhase() {
		
		Bukkit.getScheduler().runTaskTimer(SkyUHC.instance, new Runnable() {
			
			@Override
			public void run() {
				for(Player player : SkyUHC.instance.playerInGame){
					if(SkyUHC.instance.playerInGame.size() != 1){
						
						TNTPrimed tnt = (TNTPrimed) player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);
						tnt.setYield(3);
						tnt.setCustomName("TNT folle !");
	
					}  else {
						Bukkit.getScheduler().cancelAllTasks();
						new GameFinish();
					}
				}
				
			}
			
		}, 0, 200);
		
	
	}
	
}
