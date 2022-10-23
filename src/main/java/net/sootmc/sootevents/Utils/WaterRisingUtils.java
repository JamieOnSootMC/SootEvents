package net.sootmc.sootevents.Utils;

import net.sootmc.sootevents.SootEvents;

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
        SootEvents.instance.getConfig().set("waterRising.toggled", enabled);
    }

}
