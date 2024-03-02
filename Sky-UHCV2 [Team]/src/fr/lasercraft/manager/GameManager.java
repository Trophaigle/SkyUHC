package fr.lasercraft.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.lasercraft.GameState;
import fr.lasercraft.SkyUHC;
import fr.lasercraft.lasercraftapi.Title;
import fr.lasercraft.lasercraftapi.menuitems.utils.ItemBuilder;
import fr.lasercraft.lasercraftapi.scoreboard.ScoreboardSign;
import fr.lasercraft.runnable.GameRunnable;
import fr.lasercraft.runnable.PreGameRunnable;
import fr.lasercraft.team.Team;

public class GameManager {
	
	public static int timer = 10;
	
	public static void pregameManager(){
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.getInventory().clear();
			ScoreboardSign cs = SkyUHC.instance.get(p);
			cs.removeLine(6);
			cs.removeLine(7);
			cs.setLine(6, "§7Téléportation...");
			if(!SkyUHC.instance.cs.containsKey(p))
                SkyUHC.instance.cs.put(p, cs);
			
			
		}
		
		for(Player p : SkyUHC.instance.playerInGame) {
			for(Team team : SkyUHC.instance.teams){
				/*if(!team.contains(p)){
					if(team.getSize() != 2){
						team.addPlayers(p);
					}
				}*/
				for(Player player : team.getPlayers()){
					List<Player> list = new ArrayList<>();
					list.add(player);
					LocationManager.teleport(list);
				}
			}
			
		}
		Bukkit.getServer().broadcastMessage("run");
		Bukkit.getScheduler().runTaskTimer(SkyUHC.instance, new Runnable() {
		
			@Override
			public void run() {
				if(timer == 0){
					for(Player p : SkyUHC.instance.playerInGame) {
						p.setGameMode(GameMode.SURVIVAL);
						Title.sendTitle(p, "§aLa partie commence !", "§bVous avez §e5 min §bpour vous équiper !", 80);
						p.playSound(p.getLocation(), Sound.EXPLODE, 4.0F, 4.0F);
						GameState.setState(GameState.PREGAME);
						for(Entry<Player, ScoreboardSign> entry : SkyUHC.instance.cs.entrySet()){
							entry.getValue().removeLine(6);
						}
						Bukkit.getScheduler().cancelAllTasks();
						ItemStack hache = new ItemBuilder().type(Material.DIAMOND_AXE).build();
						ItemStack tableEnc = new ItemBuilder().type(Material.ENCHANTMENT_TABLE).build();
						ItemStack enclume = new ItemBuilder().type(Material.ANVIL).amount(2).build();
						ItemStack steaks = new ItemBuilder().type(Material.COOKED_BEEF).amount(10).build();
						ItemStack bibli = new ItemBuilder().type(Material.BOOKSHELF).amount(5).build();
						p.getInventory().addItem(hache);
						p.getInventory().addItem(steaks);
						p.getInventory().addItem(enclume);
						p.getInventory().addItem(tableEnc);
						p.getInventory().addItem(bibli);
						new PreGameRunnable().runTaskTimer(SkyUHC.instance, 0, 20);
						timer = 10;
						
					}
				}
				if(timer <= 5 && timer != 0) {
					for(Player p : SkyUHC.instance.playerInGame) Title.sendTitle(p, "§6Début dans", "§l§e" + timer + " seconde(s)", 20);
				}
				timer--;
			}
		}, 0, 20);
	}

	public static void gameManager() {
		new GameRunnable().runTaskTimer(SkyUHC.instance, 0, 20);
		for(Player p : Bukkit.getOnlinePlayers()) {
			ScoreboardSign cs = SkyUHC.instance.get(p);
			cs.removeLine(7);
			cs.removeLine(8);
			cs.setLine(8, "    ");
			if(!SkyUHC.instance.cs.containsKey(p))
                SkyUHC.instance.cs.put(p, cs);
			
			
		}
		for(Player p : SkyUHC.instance.playerInGame){
			LocationManager.teleportDeathMatch(p);
		}
		
	}
	
}
