package de.primeapi.primeplugins.skypvp.managers;

import de.primeapi.primeplugins.spigotapi.PrimeCore;
import de.primeapi.util.sql.queries.Collector;
import de.primeapi.util.sql.queries.Retriever;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Lukas S. PrimeAPI
 * created on 20.08.2022
 * crated for SkyPvP
 */
public class TimeoutManager {

	public static Retriever<Long> getTimeout(UUID uuid, String name){
		return PrimeCore.getInstance().getDb().select("SELECT `release` FROM prime_skypvp_timeout WHERE uuid = ? AND name = ?")
				.parameters(uuid.toString(), name)
				.execute(Long.class)
				.get();
	}

	public static void resetTimeout(UUID uuid, String name){
		PrimeCore.getInstance().getDb().update("DELETE FROM prime_skypvp_timeout WHERE uuid = ? AND name = ?")
				.parameters(uuid.toString(), name).execute();
	}

	public static void setTimeout(UUID uuid, String name, Integer timeoutInHours){
		resetTimeout(uuid, name);
		PrimeCore.getInstance().getDb().update("INSERT INTO prime_skypvp_timeout VALUES (id, ?,?,?)")
				.parameters(uuid.toString(), name, System.currentTimeMillis() + TimeUnit.HOURS.toMillis(timeoutInHours)).execute();
	}


}
