package de.primeapi.primeplugins.skypvp.listeners;

import de.primeapi.primeplugins.skypvp.data.oop.subclasses.WarpStorage;
import de.primeapi.primeplugins.skypvp.managers.CombatManager;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.skypvp.sql.stats.StatsAdapter;
import de.primeapi.primeplugins.spigotapi.api.plugins.coins.CoinsAPI;
import org.bukkit.entity.Player;
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
		if(CombatManager.isInFight(event.getPlayer())){
			Player player = event.getPlayer();
			player.setHealth(0);
		}
	}

}
