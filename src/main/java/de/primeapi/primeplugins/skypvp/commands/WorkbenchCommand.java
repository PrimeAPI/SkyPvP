package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

/**
 * @author Lukas S. PrimeAPI
 * created on 18.08.2022
 * crated for PrimePlugins-ROOT
 */
@Command(name = "workbench", permission = "skypvp.workbench")
public class WorkbenchCommand {



	@SubCommand(name = "")
	public void openWorkbench(@SenderField Player player){
		Message.WORKBENCH_OPEN.send(player);
		player.openWorkbench(null,true);
	}

}
