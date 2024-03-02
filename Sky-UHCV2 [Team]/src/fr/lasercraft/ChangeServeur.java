package fr.lasercraft;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ChangeServeur {
	
	private static Plugin plugin = null;
	
	public static void init(final Plugin pl){
		plugin = pl;
		Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
	}
	
	public static void changeServer(final Player p, final String server){
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);
		p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
	}
}
