package net.sootmc.sootevents.Commands;

import dev.jamieisgeek.CommandHandler;
import dev.jamieisgeek.CommandInfo;
import net.sootmc.sootevents.SootEvents;
import net.sootmc.sootevents.Utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

@CommandInfo(name = "togglechat", permission = "sootevents.command.togglechat", requiresPlayer = false)
public class ChatToggle extends CommandHandler {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (ChatUtils.getChatUtils().toggled) {
            ChatUtils.getChatUtils().setChatEnabled(false);
            Bukkit.broadcastMessage(SootEvents.PREFIX + "Chat has been enabled.");
        } else {
            ChatUtils.getChatUtils().setChatEnabled(true);
            Bukkit.broadcastMessage(SootEvents.PREFIX + "Chat has been disabled.");
        }
    }
}
