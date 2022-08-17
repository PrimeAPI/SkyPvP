package de.primeapi.primeplugins.skypvp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.primeapi.primeplugins.skypvp.commands.MainCommand;
import de.primeapi.primeplugins.skypvp.util.ItemStackSerializer;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.CommandHandler;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
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
		System.out.println("MainCommand.class.isAnnotationPresent(Command.class) = " + MainCommand.class.isAnnotationPresent(Command.class));
		System.out.println("getClasses(MainCommand.class.getPackage().getName()).length = " + getClasses(MainCommand.class.getPackage().getName()).length);

		try {
			Reflections reflections = new Reflections(MainCommand.class.getPackage().getName());
			System.out.println("reflections.getAllTypes().size() = " + reflections.getAllTypes().size());
			reflections.getAllTypes().forEach(s -> {
				System.out.println("s = " + s);
			});
			System.out.println("reflections = " + reflections.getTypesAnnotatedWith(Command.class).size());
			CommandHandler.loadCommands(this, MainCommand.class.getPackage().getName());
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}


	}

	/**
	 * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
	 *
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static Class[] getClasses(String packageName)
			throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}
}
