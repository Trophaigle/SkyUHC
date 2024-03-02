package fr.lasercraft.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.TreeType;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.lasercraft.GameState;
import fr.lasercraft.SkyUHC;
import fr.lasercraft.lasercraftapi.Title;
import fr.lasercraft.lasercraftapi.BDD.MySQL;
import fr.lasercraft.lasercraftapi.inventory.InventoryHeadEvent;
import fr.lasercraft.lasercraftapi.menuitems.utils.ItemBuilder;
import fr.lasercraft.lasercraftapi.scoreboard.ScoreboardSign;
import fr.lasercraft.manager.GameFinish;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;

public class PlayerDeathListener implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		final Player p = event.getEntity();
		if(p.getKiller() == null) {event.setDeathMessage(p.getName() + " est mort...");
		} else {
			event.setDeathMessage(p.getName() + " à été tué par " + p.getKiller().getName());
			SkyUHC.instance.coins.put(p.getKiller(), SkyUHC.instance.coins.get(p.getKiller()) + 1.25);
			SkyUHC.instance.kills.put(p.getKiller(), SkyUHC.instance.kills.get(p.getKiller()) + 1);
			MySQL.addCoins(p.getKiller(), (long) 1.25);
			ScoreboardSign scoreboard = SkyUHC.instance.get(p.getKiller());
			scoreboard.setLine(3, "§aKills: §f" + SkyUHC.instance.kills.get(p.getKiller()));
			 if(!SkyUHC.instance.cs.containsKey(p))
                 SkyUHC.instance.cs.put(p, scoreboard);
		}
		
		p.sendMessage("Vous êtes désormais spectateur !");
		Title.sendTitle(p, "Vous êtes mort", " ", 50);
        Bukkit.getScheduler().runTaskLater(SkyUHC.instance, new Runnable() {
			
			@Override
			public void run() {
				p.spigot().respawn();
				p.teleport(PlayJoinListener.spawn);
			}
		}, 5);
		SkyUHC.instance.playerInGame.remove(p);
		ItemStack item = new ItemBuilder().type(Material.COMPASS).name("§bVoir joueurs").build();
		p.getInventory().addItem(item);
		//METTRE ECLAIRE
		
		
		if(SkyUHC.instance.playerInGame.size() == 0){
			GameState.setState(GameState.FINISH);
			new GameFinish();
		}
	}
	
	 @EventHandler
	 public void onEvent(InventoryHeadEvent e){
		 Player p = e.getPlayer();
		 Inventory inv = Bukkit.createInventory(null, 3*9, "Joueur(s) en jeu");
		 for(Player player : SkyUHC.instance.playerInGame){
			 ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
			 SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
			 skullMeta.setDisplayName(player.getName());
			 skullMeta.setOwner(player.getName());
			 skull.setItemMeta(skullMeta);
			 inv.addItem(skull);}
		 	 p.openInventory(inv);
	 }
	 
	 @EventHandler
	 public void onDamage(EntityDamageEvent e) {
		 if(GameState.isState(GameState.GAME)) {
			 e.setDamage(0);
			 e.setCancelled(true);
		 }
		 
	 }
	
}
