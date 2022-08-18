package de.primeapi.primeplugins.skypvp.data.oop.subclasses;

import de.primeapi.primeplugins.skypvp.api.annotations.GsonIgnore;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lukas S. PrimeAPI
 * created on 18.08.2022
 * crated for PrimePlugins-ROOT
 */
@Data
public class WarpStorage {
	@GsonIgnore
	private static WarpStorage instance;

	public static WarpStorage getInstance() {
		return instance;
	}

	public WarpStorage(){
		instance = this;
	}


	WarpPoint spawn;
	WarpPoint plot;
	List<WarpPoint> warps = new ArrayList<>();


	public void addWarp(WarpPoint warpPoint){
		warps.add(warpPoint);
	}
	public void removeWarp(WarpPoint warpPoint){
		warps.remove(warpPoint);
	}

	public WarpPoint getWarp(String name){
		switch (name.toLowerCase()){
			case "spawn":{
				return spawn;
			}
			case "plot":
			case "plotworld":{
				return plot;
			}
			default:{
				return warps.stream().filter(warpPoint -> warpPoint.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
			}
		}
	}

	/**
	 * @author Lukas S. PrimeAPI
	 * created on 18.08.2022
	 * crated for PrimePlugins-ROOT
	 */
	@Data
	public static class WarpPoint {

		final String name;
		final Location location;
		final String displayname;
		final ItemStack display;

	}
}
