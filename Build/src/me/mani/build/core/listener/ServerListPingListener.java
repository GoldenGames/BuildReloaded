package me.mani.build.core.listener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingListener extends BuildListener {
	
	public static HashMap<String, String> connectedPlayer = new HashMap<>();
	public static String target = "Keins";
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onServerListPing(ServerListPingEvent ev) {
		
		// Core
		
		String playerName = "Gast";
		if (connectedPlayer.containsKey(ev.getAddress().getHostAddress()))
			playerName = connectedPlayer.get(ev.getAddress().getHostAddress());
		
		ev.setMotd("§7[§l§3BauServer§7] §eWillkommen " + playerName + "! \n"
				 + "§8>>> §4Ziel: §c" + target + " §8>>>");
		
		BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = img.getGraphics();
		int increment = 255 / 64;
		for (int i = 0; i < 64; i++) {
			graphics.setColor(new Color(255, 255 - i * increment, 255));
			graphics.drawLine(i, 0, i, 64);
		}
		
		try {
			ev.setServerIcon(Bukkit.loadServerIcon(img));
		} catch (Exception e) { e.printStackTrace(); }		
	}

}
