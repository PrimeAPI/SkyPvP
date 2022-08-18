package de.primeapi.primeplugins.skypvp.data.oop.subclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

/**
 * @author Lukas S. PrimeAPI
 * created on 18.08.2022
 * crated for PrimePlugins-ROOT
 */
@Data
public class WarpPoint {

	final String name;
	final Location location;
	final String displayname;
	final ItemStack display;

}
