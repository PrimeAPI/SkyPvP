package de.primeapi.primeplugins.skypvp.data;

import com.google.gson.Gson;
import de.primeapi.primeplugins.skypvp.SkyPvP;
import de.primeapi.primeplugins.skypvp.data.oop.Storage;
import lombok.Data;
import lombok.Getter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Lukas S. PrimeAPI
 * created on 18.08.2022
 * crated for PrimePlugins-ROOT
 */
@Getter
public class DataProvider {
	private final String PATH = "plugins/primeplugins/skypvp/storage.json";

	private static DataProvider instance;
	private Storage storage;

	public static DataProvider getInstance() {
		return instance;
	}

	public DataProvider() throws IOException {
		instance = this;
		loadData();
	}


	/**
	 * Loads all crates stored in the folder
	 */
	private void loadData() throws IOException {
		File file = new File(PATH);
		if(file.createNewFile()){
			this.storage = new Storage();
			save();
		}else {
			String json = new String(Files.readAllBytes(Paths.get(file.getPath())), StandardCharsets.UTF_8);
			storage = SkyPvP.getInstance().getGson().fromJson(json, Storage.class);
		}

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
