package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.managers.TimeoutManager;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.skypvp.util.TimeUtils;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.*;
import de.primeapi.primeplugins.spigotapi.gui.itembuilder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.TimeUnit;

/**
 * @author Lukas S. PrimeAPI
 * created on 21.08.2022
 * crated for SkyPvP
 */
@Command(name = "head", permission = "skypvp.head")
public class HeadCommand {

	@SubCommand(name = "<name>")
	public void onRoot(
			@SenderField Player player,
			@SingleAttribute(name = "name", required = false) String name
			) {
		System.out.println("HeadCommand.1");
		TimeoutManager.getTimeout(player.getUniqueId(), "COMMAND:HEAD").submit(aLong -> {
			try {
				System.out.println("HeadCommand.2");
				if (aLong != null && aLong > System.currentTimeMillis() && ! player.hasPermission("skypvp.head.bypass")) {
					System.out.println("HeadCommand.3");
					Message.HEAD_TIMEOUT.replace("date", TimeUtils.unixToRemaining(aLong)).send(player);
					return;
				}
				System.out.println("HeadCommand.4");
				TimeoutManager.setTimeout(player.getUniqueId(), "COMMAND:HEAD", 24*7);
				ItemStack itemStack = new ItemBuilder(Material.SKULL_ITEM, (byte) 3)
						.setSkullOwner(name)
						.build();
				System.out.println("HeadCommand.5");
				player.getInventory().addItem(itemStack);
				System.out.println("HeadCommand.6");
				Message.HEAD_SUCCESS.replace("name", name).send(player);
				System.out.println("HeadCommand.7");
			}catch (Exception ex){
				ex.printStackTrace();
			}
		});
	}
}
