package de.primeapi.primeplugins.skypvp.util;

import de.primeapi.primeplugins.skypvp.managers.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 20.08.2022
 * crated for SkyPvP
 */
public class VanishUtils {

	public static void update(){
		for (Player player : Bukkit.getOnlinePlayers()) {
			for (Player t : Bukkit.getOnlinePlayers()) {
				if(StateManager.isVanish(t)){
					player.hidePlayer(t);
				}else {
					player.showPlayer(t);
				}
			}
		}
	}

}
