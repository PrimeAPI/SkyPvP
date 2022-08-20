package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.managers.StateManager;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.skypvp.util.VanishUtils;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 20.08.2022
 * crated for SkyPvP
 */
@Command(name = "vanish", permission = "skypvp.vanish")
public class VanishCommand {

	@SubCommand(name = "<name>", priority = CommandPriority.LOW)
	public void onVanish(
			@SenderField Player player,
			@SingleAttribute(name = "name", required = false) Player target
	                    ) {
		if (target == null) {
			if (StateManager.isVanish(player)) {
				StateManager.setVanish(player, false);
				Message.VANISH_SELF_OFF.send(player);
				VanishUtils.update();
			} else {
				StateManager.setVanish(player, true);
				Message.VANISH_SELF_ON.send(player);
				VanishUtils.update();
			}
		} else {
			if (StateManager.isVanish(target)) {
				StateManager.setVanish(target, false);
				Message.VANISH_SELF_OFF.send(target);
				Message.VANISH_OTHER_OFF.replace("name", target.getName()).send(player);
				VanishUtils.update();
			} else {
				StateManager.setVanish(target, true);
				Message.VANISH_SELF_ON.send(target);
				Message.VANISH_OTHER_ON.replace("name", target.getName()).send(player);
				VanishUtils.update();
			}
		}
	}

	@SubCommand(name = "list", permission = "vanish.list")
	public void onList(@SenderField Player player) {
		Message.VANISH_LIST_TITLE.send(player);
		for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
			if (StateManager.isVanish(player)) {
				Message.VANISH_LIST_ENTRY.replace("name", onlinePlayer.getName()).send(player);
			}
		}
	}

}
