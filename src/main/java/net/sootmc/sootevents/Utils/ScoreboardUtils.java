package net.sootmc.sootevents.Utils;

import net.sootmc.sootevents.SootEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.concurrent.TimeUnit;

public class ScoreboardUtils {
    public static ScoreboardUtils scoreboardUtils;
    private ScoreboardManager scoreboardManager;
    public boolean toggled;
    int roundLength = SootEvents.instance.getConfig().getInt("length");
    private boolean timerRunning = false;

    public void makeScoreboard(Player player) {
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("scoreboard", "dummy", ChatColor.GOLD + "Soot" + ChatColor.RED + "Events");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score currentEvent = objective.getScore("Current Event: " + ChatColor.GREEN + SootEvents.instance.getConfig().getString("currentEvent"));
        Score empty = objective.getScore("  ");
        Score host = objective.getScore("Host: " + ChatColor.GREEN + "" + ChatColor.translateAlternateColorCodes('&', SootEvents.instance.getConfig().getString("host")));
        Score blank = objective.getScore(" ");
        Score timer = objective.getScore("Event Length: " + ChatColor.GREEN + formatTimer(roundLength));

        currentEvent.setScore(5);
        empty.setScore(4);
        host.setScore(3);
        blank.setScore(2);
        timer.setScore(1);

        player.setScoreboard(scoreboard);
    }

    public void setScoreboardEnabled(boolean toggled) {
        this.toggled = toggled;
    }
    public void setTimerRunning(boolean timerRunning) { this.timerRunning = timerRunning; }

    public void Start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(roundLength > 0 && timerRunning) {
                    roundLength = roundLength - 1000;
                    Bukkit.getOnlinePlayers().forEach(player -> makeScoreboard(player));
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(SootEvents.instance, 0, 20L);
    }

    private String formatTimer(int duration) {

        return String.format("%s:%s:%s", TimeUnit.MILLISECONDS.toHours(duration),
                TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration)),
                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
        );
    }

    public ScoreboardUtils() {
        scoreboardUtils = this;
        scoreboardManager = Bukkit.getScoreboardManager();
    }
    public static ScoreboardUtils getScoreboardUtils() {
        return scoreboardUtils;
    }
}
