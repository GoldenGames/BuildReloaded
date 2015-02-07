package me.mani.build.core.command;

import me.mani.build.core.Build;
import me.mani.build.ranks.Rank;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RankCommand extends BuildCommand {

	public RankCommand() {
		super("rank");
	}

	private boolean b;
	
	@Override
	public String onCommand(Player p, String[] args) {
		
		if (args.length >= 2 && args[0].equalsIgnoreCase("create")) {
			if (Build.rankManager.getRankByName(args[1]) != null)
				return messageFormat + "Diesen Rang gibt es schon!";
			Build.rankManager.registerRank(new Rank(
					args[1],
					args[2] != null ? args[2] : args[0].toLowerCase(),
					args[3] != null ? ChatColor.getByChar(args[3]) : ChatColor.WHITE,
					args[4] != null ? ChatColor.getByChar(args[4]) : ChatColor.WHITE)
			);
			return messageFormat + "Der Rang wurde erstellt!";
		}
		if (args.length >= 2 && args[0].equalsIgnoreCase("delete")) {
			if (Build.rankManager.getRankByName(args[1]) == null)
				return messageFormat + "Diesen Rang gibt es nicht!";
			Build.rankManager.unregisterRank(Build.rankManager.getRankByName(args[1]));
			return messageFormat + "Der Rang wurde entfernt!";
		}
		if (args.length >= 3 && args[0].equalsIgnoreCase("addplayer")) {
			if (Build.rankManager.getRankByName(args[1]) == null)
				return messageFormat + "Diesen Rang gibt es nicht!";
			else if (args[2].length() > 16)
				return messageFormat + "Dies ist kein gültiger Spielername!";
			Build.rankManager.getRankByName(args[1]).addPlayer(args[2]);
			return messageFormat + "Dem Spieler wurde der Rang gegeben!";
		}
		if (args.length >= 3 && args[0].equalsIgnoreCase("removeplayer")) {		
			if (Build.rankManager.getRankByName(args[1]) == null)
				return messageFormat + "Diesen Rang gibt es nicht!";
			else if (args[2].length() > 16)
				return messageFormat + "Dies ist kein gültiger Spielername!";
			Build.rankManager.getRankByName(args[1]).containsPlayer(args[2], (contains) -> b = contains);
			if (b)
				return messageFormat + "Der Spieler hat diesen Rang bereits!";
			Build.rankManager.getRankByName(args[1]).removePlayer(args[2]);
			return messageFormat + "Dem Spieler wurde der Rang entnommen!";		
		}
		
		return messageFormat + "/rank <OPERATION> [ARGS]...";
	}

}
