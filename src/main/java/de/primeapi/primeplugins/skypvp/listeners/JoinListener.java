package de.primeapi.primeplugins.skypvp.listeners;

import de.primeapi.primeplugins.skypvp.data.oop.subclasses.KitStorage;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.WarpStorage;
import de.primeapi.primeplugins.skypvp.util.VanishUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
public class JoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		VanishUtils.update();
		event.setJoinMessage(null);
		event.getPlayer().teleport(WarpStorage.getInstance().getSpawn().getLocation());

		if(!event.getPlayer().hasPlayedBefore()){
			KitStorage.getInstance().kits.stream().filter(kit -> kit.getName().equalsIgnoreCase("start"))
					.findFirst().ifPresent(kit -> {
						event.getPlayer().getInventory().addItem(kit.getItemStack(1));
			          });
		}
	}

	@EventHandler
	public void onSpawn(PlayerSpawnLocationEvent event) {
		event.setSpawnLocation(WarpStorage.getInstance().getSpawn().getLocation());
	}

}
