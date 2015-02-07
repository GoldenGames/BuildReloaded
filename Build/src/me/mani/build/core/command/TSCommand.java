package me.mani.build.core.command;

import me.mani.build.teamlezz.Teamlezz;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public class TSCommand extends BuildCommand {

	public TSCommand() {
		super("ts", true);
	}

	@Override
	public String onCommand(Player p, String[] args) {
		
		if  (args[0] == null)
			return messageFormat + "/ts <INFO|BROADCAST> [ARGS]...";
		
		TS3Api api = Teamlezz.getAPI();
		
		if (args[0].equalsIgnoreCase("info")) {
			for (Client c : api.getClients())
				Bukkit.broadcastMessage(c.getId() + " | " + c.getNickname());
			Bukkit.broadcastMessage("\n ");
			for (Channel c : api.getChannels())
				Bukkit.broadcastMessage(c.getId()+ " | " + c.getName());
			return messageFormat + "Die Channel + Client Liste wurde angezeigt!";
		}
		else if (args[0].equalsIgnoreCase("broadcast")) {
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < args.length; i++)
				sb.append(args[i] + " ");
			api.broadcast(sb.toString().trim());
			return messageFormat + "Die Nchricht wurde in den Teamspeak gesendet!";
		}
		
		int clientID = Integer.valueOf(args[0]);
		int channelID = Integer.valueOf(args[1]);		
		
		api.moveClient(clientID, channelID);
		
		return messageFormat + "Der Client wurde verschoben!";
		
	}
}
