package net.sootmc.sootevents.Utils;

import net.sootmc.sootevents.SootEvents;
import org.bukkit.GameMode;

public class GamemodeUtils {

    public static GameMode gamemodeSwitcher(String gamemode) {
        switch(gamemode) {
            case "survival":
                return GameMode.SURVIVAL;
            case "creative":
                return GameMode.CREATIVE;
            case "adventure":
                return GameMode.ADVENTURE;
            case "spectator":
                return GameMode.SPECTATOR;
        }

        return null;
    }
}
