package net.sootmc.sootevents.Listeners;

import net.sootmc.sootevents.SootEvents;
import net.sootmc.sootevents.Utils.GamemodeUtils;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        GameMode gamemode = GamemodeUtils.gamemodeSwitcher(SootEvents.instance.getConfig().getString("gamemode"));
        new BukkitRunnable() {
            @Override
            public void run() {
                if(gamemode != null) {
                    event.getPlayer().setGameMode(gamemode);
                } else {
                    SootEvents.instance.getLogger().severe("Invalid gamemode in config.yml! Defaulting to survival");
                    event.getPlayer().setGameMode(GameMode.SURVIVAL);
                }
            }
        }.runTaskLater(SootEvents.instance, 60L);
    }
}
