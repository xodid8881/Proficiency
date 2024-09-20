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

public class LivestockEvent implements Listener {
    FileConfiguration Config = ConfigManager.getConfig("proficiency");

    public void GiveExpEvent(Player player, int GiveExp){
        String name = Objects.requireNonNull(player).getName();
        int lv = Config.getInt("숙련도." + name + ".목축Lv");
        int exp = Config.getInt("숙련도." + name + ".목축Exp");
        int maxexp = Config.getInt("숙련도." + name + ".목축MaxExp");
        Config.set("숙련도." + name + ".목축Exp", exp + GiveExp);
        ConfigManager.saveConfigs();
        String msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7목축 + &a" + GiveExp + " &7[" + exp + "/" + maxexp + "]");
        player.sendActionBar(msg);

        if (maxexp <= exp) {
            int PlusLv = lv + 1;
            int PlusMaxExp = maxexp + 50;
            Config.set("숙련도." + name + ".목축Lv", PlusLv);
            Config.set("숙련도." + name + ".목축Exp", 0);
            Config.set("숙련도." + name + ".목축MaxExp", PlusMaxExp);

            int point = Config.getInt("point." + name);
            int Pluspoint = point + 1;
            Config.set("point." + name , Pluspoint);
            ConfigManager.saveConfigs();
            String s = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도]");
            String s1 = ChatColor.translateAlternateColorCodes('&', "&a&l- &7목축 레벨업 - &a" + PlusLv + "&f.&7Lv");
            player.sendTitle(s, s1);

            msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7목축 레벨업을 진행했습니다.");
            player.sendMessage(msg);
        }
    }
    @EventHandler
    public void onDeath (EntityDeathEvent event) {
        Entity entity = event.getEntity();
        Player player = event.getEntity().getKiller();
        if (entity.getType() == EntityType.PIG){
            int GiveExp = 60;
            GiveExpEvent(player, GiveExp);
        }
        if (entity.getType() == EntityType.CHICKEN){
            int GiveExp = 60;
            GiveExpEvent(player, GiveExp);
        }
        if (entity.getType() == EntityType.SHEEP){
            int GiveExp = 60;
            GiveExpEvent(player, GiveExp);
        }
        if (entity.getType() == EntityType.COW){
            int GiveExp = 60;
            GiveExpEvent(player, GiveExp);
        }
    }
}
