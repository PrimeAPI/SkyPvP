package de.primeapi.primeplugins.skypvp.data.oop.subclasses;

import de.primeapi.primeplugins.skypvp.api.annotations.GsonIgnore;
import de.primeapi.primeplugins.skypvp.util.SkyUtils;
import de.primeapi.primeplugins.spigotapi.gui.itembuilder.ItemBuilder;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lukas S. PrimeAPI
 * created on 18.08.2022
 * crated for PrimePlugins-ROOT
 */
@Data
public class KitStorage {
	@GsonIgnore
	private static KitStorage instance;

	public KitStorage() {
		instance = this;
	}

	public static KitStorage getInstance() {
		return instance;
	}

	public List<Kit> kits = new ArrayList<>();



	/**
	 * @author Lukas S. PrimeAPI
	 * created on 18.08.2022
	 * crated for PrimePlugins-ROOT
	 */
	@Data
	public static class Kit {

		final String name;
		final ItemStack[] items;
		final String displayName;
		final String displaySkull;
		final Integer timeout;
		final boolean list;
		final String permission;


		public ItemStack getItemStack(int amount){
			ItemBuilder itemBuilder = new ItemBuilder(Material.SKULL_ITEM, (byte)3).setAmount(amount).setDisplayName(getDisplayName());
			if(SkyUtils.isSkullTexture(getDisplaySkull())){
				itemBuilder.setSkullTexture(getDisplaySkull());
			}else {
				itemBuilder.setSkullOwner(getDisplaySkull());
			}
			itemBuilder.addLore("§e§lKit");
			return itemBuilder.build();
		}

	}
}
