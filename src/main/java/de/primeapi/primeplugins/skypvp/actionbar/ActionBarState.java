package de.primeapi.primeplugins.skypvp.actionbar;

import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 21.08.2022
 * crated for SkyPvP
 */
public interface ActionBarState {

	boolean display(Player player);
	String getMessage(Player player);

}
