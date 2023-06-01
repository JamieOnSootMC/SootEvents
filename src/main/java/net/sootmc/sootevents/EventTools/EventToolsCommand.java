package net.sootmc.sootevents.EventTools;

import dev.jamieisgeek.CommandHandler;
import dev.jamieisgeek.CommandInfo;
import net.sootmc.sootevents.SootEvents;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandInfo(name = "eventtools", permission = "sootevents.command.eventtools", requiresPlayer = true)
public class EventToolsCommand extends CommandHandler {
    @Override
    public void execute(Player player, String[] args) {
        if(SootEvents.storedInventories.containsKey(player.getUniqueId())) {
            player.getInventory().clear();
            player.getInventory().setContents(SootEvents.storedInventories.get(player.getUniqueId()));
            player.updateInventory();
            SootEvents.storedInventories.remove(player.getUniqueId());
            player.sendMessage(SootEvents.PREFIX + "Your inventory has been restored.");

            return;
        }

        ItemStack[] inventory = player.getInventory().getContents();
        SootEvents.storedInventories.put(player.getUniqueId(), inventory);
        player.getInventory().clear();

        ItemStack[] eventTools = new ItemStack[9];

        eventTools[0] = new ItemStack(Material.PAPER, 1);
        ItemMeta paperMeta = eventTools[0].getItemMeta();
        paperMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Toggle Chat");
        eventTools[0].setItemMeta(paperMeta);

        eventTools[3] = new ItemStack(Material.WOODEN_SWORD, 1);
        ItemMeta swordMeta = eventTools[3].getItemMeta();
        swordMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "PvP On");
        eventTools[3].setItemMeta(swordMeta);

        eventTools[5] = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta appleMeta = eventTools[5].getItemMeta();
        appleMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "PvP Off");
        eventTools[5].setItemMeta(appleMeta);

        eventTools[8] = new ItemStack(Material.BARRIER, 1);
        ItemMeta barrierMeta = eventTools[8].getItemMeta();
        barrierMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Close");
        eventTools[8].setItemMeta(barrierMeta);

        player.getInventory().setContents(eventTools);
        player.updateInventory();
    }
}
