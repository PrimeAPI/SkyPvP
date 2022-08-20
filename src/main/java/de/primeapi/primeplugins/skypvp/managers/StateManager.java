package de.primeapi.primeplugins.skypvp.managers;

import de.primeapi.primeplugins.spigotapi.PrimeCore;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
public class StateManager {

	private static final List<Player> buildMode = new ArrayList<>();
	private static final List<UUID> vanish = new ArrayList<>();

	public static boolean isBuildMode(Player player) {
		return buildMode.contains(player);
	}

	public static void setBuildMode(Player player, boolean b) {
		if (b && !isBuildMode(player)) {buildMode.add(player);} else buildMode.remove(player);
	}

	public static boolean isVanish(Player player) {
		return vanish.contains(player.getUniqueId());
	}

	public static void setVanish(Player player, boolean b) {
		if (b && !isVanish(player)) {
			vanish.add(player.getUniqueId());
		} else {vanish.remove(player.getUniqueId());}
	}
}
