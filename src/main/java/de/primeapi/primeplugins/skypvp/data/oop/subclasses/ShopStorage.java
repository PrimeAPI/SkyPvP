package de.primeapi.primeplugins.skypvp.data.oop.subclasses;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lukas S. PrimeAPI
 * created on 23.08.2022
 * crated for SkyPvP
 */
@Getter
public class ShopStorage {

	private static ShopStorage instance;

	List<Category> categories = new ArrayList<>();

	public ShopStorage() {
		instance = this;
	}

	public static ShopStorage getInstance() {
		return instance;
	}

	//Method to add Category to List
	public void addCategory(Category category) {
		categories.add(category);
	}


	@Data @RequiredArgsConstructor
	public static class Category{
		final String name;
		final String displayname;
		final ItemStack displayItem;
		List<Item> items = new ArrayList<>();
	}

	@Data @RequiredArgsConstructor
	public static class Item{
		final ItemStack itemStack;
		final int price;
	}


}
