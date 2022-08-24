package de.primeapi.primeplugins.skypvp.sql.stats.perk;

import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 24.08.2022
 * crated for SkyPvP
 */
public interface PerkChecker {

	void check(Player player, boolean state);

}
