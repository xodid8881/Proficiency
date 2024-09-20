package org.hwabeag.proficiency.expansions;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.hwabeag.proficiency.Proficiency;
import org.hwabeag.proficiency.config.ConfigManager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ProficiencyExpansion extends PlaceholderExpansion {


    FileConfiguration Config = ConfigManager.getConfig("proficiency");
    private final Proficiency plugin; //

    public ProficiencyExpansion(Proficiency plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "proficiency";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equalsIgnoreCase("get")) { // %playertime.player.get%
            String Name = Objects.requireNonNull(player).getName();
            if (Config.getString("proficiency." + Name) == null) {
                return null;
            } else {
                return Config.getString("proficiency." + Name);
            }
        }
        return null; //
    }
}