package de.primeapi.primeplugins.skypvp.data.oop;

import de.primeapi.primeplugins.skypvp.data.oop.subclasses.KitStorage;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.NPCStorage;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.RegionStorage;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.WarpStorage;
import lombok.Data;

/**
 * @author Lukas S. PrimeAPI
 * created on 18.08.2022
 * crated for PrimePlugins-ROOT
 */
@Data
public class Storage {

	private static Storage instance;
	WarpStorage warpStorage = new WarpStorage();
	NPCStorage npcStorage = new NPCStorage();
	RegionStorage regionStorage = new RegionStorage();
	KitStorage kitStorage = new KitStorage();
	public Storage() {
		instance = this;
	}

	public static Storage getInstance() {
		return instance;
	}


}
