package org.hwabeag.proficiency.events;


import net.momirealms.customcrops.api.core.block.CropBlock;
import net.momirealms.customcrops.api.core.mechanic.crop.CropConfig;
import net.momirealms.customcrops.api.core.world.CustomCropsBlockState;
import net.momirealms.customcrops.api.event.CropBreakEvent;
import net.momirealms.customcrops.api.event.CropInteractEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.hwabeag.proficiency.config.ConfigManager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FarmEvent implements Listener {
    FileConfiguration Config = ConfigManager.getConfig("proficiency");

    public void GiveExpEvent(Player player, int GiveExp){
        String name = Objects.requireNonNull(player).getName();
        int lv = Config.getInt("숙련도." + name + ".농사Lv");
        int exp = Config.getInt("숙련도." + name + ".농사Exp");
        int maxexp = Config.getInt("숙련도." + name + ".농사MaxExp");
        Config.set("숙련도." + name + ".농사Exp", exp + GiveExp);
        ConfigManager.saveConfigs();
        String msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7농사 + &a" + GiveExp + " &7[" + exp + "/" + maxexp + "]");
        player.sendActionBar(msg);

        if (maxexp <= exp) {
            int PlusLv = lv + 1;
            int PlusMaxExp = maxexp + 50;
            Config.set("숙련도." + name + ".농사Lv", PlusLv);
            Config.set("숙련도." + name + ".농사Exp", 0);
            Config.set("숙련도." + name + ".농사MaxExp", PlusMaxExp);

            int point = Config.getInt("point." + name);
            int Pluspoint = point + 1;
            Config.set("point." + name , Pluspoint);
            ConfigManager.saveConfigs();
            String s = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도]");
            String s1 = ChatColor.translateAlternateColorCodes('&', "&a&l- &7농사 레벨업 - &a" + PlusLv + "&f.&7Lv");
            player.sendTitle(s, s1);

            msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7농사 레벨업을 진행했습니다.");
            player.sendMessage(msg);
        }
    }

    @EventHandler
    public void onCropInteractEvent(CropInteractEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        if(player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            if (event.blockState().type() instanceof CropBlock cropBlock) {
                CropConfig config = cropBlock.config(event.blockState());
                String key = config.id();
                int point = cropBlock.point(event.blockState());
                if (!event.isCancelled()) {
                    if (Config.getString("숙련도." + name + ".농사Lv") != null) {
                        if (Objects.equals(key, "apple")) { // 사과
                            if (point == 8) {
                                int GiveExp = 40;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "corn")) { // 옥수수
                            if (point == 3) {
                                int GiveExp = 35;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "cabbage")) { // 양배추
                            if (point == 3) {
                                int GiveExp = 25;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "tomato")) { // 토마토
                            if (point == 5) {
                                int GiveExp = 15;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "eggplant")) { // 가지
                            if (point == 3) {
                                int GiveExp = 45;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "garlic")) { // 마늘
                            if (point == 3) {
                                int GiveExp = 35;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "grape")) { // 포도
                            if (point == 8) {
                                int GiveExp = 35;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "hop")) { // 홉
                            if (point == 3) {
                                int GiveExp = 25;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "pepper")) { // 고추
                            if (point == 5) {
                                int GiveExp = 25;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "pineapple")) { // 파인애플
                            if (point == 3) {
                                int GiveExp = 25;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "pitaya")) { // 파타야
                            if (point == 8) {
                                int GiveExp = 25;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onCropBreakEvent(CropBreakEvent event) {
        if (event.blockState().type() instanceof CropBlock cropBlock) {
            CropConfig config = cropBlock.config(event.blockState());
            String key = config.id();
            int point = cropBlock.point(event.blockState());
            if (event.entityBreaker() instanceof Player player) {
                String name = Objects.requireNonNull(player).getName();
                if (!event.isCancelled()) {
                    if (Config.getString("숙련도." + name + ".농사Lv") != null) {
                        if (Objects.equals(key, "apple")) { // 사과
                            if (point == 8) {
                                int GiveExp = 40;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "cabbage")) { // 양배추
                            if (point == 3) {
                                int GiveExp = 25;
                                GiveExpEvent(player, GiveExp);
                            }
                            if (point == 4) {
                                int GiveExp = 50;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "chinese_cabbage")) { // 배추
                            if (point == 3) {
                                int GiveExp = 40;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "corn")) { // 옥수수
                            if (point == 3) {
                                int GiveExp = 35;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "tomato")) { // 토마토
                            if (point == 5) {
                                int GiveExp = 15;
                                GiveExpEvent(player, GiveExp);
                            }
                            if (point == 6) {
                                int GiveExp = 50;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "eggplant")) { // 가지
                            if (point == 3) {
                                int GiveExp = 45;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "garlic")) { // 마늘
                            if (point == 3) {
                                int GiveExp = 35;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "grape")) { // 포도
                            if (point == 8) {
                                int GiveExp = 35;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "hop")) { // 홉
                            if (point == 3) {
                                int GiveExp = 25;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "pepper")) { // 고추
                            if (point == 5) {
                                int GiveExp = 25;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "pineapple")) { // 파인애플
                            if (point == 3) {
                                int GiveExp = 25;
                                GiveExpEvent(player, GiveExp);
                            }
                            if (point == 4) {
                                int GiveExp = 50;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                        if (Objects.equals(key, "pitaya")) { // 파타야
                            if (point == 8) {
                                int GiveExp = 25;
                                GiveExpEvent(player, GiveExp);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        Block block = event.getBlock();
        if (!event.isCancelled()) {
            if (player.getWorld().getWorldFolder().getName().equals("Rpg")){
                return;
            }
            if (Config.getString("숙련도." + name + ".농사Lv") != null) {
                if (block.getType().equals(Material.WHEAT)) {
                    if (((Ageable) block.getBlockData()).getAge() == ((Ageable) block.getBlockData()).getMaximumAge()) {
                        int GiveExp = 4;
                        GiveExpEvent(player, GiveExp);
                    }
                }
                if (block.getType().equals(Material.CARROTS)) {
                    if (((Ageable) block.getBlockData()).getAge() == ((Ageable) block.getBlockData()).getMaximumAge()) {
                        int GiveExp = 4;
                        GiveExpEvent(player, GiveExp);
                    }
                }
                if (block.getType().equals(Material.POTATOES)) {
                    if (((Ageable) block.getBlockData()).getAge() == ((Ageable) block.getBlockData()).getMaximumAge()) {
                        int GiveExp = 4;
                        GiveExpEvent(player, GiveExp);
                    }
                }
                if (block.getType().equals(Material.PUMPKIN)) {
                    int GiveExp = 10;
                    GiveExpEvent(player, GiveExp);
                }
                if (block.getType().equals(Material.MELON)) {
                    int GiveExp = 10;
                    GiveExpEvent(player, GiveExp);
                }
            }
        }
    }
}
