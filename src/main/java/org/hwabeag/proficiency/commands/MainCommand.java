package org.hwabeag.proficiency.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.hwabeag.proficiency.config.ConfigManager;
import org.hwabeag.proficiency.inventorys.ProficiencyGUI;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MainCommand implements CommandExecutor {

    FileConfiguration Config = ConfigManager.getConfig("proficiency");
    String Prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Config.getString("proficiency.prefix")));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 0) {
                p.sendMessage(Prefix + " /숙련도 정보 [닉네임] - 나의 정보 혹 다른 유저의 정보를 확인합니다.");
                return true;
            }
            if (args[0].equalsIgnoreCase("정보")) {
                if (args.length == 1) {
                    String Name = Objects.requireNonNull(p).getName();
                    ProficiencyGUI inv = new ProficiencyGUI(Name);
                    inv.open(p);
                    return true;
                }
                if (args.length == 2) {
                    Player target = Bukkit.getServer().getPlayerExact(args[1]);
                    String Name = Objects.requireNonNull(target).getName();
                    ProficiencyGUI inv = new ProficiencyGUI(Name);
                    inv.open(p);
                    return true;
                }
            }
            return true;
        }
        return false;
    }
}