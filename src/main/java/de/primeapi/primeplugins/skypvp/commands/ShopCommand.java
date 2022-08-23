package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.SkyPvP;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.ShopStorage;
import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.skypvp.util.ItemStackSelector;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.*;
import de.primeapi.primeplugins.spigotapi.api.plugins.coins.CoinsAPI;
import de.primeapi.primeplugins.spigotapi.gui.GUIBuilder;
import de.primeapi.primeplugins.spigotapi.gui.itembuilder.ItemBuilder;
import de.primeapi.primeplugins.spigotapi.gui.itembuilder.SkullTexture;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @author Lukas S. PrimeAPI
 * created on 23.08.2022
 * crated for SkyPvP
 */
@Command(name = "shop")
public class ShopCommand {


	@SubCommand(name = "open")
	public void openShop(@SenderField Player player) {
		List<ShopStorage.Category> categories = ShopStorage.getInstance().getCategories();
		int rows = (categories.size() / 7) + 3;
		GUIBuilder builder = new GUIBuilder(rows * 9, "§b§lShop");
		builder.fillInventory();
		int i = 10;

		for (ShopStorage.Category category : categories) {
			builder.addItem(
					i, category.getDisplayItem(), (player1, itemStack) -> openCategory(player, category));
			if (i % 8 == 0) {
				i += 3;
			} else {
				i++;
			}
		}
		player.openInventory(builder.build(SkyPvP.getInstance()));
	}

	private void openCategory(Player player, ShopStorage.Category category) {
		List<ShopStorage.Item> items = category.getItems();
		int rows = (items.size() / 7) + 3;
		GUIBuilder builder = new GUIBuilder(rows * 9, category.getDisplayname());
		builder.fillInventory();
		int i = 10;

		for (ShopStorage.Item item : items) {
			builder.addItem(
					i, item.getItemStack(), (player1, itemStack) -> openItem(player, item, category));
			if (i % 8 == 0) {
				i += 3;
			} else {
				i++;
			}
		}
		builder.addItem(
				(rows * 9) - 1, new ItemBuilder(Material.SKULL_ITEM, (byte) 3).setSkullTexture(
						SkullTexture.OAK_ARROW_LEFT).setDisplayName("§c&lZurück").build(),
				(player1, itemStack) -> openShop(player)
		               );
		player.openInventory(builder.build(SkyPvP.getInstance()));
	}

	private void openItem(Player player, ShopStorage.Item item, ShopStorage.Category category) {
		player.openInventory(
				new GUIBuilder(3 * 9, "§b&l" + item.getItemStack().getType())
						.fillInventory()
						.addItem(11, new ItemBuilder(item.getItemStack().getType(),
						                             (byte) item.getItemStack().getDurability()
						         ).setAmount(1)
						          .setDisplayName("§7Kaufe §e1x")
						          .addLore("§7Preis: §e" + (item.getPrice() * 1))
						          .build(),
						         (player1, itemStack) -> buyItem(player, item, 1)
						        )
						.addItem(12, new ItemBuilder(item.getItemStack().getType(),
						                             (byte) item.getItemStack().getDurability()
						         ).setAmount(32)
						          .setDisplayName("§7Kaufe §e32x")
						          .addLore("§7Preis: §e" + (item.getPrice() * 32))
						          .build(),
						         (player1, itemStack) -> buyItem(player, item, 32)
						        )
						.addItem(13, new ItemBuilder(item.getItemStack().getType(),
						                             (byte) item.getItemStack().getDurability()
						         ).setAmount(64)
						          .setDisplayName("§7Kaufe §e64x")
						          .addLore("§7Preis: §e" + (item.getPrice() * 64))
						          .build(),
						         (player1, itemStack) -> buyItem(player, item, 64)
						        )
						.addItem(14, new ItemBuilder(item.getItemStack().getType(),
						                             (byte) item.getItemStack().getDurability()
						         ).setAmount(64)
						          .setDisplayName("§7Kaufe §e512x")
						          .addLore("§7Preis: §e" + (item.getPrice() * 512))
						          .build(),
						         (player1, itemStack) -> buyItem(player, item, 512)
						        )
						.addItem(15, new ItemBuilder(item.getItemStack().getType(),
						                             (byte) item.getItemStack().getDurability()
						         ).setAmount(64)
						          .setDisplayName("§7Kaufe §e1024x")
						          .addLore("§7Preis: §e" + (item.getPrice() * 1024))
						          .build(),
						         (player1, itemStack) -> buyItem(player, item, 1024)
						        )
						.addItem(
								(3 * 9) - 1, new ItemBuilder(Material.SKULL_ITEM, (byte) 3).setSkullTexture(
										SkullTexture.OAK_ARROW_LEFT).setDisplayName("§c&lZurück").build(),
								(player1, itemStack) -> openCategory(player, category)
						        ).build(SkyPvP.getInstance())
		                    )
		;

	}

	private void buyItem(Player player, ShopStorage.Item item, int amount) {
		CoinsAPI.getInstance().getCoins(player.getUniqueId()).submit(coins -> {
			int price = item.getPrice() * amount;
			if (price > coins) {
				Message.SHOP_BUY_MONEY.send(player);
				player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
				return;
			}
			CoinsAPI.getInstance().addCoins(player.getUniqueId(), -price);
			ItemStack itemStack = item.getItemStack().clone();
			itemStack.setAmount(amount);
			player.getInventory().addItem(itemStack);
		});
	}


	@SubCommand(name = "add category <name> <displayname>", permission = "skypvp.shop.add.category")
	public void addCategory(
			@SenderField Player player,
			@SingleAttribute(name = "name") String name,
			@MultiAttribute(name = "displayname") String displayname
	                       ) {
		Message.SHOP_ADD_SELECT_ITEM.send(player);
		new ItemStackSelector(player, itemStack -> {
			ShopStorage.getInstance()
			           .addCategory(new ShopStorage.Category(name, displayname.replace("&", "§"), itemStack));
			Message.SHOP_ADD_CATEGORY_SUCCESS.send(player);
		});
	}

	@SubCommand(name = "add item <category> <price>", permission = "skypvp.shop.add.item")
	public void addItem(
			@SenderField Player player,
			@SingleAttribute(name = "category") String category,
			@SingleAttribute(name = "price") int price
	                   ) {

		Message.SHOP_ADD_SELECT_ITEM.send(player);
		new ItemStackSelector(player, itemStack -> {
			ShopStorage.getInstance()
			           .getCategories()
			           .stream()
			           .filter(category1 -> category1.getName().equalsIgnoreCase(category))
			           .findFirst()
			           .ifPresent(category1 -> {
				           category1.getItems()
				                    .add(new ShopStorage.Item(itemStack, price));
				           Message.SHOP_ADD_ITEM_SUCCESS.send(player);
			           });
		});
	}

}
