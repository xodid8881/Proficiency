package org.hwabeag.proficiency.inventorys;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

import static org.hwabeag.proficiency.config.ConfigManager.getConfig;

public class ProficiencyGUI implements Listener {
    private final Inventory inv;
    FileConfiguration Config = getConfig("proficiency");

    private ItemStack getHead(String name) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&l" + name + " &7님"));
        skull.setOwner(name);

        String point = Config.getString("숙련도." + name + ".point");
        String lpoint = Config.getString("숙련도." + name + ".lpoint");
        ArrayList<String> loreList = new ArrayList<>();
        loreList.add(ChatColor.translateAlternateColorCodes('&', "&7&l- &a&l나의 정보:"));
        loreList.add(ChatColor.translateAlternateColorCodes('&', "&7" + point + "&f.&aPoint"));
        loreList.add(ChatColor.translateAlternateColorCodes('&', "&7" + lpoint + "&f.&aLPoint"));
        skull.setLore(loreList);

        item.setItemMeta(skull);
        return item;
    }

    private ItemStack getItem(String name, Material ItemType, String proficiency) {
        ItemStack item = new ItemStack(ItemType, 1, (short) 3);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&l" + proficiency));
        String lv = Config.getString("숙련도." + name + "." + proficiency + "Lv");
        String exp = Config.getString("숙련도." + name + "." + proficiency + "Exp");
        String maxexp = Config.getString("숙련도." + name + "." + proficiency + "MaxExp");
        ArrayList<String> loreList = new ArrayList<>();
        loreList.add(ChatColor.translateAlternateColorCodes('&', "&7&l- &a&l나의 레벨: &7lv." + lv));
        loreList.add(ChatColor.translateAlternateColorCodes('&', "&7&l- &a&l나의 경험치: &7[ " + exp + "/" + maxexp + " ]"));
        itemMeta.setLore(loreList);
        item.setItemMeta(itemMeta);
        return item;
    }

    private void initItemSetting(String name) {
        inv.setItem(4, getHead(name));
        inv.setItem(9, getItem(name, Material.NETHERITE_PICKAXE, "광질"));
        inv.setItem(10, getItem(name, Material.NETHERITE_HOE, "농사"));
        inv.setItem(11, getItem(name, Material.NETHERITE_AXE, "벌목"));
        inv.setItem(12, getItem(name, Material.LEAD, "목축"));
        inv.setItem(13, getItem(name, Material.PORKCHOP, "사냥"));
        inv.setItem(14, getItem(name, Material.COOKIE, "요리"));
        inv.setItem(15, getItem(name, Material.NETHERITE_SWORD, "전투"));
        inv.setItem(16, getItem(name, Material.TROPICAL_FISH, "낚시"));
        inv.setItem(17, getItem(name, Material.RED_MUSHROOM, "채집"));
    }

    public ProficiencyGUI(String Name) {
        this.inv = Bukkit.createInventory(null,27,ChatColor.translateAlternateColorCodes('&', "&a&l숙련도 정보"));
        initItemSetting(Name);
    }

    public void open(Player player){
        player.openInventory(inv);
    }

}
