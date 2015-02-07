package me.mani.build.ranks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

public class RankManager {
	
	private static final String CONFIG_PATH = "plugins/BuildReloaded/ranks.yml";
	
	private YamlConfiguration cfg;
	private List<Rank> ranks = new ArrayList<>();
	
	public RankManager() {
		cfg = YamlConfiguration.loadConfiguration(new File(CONFIG_PATH));
	}
	
	public void saveRanks() {
		cfg.set("ranks", ranks);
		try { cfg.save(new File(CONFIG_PATH)); }
		catch (IOException e) { e.printStackTrace(); }
	}
	
	@SuppressWarnings("unchecked")
	public void loadRanks() {
		ranks = (List<Rank>) new ArrayList<>(cfg.getList("ranks"));	
	}
	
	public void registerRank(Rank rank) {
		ranks.add(rank);
	}
	
	public void unregisterRank(Rank rank) {
		ranks.remove(rank);
	}
	
	public Rank getRankByName(String name) {
		for (Rank rank : ranks)
			if (rank.getName().equals(name))
				return rank;
		return null;
	}

}
