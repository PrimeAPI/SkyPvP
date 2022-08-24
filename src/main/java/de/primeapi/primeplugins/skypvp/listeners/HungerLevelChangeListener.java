package de.primeapi.primeplugins.skypvp.listeners;

import de.primeapi.primeplugins.skypvp.sql.stats.perk.Perk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * @author Lukas S. PrimeAPI
 * created on 24.08.2022
 * crated for SkyPvP
 */
public class HungerLevelChangeListener implements Listener {

	@EventHandler
	public void onHungerLevelChange(FoodLevelChangeEvent event){
		if(event.getEntity() instanceof Player){
			Player player = (Player) event.getEntity();
			if(Perk.NO_HUNGER.isActive(player)){
				event.setCancelled(true);
			}
		}

	}

}
