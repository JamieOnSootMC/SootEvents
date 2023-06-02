package net.sootmc.sootevents.Utils;

import net.sootmc.sootevents.SootEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossbarUtils {
    public static BossbarUtils bossbarUtils;
    private String title;
    private BossBar bossbar;

    public BossbarUtils() {
        this.title = ChatColor.translateAlternateColorCodes('&', SootEvents.instance.getConfig().getString("bossbar.title"));
        bossbarUtils = this;
    }
    public void makeBar() {
        this.bossbar = Bukkit.createBossBar(
                this.title,
                BarColor.BLUE,
                BarStyle.SOLID);

        this.bossbar.setVisible(true);
        this.bossbar.setProgress(1.0);

        Bukkit.getOnlinePlayers().forEach(player -> this.bossbar.addPlayer(player));
    }
    
    public void giveBar(Player player) { this.bossbar.addPlayer(player);}

    public void removeBar() {
        Bukkit.getOnlinePlayers().forEach(player -> this.bossbar.removePlayer(player));
    }

    public static BossbarUtils getBossbarUtils() {
        return bossbarUtils;
    }
}
