package net.sootmc.sootevents.Commands;

import net.sootmc.sootevents.SootEvents;
import net.sootmc.sootevents.Utils.Helpers.CommandHandler;
import net.sootmc.sootevents.Utils.Helpers.CommandInfo;
import net.sootmc.sootevents.Utils.ScoreboardUtils;
import org.bukkit.command.CommandSender;

@CommandInfo(name = "timer", permission = "sootevents.command.timer", requiresPlayer = false)
public class Timer extends CommandHandler {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length != 2) {
            sender.sendMessage(SootEvents.PREFIX + "Invalid Args: /timer set <time>");
            return;
        }

        if(args[0].equalsIgnoreCase("set")) {
            try {
                int time = Integer.parseInt(args[1]);

                ScoreboardUtils utils = ScoreboardUtils.getScoreboardUtils();
                utils.setTimer(time);
                sender.sendMessage(SootEvents.PREFIX + "Time has been set to " + time + ".");
            } catch (NumberFormatException e) {
                sender.sendMessage(SootEvents.PREFIX + "Invalid Args: /timer set <time>");
            }
        }
    }
}
