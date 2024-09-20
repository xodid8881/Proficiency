package org.hwabeag.proficiency.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.hwabeag.proficiency.config.ConfigManager;

import java.util.Objects;

public class FellingEvent implements Listener {
    FileConfiguration Config = ConfigManager.getConfig("proficiency");

    public void GiveExpEvent(Player player, int GiveExp){
        String name = Objects.requireNonNull(player).getName();
        int lv = Config.getInt("숙련도." + name + ".벌목Lv");
        int exp = Config.getInt("숙련도." + name + ".벌목Exp");
        int maxexp = Config.getInt("숙련도." + name + ".벌목MaxExp");
        Config.set("숙련도." + name + ".벌목Exp", exp + GiveExp);
        ConfigManager.saveConfigs();
        String msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7벌목 + &a" + GiveExp + " &7[" + exp + "/" + maxexp + "]");
        player.sendActionBar(msg);

        if (maxexp <= exp) {
            int PlusLv = lv + 1;
            int PlusMaxExp = maxexp + 50;
            Config.set("숙련도." + name + ".벌목Lv", PlusLv);
            Config.set("숙련도." + name + ".벌목Exp", 0);
            Config.set("숙련도." + name + ".벌목MaxExp", PlusMaxExp);

            int point = Config.getInt("point." + name);
            int Pluspoint = point + 1;
            Config.set("point." + name , Pluspoint);
            ConfigManager.saveConfigs();
            String s = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도]");
            String s1 = ChatColor.translateAlternateColorCodes('&', "&a&l- &7벌목 레벨업 - &a" + PlusLv + "&f.&7Lv");
            player.sendTitle(s, s1);

            msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7벌목 레벨업을 진행했습니다.");
            player.sendMessage(msg);
        }
    }

    @EventHandler
    public void onBreak (BlockBreakEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        Block block = event.getBlock();
        if (!event.isCancelled()) {
            World world = event.getPlayer().getWorld();
            String worldname = world.getName();
            if(worldname.equals("lumberyard")) {
                if (Config.getString("숙련도." + name + ".벌목Lv") != null) {
                    if (block.getType().equals(Material.OAK_LOG)) {
                        int GiveExp = 20;
                        GiveExpEvent(player, GiveExp);
                    }
                    if (block.getType().equals(Material.OAK_WOOD)) {
                        int GiveExp = 20;
                        GiveExpEvent(player, GiveExp);
                    }
                }
            }
        }
    }
}
