package net.sootmc.sootevents.MuteChat;

import net.sootmc.sootevents.SootEvents;

public class ChatUtils {

    public boolean toggled;
    public static ChatUtils chatUtils;

    public ChatUtils() {
        chatUtils = this;
    }

    public static ChatUtils getChatUtils() {
        return chatUtils;
    }

    public void setChatEnabled(boolean enabled) {
        toggled = enabled;
        SootEvents.instance.getConfig().set("chat.toggled", enabled);
    }

}
