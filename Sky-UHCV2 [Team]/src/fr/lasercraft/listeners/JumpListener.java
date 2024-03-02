package fr.lasercraft.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class JumpListener implements Listener {
	
	public List<Block> block = new ArrayList<>();
	public int y = 178;
	int distance = 2;
	
	 @EventHandler
	 public void onJump(PlayerMoveEvent e){
		 Block b = e.getPlayer().getLocation().add(0, -1, 0).getBlock();
		 
		 if(b.getType().equals(Material.STAINED_GLASS) && b.getData() == (byte) 0){
			if(!block.contains(b)) {
				block.add(b);
			Block bo = b.getWorld().getBlockAt(e.getPlayer().getLocation().add(getDirection(e.getPlayer())));
			bo.setType(Material.STAINED_GLASS);
				}
		   }
	 }
	 
	  public static Location getLocationFacing(Player p) { 
		  Location l = p.getLocation();
		    Vector v = getDirection(p);
		    
		    v.multiply(3);
		    l.add(v);
		    	
	    boolean ok = false;
	    for (int n = 0; n < 5; n++) {
	      if (l.getBlock().getType().isSolid())
	      {
	        l.add(0.0D, 1.0D, 0.0D);
	      }
	      else
	      {
	        ok = true;
	        break;
	      }
	    }
	    if (!ok) {
	      l.subtract(0.0D, 5.0D, 0.0D);
	    }
	    return l;
	  }

	  public static Vector getDirection(Player p)
	  {
		  	Vector vector = new Vector();
	    
		  		//double rotX = p.getLocation().getYaw() + 90;
		  	double rotX = p.getLocation().getYaw() + (new Random().nextInt(80) - 40);
		  		double rotY = 45;
	     
	      			vector.setY(-Math.sin(Math.toRadians(rotY)));
	     
	      			double xz = Math.cos(Math.toRadians(rotY));
	     
	  				vector.setX((-xz * Math.sin(Math.toRadians(rotX))));
	     
	  				vector.setZ((xz * Math.cos(Math.toRadians(rotX))));
	   
	  				vector.multiply(4);
	  				
	  				vector.setY(0);
	  				
	  				return vector;
	   	}
}
