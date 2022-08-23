package de.primeapi.primeplugins.skypvp;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.primeapi.primeplugins.skypvp.actionbar.ActionBarManager;
import de.primeapi.primeplugins.skypvp.api.annotations.GsonIgnore;
import de.primeapi.primeplugins.skypvp.commands.*;
import de.primeapi.primeplugins.skypvp.commands.warp.PlotWorldCommand;
import de.primeapi.primeplugins.skypvp.commands.warp.SpawnCommand;
import de.primeapi.primeplugins.skypvp.commands.warp.WarpCommand;
import de.primeapi.primeplugins.skypvp.data.DataProvider;
import de.primeapi.primeplugins.skypvp.listeners.*;
import de.primeapi.primeplugins.skypvp.listeners.pvp.EntityDamageByEntityListener;
import de.primeapi.primeplugins.skypvp.listeners.pvp.EntityDamageListener;
import de.primeapi.primeplugins.skypvp.listeners.pvp.PlayerDeathListener;
import de.primeapi.primeplugins.skypvp.managers.NPCManager;
import de.primeapi.primeplugins.skypvp.messages.MessageManager;
import de.primeapi.primeplugins.skypvp.util.ItemStackSerializer;
import de.primeapi.primeplugins.skypvp.util.LocationSerializer;
import de.primeapi.primeplugins.skypvp.util.SkyPvPScoreboard;
import de.primeapi.primeplugins.spigotapi.PrimeCore;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.CommandHandler;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * @author Lukas S. PrimeAPI
 * created on 17.08.2022
 * crated for PrimePlugins-ROOT
 */
@Getter
public class SkyPvP extends JavaPlugin {

	private static SkyPvP instance;
	private Gson gson;
	private MessageManager messageManager;
	private DataProvider dataProvider;
	private NPCManager npcManager;

	public static SkyPvP getInstance() {
		return instance;
	}


	@SneakyThrows
	@Override
	public void onEnable() {
		instance = this;

		getLogger().log(Level.INFO, "---------------[ PrimeAPI | SkyPvP ]---------------");
		getLogger().log(Level.INFO, "Plugin: SkyPvP");
		getLogger().log(Level.INFO, "Author: PrimeAPI");
		getLogger().log(Level.INFO, "Version: " + getDescription().getVersion());
		getLogger().log(Level.INFO, "---------------[ PrimeAPI | SkyPvP ]---------------");

		gson = new GsonBuilder()
				.setPrettyPrinting()
				.registerTypeHierarchyAdapter(ItemStack.class, new ItemStackSerializer())
				.registerTypeHierarchyAdapter(Location.class, new LocationSerializer())
				.setExclusionStrategies(new ExclusionStrategy() {
					@Override
					public boolean shouldSkipField(FieldAttributes f) {
						return f.getAnnotation(GsonIgnore.class) != null;
					}

					@Override
					public boolean shouldSkipClass(Class<?> clazz) {
						return false;
					}
				})
				.create();
		File ord = new File("plugins/primeplugins/skypvp");
		if (ord.exists()) ord.mkdir();
		messageManager = new MessageManager();
		dataProvider = new DataProvider();
		npcManager = new NPCManager();


		CommandHandler.registerClass(this, MainCommand.class, FlyCommand.class, WorkbenchCommand.class,
		                             GamemodeCommand.class,
		                             WarpCommand.class, SpawnCommand.class, PlotWorldCommand.class,
		                             NPCCommand.class, TeleportCommand.class, TPHereCommand.class, HealCommand.class,
		                             FeedCommand.class, EditItemCommand.class, EnderchestCommand.class,
		                             RegionCommand.class, BuildCommand.class, StatsCommand.class,
		                             KitCommand.class, VanishCommand.class, HeadCommand.class,
		                             GiveItemCommand.class, StorageCommand.class, ShopCommand.class, RestartCommand.class,
		                             PerkCommand.class
		                            );
		Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new QuitListener(), this);
		Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
		Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), this);
		Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
		Bukkit.getPluginManager().registerEvents(new WorldLoadListener(), this);
		Bukkit.getPluginManager().registerEvents(new WorldChangeListener(), this);

		PrimeCore.getInstance().getDb().update(
				"CREATE TABLE IF NOT EXISTS prime_skypvp_stats (" +
						"`id` INT NOT NULL AUTO_INCREMENT UNIQUE ," +
						"`uuid` VARCHAR(36) NOT NULL," +
						"`type` VARCHAR(16) NOT NULL," +
						"`amount` INT NOT NULL," +
						"`time` BIGINT NOT NULL," +
						"PRIMARY KEY (`id`)" +
						");"
		                                      ).execute();
		PrimeCore.getInstance().getDb().update(
				"CREATE TABLE IF NOT EXISTS prime_skypvp_timeout (" +
						"`id` INT NOT NULL AUTO_INCREMENT UNIQUE ," +
						"`uuid` VARCHAR(36) NOT NULL," +
						"`name` VARCHAR(64) NOT NULL," +
						"`release` BIGINT NOT NULL," +
						"PRIMARY KEY (`id`)" +
						");"
		                                      ).execute();
		PrimeCore.getInstance().getDb().update(
				"CREATE TABLE IF NOT EXISTS prime_skypvp_perks (" +
						"`id` INT NOT NULL AUTO_INCREMENT UNIQUE ," +
						"`uuid` VARCHAR(36) NOT NULL," +
						"`perk` VARCHAR(16) NOT NULL," +
						"`active` INT NOT NULL," +
						"PRIMARY KEY (`id`)" +
						");"
		                                      ).execute();

		PrimeCore.getInstance().getScoreboardManager().defaultSettings = new SkyPvPScoreboard();


		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, ActionBarManager::loop, 20L, 20L);


	}

	@Override
	public void onDisable() {
		try {
			dataProvider.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
