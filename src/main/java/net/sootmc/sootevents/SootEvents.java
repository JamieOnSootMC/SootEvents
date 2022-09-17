package net.sootmc.sootevents;

import net.sootmc.sootevents.MuteChat.ChatCommand;
import net.sootmc.sootevents.MuteChat.ChatListener;
import net.sootmc.sootevents.MuteChat.ChatUtils;
import net.sootmc.sootevents.Scoreboard.ScoreboardCommands;
import net.sootmc.sootevents.Scoreboard.ScoreboardEventHandler;
import net.sootmc.sootevents.Scoreboard.ScoreboardUtils;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SootEvents extends JavaPlugin {

    public static SootEvents instance;
    public static String PREFIX = ChatColor.GOLD + "Soot" + ChatColor.RED + "Events" + ChatColor.RESET + " | ";

    @Override
    public void onEnable() {
        new ScoreboardUtils();
        new ChatUtils();

        instance = this;
        saveDefaultConfig();

        ScoreboardUtils.getScoreboardUtils().setScoreboardEnabled(this.getConfig().getBoolean("toggled"));
        ChatUtils.getChatUtils().setChatEnabled(this.getConfig().getBoolean("chat.toggled"));

        getServer().getPluginManager().registerEvents(new ScoreboardEventHandler(), this);
        getCommand("scoreboard").setExecutor(new ScoreboardCommands());

        getCommand("togglechat").setExecutor(new ChatCommand());
        getServer().getPluginManager().registerEvents(new ChatListener(), this);


        getLogger().info("SootEvents has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SootEvents has been disabled!");
    }
}
