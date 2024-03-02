package fr.lasercraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.lasercraft.runnable.LobbyRunnable;

public class StartExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		
		if(sender.isOp()){
			LobbyRunnable.timer = 1;
		}
		
		return false;
	}

}
