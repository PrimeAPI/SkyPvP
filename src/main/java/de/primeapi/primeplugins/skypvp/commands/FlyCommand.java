package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.spigotapi.api.PrimePlayer;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SenderField;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SingleAttribute;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 18.08.2022
 * crated for PrimePlugins-ROOT
 */
@Command(name = "fly")
public class FlyCommand {

	@SubCommand(name = "<player>", permission = "skypvp.fly")
	public void fly(
			@SenderField PrimePlayer primePlayer,
			@SingleAttribute(name = "player", required = false) Player target
	               ) {

		Player player;

		if (target == null) {player = primePlayer.thePlayer();} else {
			player = target;
			Message.FLY_TOGGLE_OTHER.replace("name", player.getName()).send(primePlayer);
		}

		if (player.getAllowFlight()) {
			Message.FLY_TOGGLE_OFF.send(player);
			player.setFlying(false);
			player.setAllowFlight(false);
		} else {
			Message.FLY_TOGGLE_ON.send(player);
			player.setAllowFlight(true);
			player.setFlying(true);
		}
	}

}
