package me.mani.build.core.command;

import me.mani.build.core.Build;
import me.mani.build.core.config.Config;
import me.mani.build.core.config.Config.ConfigKey;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class BuildCommand implements CommandExecutor {
	
	private String label;
	private boolean playerOnly;
	
	protected String messageFormat;
	
	public BuildCommand(String label) {
		this(label, false);
	}
	
	public BuildCommand(String label, boolean playerOnly) {
		this.label = label;
		this.playerOnly = playerOnly;
		this.messageFormat = Config.getConfig().getString(ConfigKey.MESSAGE_FORMAT);
		register();
	}
	
	private void register() {
		Build.getInstance().getCommand(label).setExecutor(this);
	}
	
	@Deprecated
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (playerOnly && !(sender instanceof Player))
			onCommand(null, args);
		else
			onCommand((Player) sender, args);		
		return true;
	}
	
	public abstract void onCommand(Player p, String[] args);

}
