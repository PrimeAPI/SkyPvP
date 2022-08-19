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


	TELEPORT_TP("§7 Du hast dich zu §e%spieler% §7teleportiert!", true),
	TELEPORT_PP("§7 Du wurdest zu §e%tplayer% §7teleportiert!", true),
	TELEPORT_TT("§7 Der Spieler: §e%tpplayer% §7 wurde von §e%staff% §7 zu dir gebracht!", true),

	NPC_DELETE("§7 Schlage nun den NPC den du entfernen möchtest!", true),

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

	REGION_COMMAND_ADD("§7 Du hast diese Welt erfolgreich als §e%name% Region §7gesetzt!", true),
	REGION_COMMAND_INFO_MESSAGE("§7 Diese Welt ist als §e%name% Region §7gesetzt!", true),
	REGION_COMMAND_INFO_404("§c Diese Welt wurde noch nicht gesetzt!", true),

	BUILD_ON("§7 Du kannst nun §abauen§7!", true),
	BUILD_OFF("§7 Du kannst nun nicht mehr §cbauen§7!", true),

	PVP_DEATH_PLAYER_NOKILLER("§7 Du bist §cgestorben§7!", true),
	PVP_DEATH_PLAYER_KILLER("§7 Du wurdest von §e%name% §7getötet§7!", true),
	PVP_DEATH_KILLER("§7 Du hast §e%name%§a getötet§7! §8[§6+50 Coins§8]", true),

	STATS_ERROR_NOTFOUND("§c Der angegebene Spieler wurde nicht gefunden!", true),


	STATS_GUI_TITLE_BASE("§6§lStats §8| %type% §8| §e%time%", false),
	STATS_GUI_TITLE_TYPE_PLAYER("§e%player%", false),
	STATS_GUI_TITLE_TYPE_TOP("§eBestenlite", false),
	STATS_GUI_TITLE_TIME_ALLTIME("§eAlltime", false),
	STATS_GUI_TITLE_TIME_WEEKLY("§eWöchentlich", false),
	STATS_GUI_TITLE_TIME_MONTHLY("§eMonatlich", false),

	STATS_GUI_TIME_ALLTIME("§eAlltime", false),
	STATS_GUI_TIME_WEEKLY("§eWöchentlich", false),
	STATS_GUI_TIME_MONTHLY("§eMonatlich", false),

	STATS_GUI_TYPE_SELF("§eEigene Statistiken", false),
	STATS_GUI_TYPE_SEARCH("§eSuche Spieler", false),
	STATS_GUI_TYPE_LIST("§eBestenliste", false),

	STATS_LIST_NAME("§e%name%", false),
	STATS_LIST_LORE_1("§7Kills §8» §e%int%", false),
	STATS_LIST_LORE_2("§7K/D §8» §e%int%", false),

	STATS_GUI_PLAYER_RANK("§7Rang §8» §e#%rank%", false),
	STATS_GUI_PLAYER_KILL("§7Kills §8» §e%int%", false),
	STATS_GUI_PLAYER_DEATH("§7Tode §8» §e%int%", false),
	STATS_GUI_PLAYER_BLOCKS("§7Platzierte Blöcke §8» §e%int%", false),
	STATS_GUI_PLAYER_KD("§7K/D §8» §e%int%", false),

	COMBAT_REMAINING("§7 Du bist noch für §e%sec% Sekunden §7im Kampf!", true),


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

	public void send(Player player) {
		player.sendMessage(getContent());
	}

	public void send(PrimePlayer primePlayer) {
		send(primePlayer.thePlayer());
	}
}
