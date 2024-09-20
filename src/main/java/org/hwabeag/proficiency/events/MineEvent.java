package org.hwabeag.proficiency.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.hwabeag.proficiency.config.ConfigManager;

import java.util.Objects;

public class MineEvent implements Listener {
    FileConfiguration Config = ConfigManager.getConfig("proficiency");

    public void GiveExpEvent(Player player, int GiveExp){
        String name = Objects.requireNonNull(player).getName();
        int lv = Config.getInt("숙련도." + name + ".광질Lv");
        int exp = Config.getInt("숙련도." + name + ".광질Exp");
        int maxexp = Config.getInt("숙련도." + name + ".광질MaxExp");
        Config.set("숙련도." + name + ".광질Exp", exp + GiveExp);
        ConfigManager.saveConfigs();
        String msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7광질 + &a" + GiveExp + " &7[" + exp + "/" + maxexp + "]");
        player.sendActionBar(msg);

        if (maxexp <= exp) {
            int PlusLv = lv + 1;
            int PlusMaxExp = maxexp + 50;
            Config.set("숙련도." + name + ".광질Lv", PlusLv);
            Config.set("숙련도." + name + ".광질Exp", 0);
            Config.set("숙련도." + name + ".광질MaxExp", PlusMaxExp);

            int point = Config.getInt("point." + name);
            int Pluspoint = point + 1;
            Config.set("point." + name , Pluspoint);
            ConfigManager.saveConfigs();
            String s = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도]");
            String s1 = ChatColor.translateAlternateColorCodes('&', "&a&l- &7광질 레벨업 - &a" + PlusLv + "&f.&7Lv");
            player.sendTitle(s, s1);

            msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7광질 레벨업을 진행했습니다.");
            player.sendMessage(msg);
        }
    }

    @EventHandler
    public void onBreak (BlockBreakEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        Block block = event.getBlock();
        if (!event.isCancelled()) {
            if (Config.getString("숙련도." + name + ".광질Lv") != null) {
                if (block.getType().equals(Material.IRON_ORE)) {
                    int GiveExp = 25;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.DEEPSLATE_IRON_ORE)) {
                    int GiveExp = 25;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.GOLD_ORE)) {
                    int GiveExp = 30;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.DEEPSLATE_GOLD_ORE)) {
                    int GiveExp = 30;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.COAL_ORE)) {
                    int GiveExp = 8;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.DEEPSLATE_COAL_ORE)) {
                    int GiveExp = 8;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.DIAMOND_ORE)) {
                    int GiveExp = 50;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.DEEPSLATE_DIAMOND_ORE)) {
                    int GiveExp = 50;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.EMERALD_ORE)) {
                    int GiveExp = 80;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.DEEPSLATE_EMERALD_ORE)) {
                    int GiveExp = 80;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.COPPER_ORE)) {
                    int GiveExp = 10;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.DEEPSLATE_COPPER_ORE)) {
                    int GiveExp = 10;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.LAPIS_ORE)) {
                    int GiveExp = 12;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.DEEPSLATE_LAPIS_ORE)) {
                    int GiveExp = 12;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.REDSTONE_ORE)) {
                    int GiveExp = 11;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.DEEPSLATE_REDSTONE_ORE)) {
                    int GiveExp = 11;
                    GiveExpEvent(player, GiveExp);
                }
            }
        }
    }
}
