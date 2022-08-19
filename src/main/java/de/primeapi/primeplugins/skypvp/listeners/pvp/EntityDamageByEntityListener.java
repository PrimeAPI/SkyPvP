package de.primeapi.primeplugins.skypvp.listeners.pvp;

import de.primeapi.primeplugins.skypvp.data.oop.subclasses.RegionStorage;
import de.primeapi.primeplugins.skypvp.managers.CombatManager;
import de.primeapi.primeplugins.skypvp.managers.StateManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @author Lukas S. PrimeAPI
 * created on 19.08.2022
 * crated for SkyPvP
 */
public class EntityDamageByEntityListener implements Listener {

	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent event){
		if(event.getEntity() instanceof Player){
			CombatManager.registerHit((Player) event.getEntity());
		}
		if(event.getDamager() instanceof Player){
			CombatManager.registerHit((Player) event.getDamager());
		}
		Location location = RegionStorage.getInstance()
		                                 .getPvp()
		                                 .stream()
		                                 .filter(location1 -> location1.getWorld()
		                                                               .equals(event.getEntity()
		                                                                            .getLocation()
		                                                                            .getWorld()))
		                                 .findFirst()
		                                 .orElse(null);
		Player damager = null;
		if(event.getDamager() instanceof Player){
			damager = (Player) event.getDamager();
		}
		boolean v;
		if(location == null ){
			v = true;
		}else {
			v = event.getEntity().getLocation().getY() > location.getY();
		}
		if(RegionStorage.getInstance().isPlot(event.getEntity().getLocation())) v = true;
		if(damager != null && StateManager.isBuildMode(damager)){
			v = false;
		}

		event.setCancelled(v);

	}

}
