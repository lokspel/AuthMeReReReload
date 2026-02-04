package fr.xephi.authme.listener;

import io.papermc.paper.event.player.AsyncPlayerSpawnLocationEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import javax.inject.Inject;

/**
 * Listener of player events for Paper-specific events (Minecraft 1.21.9+).
 * Handles AsyncPlayerSpawnLocationEvent which replaces the deprecated PlayerSpawnLocationEvent.
 */
public class PlayerListener1219 implements Listener {

    private static boolean isAsyncPlayerSpawnLocationEventCalled = false;

    public static boolean isAsyncPlayerSpawnLocationEventCalled() {
        return isAsyncPlayerSpawnLocationEventCalled;
    }

    /**
     * Called async before player entity is created.
     * This is Paper's replacement for the deprecated PlayerSpawnLocationEvent.
     * Note: the following event is called since Paper 1.21.9+
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onAsyncPlayerSpawn(AsyncPlayerSpawnLocationEvent event) {
        isAsyncPlayerSpawnLocationEventCalled = true;
    }
}
