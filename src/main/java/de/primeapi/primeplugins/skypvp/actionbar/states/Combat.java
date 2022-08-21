package de.primeapi.primeplugins.skypvp.actionbar.states;

import de.primeapi.primeplugins.skypvp.actionbar.ActionBarState;
import de.primeapi.primeplugins.skypvp.managers.CombatManager;
import org.bukkit.entity.Player;

import javax.swing.*;

/**
 * @author Lukas S. PrimeAPI
 * created on 21.08.2022
 * crated for SkyPvP
 */
public class Combat implements ActionBarState {

	@Override
	public boolean display(Player player) {
		return CombatManager.isInFight(player);
	}

	@Override
	public String getMessage(Player player) {
		return "§cIm Kampf§7: §c" + CombatManager.remainingSeconds(player);
	}

}
