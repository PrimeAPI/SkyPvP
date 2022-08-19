package de.primeapi.primeplugins.skypvp.listeners;

import de.primeapi.primeplugins.skypvp.data.oop.subclasses.WarpStorage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
public class JoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		event.getPlayer().teleport(WarpStorage.getInstance().getSpawn().getLocation());
	}

	@EventHandler
	public void onSpawn(PlayerSpawnLocationEvent event) {
		event.setSpawnLocation(WarpStorage.getInstance().getSpawn().getLocation());
	}

}
