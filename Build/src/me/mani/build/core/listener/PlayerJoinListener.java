package me.mani.build.core.listener;

import me.mani.build.spongeblocks.SpongeBlocks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener extends BuildListener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev) {
		
		// Core
		
		ServerListPingListener.connectedPlayer.putIfAbsent(ev.getPlayer().getAddress().getAddress().getHostAddress(), ev.getPlayer().getName());
		
		// SpongeBlocks
		
		if (!SpongeBlocks.hasSpongeBlock(ev.getPlayer()))
			SpongeBlocks.addSpongeBlock(ev.getPlayer());
	}

}
