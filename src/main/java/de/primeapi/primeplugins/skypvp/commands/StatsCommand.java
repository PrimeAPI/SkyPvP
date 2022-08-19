package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.SkyPvP;
import de.primeapi.primeplugins.skypvp.sql.stats.Stats;
import de.primeapi.primeplugins.spigotapi.api.PrimePlayer;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SenderField;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SingleAttribute;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import de.primeapi.primeplugins.spigotapi.gui.AnvilGUI;
import de.primeapi.primeplugins.spigotapi.gui.GUIBuilder;
import de.primeapi.primeplugins.spigotapi.gui.itembuilder.ItemBuilder;
import de.primeapi.primeplugins.spigotapi.sql.SQLPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import static de.primeapi.primeplugins.skypvp.messages.Message.*;
import static de.primeapi.primeplugins.skypvp.sql.stats.StatsAdapter.*;

import java.util.concurrent.TimeUnit;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
@Command(name = "stats")
public class StatsCommand {

	@SubCommand(name = "<player>")
	public void onCommand(@SenderField PrimePlayer player, @SingleAttribute(name = "player", required = false) String name){
		if(name == null){
			openStatsPlayer(player.thePlayer(), player, Time.ALLTIME);
		}else {
			SQLPlayer.loadPlayerByName(name).submit(sqlPlayer -> {
				if(sqlPlayer == null){
					STATS_ERROR_NOTFOUND.send(player);
					player.thePlayer().playSound(player.thePlayer().getLocation(), Sound.NOTE_BASS, 1, 1);
				}else {
					openStatsPlayer(player.thePlayer(), sqlPlayer, Time.ALLTIME);
				}
			});
		}
	}


	public void openStatsPlayer(Player p, SQLPlayer target, Time time) {
		target.retrieveUniqueId().submit(uuid -> {
			String title = STATS_GUI_TITLE_BASE
					.replace("type", STATS_GUI_TITLE_TYPE_PLAYER.replace("player",
					                                                     target.retrieveRealName().complete())
					                                            .getContent())
					.replace("time", time.getName())
					.getContent();
			GUIBuilder guiBuilder = new GUIBuilder(5 * 9, title);
			guiBuilder.fillInventory();
			addDefaultItems(p, guiBuilder, target, time, false);

			Stats stats = getStats(uuid, time.toTime()).complete();
			guiBuilder.addItem(
					13,
					new ItemBuilder(Material.SKULL_ITEM, (byte) 3)
							.setSkullOwner(target.retrieveRealName().complete())
							.setDisplayName(String.valueOf(
									STATS_GUI_PLAYER_RANK.replace("rank", getRank(uuid, time.toTime()).complete()).getContent()))
							.build()
			                  );
			guiBuilder.addItem(
					21,
					new ItemBuilder(Material.GOLD_SWORD)
							.setDisplayName(
									String.valueOf(STATS_GUI_PLAYER_KILL.replace("int", stats.getKills()).getContent()))
							.build()
			                  );
			guiBuilder.addItem(
					22,
					new ItemBuilder(Material.GOLD_CHESTPLATE)
							.setDisplayName(
									String.valueOf(STATS_GUI_PLAYER_KD.replace("int", stats.getKD()).getContent()))
							.build()
			                  );
			guiBuilder.addItem(
					23,
					new ItemBuilder(Material.SKULL_ITEM)
							.setDisplayName(String.valueOf(
									STATS_GUI_PLAYER_DEATH.replace("int", stats.getDeaths()).getContent()))
							.build()
			                  );
			p.openInventory(guiBuilder.build(SkyPvP.getInstance()));
		});
	}

	public void openTopList(Player p, Time time) {
		getTop(StatsEntry.KILL, time.toTime(), 15).submit(list -> {
			String title = STATS_GUI_TITLE_BASE
					.replace("type", STATS_GUI_TITLE_TYPE_TOP.getContent())
					.replace("time", time.getName())
					.getContent();
			GUIBuilder guiBuilder = new GUIBuilder(5 * 9, title);
			guiBuilder.fillInventory();
			addDefaultItems(p, guiBuilder, null, time, true);

			int count = 5;
			int j = 0;
			for (int i = 11; i < 34; i++) {
				if (count == 0) {
					i += 4;
					count = 5;
				}
				if (j >= list.size()) break;
				Stats stats = list.get(j);
				SQLPlayer sqlPlayer = new SQLPlayer(stats.getUuid());

				String name = sqlPlayer.retrieveRealName().complete();
				guiBuilder.addItem(
						i,
						new ItemBuilder(Material.SKULL_ITEM, (byte) 3)
								.setSkullOwner(name)
								.setDisplayName(STATS_LIST_NAME.replace("name", name).getContent())
								.addLore(STATS_LIST_LORE_1.replace("int", stats.getKills()).getContent())
								.addLore(STATS_LIST_LORE_2.replace("int", stats.getKD()).getContent())
								.build(),
						(player, itemStack) -> {
							player.closeInventory();
							openStatsPlayer(p, sqlPlayer, time);
						}
				                  );

				count--;
				j++;
			}


			p.openInventory(guiBuilder.build(SkyPvP.getInstance()));
		});
	}

