package fr.lasercraft.runnable;

import java.text.SimpleDateFormat;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lasercraft.GameState;
import fr.lasercraft.SkyUHC;
import fr.lasercraft.lasercraftapi.Title;
import fr.lasercraft.lasercraftapi.scoreboard.ScoreboardSign;
import fr.lasercraft.manager.GameFinish;
import fr.lasercraft.manager.GameManager;


public class PreGameRunnable extends BukkitRunnable{

	private int timer = 1080;
	
	@Override
	public void run() {
		if(timer == 30 || timer == 10 || timer <= 5 && timer != 0){
			for(Player p : Bukkit.getOnlinePlayers()) Title.sendTitle(p, "§cTéléportation dans", "§b" + timer +" §eseconde(s)", 40);
		}
		
		if(timer == 0){
			this.cancel();
			Bukkit.getWorld("world").setPVP(true);
			for(Player p : Bukkit.getOnlinePlayers()){ Title.sendTitle(p, "A l'attaque !", "§7Le PvP est §cactivé", 60); p.playSound(p.getLocation(), Sound.EXPLODE, 4.0F, 4.0F);}
			GameState.setState(GameState.GAME);
			GameManager.gameManager();	
		}
		
		for(Player pls : Bukkit.getOnlinePlayers())
        {
            ScoreboardSign cs = SkyUHC.instance.get(pls);
            String timer1 = new SimpleDateFormat("mm:ss").format(timer * 1000);
        	cs.setLine(6, "§cTéléportation §f§6" + timer1);
            	cs.setLine(10, "§aStatut: §ePréparation");
            	cs.setLine(7, "- Timer -");
            	
                if(!SkyUHC.instance.cs.containsKey(pls))
                    SkyUHC.instance.cs.put(pls, cs);
        }
		
		timer--;
		
		if(SkyUHC.instance.playerInGame.size() == 0){
			new GameFinish();
		}
	}

	
	
}
