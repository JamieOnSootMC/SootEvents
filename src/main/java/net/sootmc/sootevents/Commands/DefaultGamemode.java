package net.sootmc.sootevents.Commands;

import net.sootmc.sootevents.SootEvents;
import net.sootmc.sootevents.Utils.CommandInfo;
import org.bukkit.command.CommandSender;

@CommandInfo(name = "defaultgamemode", permission = "sootevents.command.default", requiresPlayer = false)
public class DefaultGamemode extends CommandHandler {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            sender.sendMessage("Invalid argument(s). Correct usage:");
            sender.sendMessage("/defaultgamemode <survival|creative|adventure|spectator>");
        } else {
            SootEvents.instance.getConfig().set("gamemode", args[0].toLowerCase());
        }
    }
}
