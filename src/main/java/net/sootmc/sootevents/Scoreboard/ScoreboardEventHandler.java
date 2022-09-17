package net.sootmc.sootevents.Scoreboard;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ScoreboardEventHandler implements Listener {

    public void onPlayerJoin(PlayerJoinEvent event) {
        if(ScoreboardUtils.getScoreboardUtils().toggled)
            ScoreboardUtils.getScoreboardUtils().makeScoreboard(event.getPlayer());
    }
}
