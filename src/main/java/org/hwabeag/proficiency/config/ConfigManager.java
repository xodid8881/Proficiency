package org.hwabeag.proficiency.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.hwabeag.proficiency.Proficiency;

import java.util.HashMap;

public class ConfigManager {
    private static final Proficiency plugin = Proficiency.getPlugin();

    private static final HashMap<String, ConfigMaker> configSet = new HashMap<>();


    public ConfigManager() {
        String path = plugin.getDataFolder().getAbsolutePath();
        configSet.put("proficiency", new ConfigMaker(path, "Proficiency.yml"));
        configSet.put("proficiency_cook_exp", new ConfigMaker(path, "Proficiency_Cook_Exp.yml"));
        loadSettings();
        saveConfigs();
    }

    public static void reloadConfigs() {
        for (String key : configSet.keySet()){
            plugin.getLogger().info(key);
            configSet.get(key).reloadConfig();
        }
    }

    public static void saveConfigs(){
        for (String key : configSet.keySet())
            configSet.get(key).saveConfig();
    }

    public static FileConfiguration getConfig(String fileName) {
        return configSet.get(fileName).getConfig();
    }

    public void loadSettings(){
        FileConfiguration Config = getConfig("proficiency");
        Config.options().copyDefaults(true);

        Config.addDefault("proficiency.prefix", "&a&l[Proficiency]&7");
    }


}