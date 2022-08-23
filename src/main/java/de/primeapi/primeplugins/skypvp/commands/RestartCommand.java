package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.SkyPvP;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.*;
import net.minecraft.server.v1_8_R3.CommandSaveAll;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 23.08.2022
 * crated for SkyPvP
 */
@Command(name = "stop", permission = "skypvp.stop")
public class RestartCommand {

	private int countdown;
	private int id;


	@SubCommand(name = "<seconds>", priority = CommandPriority.LOW)
	public void restart(
			@SingleAttribute(name = "seconds", required = false) Integer time
	                   ) {
		if (time == null) {
			time = 0;
		}
		countdown = time;
		Bukkit.broadcastMessage(Message.RESTART_BROADCAST.replace("sec", countdown).getContent());
		id = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyPvP.getInstance(), () -> {
			countdown--;
			if (countdown <= 0) {
				for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
					onlinePlayer.kickPlayer(Message.RESTART_KICK.getContent());
				}
				Bukkit.getServer().shutdown();
			}
			if (
					countdown < 11 ||
							(countdown < 101 && countdown % 10 == 0) ||
							(countdown > 100 && countdown % 30 == 0)
			) {
				Bukkit.broadcastMessage(Message.RESTART_BROADCAST.replace("sec", countdown).getContent());
			}
		}, 20L, 20L);
	}

	@SubCommand(name = "abort")
	public void abort(@SenderField Player player) {
		Bukkit.getScheduler().cancelTask(id);
		Bukkit.broadcastMessage(Message.RESTART_ABORT.getContent());
	}

}
