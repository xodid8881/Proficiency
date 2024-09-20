package org.hwabeag.proficiency.events;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.hwabeag.proficiency.config.ConfigManager;

import java.util.Objects;

public class HuntEvent implements Listener {
    FileConfiguration Config = ConfigManager.getConfig("proficiency");

    public void GiveExpEvent(Player player, int GiveExp) {
        String name = Objects.requireNonNull(player).getName();
        int lv = Config.getInt("숙련도." + name + ".사냥Lv");
        int exp = Config.getInt("숙련도." + name + ".사냥Exp");
        int maxexp = Config.getInt("숙련도." + name + ".사냥MaxExp");
        Config.set("숙련도." + name + ".사냥Exp", exp + GiveExp);
        ConfigManager.saveConfigs();
        String msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7사냥 + &a" + GiveExp + " &7[" + exp + "/" + maxexp + "]");
        player.sendActionBar(msg);

        if (maxexp <= exp) {
            int PlusLv = lv + 1;
            int PlusMaxExp = maxexp + 50;
            Config.set("숙련도." + name + ".사냥Lv", PlusLv);
            Config.set("숙련도." + name + ".사냥Exp", 0);
            Config.set("숙련도." + name + ".사냥MaxExp", PlusMaxExp);

            int point = Config.getInt("point." + name);
            int Pluspoint = point + 1;
            Config.set("point." + name , Pluspoint);
            ConfigManager.saveConfigs();
            String s = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도]");
            String s1 = ChatColor.translateAlternateColorCodes('&', "&a&l- &7사냥 레벨업 - &a" + PlusLv + "&f.&7Lv");
            player.sendTitle(s, s1);

            msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7사냥 레벨업을 진행했습니다.");
            player.sendMessage(msg);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (event.getEntity().getKiller() != null) {
            Player player = event.getEntity().getKiller();
            if (entity.getType() == EntityType.SKELETON) {
                int GiveExp = 70;
                GiveExpEvent(player, GiveExp);
            }
            if (entity.getType() == EntityType.STRAY) {
                int GiveExp = 70;
                GiveExpEvent(player, GiveExp);
            }
            if (entity.getType() == EntityType.HUSK) {
                int GiveExp = 70;
                GiveExpEvent(player, GiveExp);
            }
            if (entity.getType() == EntityType.ZOMBIE) {
                int GiveExp = 70;
                GiveExpEvent(player, GiveExp);
            }
            if (entity.getType() == EntityType.CREEPER) {
                int GiveExp = 70;
                GiveExpEvent(player, GiveExp);
            }
            if (entity.getType() == EntityType.SPIDER) {
                int GiveExp = 70;
                GiveExpEvent(player, GiveExp);
            }
            if (entity.getType() == EntityType.CAVE_SPIDER) {
                int GiveExp = 70;
                GiveExpEvent(player, GiveExp);
            }
            if (entity.getType() == EntityType.ENDERMAN) {
                int GiveExp = 60;
                GiveExpEvent(player, GiveExp);
            }
        }
    }
}
