package org.hwabeag.proficiency.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.hwabeag.proficiency.config.ConfigManager;

import java.util.Objects;

public class JoinEvent implements Listener {
    FileConfiguration Config = ConfigManager.getConfig("proficiency");
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {
        Player p = event.getPlayer();
        String Name = Objects.requireNonNull(p).getName();
        if (Config.getString("숙련도." + Name) == null) {
            Config.addDefault("숙련도." + Name + ".광질Lv", 1);
            Config.addDefault("숙련도." + Name + ".광질Exp", 1);
            Config.addDefault("숙련도." + Name + ".광질MaxExp", 300);

            Config.addDefault("숙련도." + Name + ".농사Lv", 1);
            Config.addDefault("숙련도." + Name + ".농사Exp", 1);
            Config.addDefault("숙련도." + Name + ".농사MaxExp", 300);

            Config.addDefault("숙련도." + Name + ".벌목Lv", 1);
            Config.addDefault("숙련도." + Name + ".벌목Exp", 1);
            Config.addDefault("숙련도." + Name + ".벌목MaxExp", 300);

            Config.addDefault("숙련도." + Name + ".목축Lv", 1);
            Config.addDefault("숙련도." + Name + ".목축Exp", 1);
            Config.addDefault("숙련도." + Name + ".목축MaxExp", 300);

            Config.addDefault("숙련도." + Name + ".사냥Lv", 1);
            Config.addDefault("숙련도." + Name + ".사냥Exp", 1);
            Config.addDefault("숙련도." + Name + ".사냥MaxExp", 300);

            Config.addDefault("숙련도." + Name + ".요리Lv", 1);
            Config.addDefault("숙련도." + Name + ".요리Exp", 1);
            Config.addDefault("숙련도." + Name + ".요리MaxExp", 300);

            Config.addDefault("숙련도." + Name + ".전투Lv", 1);
            Config.addDefault("숙련도." + Name + ".전투Exp", 1);
            Config.addDefault("숙련도." + Name + ".전투MaxExp", 300);

            Config.addDefault("숙련도." + Name + ".낚시Lv", 1);
            Config.addDefault("숙련도." + Name + ".낚시Exp", 1);
            Config.addDefault("숙련도." + Name + ".낚시MaxExp", 300);

            Config.addDefault("숙련도." + Name + ".채집Lv", 1);
            Config.addDefault("숙련도." + Name + ".채집Exp", 1);
            Config.addDefault("숙련도." + Name + ".채집MaxExp", 300);

            Config.addDefault("숙련도." + Name + ".point", 0);
            Config.addDefault("숙련도." + Name + ".lpoint", 0);
            ConfigManager.saveConfigs();
        }
    }
}
