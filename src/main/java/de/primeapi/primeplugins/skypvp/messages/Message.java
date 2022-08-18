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

	GAMEMODE_TOGGLE_OTHER("§7 Du hast §e%player% §7in den Gamemode §e%gamemode% §7gesetzt!", true),
	GAMEMODE_INVALID_INPUT("§7 Du musst einen Gamemode zwischen 0 und 3 angeben!", true),
	GAMEMODE_SET("§7 Du wurdest in den Gamemode §e%gamemode% §7gesetzt!", true),

	WORKBENCH_OPEN("§7 Du hast eine §eWerkbank §7geöffnet", true),

	TELEPORT_TP("§7 Du hast dich zu §e%spieler% §7teleportiert!", true),
	TELEPORT_PP("§7 Der §eAdministrator: %staff% §7hat dich zu §e%tplayer% §7gebracht!", true),
	TELEPORT_TT("§7 Der Spieler: §e%tpplayer% §7 wurde von §e%staff% §7 zu dir gebracht!", true),

	TPHERE_DONE("§e%staff% §7hat dich zu sich teleportiert!", true),

	HEAL_PLAYER("§7 Du hast §edich §7erfolgreich geheilt!", true),
	HEAL_OPLAYER("§7 Du hast §e%player% §7erfolreich geheilt", true),
	HEAL_ME("§e%staff% §7hat dich geheilt!", true),

	FEED_OPLAYER("§7 Du hast den Hunger §evon %player%§7 gestillt!", true),
	FEED_ME("§7 Dein Hunger wurde von: %staff% gestillt!", true),
	FEED_PLAYER("§7 Du hast §edeinen§7 Hunger gestillt!", true),

	EditItem_ItemInHand("§7 Du musst ein Item in der Hand halten!", true),

	EditItem_SetItemName("§7 Du hast dein Item erfolgreich umbenannt!", true),

	EnderChest_Open("§7 Du hast deine Enderchest geöffnet!", true),
	EnderChest_Other("§7 Du hast die Enderchest von %player% geöffnet!", true),
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
