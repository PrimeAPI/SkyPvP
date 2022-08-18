package de.primeapi.primeplugins.skypvp.commands.warp;

import de.primeapi.primeplugins.skypvp.data.DataProvider;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.WarpPoint;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SenderField;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 18.08.2022
 * crated for SkyPvP
 */
@Command(name = "plotworld")
public class PlotWorldCommand {

	@SubCommand(name = "")
	public void teleport(
			@SenderField Player player
	                    ) {

		WarpPoint warpPoint = DataProvider.getInstance().getStorage().getWarpStorage().getPlot();
		if (warpPoint == null) {
			Message.WARP_404.send(player);
			return;
		}
		player.teleport(warpPoint.getLocation());
		Message.WARP_SUCCESS.replace("display", warpPoint.getDisplayname()).send(player);


	}
}
