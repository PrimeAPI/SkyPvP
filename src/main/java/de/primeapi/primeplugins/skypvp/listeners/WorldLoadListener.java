package de.primeapi.primeplugins.skypvp.listeners;

import de.primeapi.primeplugins.skypvp.data.DataProvider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

import java.io.IOException;

/**
 * @author Lukas S. PrimeAPI
 * created on 23.08.2022
 * crated for SkyPvP
 */
public class WorldLoadListener implements Listener {

	@EventHandler
	public void onLoad(WorldLoadEvent e){
		try {
			DataProvider.getInstance().loadData();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
