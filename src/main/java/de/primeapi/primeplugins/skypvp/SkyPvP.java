package de.primeapi.primeplugins.skypvp;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.primeapi.primeplugins.skypvp.api.annotations.GsonIgnore;
import de.primeapi.primeplugins.skypvp.commands.FlyCommand;
import de.primeapi.primeplugins.skypvp.commands.GamemodeCommand;
import de.primeapi.primeplugins.skypvp.commands.MainCommand;
import de.primeapi.primeplugins.skypvp.commands.WorkbenchCommand;
import de.primeapi.primeplugins.skypvp.commands.warp.PlotWorldCommand;
import de.primeapi.primeplugins.skypvp.commands.warp.SpawnCommand;
import de.primeapi.primeplugins.skypvp.commands.warp.WarpCommand;
import de.primeapi.primeplugins.skypvp.data.DataProvider;
import de.primeapi.primeplugins.skypvp.messages.MessageManager;
import de.primeapi.primeplugins.skypvp.util.ItemStackSerializer;
import de.primeapi.primeplugins.skypvp.util.LocationSerializer;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.CommandHandler;
import lombok.Getter;
import lombok.SneakyThrows;
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


		CommandHandler.registerClass(this, MainCommand.class, FlyCommand.class, WorkbenchCommand.class, GamemodeCommand.class,
		                             WarpCommand.class, SpawnCommand.class, PlotWorldCommand.class
		                            );


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
