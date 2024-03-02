package fr.lasercraft.runnable;

import java.text.SimpleDateFormat;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lasercraft.GameState;
import fr.lasercraft.SkyUHC;
import fr.lasercraft.lasercraftapi.scoreboard.ScoreboardSign;
import fr.lasercraft.manager.GameFinish;
import fr.lasercraft.manager.UltimePhase;

public class GameRunnable extends BukkitRunnable{

	private int timer = 900;
	
	@Override
	public void run() {
		
		if(!GameState.isState(GameState.GAME)) {
			this.cancel();
		}
		
		for(Player pls : Bukkit.getOnlinePlayers())
        {
            ScoreboardSign cs = SkyUHC.instance.get(pls);
            String timer1 = new SimpleDateFormat("mm:ss").format(timer * 1000);
        	cs.setLine(6, "§fTemps §6" + timer1);
            	cs.setLine(10, "§aStatut: §eCombat !");
            	
                if(!SkyUHC.instance.cs.containsKey(pls))
                    SkyUHC.instance.cs.put(pls, cs);
        }
		timer--;
		
		if(timer == 0) {
			this.cancel();
			new UltimePhase();
			for(Entry<Player, ScoreboardSign> entry : SkyUHC.instance.cs.entrySet()) {
				entry.getValue().removeLine(6);
				entry.getValue().removeLine(7);
				entry.getValue().setLine(6, "§cPluie de TNT !");
			}
			Bukkit.broadcastMessage("§cAttention des TnT sont maintenant présentent !");
			
		}
		
		if(SkyUHC.instance.playerInGame.size() == 1){
			new GameFinish();
			this.cancel();
		}
	}
	
	public static void cancelTask() {
		
	}

}
