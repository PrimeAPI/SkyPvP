package de.primeapi.primeplugins.skypvp.listeners.pvp;

import de.primeapi.primeplugins.skypvp.data.oop.subclasses.WarpStorage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
public class PlayerDeathListener implements Listener {


	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		event.setDeathMessage(null);
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		event.setRespawnLocation(WarpStorage.getInstance().getSpawn().getLocation());
	}



}
