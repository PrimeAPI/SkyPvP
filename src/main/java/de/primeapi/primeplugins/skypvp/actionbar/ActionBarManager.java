package de.primeapi.primeplugins.skypvp.actionbar;

import de.primeapi.primeplugins.skypvp.actionbar.states.Area;
import de.primeapi.primeplugins.skypvp.actionbar.states.BuildState;
import de.primeapi.primeplugins.skypvp.actionbar.states.Combat;
import de.primeapi.primeplugins.skypvp.actionbar.states.VanishState;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lukas S. PrimeAPI
 * created on 21.08.2022
 * crated for SkyPvP
 */
public class ActionBarManager {

	private static List<ActionBarState> actionBarStates;

	static {
		actionBarStates = new ArrayList<>();
		actionBarStates.add(new Combat());
		actionBarStates.add(new Area());
		actionBarStates.add(new BuildState());
		actionBarStates.add(new VanishState());
	}


	public static void loop() {
		for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
			String message = actionBarStates.stream()
			                                       .filter(actionBarState -> actionBarState.display(onlinePlayer))
			                                       .map(actionBarState -> actionBarState.getMessage(onlinePlayer))
			                                       .collect(Collectors.joining(" §8| "));
			sendActionBar(onlinePlayer, message);
		}
	}

	private static void sendActionBar(Player player, String message) {
		IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
		PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(ppoc);
	}

}
