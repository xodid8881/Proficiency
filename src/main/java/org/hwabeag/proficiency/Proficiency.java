package org.hwabeag.proficiency;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.hwabeag.proficiency.commands.CookAddPointCommand;
import org.hwabeag.proficiency.commands.MainCommand;
import org.hwabeag.proficiency.commands.OPCommand;
import org.hwabeag.proficiency.config.ConfigManager;
import org.hwabeag.proficiency.events.*;
import org.hwabeag.proficiency.expansions.ProficiencyExpansion;

import java.util.Objects;

public final class Proficiency extends JavaPlugin {

    private static ConfigManager configManager;

    private FileConfiguration config;

    public static Proficiency getPlugin() {
        return JavaPlugin.getPlugin(Proficiency.class);
    }

    public static void getConfigManager() {
        if (configManager == null)
            configManager = new ConfigManager();
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new BattleEvent(), this);
        getServer().getPluginManager().registerEvents(new FarmEvent(), this);
        getServer().getPluginManager().registerEvents(new FellingEvent(), this);
        getServer().getPluginManager().registerEvents(new FishingEvent(), this);
        getServer().getPluginManager().registerEvents(new HuntEvent(), this);
        getServer().getPluginManager().registerEvents(new InvClickEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new LivestockEvent(), this);
        getServer().getPluginManager().registerEvents(new MineEvent(), this);
    }

    private void registerCommands(){
        Objects.requireNonNull(getServer().getPluginCommand("숙련도")).setExecutor(new MainCommand());
        Objects.requireNonNull(getServer().getPluginCommand("숙련도설정")).setExecutor(new OPCommand());
        Objects.requireNonNull(getServer().getPluginCommand("cookingadd")).setExecutor(new CookAddPointCommand());
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("[Proficiency] Enable");
        getConfigManager();
        registerCommands();
        registerEvents();
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new ProficiencyExpansion(this).register();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("[Proficiency] Disable");
        ConfigManager.saveConfigs();
    }
}
