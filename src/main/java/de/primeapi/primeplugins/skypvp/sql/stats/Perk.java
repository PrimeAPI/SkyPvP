package de.primeapi.primeplugins.skypvp.sql.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * @author Lukas S. PrimeAPI
 * created on 23.08.2022
 * crated for SkyPvP
 */
@AllArgsConstructor @Getter
public enum Perk {
	FLY("Fliegen", Material.FEATHER),
	NO_HUNGER("Kein Hunger" , Material.COOKED_BEEF),
	COIN_MULTIPLIER("Coin Multiplikator" , Material.GOLD_INGOT),
	FULL_BRIGHT("Nachtsicht" , Material.GLOWSTONE),
	WATER_BREATHING("Wasser Atem" , Material.WATER_BUCKET),
	FIRE_RESISTANCE("Feuerresistenz" , Material.FLINT_AND_STEEL),
	HASTE("Schneller Abbauen", Material.GOLD_PICKAXE);


	String displayName;
	Material material;

	public Boolean getState(Player player){
		return PerkAdapter.storage.getOrDefault(player.getUniqueId(), new HashMap<>()).getOrDefault(this, null);
	}
	public boolean isActive(Player player){
		return PerkAdapter.storage.getOrDefault(player.getUniqueId(), new HashMap<>()).getOrDefault(this, false);
	}

}
