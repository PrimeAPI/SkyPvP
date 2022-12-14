package de.primeapi.primeplugins.skypvp.messages;

import de.primeapi.primeplugins.spigotapi.api.PrimePlayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;
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
	PVP_DEATH_KILLER("§7 Du hast §e%name%§a getötet§7! §8[§6+%coins% Coins§8]", true),

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

	KIT_SETUP_SUCCESS("§7 Du hast erfolgreich das Kit §e%display% §7erstellt!", true),
	KIT_NOTFOUND("§7 Dieses Kit existiert nicht!", true),
	KIT_DELETE_SUCCESS("§7 Kit erfolreich gelöscht!", true),
	KIT_GIVING_TIMEOUT("§7 Du kannst dieses Kit erst in §e%date% §ewieder erhalten!", true),

	VANISH_SELF_ON("§7 Du bist nun §aunsichtbar§7!", true),
	VANISH_SELF_OFF("§7 Du bist nun nicht mehr §cunsichtbar§7!", true),
	VANISH_OTHER_ON("§7 §e%name% ist nun §aunsichtbar§7!", true),
	VANISH_OTHER_OFF("§7 §e%name% ist nun nicht mehr §cunsichtbar§7!", true),
	VANISH_LIST_TITLE("§7 Unsichtbare Spieler: ", true),
	VANISH_LIST_ENTRY("§7 - §e%name%", true),
	HEAD_TIMEOUT("§7 Du kannst dieses Command erst in §e%date% §7wieder benutzen!", true),
	HEAD_SUCCESS("§7 Du hast den Kopf von §e%name% erhalten!", true),

	GIVE_ITEM_SUCCESS("§7 Du hast §e%item% §7erfolgreich gegeben!", true),
	GIVE_ITEM_SELECT("§7 Klicke auf ein Item in deinem Inventar", true),
	GIVE_ITEM_PLAYER("§7 Alle haben ein §eItem §aerhalten§7!", true),


	SHOP_ADD_SELECT_ITEM("§7 Klicke auf ein Item in deinem Inventar", true),
	SHOP_ADD_CATEGORY_SUCCESS("§7 Kategorie erfolgreich hinzugefügt!", true),
	SHOP_ADD_ITEM_SUCCESS("§7 Item erfolgreich hinzugefügt!", true),
	SHOP_ADD_ITEM_FAIL("§c Kategorie nicht gefunden!", true),
	SHOP_BUY_MONEY("§c Du hast nicht genügend Coins!", true),

	RESTART_BROADCAST("§c§lRESTART §8● §cSkyPvP wird in §e%sec% §cSekunden neugestartet!", false),
	RESTART_ABORT("§c§lRESTART §8● §7Der Restart wurde §aabgebrochen§7!", false),
	RESTART_KICK("§7SkyPvP startet neu...", false),

	PERK_ADMIN_PLAYER_NOT_FOUND("§c Der angegebene Spieler wurde nicht gefunden!", true),
	PERK_ADMIN_PERK_NOT_FOUND("§c Das angegebene Perk wurde nicht gefunden!", true),
	PERK_ADMIN_ADD_SUCCESS("§7 Perk erfolgreich hinzugefügt!", true),
	PERK_ADMIN_GET_MESSAGE("§7 Status des Perks §e%perk%§7: §6%val%", true),


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
	public void send(CommandSender sender) {
		sender.sendMessage(getContent());
	}

	public void send(PrimePlayer primePlayer) {
		send(primePlayer.thePlayer());
	}
}
