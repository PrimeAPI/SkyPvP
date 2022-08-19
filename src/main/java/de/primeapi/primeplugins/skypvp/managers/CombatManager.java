package de.primeapi.primeplugins.skypvp.managers;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
public class CombatManager {

	private static HashMap<UUID, Long> lastHit = new HashMap<>();

	private static final Integer cooldown = 60;

	public static void registerHit(Player player){
		lastHit.put(player.getUniqueId(), System.currentTimeMillis());
	}

	public static void reset(Player player){
		lastHit.remove(player.getUniqueId());
	}

	public static boolean isInFight(Player player){
		if(!lastHit.containsKey(player.getUniqueId())) return false;
		return lastHit.get(player.getUniqueId()) + TimeUnit.SECONDS.toMillis(60) > System.currentTimeMillis();
	}

	public static int remainingSeconds(Player player){
		if(!lastHit.containsKey(player.getUniqueId())) return 0;
		long diff = (lastHit.get(player.getUniqueId()) + TimeUnit.SECONDS.toMillis(60)) - System.currentTimeMillis();
		return ((Long)TimeUnit.MILLISECONDS.toSeconds(diff)).intValue();
	}


}
