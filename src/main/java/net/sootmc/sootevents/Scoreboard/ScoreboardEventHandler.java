package net.sootmc.sootevents.Scoreboard;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ScoreboardEventHandler implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(ScoreboardUtils.getScoreboardUtils().toggled)
            ScoreboardUtils.getScoreboardUtils().makeScoreboard(event.getPlayer());
    }
}
