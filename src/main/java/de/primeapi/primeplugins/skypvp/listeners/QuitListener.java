package de.primeapi.primeplugins.skypvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
public class QuitListener implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		event.setQuitMessage(null);
	}

}
