package net.sootmc.sootevents;

import net.sootmc.sootevents.Bossbar.BossbarCommand;
import net.sootmc.sootevents.Bossbar.BossbarEvents;
import net.sootmc.sootevents.Bossbar.BossbarUtils;
import net.sootmc.sootevents.MuteChat.ChatCommand;
import net.sootmc.sootevents.MuteChat.ChatListener;
import net.sootmc.sootevents.MuteChat.ChatUtils;
import net.sootmc.sootevents.Scoreboard.ScoreboardCommands;
import net.sootmc.sootevents.Scoreboard.ScoreboardEventHandler;
import net.sootmc.sootevents.Scoreboard.ScoreboardUtils;
import net.sootmc.sootevents.WaterRising.WaterRisingCommand;
import net.sootmc.sootevents.WaterRising.WaterRisingEvents;
import net.sootmc.sootevents.WaterRising.WaterRisingUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class SootEvents extends JavaPlugin {

    public static SootEvents instance;
    public static String PREFIX = ChatColor.GOLD + "Soot" + ChatColor.RED + "Events" + ChatColor.RESET + " | ";

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        whitelistCheck();

        new ScoreboardUtils();
        new ChatUtils();
        new WaterRisingUtils();
        new BossbarUtils();

        ScoreboardUtils.getScoreboardUtils().setScoreboardEnabled(this.getConfig().getBoolean("toggled"));
        ChatUtils.getChatUtils().setChatEnabled(this.getConfig().getBoolean("chat.toggled"));

        getServer().getPluginManager().registerEvents(new ScoreboardEventHandler(), this);
        getCommand("scoreboard").setExecutor(new ScoreboardCommands());

        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getCommand("togglechat").setExecutor(new ChatCommand());

        getServer().getPluginManager().registerEvents(new WaterRisingEvents(), this);
        getCommand("toggledamager").setExecutor(new WaterRisingCommand());

        getServer().getPluginManager().registerEvents(new BossbarEvents(), this);
        getCommand("bossbar").setExecutor(new BossbarCommand());

        getLogger().info("SootEvents has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SootEvents has been disabled!");
    }

    private void whitelistCheck() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(getServer().hasWhitelist()) {
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        if(!player.isWhitelisted()) {
                            player.kickPlayer("You are not whitelisted on this server.");
                        }
                    });
                }
            }
        }.runTaskTimer(this, 0, 20L);
    }
}
