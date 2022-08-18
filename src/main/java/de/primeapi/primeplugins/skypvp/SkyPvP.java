package de.primeapi.primeplugins.skypvp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.primeapi.primeplugins.skypvp.commands.*;
import de.primeapi.primeplugins.skypvp.messages.MessageManager;
import de.primeapi.primeplugins.skypvp.util.ItemStackSerializer;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.CommandHandler;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
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
				.create();
		File ord = new File("plugins/primeplugins/skypvp");
		if (ord.exists()) ord.mkdir();
		messageManager = new MessageManager();


		CommandHandler.registerClass(this, MainCommand.class, FlyCommand.class, WorkbenchCommand.class, GamemodeCommand.class, TeleportCommand.class, TPHereCommand.class, HealCommand.class, FeedCommand.class);


	}

	@Override
	public void onDisable() {
		super.onDisable();
	}
}
