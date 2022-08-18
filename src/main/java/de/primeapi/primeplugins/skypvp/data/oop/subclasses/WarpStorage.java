package de.primeapi.primeplugins.skypvp.data.oop.subclasses;

import de.primeapi.primeplugins.skypvp.api.annotations.GsonIgnore;
import de.primeapi.primeplugins.skypvp.data.oop.Storage;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

}
