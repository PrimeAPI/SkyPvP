package de.primeapi.primeplugins.skypvp.data;

import de.primeapi.primeplugins.skypvp.SkyPvP;
import de.primeapi.primeplugins.skypvp.data.oop.Storage;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.KitStorage;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.NPCStorage;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.RegionStorage;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.ShopStorage;
import lombok.Getter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Lukas S. PrimeAPI
 * created on 18.08.2022
 * crated for PrimePlugins-ROOT
 */
@Getter
public class DataProvider {
	private static DataProvider instance;
	private final String PATH = "plugins/primeplugins/skypvp/storage.json";
	private Storage storage;

	public DataProvider() throws IOException {
		instance = this;
		loadData();
	}

	public static DataProvider getInstance() {
		return instance;
	}

	/**
	 * Loads all crates stored in the folder
	 */
	public void loadData() throws IOException {
		File file = new File(PATH);
		if (file.createNewFile()) {
			this.storage = new Storage();
			save();
		} else {
			String json = new String(Files.readAllBytes(Paths.get(file.getPath())), StandardCharsets.UTF_8);
			storage = SkyPvP.getInstance().getGson().fromJson(json, Storage.class);
		}
		if (storage.getNpcStorage() == null) storage.setNpcStorage(new NPCStorage());
		if (storage.getRegionStorage() == null) storage.setRegionStorage(new RegionStorage());
		if (storage.getKitStorage() == null) storage.setKitStorage(new KitStorage());
		if (storage.getShopStorage() == null) storage.setShopStorage(new ShopStorage());
	}


	public void save() throws IOException {
		File file = new File(PATH);
		file.createNewFile();
		String json = SkyPvP.getInstance().getGson().toJson(storage);
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
		writer.write(json);
		writer.close();
	}


}
