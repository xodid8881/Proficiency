package org.hwabeag.proficiency.events;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.hwabeag.proficiency.config.ConfigManager;

import java.util.Objects;

public class BattleEvent implements Listener {
    FileConfiguration Config = ConfigManager.getConfig("proficiency");

    public void GiveExpEvent(Player player, int GiveExp) {
        String name = Objects.requireNonNull(player).getName();
        int lv = Config.getInt("숙련도." + name + ".전투Lv");
        int exp = Config.getInt("숙련도." + name + ".전투Exp");
        int maxexp = Config.getInt("숙련도." + name + ".전투MaxExp");
        Config.set("숙련도." + name + ".전투Exp", exp + GiveExp);
        ConfigManager.saveConfigs();
        String msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7전투 + &a" + GiveExp + " &7[" + exp + "/" + maxexp + "]");
        player.sendActionBar(msg);

        if (maxexp <= exp) {
            int PlusLv = lv + 1;
            int PlusMaxExp = maxexp + 50;
            Config.set("숙련도." + name + ".전투Lv", PlusLv);
            Config.set("숙련도." + name + ".전투Exp", 0);
            Config.set("숙련도." + name + ".전투MaxExp", PlusMaxExp);

            int point = Config.getInt("point." + name);
            int Pluspoint = point + 1;
            Config.set("point." + name , Pluspoint);
            ConfigManager.saveConfigs();
            String s = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도]");
            String s1 = ChatColor.translateAlternateColorCodes('&', "&a&l- &7전투 레벨업 - &a" + PlusLv + "&f.&7Lv");
            player.sendTitle(s, s1);

            msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7전투 레벨업을 진행했습니다.");
            player.sendMessage(msg);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getDamager() instanceof Player damager)) return;
        if (!event.isCancelled()) {
            int GiveExp = 15;
            GiveExpEvent(damager, GiveExp);
        }
    }
}
