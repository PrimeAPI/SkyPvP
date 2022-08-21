package de.primeapi.primeplugins.skypvp.actionbar.states;

import de.primeapi.primeplugins.skypvp.actionbar.ActionBarState;
import de.primeapi.primeplugins.skypvp.managers.StateManager;
import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 21.08.2022
 * crated for SkyPvP
 */
public class BuildState implements ActionBarState {

	@Override
	public boolean display(Player player) {
		return StateManager.isBuildMode(player);
	}

	@Override
	public String getMessage(Player player) {
		return "§eBuildMode§7: §aAktiviert";
	}

}
