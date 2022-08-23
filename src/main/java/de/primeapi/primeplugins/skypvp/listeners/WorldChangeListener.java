package de.primeapi.primeplugins.skypvp.listeners;

import de.primeapi.primeplugins.skypvp.data.oop.subclasses.RegionStorage;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.skypvp.sql.stats.Perk;
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
		if(RegionStorage.getInstance().isPlot(event.getPlayer().getLocation())){
			if(Perk.FLY.isActive(event.getPlayer())){
				event.getPlayer().setAllowFlight(true);
				Message.FLY_TOGGLE_ON.send(event.getPlayer());
			}
		}else {
			if(event.getPlayer().getAllowFlight()){
				event.getPlayer().setFlying(false);
				event.getPlayer().setAllowFlight(false);
				Message.FLY_TOGGLE_OFF.send(event.getPlayer());
			}
		}
	}

}
