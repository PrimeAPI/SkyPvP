package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.spigotapi.api.PrimePlayer;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.*;
import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 17.08.2022
 * crated for PrimePlugins-ROOT
 */
@Command(name = "test")
public class TestCommand {


	@SubCommand(name = "say <message>")
	public void subCommand(@SenderField Player player, @SingleAttribute(name = "message") String message){
		player.chat(message);

	}

	@SubCommand(name = "sudo <command>", permission = "perm.perm")
	public void subCommand2(@SenderField PrimePlayer player, @MultiAttribute(name = "command") String command){
		player.thePlayer().chat("/" + command);
	}

}