package net.sootmc.sootevents.Commands;

import dev.jamieisgeek.CommandHandler;
import dev.jamieisgeek.CommandInfo;
import net.sootmc.sootevents.SootEvents;
import net.sootmc.sootevents.Utils.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

@CommandInfo(
        name = "scoreboard",
        permission = "sootevents.command.scoreboard",
        requiresPlayer = false
)

public class Scoreboard extends CommandHandler {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(SootEvents.PREFIX + "Invalid argument(s). Correct usage:");
            sender.sendMessage(SootEvents.PREFIX + "/scoreboard <on|off|start|pause|reload>");
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("on")) {
                ScoreboardUtils.getScoreboardUtils().setScoreboardEnabled(true);
                Bukkit.getOnlinePlayers().forEach(player -> ScoreboardUtils.getScoreboardUtils().makeScoreboard(player));
                sender.sendMessage(SootEvents.PREFIX + "Scoreboard has been enabled.");
            }

            if (args[0].equalsIgnoreCase("off")) {
                ScoreboardUtils.getScoreboardUtils().setScoreboardEnabled(false);
                Bukkit.getOnlinePlayers().forEach(player -> player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard()));
                sender.sendMessage(SootEvents.PREFIX + "Scoreboard has been disabled.");
            }

            if (args[0].equalsIgnoreCase("start")) {
                ScoreboardUtils.getScoreboardUtils().setTimerRunning(true);
                ScoreboardUtils.getScoreboardUtils().Start();
            }

            if (args[0].equalsIgnoreCase("reload")) {
                SootEvents.instance.reloadConfig();
                Bukkit.getOnlinePlayers().forEach(player -> ScoreboardUtils.getScoreboardUtils().makeScoreboard(player));
                sender.sendMessage(SootEvents.PREFIX + "Config and Scoreboard have been reloaded.");
            }

            if (args[0].equalsIgnoreCase("pause")) {
                ScoreboardUtils.getScoreboardUtils().setTimerRunning(false);
            }

        } else {
            sender.sendMessage(SootEvents.PREFIX + "Invalid argument(s). Correct usage:");
            sender.sendMessage(SootEvents.PREFIX + "/scoreboard <on|off|start|pause|reload>");
        }
    }
}
