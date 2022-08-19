package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 17.08.2022
 * crated for PrimePlugins-ROOT
 */
@Command(name = "skypvp")
public class MainCommand {

	public MainCommand() {
		System.out.println("MainCommand.MainCommand");
	}

	@SubCommand(name = "")
	public void execute(Player player) {
		player.sendMessage("§9§lSkyPvP");
	}
}
