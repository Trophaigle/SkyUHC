package fr.lasercraft.inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.lasercraft.SkyUHC;
import fr.lasercraft.lasercraftapi.menuitem.MenuItem2;
import fr.lasercraft.lasercraftapi.virtualinventory.VirtualInventory;
import fr.lasercraft.team.Team;

public class MenuItemTeam extends MenuItem2{

	public MenuItemTeam(String name, List<String> list, byte color) {
		super(name, list, new ItemStack(Material.WOOL, 0, color));
	}

	@Override
	public void onInventoryClicEvent(Player p) {
		Team team = SkyUHC.instance.getTeamByName(name);
		team.addPlayers(p);
		
	}
	
	public static void refreshItemTeam(Team team) {
		
		VirtualInventory menuTeamSelector = VirtualInventory.get(VirtualInventoryTeam.id);
		
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
		
		menuTeamSelector.registerAndSetItem(new MenuItemTeam(team.getTag() + team.getName(), list, team.getWooldata()), team.teamNumber);
		
	}
	
}
