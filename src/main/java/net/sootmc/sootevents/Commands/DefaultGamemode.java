package net.sootmc.sootevents.Commands;

import dev.jamieisgeek.CommandHandler;
import dev.jamieisgeek.CommandInfo;
import net.sootmc.sootevents.SootEvents;
import org.bukkit.command.CommandSender;

@CommandInfo(
        name = "defaultgamemode",
        permission = "sootevents.command.default",
        requiresPlayer = false
)

public class DefaultGamemode extends CommandHandler {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Invalid argument(s). Correct usage:");
            sender.sendMessage("/defaultgamemode <survival|creative|adventure|spectator>");
        } else {
            SootEvents.instance.getConfig().set("gamemode", args[0].toLowerCase());
        }
    }
}
