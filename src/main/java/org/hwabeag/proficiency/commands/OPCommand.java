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

public class OPCommand implements CommandExecutor {

    FileConfiguration Config = ConfigManager.getConfig("proficiency");
    String Prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Config.getString("proficiency.prefix")));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (p.isOp()) {
                if (args.length == 0) {
                    p.sendMessage(Prefix + " /숙련도설정 포인트 [닉네임] [Point] - 유저의 포인트를 설정합니다.");
                    p.sendMessage(Prefix + " /숙련도설정 L포인트 [닉네임] [Point] - 유저의 L포인트를 설정합니다.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("포인트")) {
                    if (args.length == 1) {
                        p.sendMessage(Prefix + " /숙련도설정 포인트 [닉네임] [Point] - 유저의 포인트를 설정합니다.");
                        return true;
                    }
                    if (args.length == 2) {
                        p.sendMessage(Prefix + " /숙련도설정 포인트 [닉네임] [Point] - 유저의 포인트를 설정합니다.");
                        return true;
                    }
                    if (Bukkit.getServer().getPlayerExact(args[1]) != null) {
                        Player target = Bukkit.getServer().getPlayerExact(args[1]);
                        String Name = Objects.requireNonNull(target).getName();
                        if (Config.getString("point." + Name) != null) {
                            Config.set("point." + Name, args[2]);
                            p.sendMessage(Prefix + " " + Name + " 님의 포인트를 " + args[2] + "점으로 설정했습니다.");
                            ConfigManager.saveConfigs();
                        }
                    }
                    p.sendMessage(Prefix + " " + args[1] + " 닉네임의 유저가 존재하지 않습니다.");
                    return true;
                }
                if (args[0].equalsIgnoreCase("L포인트")) {
                    if (args.length == 1) {
                        p.sendMessage(Prefix + " /숙련도설정 L포인트 [닉네임] [Point] - 유저의 L포인트를 설정합니다.");
                        return true;
                    }
                    if (args.length == 2) {
                        p.sendMessage(Prefix + " /숙련도설정 L포인트 [닉네임] [Point] - 유저의 L포인트를 설정합니다.");
                        return true;
                    }
                    if (Bukkit.getServer().getPlayerExact(args[1]) != null) {
                        Player target = Bukkit.getServer().getPlayerExact(args[1]);
                        String Name = Objects.requireNonNull(target).getName();
                        if (Config.getString("lpoint." + Name) != null) {
                            Config.set("lpoint." + Name, args[2]);
                            p.sendMessage(Prefix + " " + Name + " 님의 L포인트를 " + args[2] + "점으로 설정했습니다.");
                            ConfigManager.saveConfigs();
                        }
                    }
                    p.sendMessage(Prefix + " " + args[1] + " 닉네임의 유저가 존재하지 않습니다.");
                    return true;
                }
                p.sendMessage(Prefix + " /숙련도설정 포인트 [닉네임] [Point] - 유저의 포인트를 설정합니다.");
                p.sendMessage(Prefix + " /숙련도설정 L포인트 [닉네임] [Point] - 유저의 L포인트를 설정합니다.");
            } else {
                p.sendMessage(Prefix + " 당신은 권한이 없습니다.");
            }
            return true;
        }
        return false;
    }
}