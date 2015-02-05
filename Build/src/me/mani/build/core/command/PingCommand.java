package me.mani.build.core.command;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingCommand extends BuildCommand {
	
	public PingCommand() {
		super("ping", true);
	}

	@Override
	public void onCommand(Player p, String[] args) {
		p.sendMessage(messageFormat + ((CraftPlayer) p).getHandle().ping);
	}

}
