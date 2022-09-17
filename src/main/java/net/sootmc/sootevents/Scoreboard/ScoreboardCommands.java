package net.sootmc.sootevents.Scoreboard;

import net.sootmc.sootevents.SootEvents;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class ScoreboardCommands implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(SootEvents.PREFIX + "Invalid argument(s). Correct usage:");
            sender.sendMessage(SootEvents.PREFIX + "/scoreboard <on|off>");
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("on")) {
                ScoreboardUtils.getScoreboardUtils().setScoreboardEnabled(true);
                Bukkit.getOnlinePlayers().forEach(player -> ScoreboardUtils.getScoreboardUtils().makeScoreboard(player));
                sender.sendMessage(SootEvents.PREFIX + "Scoreboard has been enabled.");
                return true;
            }

            if (args[0].equalsIgnoreCase("off")) {
                ScoreboardUtils.getScoreboardUtils().setScoreboardEnabled(false);
                Bukkit.getOnlinePlayers().forEach(player -> player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard()));
                sender.sendMessage(SootEvents.PREFIX + "Scoreboard has been disabled.");
                return true;
            }

            if(args[0].equalsIgnoreCase("reload")) {
                SootEvents.instance.reloadConfig();
                Bukkit.getOnlinePlayers().forEach(player -> ScoreboardUtils.getScoreboardUtils().makeScoreboard(player));
                sender.sendMessage(SootEvents.PREFIX + "Config and Scoreboard have been reloaded.");
                return true;
            }

        } else {
            sender.sendMessage(SootEvents.PREFIX + "Invalid argument(s). Correct usage:");
            sender.sendMessage(SootEvents.PREFIX + "/scoreboard <on|off|reload>");

            return true;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1) {
            return List.of("on", "off", "set", "get");
        } else if (args.length == 2) {
            return List.of("current", "host", "timer");
        } else {
            return null;
        }
    }
}
