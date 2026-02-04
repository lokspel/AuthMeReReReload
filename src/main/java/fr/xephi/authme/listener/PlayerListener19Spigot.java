package fr.xephi.authme.listener;

import fr.xephi.authme.service.TeleportationService;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import javax.inject.Inject;

/**
 * Listener for Spigot 1.9+ spawn location events.
 * This uses the deprecated PlayerSpawnLocationEvent for backwards compatibility with Spigot.
 * On Paper 1.21.9+, PlayerListener1219 is used instead with AsyncPlayerSpawnLocationEvent.
 */
@SuppressWarnings({"removal"}) // Intentionally using deprecated event for Spigot compatibility
public class PlayerListener19Spigot implements Listener {

    private static boolean isPlayerSpawnLocationEventCalled = false;

    @Inject
    private TeleportationService teleportationService;

    public static boolean isPlayerSpawnLocationEventCalled() {
        return isPlayerSpawnLocationEventCalled;
    }

    /**
     * Note: the following event is called since MC1.9, in older versions we have to fallback on the PlayerJoinEvent.
     * This event is deprecated on Paper 1.21.9+ but still needed for Spigot compatibility.
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerSpawn(PlayerSpawnLocationEvent event) {
        isPlayerSpawnLocationEventCalled = true;
        final Player player = event.getPlayer();

        Location customSpawnLocation = teleportationService.prepareOnJoinSpawnLocation(player);
        if (customSpawnLocation != null) {
            event.setSpawnLocation(customSpawnLocation);
        }
    }
}
