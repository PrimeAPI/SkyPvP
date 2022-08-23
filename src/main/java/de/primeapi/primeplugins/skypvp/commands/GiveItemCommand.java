package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.SkyPvP;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.skypvp.util.ItemStackSelector;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SenderField;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import de.primeapi.primeplugins.spigotapi.gui.GUIBuilder;
import de.primeapi.primeplugins.spigotapi.gui.itembuilder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 21.08.2022
 * crated for SkyPvP
 */
@Command(name = "giveitem", permission = "skypvp.giveitem")
public class GiveItemCommand {

	@SubCommand(name = "")
	public void onCommand(@SenderField Player player) {
		Message.GIVE_ITEM_SELECT.send(player);
		new ItemStackSelector(player, itemStack -> {
			player.openInventory(
					new GUIBuilder(3 * 9)
							.fillInventory()
							.addItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE, (byte) 14).setDisplayName(
									"§cAbbrechen").build(), (player1, itemStack1) -> {
								player1.closeInventory();
							})
							.addItem(8 + 9, new ItemBuilder(Material.STAINED_GLASS_PANE, (byte) 4).setDisplayName(
									"§aItem an alle geben").build(), (player1, itemStack1) -> {
								for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
									onlinePlayer.getInventory().addItem(itemStack);
									Message.GIVE_ITEM_PLAYER.send(onlinePlayer);
								}
								player1.closeInventory();
								Message.GIVE_ITEM_SUCCESS.replace("item", itemStack.getType().toString()).send(player);
							})
							.addItem(4 + 9, itemStack)
							.build(SkyPvP.getInstance())
			                    );
		});
	}

}
