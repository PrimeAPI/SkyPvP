package de.primeapi.primeplugins.skypvp.listeners;

import de.primeapi.primeplugins.skypvp.data.oop.subclasses.RegionStorage;
import de.primeapi.primeplugins.skypvp.managers.StateManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
public class BlockListener implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (RegionStorage.getInstance().isPvP(event.getBlock().getLocation())) {
			if (!StateManager.isBuildMode(event.getPlayer())) {
				event.setCancelled(true);
			}
		}

	}

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if (RegionStorage.getInstance().isPvP(event.getBlock().getLocation())) {
			if (!StateManager.isBuildMode(event.getPlayer())) {
				event.setCancelled(true);
			}
		}

	}

}
