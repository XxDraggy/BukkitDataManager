package com.xxdraggy.datamanager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class DataManager {
    private static JavaPlugin plugin;

    public static void register(JavaPlugin plugin) {
        DataManager.plugin = plugin;
        DataManager.plugin.getDataFolder().mkdir();

        Bukkit.getLogger().log(Level.INFO, "[DataManager] Successfully registered!");
    }

    public static YMLFile getYmlFile(String name) {
        return new YMLFile(name + ".yml", DataManager.plugin.getDataFolder().getPath());
    }

    public static JSONFile getJsonFile(String name) {
        return new JSONFile();
    }
}
