package net.sootmc.sootevents.Listeners;

import net.sootmc.sootevents.Utils.BossbarUtils;
import net.sootmc.sootevents.SootEvents;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BossbarEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(SootEvents.instance.getConfig().getBoolean("bossbar.toggled"))
            BossbarUtils.getBossbarUtils().giveBar(event.getPlayer());
    }
}
