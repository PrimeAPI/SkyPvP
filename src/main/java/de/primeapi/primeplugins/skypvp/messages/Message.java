package de.primeapi.primeplugins.skypvp.messages;

import de.primeapi.primeplugins.spigotapi.api.PrimePlayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

@Getter
public enum Message {

	PREFIX("§dSkyPvP §8●", false),

	FLY_TOGGLE_ON("§7 Du kannst nun §afliegen§7!", true),
	FLY_TOGGLE_OFF("§7 Du kannst nun nicht mehr §cfliegen§7!", true),
	FLY_TOGGLE_OTHER("§7 Du hast den §eFlugmodus §7für §6%name% §7geändert!", true),

	WORKBENCH_OPEN("§7 Du hast eine §eWerkbank §7geöffnet", true),


	PLACEHOLDER("DO NOT TOUCH", false);

	String path;
	@Setter
	String content;
	Boolean prefix;

	Message(String content, Boolean prefix) {
		this.content = content;
		this.prefix = prefix;
		this.path = this.toString().replaceAll("_", ".").toLowerCase();
	}

	Message(String path, String content, Boolean prefix) {
		this.content = content;
		this.prefix = prefix;
		this.path = path;
	}


	public Message replace(String key, String value) {
		if (!key.startsWith("%")) {
			key = "%" + key + "%";
		}
		String s = getContent().replaceAll(key, value);
		PLACEHOLDER.setContent(s);
		return PLACEHOLDER;
	}

	public Message replace(String key, Object value) {
		return replace(key, String.valueOf(value));
	}

	public void send(Player player){
		player.sendMessage(getContent());
	}

	public void send(PrimePlayer primePlayer){
		send(primePlayer.thePlayer());
	}
}
