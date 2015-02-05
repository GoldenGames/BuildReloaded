package me.mani.build.spongeblocks;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SpongeBlocks {
	
	private static HashMap<Player, SpongeBlock> spongeBlocks = new HashMap<>();
	
	public static boolean hasSpongeBlock(Player p) {
		return spongeBlocks.containsKey(p);
	}
	
	public static void addSpongeBlock(Player p) {
		spongeBlocks.put(p, new SpongeBlock(Material.SPONGE, (byte) 0));
	}
	
	public static SpongeBlock getSpongeBlock(Player p) {
		return spongeBlocks.get(p);
	}

}
