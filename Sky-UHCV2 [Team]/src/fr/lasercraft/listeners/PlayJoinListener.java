package fr.lasercraft.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import fr.lasercraft.GameState;
import fr.lasercraft.SkyUHC;
import fr.lasercraft.lasercraftapi.menuitems.utils.ItemBuilder;
import fr.lasercraft.lasercraftapi.scoreboard.ScoreboardSign;
import fr.lasercraft.runnable.LobbyRunnable;
import fr.lasercraft.team.TeamManager;

public class PlayJoinListener implements Listener {

	public static Location spawn = new Location(Bukkit.getWorld("world"), -922.482, 170, -282.479, (float) 179.5, (float)-7.1);
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		p.getInventory().clear();
		p.setHealth(20);
		p.setFoodLevel(20);
		p.getActivePotionEffects().clear();
		if(GameState.isState(GameState.LOBBY)) { 
		SkyUHC.instance.playerInGame.add(p);
		SkyUHC.instance.kills.put(p, 0);
		SkyUHC.instance.coins.put(p, 0.00);
		p.teleport(spawn);
		p.setGameMode(GameMode.ADVENTURE);
		e.setJoinMessage("§e" + p.getName() + " §7à rejoint la partie §a[" + SkyUHC.instance.playerInGame.size() + "/" + Bukkit.getMaxPlayers() + "]");
		giveReturnHubItem(p);
		setScoreboard(p);
		giveItemTeam(p);
		if (Bukkit.getOnlinePlayers().size() >= 1 && LobbyRunnable.start == false) {
			new LobbyRunnable().runTaskTimer(SkyUHC.instance, 0L, 20L);
			
			}
		} else {
			p.setGameMode(GameMode.SPECTATOR);
			
		}
	}

	private void giveItemTeam(Player p) {
		ItemStack s = new ItemBuilder().type(Material.WOOL).amount(1).name("§6Choisi ton équipe").build();
		p.getInventory().addItem(s);
	}

	private void setScoreboard(Player p) {
		ScoreboardSign cs = SkyUHC.instance.get(p);
        if(cs == null)
        {
            cs = new ScoreboardSign(p, "§7- §6§lUHC-Fight | Team §f§7-");
            cs.create();
            cs.setLine(10, "   ");
            cs.setLine(9, "Team: Aucune");
            cs.setLine(8, "     ");
            cs.setLine(7, "- Timer -");
            cs.setLine(6, LobbyRunnable.start ? "Début dans §a" + LobbyRunnable.timer : "§7En attente...");
            cs.setLine(5, "  ");
        	cs.setLine(4, "- Stats -");
        	cs.setLine(3, "§aKills: §f" + SkyUHC.instance.kills.get(p));
        	cs.setLine(2, " ");
        	cs.setLine(1, "§7ID §f"+ Bukkit.getServerName());
        	cs.setLine(0, "§6play.lasercraft.fr");
        }
            if(!SkyUHC.instance.cs.containsKey(p))
                SkyUHC.instance.cs.put(p, cs);
		
	}

	private void giveReturnHubItem(Player p) {
		ItemStack hub = new ItemStack(Material.BED);
		ItemMeta m1 = hub.getItemMeta();
		m1.setDisplayName("§fRetourner au Hub");
		hub.setItemMeta(m1);
		
		p.getInventory().setItem(8, hub);
	}

	@EventHandler
	public void onFood(FoodLevelChangeEvent e){
		if(GameState.isState(GameState.LOBBY) || GameState.isState(GameState.FINISH)) { e.setCancelled(true); e.setFoodLevel(20); }
	}
	
}
