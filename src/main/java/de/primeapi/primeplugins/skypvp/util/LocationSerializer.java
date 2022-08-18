package de.primeapi.primeplugins.skypvp.util;

import com.google.gson.*;
import org.bukkit.*;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Lukas S. PrimeAPI
 * created on 16.08.2022
 * crated for PrimePlugins-ROOT
 */
public class LocationSerializer implements JsonSerializer<Location>, JsonDeserializer<Location> {
	private static final String[] BYPASS_CLASS = {"CraftMetaBlockState", "CraftMetaItem"
			/*Glowstone Support*/ ,"GlowMetaItem"};
	@Override
	public Location deserialize(JsonElement element, Type typee, JsonDeserializationContext context) throws JsonParseException {

		JsonObject object = element.getAsJsonObject();

		World world = Bukkit.getWorld(object.get("world").getAsString());
		double x = object.get("x").getAsDouble();
		double y = object.get("y").getAsDouble();
		double z = object.get("z").getAsDouble();
		float yaw = object.get("yaw").getAsFloat();
		float pitch = object.get("pitch").getAsFloat();

		return new Location(world, x,y,z,yaw,pitch);
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
