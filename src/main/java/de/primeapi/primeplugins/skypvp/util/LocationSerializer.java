package de.primeapi.primeplugins.skypvp.util;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.lang.reflect.Type;

/**
 * @author Lukas S. PrimeAPI
 * created on 16.08.2022
 * crated for PrimePlugins-ROOT
 */
public class LocationSerializer implements JsonSerializer<Location>, JsonDeserializer<Location> {
	private static final String[] BYPASS_CLASS = {"CraftMetaBlockState", "CraftMetaItem"
			/*Glowstone Support*/, "GlowMetaItem"};

	@Override
	public Location deserialize(JsonElement element, Type typee, JsonDeserializationContext context) throws
			JsonParseException {

		JsonObject object = element.getAsJsonObject();

		World world = Bukkit.getWorld(object.get("world").getAsString());
		double x = object.get("x").getAsDouble();
		double y = object.get("y").getAsDouble();
		double z = object.get("z").getAsDouble();
		float yaw = object.get("yaw").getAsFloat();
		float pitch = object.get("pitch").getAsFloat();

		return new Location(world, x, y, z, yaw, pitch);
	}

	@Override
	public JsonElement serialize(Location location, Type type, JsonSerializationContext context) {
		JsonObject object = new JsonObject();

		object.addProperty("world", location.getWorld().getName());
		object.addProperty("x", location.getX());
		object.addProperty("y", location.getY());
		object.addProperty("z", location.getZ());
		object.addProperty("yaw", location.getYaw());
		object.addProperty("pitch", location.getPitch());

		return new Gson().toJsonTree(object);


	}
}
