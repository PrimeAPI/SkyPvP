package de.primeapi.primeplugins.skypvp.sql.stats;

import de.primeapi.primeplugins.spigotapi.PrimeCore;
import de.primeapi.primeplugins.spigotapi.api.cache.Cache;
import de.primeapi.util.sql.queries.Retriever;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
public class StatsAdapter {

	private static final Cache<UUID, Stats> cache = new Cache<>();

	public static void unCache(UUID uuid) {
		cache.unCache(uuid);
	}


	public static Retriever<Integer> getStats(UUID uuid, StatsEntry entry, Long start) {
		return new Retriever<>(() -> {
			if (start == -1) {
				Stats cached = cache.getCachedValue(uuid);
				if (cached != null) return cached.get(entry);
			}
			int i = 0;
			try {
				PreparedStatement st = PrimeCore.getInstance().getConnection().prepareStatement(
						"SELECT SUM(amount) AS count FROM prime_skypvp_stats WHERE uuid = ? AND type = ? AND time >" +
								" ?;"
				                                                                               );
				st.setString(1, uuid.toString());
				st.setString(2, entry.toString());
				st.setLong(3, start);
				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					i = rs.getInt("count");
				}
				rs.close();
				st.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			return i;
		}
		);
	}

	public static void addStatsEntry(UUID uuid, StatsEntry entry, int amount) {
		unCache(uuid);
		PrimeCore.getInstance().getDb().update("INSERT INTO prime_skypvp_stats VALUES (id,?,?,?,?)")
		         .parameters(uuid.toString(), entry.toString(), amount, System.currentTimeMillis()).execute();
	}

	public static Retriever<Stats> getStats(UUID uuid, Long start) {
		return new Retriever<>(() -> {
			Stats stats = cache.getCachedValue(uuid);
			if (stats != null && start == -1) return stats;
			stats = new Stats(
					uuid,
					getStats(uuid, StatsEntry.KILL, start).complete(),
					getStats(uuid, StatsEntry.DEATH, start).complete()
			);
			if (start == -1) cache.cacheEntry(uuid, stats);
			return stats;
		}
		);
	}

	public static Retriever<Integer> getRank(UUID uuid, Long start) {
		return new Retriever<>(() -> {
			LinkedList<UUID> list = new LinkedList<>();
			try {
				PreparedStatement st = PrimeCore.getInstance().getConnection().prepareStatement(
						"SELECT uuid, SUM(amount) AS sum FROM prime_skypvp_stats WHERE type = ? AND time > ? GROUP " +
								"BY uuid ORDER BY sum DESC;"
				                                                                               );
				st.setString(1, StatsEntry.KILL.toString());
				st.setLong(2, start);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					list.add(UUID.fromString(rs.getString("uuid")));
				}
				rs.close();
				st.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			return list.indexOf(uuid) + 1;
		});
	}

	public static Retriever<Stats> getAlltime(UUID uuid) {
		return new Retriever<>(() -> {
			Stats stats = cache.getCachedValue(uuid);
			if (stats != null) return stats;
			stats = new Stats(
					uuid,
					getStats(uuid, StatsEntry.KILL, -1L).complete(),
					getStats(uuid, StatsEntry.DEATH, -1L).complete()
			);
			cache.cacheEntry(uuid, stats);
			return stats;
		});
	}

	public static Retriever<Stats> get30Dtime(UUID uuid) {
		return new Retriever<>(() -> {
			long time = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(30);
			return new Stats(
					uuid,
					getStats(uuid, StatsEntry.KILL, time).complete(),
					getStats(uuid, StatsEntry.DEATH, time).complete()
			);
		});
	}

	public static Retriever<LinkedList<Stats>> getTop(StatsEntry type, Long time, int limit) {
		return new Retriever<>(() -> {
			LinkedList<Stats> list = new LinkedList<>();
			try {
				PreparedStatement st = PrimeCore.getInstance().getConnection().prepareStatement(
						"SELECT uuid, SUM(amount) AS sum FROM prime_skypvp_stats WHERE type = ? AND time > ? GROUP " +
								"BY uuid ORDER BY sum DESC LIMIT ?;"
				                                                                               );
				st.setString(1, type.toString());
				st.setLong(2, time);
				st.setInt(3, limit);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					UUID uuid = UUID.fromString(rs.getString("uuid"));
					list.add(new Stats(
							uuid,
							getStats(uuid, StatsEntry.KILL, time).complete(),
							getStats(uuid, StatsEntry.DEATH, time).complete()
					));
				}
				rs.close();
				st.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			return list;
		});
	}


	public enum StatsEntry {
		KILL,
		DEATH
	}


}