	private void addDefaultItems(Player p, GUIBuilder builder, SQLPlayer target, Time time, boolean fromList) {
		//Glass-Panes
		{
			for (int i = 0; i < 9; i++) {
				builder.addItem(i,
				                new ItemBuilder(Material.STAINED_GLASS_PANE, (byte) 15).setDisplayName(" ").build());
			}
			builder.addItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE, (byte) 15).setDisplayName(" ").build());
			builder.addItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE, (byte) 15).setDisplayName(" ").build());
			builder.addItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE, (byte) 15).setDisplayName(" ").build());

			builder.addItem(16, new ItemBuilder(Material.STAINED_GLASS_PANE, (byte) 15).setDisplayName(" ").build());
			builder.addItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE, (byte) 15).setDisplayName(" ").build());
			builder.addItem(34, new ItemBuilder(Material.STAINED_GLASS_PANE, (byte) 15).setDisplayName(" ").build());
			for (int i = 36; i < 45; i++) {
				builder.addItem(i,
				                new ItemBuilder(Material.STAINED_GLASS_PANE, (byte) 15).setDisplayName(" ").build());
			}
		}
		//Time
		{
			builder.addItem(
					9,
					new ItemBuilder(Material.BOOK)
							.setDisplayName(STATS_GUI_TIME_ALLTIME.getContent())
							.build(),
					(player, itemStack) -> {
						player.closeInventory();
						if (fromList) {openTopList(player, Time.ALLTIME);} else {
							openStatsPlayer(player, target, Time.ALLTIME);
						}
					}
			               );

			builder.addItem(
					18,
					new ItemBuilder(Material.NETHER_STAR)
							.setDisplayName(STATS_GUI_TIME_MONTHLY.getContent())
							.build(),
					(player, itemStack) -> {
						player.closeInventory();
						if (fromList) {openTopList(player, Time.MONTH);} else {
							openStatsPlayer(player, target, Time.MONTH);
						}
					}
			               );

			builder.addItem(
					27,
					new ItemBuilder(Material.DIAMOND)
							.setDisplayName(STATS_GUI_TIME_WEEKLY.getContent())
							.build(),
					(player, itemStack) -> {
						player.closeInventory();
						if (fromList) {openTopList(player, Time.WEEK);} else openStatsPlayer(player, target, Time.WEEK);
					}
			               );
		}
		//types
		{
			builder.addItem(
					17,
					new ItemBuilder(Material.SKULL_ITEM, (byte) 3)
							.setSkullOwner(p.getName())
							.setDisplayName(STATS_GUI_TYPE_SELF.getContent())
							.build(),
					(player, itemStack) -> {
						player.closeInventory();
						openStatsPlayer(player, PrimePlayer.fromPlayer(player), time);
					}
			               );
			builder.addItem(
					26,
					new ItemBuilder(Material.NAME_TAG)
							.setDisplayName(STATS_GUI_TYPE_SEARCH.getContent())
							.build(),
					(player, itemStack) -> {
						player.closeInventory();
						searchPlacer(p, time);
					}
			               );
			builder.addItem(
					35,
					new ItemBuilder(Material.BOOK_AND_QUILL)
							.setDisplayName(STATS_GUI_TYPE_LIST.getContent())
							.build(),
					(player, itemStack) -> {
						player.closeInventory();
						openTopList(p, time);
					}
			               );
		}

	}

	public void searchPlacer(Player p, Time time) {
		AnvilGUI anvilGUI = new AnvilGUI(p, event -> {
			if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
				event.setWillClose(true);
				event.setWillDestroy(true);
				SQLPlayer sqlPlayer = SQLPlayer.loadPlayerByName(event.getName().replace(" ", "")).complete();
				if (sqlPlayer == null) {
					STATS_ERROR_NOTFOUND.send(p);
					p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
				} else {
					openStatsPlayer(p, sqlPlayer, time);
				}
			} else {
				event.setWillDestroy(false);
				event.setWillClose(false);
			}
		});

		anvilGUI.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT,
		                 new ItemBuilder(Material.NAME_TAG).setDisplayName(" ").build());
		anvilGUI.open();
	}


	public enum Type {
		PLAYER,
		TOPLIST
	}

	@AllArgsConstructor
	@Getter
	public enum Time {
		ALLTIME(STATS_GUI_TITLE_TIME_ALLTIME.getContent()),
		MONTH(STATS_GUI_TITLE_TIME_MONTHLY.getContent()),
		WEEK(STATS_GUI_TITLE_TIME_WEEKLY.getContent());

		String name;

		public long toTime() {
			switch (this) {
				case MONTH:
					return System.currentTimeMillis() - TimeUnit.DAYS.toMillis(30);
				case WEEK:
					return System.currentTimeMillis() - TimeUnit.DAYS.toMillis(7);
				default:
					return -1;
			}
		}
	}

}
