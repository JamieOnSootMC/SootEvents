package net.sootmc.sootevents;

import net.sootmc.sootevents.Utils.Helpers.CommandHandler;
import net.sootmc.sootevents.Utils.BossbarUtils;
import net.sootmc.sootevents.Utils.ChatUtils;
import net.sootmc.sootevents.Utils.ScoreboardUtils;
import net.sootmc.sootevents.Utils.WaterRisingUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public final class SootEvents extends JavaPlugin {

    public static SootEvents instance;
    public static String PREFIX = ChatColor.GOLD + "Soot" + ChatColor.RED + "Events" + ChatColor.RESET + " | ";
    private final String packageName = this.getClass().getPackage().getName();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        whitelistCheck();

        new ScoreboardUtils();
        new ChatUtils();
        new WaterRisingUtils();
        new BossbarUtils();

        ScoreboardUtils.getScoreboardUtils().setScoreboardEnabled(this.getConfig().getBoolean("toggled"));
        ChatUtils.getChatUtils().setChatEnabled(this.getConfig().getBoolean("chat.toggled"));

        try {
            registerCommands();
            registerListeners();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        getLogger().info("SootEvents has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SootEvents has been disabled!");
    }

    private void whitelistCheck() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(getServer().hasWhitelist()) {
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        if(!player.isWhitelisted()) {
                            player.kickPlayer("You are not whitelisted on this server.");
                        }
                    });
                }
            }
        }.runTaskTimer(this, 0, 20L);
    }

    private void registerCommands() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for(Class<? extends CommandHandler> clazz: new Reflections(packageName + ".Commands").getSubTypesOf(CommandHandler.class)) {
            CommandHandler commandHandler = clazz.getDeclaredConstructor().newInstance();
            getCommand(commandHandler.getCommandInfo().name()).setExecutor(commandHandler);
        }
    }

    private void registerListeners() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for(Class<?> clazz : new Reflections(packageName + ".Listeners")
                .getSubTypesOf(Listener.class)) {
            Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
