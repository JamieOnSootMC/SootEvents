package net.sootmc.sootevents.Bossbar;

import net.sootmc.sootevents.SootEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class BossbarCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0) {
            sender.sendMessage(SootEvents.PREFIX + "Invalid argument(s). Correct usage:");
            sender.sendMessage(SootEvents.PREFIX + "/bossbar <on|off|reload>");
            return true;
        } else if(args[0].equalsIgnoreCase("on")) {
            BossbarUtils.getBossbarUtils().makeBar();
            SootEvents.instance.getConfig().set("bossbar.toggled", true);
            sender.sendMessage(SootEvents.PREFIX + "Bossbar has been enabled.");
            return true;
        } else if(args[0].equalsIgnoreCase("off")) {
            BossbarUtils.getBossbarUtils().removeBar();
            SootEvents.instance.getConfig().set("bossbar.toggled", false);
            sender.sendMessage(SootEvents.PREFIX + "Bossbar has been disabled.");
            return true;
        } else if(args[0].equalsIgnoreCase("reload")) {
            SootEvents.instance.reloadConfig();
            BossbarUtils.getBossbarUtils().removeBar();
            BossbarUtils.getBossbarUtils().makeBar();
            sender.sendMessage(SootEvents.PREFIX + "Config and Bossbar have been reloaded.");
            return true;
        } else {
            sender.sendMessage(SootEvents.PREFIX + "Invalid argument(s). Correct usage:");
            sender.sendMessage(SootEvents.PREFIX + "/bossbar <on|off|reload>");
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1) {
            return List.of("on", "off", "reload");
        } else {
            return null;
        }
    }
}
