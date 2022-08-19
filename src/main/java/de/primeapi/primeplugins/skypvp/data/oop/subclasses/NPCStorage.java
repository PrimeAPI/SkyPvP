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
public class NPCStorage {
	@GsonIgnore
	private static NPCStorage instance;
	List<NPCEntry> npcs = new ArrayList<>();

	public NPCStorage() {
		instance = this;
	}

	public static NPCStorage getInstance() {
		return instance;
	}

	public void addNPC(NPCEntry entry) {
		npcs.add(entry);
	}

	public void removeNPC(NPCEntry entry) {
		npcs.remove(entry);
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class NPCEntry {
		UUID profile;
		String name;
		String displayName;
		Location location;
		boolean imitate;
		boolean look;
		String interactCommand;

	}
}
