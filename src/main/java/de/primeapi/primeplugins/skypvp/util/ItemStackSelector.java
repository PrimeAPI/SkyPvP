package de.primeapi.primeplugins.skypvp.util;
import de.primeapi.primeplugins.skypvp.SkyPvP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

/**
 * @author Lukas S. PrimeAPI
 * created on 16.08.2022
 * crated for PrimePlugins-ROOT
 */
public class ItemStackSelector {
	private Listener listener;

	public ItemStackSelector(Player player, SelectionHandler handler){
		this.listener = new Listener() {
			@EventHandler
			public void onInventoryClick(InventoryClickEvent e) {
				if(Objects.isNull(e.getCurrentItem())) return;
				e.setCancelled(true);
				handler.selected(e.getCurrentItem());
				HandlerList.unregisterAll(listener);
			}
		};

		Bukkit.getPluginManager().registerEvents(listener, SkyPvP.getInstance());
	}

	public interface SelectionHandler {
		void selected(ItemStack itemStack);
	}

}
