package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SenderField;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SingleAttribute;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import org.bukkit.entity.Player;

@Command(name = "enderchest", permission = "skypvp.enderchest")
public class EnderchestCommand {
	@SubCommand(name = "<player>")
	public void onEnderchestCommand(
			@SenderField Player player,
			@SingleAttribute(name = "player", required = false) Player target
	                               ) {
		if (target == null) {
			player.openInventory(player.getEnderChest());
			Message.EnderChest_Open.send(player);
		} else if (player.hasPermission("skypvp.enderchest.others")) {
			String name = target.getName();
			player.openInventory(target.getEnderChest());
			Message.EnderChest_Other.replace("player", target.getName()).send(player);
		}
	}
}
