package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.data.oop.subclasses.RegionStorage;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SenderField;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
@Command(name = "region", permission = "skypvp.region")
public class RegionCommand {

	@SubCommand(name = "info")
	public void list(@SenderField Player player){
		if(RegionStorage.getInstance().isPvP(player.getLocation())){
			Message.REGION_COMMAND_INFO_MESSAGE.replace("name", "PvP").send(player);
		}else if(RegionStorage.getInstance().isPlot(player.getLocation())) {
			Message.REGION_COMMAND_INFO_MESSAGE.replace("name", "Plot").send(player);
		}else if(RegionStorage.getInstance().isFarm(player.getLocation())) {
			Message.REGION_COMMAND_INFO_MESSAGE.replace("name", "Farm").send(player);
		}else {
			Message.REGION_COMMAND_INFO_404.send(player);
		}
	}

	@SubCommand(name = "add plot")
	public void addPlot(@SenderField Player player){
		RegionStorage.getInstance().addPlot(player.getLocation());
		Message.REGION_COMMAND_ADD.replace("name", "Plot").send(player);
	}
	@SubCommand(name = "add farm")
	public void addPvP(@SenderField Player player){
		RegionStorage.getInstance().addPvP(player.getLocation());
		Message.REGION_COMMAND_ADD.replace("name", "Farm").send(player);
	}
	@SubCommand(name = "add pvp")
	public void addFarm(@SenderField Player player){
		RegionStorage.getInstance().addFarm(player.getLocation());
		Message.REGION_COMMAND_ADD.replace("name", "PvP").send(player);
	}




}
