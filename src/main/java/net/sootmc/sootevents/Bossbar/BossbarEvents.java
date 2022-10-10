package net.sootmc.sootevents.Bossbar;

import net.sootmc.sootevents.SootEvents;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BossbarEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(SootEvents.instance.getConfig().getBoolean("bossbar.toggled"))
            BossbarUtils.getBossbarUtils().makeBar();
    }
}
