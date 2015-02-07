package me.mani.build.core;

import me.mani.build.core.command.PingCommand;
import me.mani.build.core.command.RankCommand;
import me.mani.build.core.command.SetTargetCommand;
import me.mani.build.core.command.TSCommand;
import me.mani.build.core.config.Config;
import me.mani.build.core.listener.BlockBreakListener;
import me.mani.build.core.listener.BlockPhysicsListener;
import me.mani.build.core.listener.BlockPlaceListener;
import me.mani.build.core.listener.PlayerInteractListener;
import me.mani.build.core.listener.PlayerJoinListener;
import me.mani.build.core.listener.ServerListPingListener;
import me.mani.build.ranks.RankManager;
import me.mani.build.teamlezz.Teamlezz;
import me.mani.build.tweetlezz.Tweetlezz;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class Build extends JavaPlugin {

	private static Build buildInstance;

	public static Tweetlezz tweetlezz;
	public static RankManager rankManager;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		buildInstance = this;

		new Teamlezz();

		Config.setConfig(getConfig());
		Config.configure();
		saveConfig();

		registerCommands();
		registerListener();
		setupTwitterChat();

		for (Player p : Bukkit.getOnlinePlayers())
			getServer().getPluginManager()
					.callEvent(new PlayerJoinEvent(p, ""));
		
		rankManager = new RankManager();
		rankManager.loadRanks();
		
		ServerListPingListener.target = Config.getConfig().getString("currentTarget"); // Loading target from config
	}

	@Override
	public void onDisable() {
		Config.getConfig().set("currentTarget", ServerListPingListener.target); // Saving target to config
		saveConfig();
		
		rankManager.saveRanks();
		
		tweetlezz.disconnect(); // Disconnect the async dispatcher
	}
	
	private void registerCommands() {
		new PingCommand();
		new TSCommand();
		new SetTargetCommand();
		new RankCommand();
	}

	private void registerListener() {
		// new PlayerChatListener(); UNUSED
		new PlayerInteractListener();
		new PlayerJoinListener();
		new BlockPhysicsListener();
		new BlockPlaceListener();
		new BlockBreakListener();
		new ServerListPingListener();
	}

	private void setupTwitterChat() {
		tweetlezz = new Tweetlezz();
		tweetlezz.connect();
		tweetlezz.registerStreamListener(new StatusListener() {

			@Override
			public void onStatus(Status status) {
				Bukkit.broadcastMessage("§3[§bTwitter§3] \n§e@"
						+ status.getUser().getScreenName() + "\n§7"
						+ status.getText());
			}

			@Override
			public void onException(Exception arg0) {}

			@Override
			public void onTrackLimitationNotice(int arg0) {}

			@Override
			public void onStallWarning(StallWarning arg0) {}

			@Override
			public void onScrubGeo(long arg0, long arg1) {}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {}
		});
	}

	public static Build getInstance() {
		return buildInstance;
	}

}
