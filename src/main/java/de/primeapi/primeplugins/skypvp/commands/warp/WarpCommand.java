package de.primeapi.primeplugins.skypvp.commands.warp;

import de.primeapi.primeplugins.skypvp.SkyPvP;
import de.primeapi.primeplugins.skypvp.data.DataProvider;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.WarpStorage;
import de.primeapi.primeplugins.skypvp.managers.CombatManager;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.skypvp.util.ItemStackSelector;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.*;
import de.primeapi.primeplugins.spigotapi.gui.GUIBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lukas S. PrimeAPI
 * created on 18.08.2022
 * crated for PrimePlugins-ROOT
 */
@Command(name = "warp")
public class WarpCommand {

	@SubCommand(name = "<warpname>", priority = CommandPriority.LOW)
	public void teleport(
			@SenderField Player player,
			@SingleAttribute(name = "warpname", required = false) String warpName
	                    ) {

		int sec = CombatManager.remainingSeconds(player);
		if(sec > 0){
			Message.COMBAT_REMAINING.replace("sec", sec).send(player);
			return;
		}
		if (warpName == null) {
			List<WarpStorage.WarpPoint> warps = new ArrayList<>(WarpStorage.getInstance().getWarps());
			warps.add(WarpStorage.getInstance().getPlot());
			warps.add(WarpStorage.getInstance().getSpawn());
			warps = warps.stream()
			             .filter(warpPoint -> warpPoint != null && warpPoint.getDisplay() != null)
			             .collect(Collectors.toList());
			int rows = (warps.size() / 7) + 3;
			GUIBuilder builder = new GUIBuilder(rows * 9, "§6§lWarps");
			builder.fillInventory();
			int i = 10;

			for (WarpStorage.WarpPoint warp : warps) {
				builder.addItem(
						i, warp.getDisplay(), (player1, itemStack) -> Bukkit.getScheduler()
						                                                    .runTask(
								                                                    SkyPvP.getInstance(),
								                                                    () -> player1.chat(
										                                                    "/warp " + warp.getName())
						                                                            ));
				if (i % 8 == 0) {
					i += 3;
				} else {
					i++;
				}
			}
			player.openInventory(builder.build(SkyPvP.getInstance()));
		} else {
			WarpStorage.WarpPoint warpPoint = DataProvider.getInstance().getStorage().getWarpStorage().getWarp(warpName);
			if (warpPoint == null) {
				Message.WARP_404.send(player);
				return;
			}
			player.teleport(warpPoint.getLocation());
			Message.WARP_SUCCESS.replace("display", warpPoint.getDisplayname()).send(player);
		}

	}

	@SubCommand(name = "set <name> <displayname>", permission = "skypvp.warp.admin")
	public void setWarp(
			@SenderField Player player,
			@SingleAttribute(name = "name") String name,
			@MultiAttribute(name = "displayname") String displayName
	                   ) {
		Location location = player.getLocation();
		Message.WARP_SETUP_SELECT_ITEM.send(player);


		new ItemStackSelector(player, itemStack -> {
			WarpStorage.WarpPoint warpPoint = new WarpStorage.WarpPoint(name.toLowerCase(), location, displayName.replace("&", "§"),
			                                                            itemStack
			);
			switch (name.toLowerCase()) {
				case "spawn":
					DataProvider.getInstance().getStorage().getWarpStorage().setSpawn(warpPoint);
					break;
				case "plot":
				case "plotworld":
					DataProvider.getInstance().getStorage().getWarpStorage().setPlot(warpPoint);
					break;
				default:
					DataProvider.getInstance().getStorage().getWarpStorage().addWarp(warpPoint);
					break;
			}
			Message.WARP_SETUP_SUCCESS
					.replace("display", displayName)
					.replace("name", name)
					.send(player);
			try {
				DataProvider.getInstance().save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	@SubCommand(name = "delete <name>", permission = "skypvp.warp.admin")
	public void deleteWarp(
			@SenderField Player player,
			@SingleAttribute(name = "name") String name
	                      ) {
		switch (name.toLowerCase()) {
			case "spawn":
			case "plot":
			case "plotworld": {
				Message.WARP_DELETE_STATIC.send(player);
				break;
			}
			default: {
				WarpStorage.WarpPoint warpPoint = DataProvider.getInstance().getStorage().getWarpStorage().getWarp(name);
				if (warpPoint == null) {
					Message.WARP_404.send(player);
					return;
				}
				DataProvider.getInstance().getStorage().getWarpStorage().removeWarp(warpPoint);
			}
		}
	}


}
