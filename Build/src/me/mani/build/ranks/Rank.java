package me.mani.build.ranks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import me.mani.build.core.util.UUIDFetcher;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;

public class Rank implements ConfigurationSerializable {
	
	private static HashMap<UUID, Rank> playerRanks = new HashMap<>();
	
	private String name;
	private String displayName;
	private ChatColor prefix;
	private ChatColor suffix;
	
	@SuppressWarnings("unchecked")
	public Rank(Map<String, Object> map) {
		this(String.valueOf(map.get("name")), String.valueOf(map.get("displayName")), ChatColor.getByChar(String.valueOf(map.get("prefix"))), ChatColor.getByChar(String.valueOf(map.get("suffix"))));
		for (String uuid : (List<String>) map.get("players"))
			playerRanks.put(UUID.fromString(uuid), this);
	}
	
	public Rank(String name, String displayName, ChatColor prefix, ChatColor suffix) {
		this.name = name;
		this.displayName = displayName;
		this.prefix = prefix;
		this.suffix = suffix;
	}

	public String getName() {
		return name;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public ChatColor getPrefix() {
		return prefix;
	}

	public ChatColor getSuffix() {
		return suffix;
	}

	public void addPlayer(String name) {
		if (Bukkit.getPlayer("name") != null)
			addPlayer(Bukkit.getPlayer("name"));
		else 
			UUIDFetcher.getUUID(name, (uuid) -> playerRanks.put(uuid, this));
	}
	
	public void addPlayer(Player player) {
		playerRanks.put(player.getUniqueId(), this);
	}	
	
	public void removePlayer(String name) {
		if (Bukkit.getPlayer("name") != null)
			removePlayer(Bukkit.getPlayer("name"));
		else 
			UUIDFetcher.getUUID(name, (uuid) -> playerRanks.remove(uuid, this));
	}
	
	public void removePlayer(Player player) {
		playerRanks.remove(player.getUniqueId(), this);
	}
	
	public void containsPlayer(String name, Consumer<Boolean> consumer) {
		if (Bukkit.getPlayer("name") != null)
			consumer.accept(containsPlayer(Bukkit.getPlayer("name")));
		else 
			UUIDFetcher.getUUID(name, (uuid) -> { consumer.accept(playerRanks.containsKey(uuid) && playerRanks.containsValue(this)); });
	}
	
	public boolean containsPlayer(Player player) {
		return playerRanks.containsKey(player.getUniqueId()) && playerRanks.containsValue(this);
	}
	
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("displayName", displayName);
		map.put("prefix", prefix.getChar());
		map.put("suffix", suffix.getChar());
		List<String> players = new ArrayList<>();
		for (UUID uuid : playerRanks.keySet())
			if (playerRanks.get(uuid) == this)
				players.add(uuid.toString());
		map.put("players", players);
		return map;
	}

	public static Rank getRank(Player player) {
		return playerRanks.get(player.getUniqueId());
	}
	
	static {
		ConfigurationSerialization.registerClass(Rank.class);
	}
	
}
