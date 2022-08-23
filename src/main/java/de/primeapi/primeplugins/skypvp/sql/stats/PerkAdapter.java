package de.primeapi.primeplugins.skypvp.sql.stats;

import de.primeapi.primeplugins.spigotapi.PrimeCore;
import de.primeapi.primeplugins.spigotapi.api.cache.Cache;
import de.primeapi.util.sql.queries.Retriever;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Lukas S. PrimeAPI
 * created on 23.08.2022
 * crated for SkyPvP
 */
public class PerkAdapter {

	public static HashMap<UUID, HashMap<Perk, Boolean>> storage = new HashMap<>();


	public static void preparePlayer(Player player, Runnable onFinish){
		PrimeCore.getInstance().getDb().select("SELECT perk, active FROM prime_skypvp_perks WHERE uuid = ?")
				.parameters(player.getUniqueId().toString())
				.execute(null)
				.getAllRowData().submit(objects -> {
					HashMap<Perk, Boolean> perks = new HashMap<>();
			         for (Object[] array : objects) {
				         try {
							 perks.put(Perk.valueOf((String) array[0]), (Integer) array[1] == 1);
				         }catch (Exception ex){
							 ex.printStackTrace();
				         }
			         }
			         storage.put(player.getUniqueId(), perks);
					 onFinish.run();
		         });
	}


	/**
	 *
	 * @param uuid the uuid of the player
	 * @param perk the perk to get the value of
	 * @return null if player does not own the Perk, otherwise the value of the Perk
	 */
	public static Retriever<Boolean> getPerk(UUID uuid, Perk perk){
		return PrimeCore.getInstance().getDb().select("SELECT active FROM prime_skypvp_perks WHERE uuid = ? AND perk = ?")
				.parameters(uuid.toString(), perk.name())
				.execute(Integer.class)
				.get()
				.map(integer -> {
					if(Objects.isNull(integer)) return null;
					return integer == 1;
				});
	}



	public static void addPerkToPlayer(UUID uuid, Perk perk){
		PrimeCore.getInstance().getDb().update("INSERT INTO prime_skypvp_perks (uuid, perk, active) VALUES (?, ?, 1)")
				.parameters(uuid.toString(), perk.name())
				.execute();
		storage.getOrDefault(uuid, new HashMap<>()).put(perk, true);
	}

	public static void updatePerk(UUID uuid, Perk perk, boolean active){
		PrimeCore.getInstance().getDb().update("UPDATE prime_skypvp_perks SET active = ? WHERE uuid = ? AND perk = ?")
				.parameters(active ? 1 : 0, uuid.toString(), perk.name())
				.execute();
		storage.getOrDefault(uuid, new HashMap<>()).put(perk, active);
	}



}
