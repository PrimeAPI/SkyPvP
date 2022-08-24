package de.primeapi.primeplugins.skypvp.sql.stats.perk;

import de.primeapi.primeplugins.skypvp.data.oop.subclasses.RegionStorage;
import de.primeapi.primeplugins.skypvp.messages.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

/**
 * @author Lukas S. PrimeAPI
 * created on 23.08.2022
 * crated for SkyPvP
 */
@AllArgsConstructor
@Getter
public enum Perk {
	FLY("Fliegen", Material.FEATHER, (player, state) -> {
		if (RegionStorage.getInstance().isPlot(player.getLocation())) {
			if (state) {
				player.setAllowFlight(true);
				Message.FLY_TOGGLE_ON.send(player);
			}else {
				player.setFlying(false);
				player.setAllowFlight(false);
				Message.FLY_TOGGLE_OFF.send(player);
			}
		}else {
			if(player.getGameMode() != GameMode.CREATIVE && player.getAllowFlight()){
				player.setFlying(false);
				player.setAllowFlight(false);
				Message.FLY_TOGGLE_OFF.send(player);
			}
		}
	}),
	NO_HUNGER("Kein Hunger", Material.COOKED_BEEF, (player, state) -> {
		if(state){
			player.setFoodLevel(23);
		}
	}),
	COIN_MULTIPLIER("Coin Multiplikator", Material.GOLD_INGOT, (player, state) -> {

	}),
	FULL_BRIGHT("Nachtsicht", Material.GLOWSTONE, (player, state) -> {
		if(state){
			player.addPotionEffect(
					new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
		}else {
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
		}
	}),
	WATER_BREATHING("Wasser Atem", Material.WATER_BUCKET, (player, state) -> {

	}),
	FIRE_RESISTANCE("Feuerresistenz", Material.FLINT_AND_STEEL, (player, state) -> {

	}),
	HASTE("Schneller Abbauen", Material.GOLD_PICKAXE, (player, state) -> {

	});


	String displayName;
	Material material;
	private PerkChecker perkChecker;


	public void check(Player player) {
		perkChecker.check(player, isActive(player));
	}

	public Boolean getState(Player player) {
		return PerkAdapter.storage.getOrDefault(player.getUniqueId(), new HashMap<>()).getOrDefault(this, null);
	}

	public boolean isActive(Player player) {
		return PerkAdapter.storage.getOrDefault(player.getUniqueId(), new HashMap<>()).getOrDefault(this, false);
	}

}
