package de.primeapi.primeplugins.skypvp.sql.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
@AllArgsConstructor
@Getter
public class Stats {

	UUID uuid;
	int kills;
	int deaths;

	public Integer get(StatsAdapter.StatsEntry entry) {
		switch (entry) {
			case KILL:
				return kills;
			case DEATH:
				return deaths;
		}
		return -1;
	}


	public double getKD() {
		if (deaths == 0 || deaths == 1) return kills;
		double k = getKills();
		double d = getDeaths();
		double kd = k / d;
		int kd100 = (int) (kd * 100d);
		return kd100 / 100d;
	}

	@Override
	public String toString() {
		return "Stats{" +
				"kills=" + kills +
				", deaths=" + deaths +
				'}';
	}
}
