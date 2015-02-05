package me.mani.build.teamlezz;

import java.util.logging.Level;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;

public class Teamlezz {
	
	private static TS3Api api;
	
	public Teamlezz() {
		final TS3Config config = new TS3Config();
		config.setHost("craplezz.de");
		config.setDebugLevel(Level.OFF);
		config.setLoginCredentials("serveradmin", "legoman");

		final TS3Query query = new TS3Query(config);
		query.connect();

		api = query.getApi();
		api.selectVirtualServerById(1);
		api.setNickname("TeamlezzBot");
		api.sendChannelMessage("TeamlezzBot is online!");
	}
	
	public static TS3Api getAPI() {
		return api;
	}

}
