package me.mani.build.core.command;

import me.mani.build.core.listener.ServerListPingListener;

import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

public class SetTargetCommand extends BuildCommand {

	public SetTargetCommand() {
		super("settarget", false);
	}

	@Override
	public String onCommand(Player p, String[] args) {
		
		// Core
		
		String target = StringUtils.join(args, ' ');
		ServerListPingListener.target = target;
		return messageFormat + "Ziel wurde gesetzt!\n" + 
				messageFormat + "§4Ziel: §c" + target;
		
	}
	
}
