package me.mani.build.core.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener extends BuildListener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent ev) {
		
		// Core
		
		if (ev.getItem() != null && ev.getItem().getType() != null)
			if (ev.getItem().getType() == Material.FEATHER)
				ev.getPlayer().setVelocity(ev.getPlayer().getLocation().getDirection().multiply(4.5D));
		
	}

}
