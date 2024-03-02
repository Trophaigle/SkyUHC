package fr.lasercraft.listeners;

import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.lasercraft.SkyUHC;
import fr.lasercraft.inventory.VirtualInventoryTeam;
import fr.lasercraft.lasercraftapi.scoreboard.ScoreboardSign;
import fr.lasercraft.lasercraftapi.virtualinventory.VirtualInventory;
import fr.lasercraft.team.TeamManager;

public class PlayerInteractItem implements Listener {

	@EventHandler
	public void onClic(PlayerInteractEvent e){
		Player p = e.getPlayer();
		
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
		
		switch(e.getItem().getItemMeta().getDisplayName()){
		
		case "§fRetourner au Hub":
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("hub01");
			p.sendPluginMessage(SkyUHC.instance, "BungeeCord", out.toByteArray());
			break;
			
		case "§6Choisi ton équipe":
			VirtualInventory.get(VirtualInventoryTeam.id).openInventory(p);
			break;
			}
		}
	}
}
