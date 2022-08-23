package de.primeapi.primeplugins.skypvp.data.oop.subclasses;

import de.primeapi.primeplugins.skypvp.api.annotations.GsonIgnore;
import lombok.Data;
import lombok.NonNull;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Lukas S. PrimeAPI
 * created on 18.08.2022
 * crated for PrimePlugins-ROOT
 */
@Data
public class RegionStorage {
	@GsonIgnore
	private static RegionStorage instance;
	List<Location> plot = new ArrayList<>();
	List<Location> farm = new ArrayList<>();
	List<Location> pvp = new ArrayList<>();

	public RegionStorage() {
		instance = this;
	}

	public static RegionStorage getInstance() {
		return instance;
	}

	public void addPlot(Location entry) {
		plot.add(entry);
	}

	public void removePlot(Location entry) {
		plot.remove(entry);
	}

	public boolean isPlot(@NonNull Location entry) {
		return plot.stream().filter(location -> Objects.nonNull(location.getWorld())).anyMatch(location -> location.getWorld().equals(entry.getWorld()));
	}

	public void addFarm(Location entry) {
		farm.add(entry);
	}

	public void removeFarm(Location entry) {
		farm.remove(entry);
	}

	public boolean isFarm(Location entry) {
		return farm.stream().anyMatch(location -> location.getWorld().equals(entry.getWorld()));
	}

	public void addPvP(Location entry) {
		pvp.add(entry);
	}

	public void removePvP(Location entry) {
		pvp.remove(entry);
	}

	public boolean isPvP(Location entry) {
		return pvp.stream().anyMatch(location -> location.getWorld().equals(entry.getWorld()));
	}

}
