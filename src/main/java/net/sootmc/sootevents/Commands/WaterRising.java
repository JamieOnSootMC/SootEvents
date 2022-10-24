package net.sootmc.sootevents.Commands;

import net.sootmc.sootevents.SootEvents;
import net.sootmc.sootevents.Utils.Helpers.CommandHandler;
import net.sootmc.sootevents.Utils.Helpers.CommandInfo;
import net.sootmc.sootevents.Utils.WaterRisingUtils;
import org.bukkit.command.CommandSender;

@CommandInfo(name = "toggledeath", permission = "sootevents.permission.toggledeath", requiresPlayer = false)
public class WaterRising extends CommandHandler {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(WaterRisingUtils.getWaterRisingUtils().toggled) {
            WaterRisingUtils.getWaterRisingUtils().setWaterRisingEnabled(false);
            sender.sendMessage(SootEvents.PREFIX + "Water rising damage has been disabled.");
        } else {
            WaterRisingUtils.getWaterRisingUtils().setWaterRisingEnabled(true);
            sender.sendMessage(SootEvents.PREFIX + "Water rising damage has been enabled.");
        }
    }
}
