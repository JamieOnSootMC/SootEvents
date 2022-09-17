package net.sootmc.sootevents;

import net.sootmc.sootevents.MuteChat.ChatCommand;
import net.sootmc.sootevents.MuteChat.ChatListener;
import net.sootmc.sootevents.MuteChat.ChatUtils;
import net.sootmc.sootevents.Scoreboard.ScoreboardCommands;
import net.sootmc.sootevents.Scoreboard.ScoreboardEventHandler;
import net.sootmc.sootevents.Scoreboard.ScoreboardUtils;
import net.sootmc.sootevents.WaterRising.WaterRisingCommand;
import net.sootmc.sootevents.WaterRising.WaterRisingEvents;
import net.sootmc.sootevents.WaterRising.WaterRisingUtils;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SootEvents extends JavaPlugin {

    public static SootEvents instance;
    public static String PREFIX = ChatColor.GOLD + "Soot" + ChatColor.RED + "Events" + ChatColor.RESET + " | ";

    @Override
    public void onEnable() {
        new ScoreboardUtils();
        new ChatUtils();
        new WaterRisingUtils();

        instance = this;
        saveDefaultConfig();

        ScoreboardUtils.getScoreboardUtils().setScoreboardEnabled(this.getConfig().getBoolean("toggled"));
        ChatUtils.getChatUtils().setChatEnabled(this.getConfig().getBoolean("chat.toggled"));

        getServer().getPluginManager().registerEvents(new ScoreboardEventHandler(), this);
        getCommand("scoreboard").setExecutor(new ScoreboardCommands());

        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getCommand("togglechat").setExecutor(new ChatCommand());

        getServer().getPluginManager().registerEvents(new WaterRisingEvents(), this);
        getCommand("toggledamager").setExecutor(new WaterRisingCommand());

        getLogger().info("SootEvents has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SootEvents has been disabled!");
    }
}
