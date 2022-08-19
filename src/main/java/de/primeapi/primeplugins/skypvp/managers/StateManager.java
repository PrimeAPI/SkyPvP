package de.primeapi.primeplugins.skypvp.managers;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
public class StateManager {

	private static final List<Player> buildMode = new ArrayList<>();

	public static boolean isBuildMode(Player player) {
		return buildMode.contains(player);
	}

	public static void setBuildMode(Player player, boolean b) {
		if (b && !isBuildMode(player)) {buildMode.add(player);} else buildMode.remove(player);
	}

}
