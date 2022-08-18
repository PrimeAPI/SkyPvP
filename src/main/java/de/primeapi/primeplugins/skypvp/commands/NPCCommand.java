package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.SkyPvP;
import de.primeapi.primeplugins.skypvp.data.DataProvider;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.NPCStorage;
import de.primeapi.primeplugins.skypvp.managers.NPCManager;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.*;
import de.primeapi.primeplugins.spigotapi.enums.PlayerSetting;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Lukas S. PrimeAPI
 * created on 18.08.2022
 * crated for SkyPvP
 */
@Command(name = "npc", permission = "skypvp.npc")
public class NPCCommand {

	@SubCommand(name = "create <name> <displayname> <skin> <command>")
	public void spawn(
			@SenderField Player player,
			@SingleAttribute(name = "name") String name,
			@SingleAttribute(name = "displayname") String displayname,
			@SingleAttribute(name = "skin") String skin,
			@MultiAttribute(name = "command") String command
	                 ) {

		UUID uuid = Bukkit.getOfflinePlayer(skin).getUniqueId();

		NPCStorage.NPCEntry entry = new NPCStorage.NPCEntry(
				uuid, name, displayname.replace('&', '§'), player.getLocation(), false, true, command);

		SkyPvP.getInstance().getNpcManager().spawnNPC(entry);
		NPCStorage.getInstance().addNPC(entry);
		try {
			DataProvider.getInstance().save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@SubCommand(name = "delete")
	public void spawn(
			@SenderField Player player
	                 ) {

		NPCManager.deleter.add(player.getUniqueId());

		Message.NPC_DELETE.send(player);
	}
}
