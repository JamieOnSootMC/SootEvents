package net.sootmc.sootevents.MuteChat;

import net.sootmc.sootevents.SootEvents;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if(ChatUtils.getChatUtils().toggled && !event.getPlayer().hasPermission("sootevents.chat.bypass")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(SootEvents.PREFIX + "Chat is currently muted!");
        }
    }
}
