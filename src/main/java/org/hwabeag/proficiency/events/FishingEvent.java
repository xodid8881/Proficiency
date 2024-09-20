package org.hwabeag.proficiency.events;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.hwabeag.proficiency.config.ConfigManager;
import org.bukkit.event.player.PlayerFishEvent;
import java.util.Objects;

public class FishingEvent implements Listener {
    FileConfiguration Config = ConfigManager.getConfig("proficiency");

    public void GiveExpEvent(Player player, int GiveExp){
        String name = Objects.requireNonNull(player).getName();
        int lv = Config.getInt("숙련도." + name + ".낚시Lv");
        int exp = Config.getInt("숙련도." + name + ".낚시Exp");
        int maxexp = Config.getInt("숙련도." + name + ".낚시MaxExp");
        Config.set("숙련도." + name + ".낚시Exp", exp + GiveExp);
        ConfigManager.saveConfigs();
        String msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7낚시 + &a" + GiveExp + " &7[" + exp + "/" + maxexp + "]");
        player.sendActionBar(msg);

        if (maxexp <= exp) {
            int PlusLv = lv + 1;
            int PlusMaxExp = maxexp + 50;
            Config.set("숙련도." + name + ".낚시Lv", PlusLv);
            Config.set("숙련도." + name + ".낚시Exp", 0);
            Config.set("숙련도." + name + ".낚시MaxExp", PlusMaxExp);

            int point = Config.getInt("point." + name);
            int Pluspoint = point + 1;
            Config.set("point." + name , Pluspoint);
            ConfigManager.saveConfigs();
            String s = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도]");
            String s1 = ChatColor.translateAlternateColorCodes('&', "&a&l- &7낚시 레벨업 - &a" + PlusLv + "&f.&7Lv");
            player.sendTitle(s, s1);

            msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7낚시 레벨업을 진행했습니다.");
            player.sendMessage(msg);
        }
    }

    @EventHandler
    public void onPlayerFish (PlayerFishEvent event) {
        Player player = event.getPlayer();
        String state = event.getState().name();
        if (state.equals("CAUGHT_FISH")){
            int GiveExp = 75;
            GiveExpEvent(player, GiveExp);
        }
    }
}
