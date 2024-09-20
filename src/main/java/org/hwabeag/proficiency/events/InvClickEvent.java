package org.hwabeag.proficiency.events;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.hwabeag.proficiency.config.ConfigManager;

import java.util.Objects;

public class InvClickEvent implements Listener {

    FileConfiguration Config = ConfigManager.getConfig("proficiency");
    String Prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Config.getString("proficiency.prefix")));

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getClickedInventory() == null)
            return;
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&a&l숙련도 정보"))){
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            player.sendMessage(Prefix + " 인벤토리 클릭이 취소되었습니다.");
        }
    }

}
