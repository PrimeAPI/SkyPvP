package de.primeapi.primeplugins.skypvp.util;

import de.primeapi.primeplugins.skypvp.managers.CombatManager;
import de.primeapi.primeplugins.skypvp.sql.stats.Stats;
import de.primeapi.primeplugins.skypvp.sql.stats.StatsAdapter;
import de.primeapi.primeplugins.spigotapi.PrimeCore;
import de.primeapi.primeplugins.spigotapi.api.plugins.perms.PermsAPI;
import de.primeapi.primeplugins.spigotapi.managers.scoreboard.objects.ScoreboardSettings;
import de.primeapi.primeplugins.spigotapi.sql.permissions.SQLGroup;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
public class SkyPvPScoreboard implements ScoreboardSettings {
	@Override
	public String getTitle() {
		return "§7» §4§lSkyPvP §7«";
	}

	@Override
	public List<String> apply(Player player) {
		Stats stats = StatsAdapter.getStats(player.getUniqueId(), -1L).complete();

		List<String> list = new ArrayList<>();
		list.add(" §1              ");
		list.add("§7Rang           ");
		list.add("§8➥ §e%rank_display%");
		list.add(" §2            ");
		list.add("§7Kills : K/D");
		list.add("§8➥ §e" + stats.getKills() + " §7: §e" + stats.getKD());
		list.add(" §3        ");
		list.add("§7Coins            ");
		list.add("§8➥ §e%prime_coins%");
		list.add(" §4            ");
		List<String> returnValue = new ArrayList<>();
		for (String s :
				list) {
			returnValue.add(ChatColor.translateAlternateColorCodes('&', PrimeCore.getInstance()
			                                                                     .getPlaceholderAPIManager()
			                                                                     .replace(player, s)));
		}
		return returnValue;
	}
}
