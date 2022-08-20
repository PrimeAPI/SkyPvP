package de.primeapi.primeplugins.skypvp.listeners;

import de.primeapi.primeplugins.skypvp.data.oop.subclasses.KitStorage;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

/**
 * @author Lukas S. PrimeAPI
 * created on 20.08.2022
 * crated for SkyPvP
 */
public class PlayerInteractListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (Objects.isNull(event.getItem())) return;
		if (Objects.isNull(event.getItem().getItemMeta())) return;
		if (Objects.isNull(event.getItem().getItemMeta().getDisplayName())) return;
		if (Objects.isNull(event.getItem().getItemMeta().getLore())) return;
		if (event.getItem().getItemMeta().getLore().size() < 1) return;
		if (event.getItem().getType() != Material.SKULL_ITEM) return;

		if (event.getItem().getItemMeta().getLore().get(0).equals("§e§lKit")) {
			KitStorage.Kit kit = KitStorage.getInstance().kits.stream()
			                                                  .filter(kit1 -> kit1.getDisplayName()
			                                                                      .equals(event.getItem()
			                                                                                   .getItemMeta()
			                                                                                   .getDisplayName()))
			                                                  .findFirst()
			                                                  .orElse(null);
			if (kit != null) {
				if (event.getItem().getAmount() > 1) {
					event.getItem().setAmount(event.getItem().getAmount() - 1);
				} else {
					event.getPlayer().getInventory().remove(event.getItem());
				}
				event.getPlayer().getInventory().addItem(kit.getItems());
			}
		}
	}

}
