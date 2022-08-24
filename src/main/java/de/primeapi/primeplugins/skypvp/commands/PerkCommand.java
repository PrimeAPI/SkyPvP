package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.SkyPvP;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.skypvp.sql.stats.perk.Perk;
import de.primeapi.primeplugins.skypvp.sql.stats.perk.PerkAdapter;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SenderField;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SingleAttribute;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import de.primeapi.primeplugins.spigotapi.gui.GUIBuilder;
import de.primeapi.primeplugins.spigotapi.gui.itembuilder.ItemBuilder;
import de.primeapi.primeplugins.spigotapi.sql.SQLPlayer;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 23.08.2022
 * crated for SkyPvP
 */
@Command(name = "perk")
public class PerkCommand {

	@SubCommand(name = "gui")
	public void gui(@SenderField Player player) {
		Perk[] perks = Perk.values();
		int rows = (perks.length / 7) + 4;
		GUIBuilder builder = new GUIBuilder(rows * 9, "§e§lPerks").fillInventory();
		int i = 10;

		for (Perk perk : perks) {
			Boolean state = perk.getState(player);
			builder.addItem(
					i, new ItemBuilder(perk.getMaterial()).setDisplayName("§e" + perk.getDisplayName()).build(),
					(player1, itemStack) -> {
						if (state != null) {
							PerkAdapter.updatePerk(player.getUniqueId(), perk, !state);
							gui(player);
							perk.check(player);
							player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
						} else {
							player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
						}
					}
			               );

			if (state == null) {
				builder.addItem(
						i + 9, new ItemBuilder(Material.BARRIER).setDisplayName("§cDu besitzt diese Perk nicht")
						                                        .build(),
						(player1, itemStack) -> player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1)
				               );
			} else if (state) {
				builder.addItem(i + 9,
				                new ItemBuilder(Material.INK_SACK, (byte) 10).setDisplayName("§cDeaktiviere diese Perk")
				                                                            .build(), (player1, itemStack) -> {
							PerkAdapter.updatePerk(player.getUniqueId(), perk, false);
							gui(player);
							perk.check(player);
							player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
						}
				               );
			} else {
				builder.addItem(i + 9,
				                new ItemBuilder(Material.INK_SACK, (byte) 1).setDisplayName("§aAktiviere diese Perk")
				                                                             .build(), (player1, itemStack) -> {
							PerkAdapter.updatePerk(player.getUniqueId(), perk, true);
							gui(player);
							perk.check(player);
							player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
						}
				               );
			}

			if (i % 8 == 0) {
				i += 12;
			} else {
				i++;
			}
		}

		player.openInventory(builder.build(SkyPvP.getInstance()));

	}


	@SubCommand(name = "admin give <player> <perk>", permission = "skypvp.admin.give")
	public void onGive(
			@SenderField CommandSender sender,
			@SingleAttribute(name = "player", required = true) String playerName,
			@SingleAttribute(name = "perk", required = true) String perkName
	                  ) {
		SQLPlayer.loadPlayerByName(playerName).submit(sqlPlayer -> {
			if (sqlPlayer == null) {
				Message.PERK_ADMIN_PLAYER_NOT_FOUND.send(sender);
				return;
			}
			Perk perk;
			try {
				perk = Perk.valueOf(perkName.toUpperCase());
			} catch (Exception ex) {
				Message.PERK_ADMIN_PERK_NOT_FOUND.send(sender);
				return;
			}
			PerkAdapter.addPerkToPlayer(sqlPlayer.retrieveUniqueId().complete(), perk);
			Message.PERK_ADMIN_ADD_SUCCESS.send(sender);
		});

	}

	@SubCommand(name = "admin get <player> <perk>", permission = "skypvp.admin.get")
	public void onGet(
			@SenderField CommandSender sender,
			@SingleAttribute(name = "player", required = true) String playerName,
			@SingleAttribute(name = "perk", required = true) String perkName
	                 ) {
		SQLPlayer.loadPlayerByName(playerName).submit(sqlPlayer -> {
			if (sqlPlayer == null) {
				Message.PERK_ADMIN_PLAYER_NOT_FOUND.send(sender);
				return;
			}
			Perk perk;
			try {
				perk = Perk.valueOf(perkName.toUpperCase());
			} catch (Exception ex) {
				Message.PERK_ADMIN_PERK_NOT_FOUND.send(sender);
				return;
			}
			Boolean b = PerkAdapter.getPerk(sqlPlayer.retrieveUniqueId().complete(), perk).complete();
			Message.PERK_ADMIN_GET_MESSAGE.replace("perk", perk.name()).replace("val", b).send(sender);
		});

	}

}
