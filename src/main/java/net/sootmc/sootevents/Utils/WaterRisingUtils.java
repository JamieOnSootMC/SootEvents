package net.sootmc.sootevents.Utils;

import net.sootmc.sootevents.SootEvents;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class WaterRisingUtils {

    public boolean toggled;
    public static WaterRisingUtils waterRisingUtils;

    public WaterRisingUtils() {
        waterRisingUtils = this;
    }

    public static WaterRisingUtils getWaterRisingUtils() {
        return waterRisingUtils;
    }

    public void setWaterRisingEnabled(boolean enabled) {
        toggled = enabled;
        this.WaterCheck();
        SootEvents.instance.getConfig().set("waterRising.toggled", enabled);
    }

    private void WaterCheck() {
        if (toggled) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(toggled) {
                        Bukkit.getOnlinePlayers().forEach(player -> {
                            if (player.getLocation().getBlock().isLiquid()) {
                                player.damage(1);
                            }
                        });
                    } else {
                        cancel();
                    }

                }
            }.runTaskTimer(SootEvents.instance, 0, 20);
        }
    }

}
