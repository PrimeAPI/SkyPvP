package de.primeapi.primeplugins.skypvp.data.oop;

import de.primeapi.primeplugins.skypvp.api.annotations.GsonIgnore;
import de.primeapi.primeplugins.skypvp.data.DataProvider;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.WarpStorage;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lukas S. PrimeAPI
 * created on 18.08.2022
 * crated for PrimePlugins-ROOT
 */
@Data
public class Storage {

	private static Storage instance;

	public static Storage getInstance() {
		return instance;
	}

	public Storage(){
		instance = this;
	}

	WarpStorage warpStorage = new WarpStorage();




}
