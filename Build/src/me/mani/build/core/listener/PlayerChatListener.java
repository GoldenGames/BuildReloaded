package me.mani.build.core.listener;

import me.mani.build.core.config.Config;
import me.mani.build.core.config.Config.ConfigKey;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener extends BuildListener {
	
	private static final String NAME_PLACEHOLDER = "%name%";
	private static final String MESSAGE_PLACEHOLDER = "%msg%";
	
	private String chatFormat;
	
	public PlayerChatListener() {
		chatFormat = Config.getConfig().getString(ConfigKey.CHAT_FORMAT);
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent ev) {
		ev.setFormat(chatFormat.replaceAll(NAME_PLACEHOLDER, ev.getPlayer().getName()).replaceAll(MESSAGE_PLACEHOLDER, ChatColor.translateAlternateColorCodes('&', ev.getMessage())));
	}

}
