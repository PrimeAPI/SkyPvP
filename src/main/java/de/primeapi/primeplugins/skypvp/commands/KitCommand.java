package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.SkyPvP;
import de.primeapi.primeplugins.skypvp.data.DataProvider;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.KitStorage;
import de.primeapi.primeplugins.skypvp.managers.TimeoutManager;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.skypvp.util.TimeUtils;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.*;
import de.primeapi.primeplugins.spigotapi.gui.GUIBuilder;
import de.primeapi.primeplugins.spigotapi.managers.messages.CoreMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Lukas S. PrimeAPI
 * created on 20.08.2022
 * crated for SkyPvP
 */
@Command(name = "kit")
public class KitCommand {

	@SubCommand(name = "<name>", priority = CommandPriority.LOW)
	public void onRoot(
			@SenderField Player player,
			@SingleAttribute(name = "name", required = false) String name
	                  ) {

		if (name == null) {
			List<KitStorage.Kit> kits = KitStorage.getInstance().getKits();
			int rows = (kits.size() / 7) + 3;
			GUIBuilder builder = new GUIBuilder(rows * 9, "§5§lKits");
			builder.fillInventory();
			int i = 10;

			for (KitStorage.Kit kit : kits) {
				builder.addItem(
						i, kit.getItemStack(1), (player1, itemStack) -> Bukkit.getScheduler()
						                                                    .runTask(
								                                                    SkyPvP.getInstance(),
								                                                    () -> player1.chat(
										                                                    "/kit " + kit.getName())
						                                                            ));
				if (i % 8 == 0) {
					i += 3;
				} else {
					i++;
				}
			}
			builder.animate(player, GUIBuilder.createDefaultAnimationConfiguration(GUIBuilder.Animation.STAR), SkyPvP.getInstance());
		} else {

			KitStorage.Kit kit = KitStorage.getInstance().kits.stream()
			                                                  .filter(kit1 -> kit1.getName().equalsIgnoreCase(name))
			                                                  .findFirst()
			                                                  .orElse(null);
			if (kit == null) {
				Message.KIT_NOTFOUND.send(player);
				return;
			}
			if(!player.hasPermission(kit.getPermission())){
				player.sendMessage(CoreMessage.NO_PERMS.replace("permission", kit.getPermission()).getContent());
				return;
			}

			TimeoutManager.getTimeout(player.getUniqueId(), "KIT:" + kit.getName()).submit(timeout -> {
				if (timeout != null && timeout > System.currentTimeMillis() && ! player.hasPermission("kit.timeout.bypass")) {
					Message.KIT_GIVING_TIMEOUT.replace("date", TimeUtils.unixToRemaining(timeout)).send(player);
					return;
				}
				TimeoutManager.setTimeout(player.getUniqueId(), "KIT:" + kit.getName(), kit.getTimeout());
				player.closeInventory();
				player.getInventory().addItem(kit.getItems());
			});
		}


	}


	@SubCommand(name = "setup <name> <skullowner> <timeoutinhours> <list> <permission> <displayname>", permission = "skypvp.kit.setup")
	public void onSetup(
			@SenderField Player player,
			@SingleAttribute(name = "name") String name,
			@SingleAttribute(name = "skullowner") String skullowner,
			@SingleAttribute(name = "timeoutinhours") Integer timeout,
			@SingleAttribute(name = "list") String list,
			@SingleAttribute(name = "permission") String permission,
			@MultiAttribute(name = "displayname") String displayname
	                   ) {

		KitStorage.getInstance().kits.add(new KitStorage.Kit(
				name,
				Arrays.stream(player.getInventory().getContents())
				      .filter(Objects::nonNull)
				      .toArray(ItemStack[]::new),
				displayname.replaceAll("&", "§"),
				skullowner,
				timeout,
				Boolean.getBoolean(list),
				permission
		));
		try {
			DataProvider.getInstance().save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Message.KIT_SETUP_SUCCESS.replace("display", displayname.replaceAll("&", "§")).send(player);
	}

	@SubCommand(name = "delete <name>", permission = "skypvp.kit.delete")
	public void onDelete(
			@SenderField Player player,
			@SingleAttribute(name = "name") String name
	                    ) {
		KitStorage.Kit kit = KitStorage.getInstance().kits.stream()
		                                                  .filter(kit1 -> kit1.getName().equalsIgnoreCase(name))
		                                                  .findFirst()
		                                                  .orElse(null);
		if (kit == null) {
			Message.KIT_NOTFOUND.send(player);
			return;
		}
		KitStorage.getInstance().kits.remove(kit);
		try {
			DataProvider.getInstance().save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Message.KIT_DELETE_SUCCESS.send(player);
	}

	@SubCommand(name = "get <name> <amount>", permission = "skypvp.kit.delete")
	public void onGet(
			@SenderField Player player,
			@SingleAttribute(name = "name") String name,
			@SingleAttribute(name = "amount") Integer amount
	                 ) {
		KitStorage.Kit kit = KitStorage.getInstance().kits.stream()
		                                                  .filter(kit1 -> kit1.getName().equalsIgnoreCase(name))
		                                                  .findFirst()
		                                                  .orElse(null);
		if (kit == null) {
			Message.KIT_NOTFOUND.send(player);
			return;
		}
		player.getInventory().addItem(kit.getItemStack(amount));


	}

}
