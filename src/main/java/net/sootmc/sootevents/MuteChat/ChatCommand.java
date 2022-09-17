package net.sootmc.sootevents.MuteChat;

import net.sootmc.sootevents.SootEvents;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(ChatUtils.getChatUtils().toggled) {
            ChatUtils.getChatUtils().setChatEnabled(false);
            Bukkit.broadcastMessage(SootEvents.PREFIX + "Chat has been enabled.");
        } else {
            ChatUtils.getChatUtils().setChatEnabled(true);
            Bukkit.broadcastMessage(SootEvents.PREFIX + "Chat has been disabled.");
        }

        return true;
    }
}
