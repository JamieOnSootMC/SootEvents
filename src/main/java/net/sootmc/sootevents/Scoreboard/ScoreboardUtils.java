package net.sootmc.sootevents.Scoreboard;

import net.sootmc.sootevents.SootEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardUtils {
    public static ScoreboardUtils instance;
    private ScoreboardManager scoreboardManager;
    public boolean toggled;

    public void makeScoreboard(Player player) {
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("scoreboard", "dummy", ChatColor.GOLD + "Soot" + ChatColor.RED + "Events");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score currentEvent = objective.getScore("Current Event: " + ChatColor.GREEN + SootEvents.instance.getConfig().getString("currentEvent"));
        Score empty = objective.getScore(" ");
        Score host = objective.getScore("Host: " + ChatColor.GREEN + SootEvents.instance.getConfig().getString("host"));
        Score blank = objective.getScore(" ");
        Score timer = objective.getScore("Event Length: " + ChatColor.GREEN + SootEvents.instance.getConfig().getString("length"));

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

    public ScoreboardUtils() {
        instance = this;
        scoreboardManager = Bukkit.getScoreboardManager();
    }
    public static ScoreboardUtils getScoreboardUtils() {
        return instance;
    }
}
