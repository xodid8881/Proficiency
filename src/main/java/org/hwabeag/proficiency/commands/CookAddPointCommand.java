package org.hwabeag.proficiency.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.hwabeag.proficiency.config.ConfigManager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CookAddPointCommand implements CommandExecutor {

    FileConfiguration Config = ConfigManager.getConfig("proficiency");
    FileConfiguration CookExpConfig = ConfigManager.getConfig("proficiency_cook_exp");
    String Prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Config.getString("proficiency.prefix")));

    public void GiveExpEvent(Player player, int GiveExp) {
        String name = Objects.requireNonNull(player).getName();
        int lv = Config.getInt("숙련도." + name + ".요리Lv");
        int exp = Config.getInt("숙련도." + name + ".요리Exp");
        int maxexp = Config.getInt("숙련도." + name + ".요리MaxExp");
        Config.set("숙련도." + name + ".요리Exp", exp + GiveExp);
        ConfigManager.saveConfigs();
        String msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7요리 + &a" + GiveExp + " &7[" + exp + "/" + maxexp + "]");
        player.sendActionBar(msg);

        if (maxexp <= exp) {
            int PlusLv = lv + 1;
            int PlusMaxExp = maxexp + 50;
            Config.set("숙련도." + name + ".요리Lv", PlusLv);
            Config.set("숙련도." + name + ".요리Exp", 0);
            Config.set("숙련도." + name + ".요리MaxExp", PlusMaxExp);
            ConfigManager.saveConfigs();
            String s = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도]");
            String s1 = ChatColor.translateAlternateColorCodes('&', "&a&l- &7요리 레벨업 - &a" + PlusLv + "&f.&7Lv");
            player.sendTitle(s, s1);

            msg = ChatColor.translateAlternateColorCodes('&', "&a&l[숙련도] &7요리 레벨업을 진행했습니다.");
            player.sendMessage(msg);
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            if (args.length == 0) {
                return true;
            }
            if (args[0].equalsIgnoreCase("exp")) {
                if (args.length == 1) {
                    return true;
                }
                if (Bukkit.getServer().getPlayerExact(args[1]) != null) {
                    if (CookExpConfig.getString("CookExpConfig." + args[2]) != null) {
                        Player target = Bukkit.getServer().getPlayerExact(args[1]);
                        int GiveExp = Integer.parseInt(Objects.requireNonNull(CookExpConfig.getString("CookExpConfig." + args[2])));
                        GiveExpEvent(target, GiveExp);
                    } else {
                        Player target = Bukkit.getServer().getPlayerExact(args[1]);
                        if (target != null) {
                            int GiveExp = 50;
                            GiveExpEvent(target, GiveExp);
                        }
                    }
                }
                return true;
            }
        } else {
            if (p.isOp()) {
                if (args.length == 0) {
                    p.sendMessage(Prefix + " /CookingAdd SetExp [Exp] - 손에 든 요리 아이템의 경험치를 설정합니다.");
                    p.sendMessage(Prefix + " /CookingAdd Exp [Player] [Exp] - 요리 경험치를 지급합니다.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("exp")) {
                    if (args.length == 1) {
                        p.sendMessage(Prefix + " /CookingAdd Exp [Player] [Type] - 요리 경험치를 지급합니다.");
                        return true;
                    }
                    if (Bukkit.getServer().getPlayerExact(args[1]) != null) {
                        Player target = Bukkit.getServer().getPlayerExact(args[1]);
                        int GiveExp = Integer.parseInt(args[2]);
                        GiveExpEvent(target, GiveExp);
                    } else {
                        p.sendMessage(Prefix + " 존재하지 않는 유저 닉네임 입니다.");
                        return true;
                    }
                }
                if (args[0].equalsIgnoreCase("setexp")) {
                    String itemname = p.getItemInHand().getItemMeta().getDisplayName();
                    if (CookExpConfig.getString("CookExpCif (Bukkit.getServer().getPlayerExact(args[1]) != null) {\n" +
                            "                        Player target = Bukkit.getServer().getPlayerExact(args[1]);\n" +
                            "                        String Name = Objects.requireNonNull(target).getName();onfig." + itemname) != null) {
                        CookExpConfig.addDefault("CookExpConfig." + itemname, args[1]);
                        p.sendMessage(Prefix + " " + itemname + " 의 경험치를 " + args[1] + ".Exp 로 설정했습니다.");
                    } else {
                        String ExitExp = CookExpConfig.getString("CookExpConfig." + itemname);
                        p.sendMessage(Prefix + " " + itemname + " 의 경험치를 " + ExitExp + ".Exp 에서 " + args[1] + ".Exp 로 설정했습니다.");
                        CookExpConfig.set("CookExpConfig." + itemname, args[1]);
                    }
                    ConfigManager.saveConfigs();
                } else {
                    Bukkit.getConsoleSender().sendMessage(Prefix + " /CookingAdd Exp [닉네임] [Type] - 요리 경험치를 지급합니다.");
                }
            } else {
                p.sendMessage(Prefix + " 당신은 권한이 없습니다.");
            }
            return true;
        }
        return false;
    }
}