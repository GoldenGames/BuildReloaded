package me.mani.build.spongeblocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

@SuppressWarnings("deprecation")
public class SpongeBlock {
	
	private Material material;
	private byte data;
	
	
	public SpongeBlock(Block block) {
		this(block.getType(), block.getData());
	}
	
	public SpongeBlock(Material material, byte data) {
		this.material = material;
		this.data = data;
	}

	public void apply(Block block) {
		BlockState state = block.getState();
		state.setType(material);
		state.setRawData(data);
		state.update(true, false);
	}
	
	public void sync(Block block) {
		this.material = block.getType();
		this.data = block.getData();
	}
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public byte getData() {
		return data;
	}

	public void setData(byte data) {
		this.data = data;
	}	
}
