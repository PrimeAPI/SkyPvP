package de.primeapi.primeplugins.skypvp.listeners.pvp;

import de.primeapi.primeplugins.skypvp.data.oop.subclasses.WarpStorage;
import de.primeapi.primeplugins.skypvp.managers.CombatManager;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.skypvp.sql.stats.StatsAdapter;
import de.primeapi.primeplugins.spigotapi.api.plugins.coins.CoinsAPI;
import org.bukkit.entity.Player;
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
		Player player = event.getEntity();
		StatsAdapter.addStatsEntry(player.getUniqueId(), StatsAdapter.StatsEntry.DEATH, 1);
		CombatManager.reset(player);

		if(player.getKiller() == null){
			Message.PVP_DEATH_PLAYER_NOKILLER.send(player);
		}else {
			Player killer = player.getKiller();
			StatsAdapter.addStatsEntry(killer.getUniqueId(), StatsAdapter.StatsEntry.KILL, 1);
			CoinsAPI.getInstance().addCoins(killer.getUniqueId(), 50);
			Message.PVP_DEATH_PLAYER_KILLER.replace("name", killer.getDisplayName()).send(player);
			Message.PVP_DEATH_KILLER.replace("name", killer.getDisplayName()).send(killer);
		}

		event.setDeathMessage(null);
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		event.setRespawnLocation(WarpStorage.getInstance().getSpawn().getLocation());
	}



}
