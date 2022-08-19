package de.primeapi.primeplugins.skypvp.data.oop.subclasses;

import de.primeapi.primeplugins.skypvp.api.annotations.GsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Lukas S. PrimeAPI
 * created on 18.08.2022
 * crated for PrimePlugins-ROOT
 */
@Data
public class RegionStorage {
	@GsonIgnore
	private static RegionStorage instance;

	public static RegionStorage getInstance() {
		return instance;
	}

	public RegionStorage(){
		instance = this;
	}



	List<Location> plot = new ArrayList<>();

	public void addPlot(Location entry){
		plot.add(entry);
	}
	public void removePlot(Location entry){
		plot.remove(entry);
	}
	public boolean isPlot(Location entry){
		return plot.stream().anyMatch(location -> location.getWorld().equals(entry.getWorld()));
	}

	List<Location> farm = new ArrayList<>();

	public void addFarm(Location entry){
		farm.add(entry);
	}
	public void removeFarm(Location entry){
		farm.remove(entry);
	}
	public boolean isFarm(Location entry){
		return farm.stream().anyMatch(location -> location.getWorld().equals(entry.getWorld()));
	}
	List<Location> pvp = new ArrayList<>();

	public void addPvP(Location entry){
		pvp.add(entry);
	}
	public void removePvP(Location entry){
		pvp.remove(entry);
	}
	public boolean isPvP(Location entry){
		return pvp.stream().anyMatch(location -> location.getWorld().equals(entry.getWorld()));
	}

}
