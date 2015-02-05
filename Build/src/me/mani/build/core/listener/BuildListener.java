package me.mani.build.core.listener;

import me.mani.build.core.Build;

import org.bukkit.event.Listener;

/**
 * The structur of the inner listener method is:
 * 
 * <ul>
 * <p>1. Core</p>
 * <p>2. Sub-plugins in abc order</p>
 * </ul>
 *  
 * @author 1999mani
 *
 */
public class BuildListener implements Listener {
	
	public BuildListener() {
		register();
	}
	
	private void register() {
		Build.getInstance().getServer().getPluginManager().registerEvents(this, Build.getInstance());
	}
	
}
