package net.sootmc.sootevents.WaterRising;

import net.sootmc.sootevents.SootEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WaterRisingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(WaterRisingUtils.getWaterRisingUtils().toggled) {
            WaterRisingUtils.getWaterRisingUtils().setWaterRisingEnabled(false);
            sender.sendMessage(SootEvents.PREFIX + "Water rising damage has been disabled.");
        } else {
            WaterRisingUtils.getWaterRisingUtils().setWaterRisingEnabled(true);
            sender.sendMessage(SootEvents.PREFIX + "Water rising damage has been enabled.");
        }

        return true;
    }
}
