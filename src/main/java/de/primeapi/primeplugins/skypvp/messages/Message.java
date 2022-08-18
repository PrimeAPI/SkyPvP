package de.primeapi.primeplugins.skypvp.messages;

import de.primeapi.primeplugins.spigotapi.api.PrimePlayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

@Getter
public enum Message {

	PREFIX("§dSkyPvP §8●", false),

	WARP_SETUP_SELECT_ITEM("§7 Clicke auf ein Item in den Inventar!", true),
	WARP_SETUP_SUCCESS("§7 Du hast erfolgreich den Warp '§e%display%§7'§8(§7%name%§8) §7gesetzt!", true),
	WARP_DELETE_STATIC("§c Dieser Warp kann nicht gelöscht werden!", true),
	WARP_404("§c Der Warp wurde nicht gefunden!", true),
	WARP_SUCCESS("§7 Du wurdest zu §e%display% §7teleportiert!", true),

	FLY_TOGGLE_ON("§7 Du kannst nun §afliegen§7!", true),
	FLY_TOGGLE_OFF("§7 Du kannst nun nicht mehr §cfliegen§7!", true),
	FLY_TOGGLE_OTHER("§7 Du hast den §eFlugmodus §7für §6%name% §7geändert!", true),

	GAMEMODE_TOGGLE_OTHER("§7 Du hast §e%player% §7in den Gamemode §e%gamemode% §7gesetzt!", true),
	GAMEMODE_INVALID_INPUT("§7 Du musst einen Gamemode zwischen 0 und 3 angeben!", true),
	GAMEMODE_SET("§7 Du wurdest in den Gamemode §e%gamemode% §7gesetzt!", true),

	WORKBENCH_OPEN("§7 Du hast eine §eWerkbank §7geöffnet", true),

	NPC_DELETE("§7 Schlage nun den NPC den du entfernen möchtest!", true),


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
