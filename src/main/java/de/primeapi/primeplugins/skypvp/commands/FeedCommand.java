package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.spigotapi.api.PrimePlayer;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SenderField;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SingleAttribute;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import org.bukkit.entity.Player;

@Command(name = "feed", permission = "skypvp.feed")
public class FeedCommand {
	@SubCommand(name = "<player>")
	public void setFeed(
			@SenderField PrimePlayer primePlayer,
			@SingleAttribute(name = "player", required = false) Player target
	                   ) {

		if (target == null) {
			primePlayer.thePlayer().setFoodLevel(23);
			Message.FEED_PLAYER.send(primePlayer);
		} else if (primePlayer.checkPermission("skypvp.feed.others")) {
			target.setFoodLevel(23);
			Message.FEED_OPLAYER.replace("player", target.getName()).send(primePlayer);
			Message.FEED_ME.replace("staff", primePlayer.thePlayer().getName()).send(target);
		}
	}
}