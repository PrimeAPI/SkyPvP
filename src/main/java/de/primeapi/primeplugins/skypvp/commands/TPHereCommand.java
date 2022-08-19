package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SenderField;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SingleAttribute;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import org.bukkit.entity.Player;

@Command(name = "tphere", permission = "skypvp.tphere")
public class TPHereCommand {
	@SubCommand(name = "<player>")

	public void tpHere(
			@SenderField Player primePlayer,
			@SingleAttribute(name = "player", required = true) Player target
	                  ) {

		target.teleport(primePlayer);
		Message.TPHERE_DONE.replace("staff", primePlayer.getName()).send(target);
	}
}
