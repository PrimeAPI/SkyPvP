package de.primeapi.primeplugins.skypvp.listeners;

import de.primeapi.primeplugins.skypvp.data.oop.subclasses.RegionStorage;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.skypvp.sql.stats.perk.Perk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

/**
 * @author Lukas S. PrimeAPI
 * created on 24.08.2022
 * crated for SkyPvP
 */
public class WorldChangeListener implements Listener {

	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent event){
		Perk.FLY.check(event.getPlayer());
	}

}
