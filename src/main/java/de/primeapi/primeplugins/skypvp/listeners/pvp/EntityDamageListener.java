package de.primeapi.primeplugins.skypvp.listeners.pvp;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
public class EntityDamageListener implements Listener {

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
		if (event.getCause() == EntityDamageEvent.DamageCause.FALL) event.setCancelled(true);


	}

}
