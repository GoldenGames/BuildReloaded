package me.mani.build.core.listener;

import me.mani.build.spongeblocks.SpongeBlocks;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener extends BuildListener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent ev) {
		
		// Core
		
		// SpongeBlocks
		
		if (ev.getPlayer().getItemInHand() != null 
				&& ev.getPlayer().getItemInHand().getType() != null 
				&& ev.getPlayer().getItemInHand().getType() == Material.SPONGE 
				&& ev.getPlayer().isSneaking()) {
			SpongeBlocks.getSpongeBlock(ev.getPlayer()).sync(ev.getBlock());
			ev.setCancelled(true);
		}
			
	}

}
