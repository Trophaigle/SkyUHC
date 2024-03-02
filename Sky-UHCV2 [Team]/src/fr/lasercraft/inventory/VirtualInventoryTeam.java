package fr.lasercraft.inventory;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.lasercraft.SkyUHC;
import fr.lasercraft.lasercraftapi.virtualinventory.VirtualInventory;
import fr.lasercraft.team.Team;

public class VirtualInventoryTeam extends VirtualInventory{

	public static int id = 1;
	
	public VirtualInventoryTeam() {
		super("Choisi ton équipe", 2, id);
		for(Team team : SkyUHC.instance.teams){
			ArrayList<String> list = new ArrayList<>();
			list.add(" ");
			for(Player player : team.getPlayers()){
				list.add("§e" + player.getName());
			}
			if(team.getSize() < 2){
				if(team.getSize() == 1){
					list.add("§7[Place disponible]");
				}
				
				if(team.getSize() == 0){
					list.add("§7[Place disponible]");
					list.add("§7[Place disponible]");
				}
			}	
			list.add("  ");
			this.registerAndSetItem(new MenuItemTeam(team.getTag() + team.getName(), list, team.getWooldata()), team.teamNumber);
		}
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public void open(Player p) {
		p.openInventory(inventory);
	}

}
