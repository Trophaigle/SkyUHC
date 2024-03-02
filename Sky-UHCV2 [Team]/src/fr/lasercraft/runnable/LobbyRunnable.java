package fr.lasercraft.runnable;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import fr.lasercraft.GameState;
import fr.lasercraft.SkyUHC;
import fr.lasercraft.lasercraftapi.Title;
import fr.lasercraft.lasercraftapi.scoreboard.ScoreboardSign;
import fr.lasercraft.manager.GameManager;

public class LobbyRunnable extends BukkitRunnable{

	public static boolean start = false;
	public static int timer = 30;
	
	@Override
	public void run() {
		
		for(Player pl : Bukkit.getOnlinePlayers()){
			pl.setLevel(timer);
		}
		
		/* MANQUE DE JOUEURS */
		if(Bukkit.getOnlinePlayers().size() < 1){
			Bukkit.broadcastMessage("Manque joueur");
			start = false;
			timer = 30;
			this.cancel();
			return;
		}
		
		if(Bukkit.getOnlinePlayers().size() == 0){
			timer = 30;
			this.cancel();
			start = false;
			return;
		}
		
		/* TEMPS A ZERO */
		if(timer == 0){
			
			this.cancel();
			GameState.setState(GameState.CANTMOVE);
			GameManager.pregameManager();
			return;
		}
		
		/* MESSAGE TIMER */
		if((timer == 30) || (timer == 15) || (timer == 10) || (timer <= 5 && timer != 0)){
			for(Player p : Bukkit.getOnlinePlayers()){
			Title.sendActionBar(p, "§6La partie commence dans §3"+timer+" §6seconde(s)");
			p.playSound(p.getLocation(), Sound.NOTE_PIANO, 4.0F, 4.0F);
			}
		}
		
		
		for(Entry<Player, ScoreboardSign> entry : SkyUHC.instance.cs.entrySet()){
			entry.getValue().setLine(6, "Début dans §a" + timer);
		
		}
		timer--;
	
	}

}
