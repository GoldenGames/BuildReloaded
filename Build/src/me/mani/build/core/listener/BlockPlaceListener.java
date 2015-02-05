package me.mani.build.core.listener;

import me.mani.build.spongeblocks.SpongeBlocks;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener extends BuildListener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent ev) {
		
		// Core
		
		// SpongeBlocks
		
		if (ev.getBlock().getType() == Material.SPONGE)
			SpongeBlocks.getSpongeBlock(ev.getPlayer()).apply(ev.getBlock());
		
	}
	
}
