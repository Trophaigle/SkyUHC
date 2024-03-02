package fr.lasercraft.ping;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import fr.lasercraft.GameState;

public class ServerPingListener implements Listener{

	@EventHandler
	public void onPing(ServerListPingEvent e){
		if(GameState.isState(GameState.LOBBY)){
			e.setMotd("&a&l> &f&aJouer &l<");
		}else if(GameState.isState(GameState.GAME)){
			e.setMotd("&c&lX &f&cEn jeu &lX");
		}else if(GameState.isState(GameState.FINISH)){
			e.setMotd("&4&l== &f&4Redémarrage &l==");
		}
	}
	
}
