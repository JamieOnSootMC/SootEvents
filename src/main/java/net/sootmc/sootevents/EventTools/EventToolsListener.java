package net.sootmc.sootevents.EventTools;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.GlobalProtectedRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import net.sootmc.sootevents.SootEvents;
import net.sootmc.sootevents.Utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Set;

public class EventToolsListener implements Listener {
    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        if (event.getItem() == null) {
            return;
        }

        System.out.println(event.getItem().getType());

        if(event.getItem().getType() != Material.PAPER && event.getItem().getType() != Material.WOODEN_SWORD && event.getItem().getType() != Material.BARRIER && event.getItem().getType() != Material.GOLDEN_APPLE) {
            System.out.println("Item is not a valid event tool.");
            return;
        }

        String itemName = ChatColor.stripColor(event.getItem().getItemMeta().getDisplayName());
        System.out.println(itemName);

        switch (itemName) {
            case "Toggle Chat" -> {
                event.setCancelled(true);
                if (ChatUtils.getChatUtils().toggled) {
                    ChatUtils.getChatUtils().setChatEnabled(false);
                    event.getPlayer().sendMessage(SootEvents.PREFIX + "Chat has been enabled.");
                } else {
                    ChatUtils.getChatUtils().setChatEnabled(true);
                    event.getPlayer().sendMessage(SootEvents.PREFIX + "Chat has been disabled.");
                }
            }

            case "PvP On" -> {
                event.setCancelled(true);
                RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
                Player player = event.getPlayer();

                Location loc = BukkitAdapter.adapt(player.getLocation());
                RegionQuery query = regionContainer.createQuery();
                Set<ProtectedRegion> regions = query.getApplicableRegions(loc).getRegions();

                if(regions.isEmpty()) {
                    GlobalProtectedRegion globalRegion = new GlobalProtectedRegion("__global__");
                    globalRegion.setFlag(Flags.PVP, StateFlag.State.ALLOW);
                    event.getPlayer().sendMessage(SootEvents.PREFIX + "PvP has been enabled in the global region.");
                    regionContainer.get(BukkitAdapter.adapt(player.getWorld())).addRegion(globalRegion);
                    return;
                }

                ProtectedRegion region = regions.iterator().next();

                if(region.getFlag(Flags.PVP) == null || region.getFlag(Flags.PVP) == StateFlag.State.DENY) {
                    region.setFlag(Flags.PVP, StateFlag.State.ALLOW);
                    event.getPlayer().sendMessage(SootEvents.PREFIX + "PvP has been enabled in region " + region.getId());
                }
            }

            case "PvP Off" -> {
                event.setCancelled(true);
                RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
                Player player = event.getPlayer();

                Location loc = BukkitAdapter.adapt(player.getLocation());
                RegionQuery query = regionContainer.createQuery();
                Set<ProtectedRegion> regions = query.getApplicableRegions(loc).getRegions();

                if(regions.isEmpty()) {
                    GlobalProtectedRegion globalRegion = new GlobalProtectedRegion("__global__");
                    globalRegion.setFlag(Flags.PVP, StateFlag.State.DENY);
                    event.getPlayer().sendMessage(SootEvents.PREFIX + "PvP has been disabled in the global region.");
                    regionContainer.get(BukkitAdapter.adapt(player.getWorld())).addRegion(globalRegion);
                    return;
                }

                ProtectedRegion region = regions.iterator().next();

                if(region.getFlag(Flags.PVP) == StateFlag.State.ALLOW) {
                    region.setFlag(Flags.PVP, StateFlag.State.DENY);
                    event.getPlayer().sendMessage(SootEvents.PREFIX + "PvP has been disabled in region " + region.getId());
                }
            }

            case "Close" -> {
                event.setCancelled(true);
                event.getPlayer().getInventory().clear();
                event.getPlayer().getInventory().setContents(SootEvents.storedInventories.get(event.getPlayer().getUniqueId()));
                event.getPlayer().updateInventory();
                SootEvents.storedInventories.remove(event.getPlayer().getUniqueId());
                event.getPlayer().sendMessage(SootEvents.PREFIX + "Your inventory has been restored.");
            }
        }
    }
}
