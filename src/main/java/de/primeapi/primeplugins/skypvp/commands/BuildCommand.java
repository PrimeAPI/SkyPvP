package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.managers.StateManager;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SenderField;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
@Command(name = "build", permission = "skypvp.build")
public class BuildCommand {

	@SubCommand(name = "")
	public void toggle(@SenderField Player player){
		if(StateManager.isBuildMode(player)){
			StateManager.setBuildMode(player, false);
			player.setGameMode(GameMode.SURVIVAL);
			Message.BUILD_OFF.send(player);
		}else {
			StateManager.setBuildMode(player, true);
			player.setGameMode(GameMode.CREATIVE);
			Message.BUILD_ON.send(player);
		}
	}

}
