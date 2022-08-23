package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.data.DataProvider;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SenderField;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import org.bukkit.entity.Player;

import java.io.IOException;

/**
 * @author Lukas S. PrimeAPI
 * created on 23.08.2022
 * crated for SkyPvP
 */
@Command(name = "storage", permission = "skypvp.storage")
public class StorageCommand {

	@SubCommand(name = "save")
	public void onSave(@SenderField Player player) {
		try {
			player.sendMessage("§aSpeichern...");
			DataProvider.getInstance().save();
			player.sendMessage("§aSpeichern erfolgreich!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SubCommand(name = "load")
	public void onLoad(@SenderField Player player) {
		try {
			player.sendMessage("§aLaden...");
			DataProvider.getInstance().loadData();
			player.sendMessage("§aLaden erfolgreich!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
