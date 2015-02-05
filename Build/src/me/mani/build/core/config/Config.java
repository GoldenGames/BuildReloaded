package me.mani.build.core.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
	
	private static FileConfiguration cfg;
	
	public static void configure() {
		for (String key : ConfigKey.getAllKeys())
			cfg.addDefault(key, "");
		cfg.options().copyDefaults(true);
	}
	
	public static void setConfig(FileConfiguration cfg) {
		Config.cfg = cfg;
	}
	
	public static FileConfiguration getConfig() {
		return cfg;
	}
	
	public static class ConfigKey {
		public static final String MESSAGE_FORMAT = "messageFormat";
		public static final String CHAT_FORMAT = "chatFormat";
		
		public static List<String> getAllKeys() {
			ConfigKey configKey = new ConfigKey();
			List<String> allKeys = new ArrayList<>();
			for (Field f : ConfigKey.class.getFields())
				if (f.getType() == String.class)
					try {
						allKeys.add((String) f.get(configKey));
					} catch (IllegalArgumentException | IllegalAccessException e) {}
			return allKeys;
		}
	}

}
