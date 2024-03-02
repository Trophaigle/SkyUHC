package fr.lasercraft.runnable;

import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lasercraft.SkyUHC;
import fr.lasercraft.lasercraftapi.scoreboard.ScoreboardSign;

public class ScoreboardLigneIPRunnable extends BukkitRunnable{

	public int t = 30;
	
	@Override
	public void run() {
		for(final Entry<Player, ScoreboardSign> entry : SkyUHC.instance.cs.entrySet()){
			new BukkitRunnable() {
				
				@Override
				public void run() {
					if(t == 15){
	            	entry.getValue().setLine(0, "§eplayer.server.fr");
	            }else if(t == 14){
	            	entry.getValue().setLine(0, "§6p§eplayer.server.fr");
	            }else if(t == 13){
	            	entry.getValue().setLine(0, "§cp§6l§eay.server.fr");
	            }else if(t == 12){
	            	entry.getValue().setLine(0, "§6p§cl§6a§eayer.server.fr");
	            }else if(t == 11){
	            	entry.getValue().setLine(0, "§ep§6l§ca§6y§e.server.fr");
	            }
	            if(t == 0){
	            	t = 30;
	            }
					
	            t--;
	            
				
	            if(!SkyUHC.instance.cs.containsKey(entry.getKey()))
                    SkyUHC.instance.cs.put(entry.getKey(), entry.getValue());
				}
			}.runTaskTimer(SkyUHC.instance, 0, 5);
			
			
		}
	}

}
