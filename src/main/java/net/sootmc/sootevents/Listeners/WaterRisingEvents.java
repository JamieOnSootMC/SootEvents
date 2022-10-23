package net.sootmc.sootevents.Listeners;

import net.sootmc.sootevents.Utils.WaterRisingUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WaterRisingEvents implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (WaterRisingUtils.getWaterRisingUtils().toggled) {
            if (event.getPlayer().getLocation().getBlock().getType() == Material.WATER) {
                event.getPlayer().damage(1);
            }
        }
    }
}
