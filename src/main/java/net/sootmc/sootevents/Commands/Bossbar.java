package net.sootmc.sootevents.Commands;

import net.sootmc.sootevents.SootEvents;
import net.sootmc.sootevents.Utils.BossbarUtils;
import net.sootmc.sootevents.Utils.CommandInfo;
import org.bukkit.command.CommandSender;

@CommandInfo(name = "bossbar", permission = "sootevents.command.bossbar", requiresPlayer = false)
public class Bossbar extends CommandHandler {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(SootEvents.PREFIX + "Invalid argument(s). Correct usage:");
            sender.sendMessage(SootEvents.PREFIX + "/bossbar <on|off|reload>");
        } else if(args[0].equalsIgnoreCase("on")) {
            BossbarUtils.getBossbarUtils().makeBar();
            SootEvents.instance.getConfig().set("bossbar.toggled", true);
            sender.sendMessage(SootEvents.PREFIX + "Bossbar has been enabled.");
        } else if(args[0].equalsIgnoreCase("off")) {
            BossbarUtils.getBossbarUtils().removeBar();
            SootEvents.instance.getConfig().set("bossbar.toggled", false);
            sender.sendMessage(SootEvents.PREFIX + "Bossbar has been disabled.");
        } else if(args[0].equalsIgnoreCase("reload")) {
            SootEvents.instance.reloadConfig();
            BossbarUtils.getBossbarUtils().removeBar();
            BossbarUtils.getBossbarUtils().makeBar();
            sender.sendMessage(SootEvents.PREFIX + "Config and Bossbar have been reloaded.");
        } else {
            sender.sendMessage(SootEvents.PREFIX + "Invalid argument(s). Correct usage:");
            sender.sendMessage(SootEvents.PREFIX + "/bossbar <on|off|reload>");
        }
    }
}
