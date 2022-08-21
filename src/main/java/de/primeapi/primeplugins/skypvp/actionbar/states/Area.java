package de.primeapi.primeplugins.skypvp.actionbar.states;

import de.primeapi.primeplugins.skypvp.actionbar.ActionBarState;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.RegionStorage;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author Lukas S. PrimeAPI
 * created on 21.08.2022
 * crated for SkyPvP
 */
public class Area implements ActionBarState {


	@Override
	public boolean display(Player player) {
		return true;
	}

	@Override
	public String getMessage(Player player) {
		if (RegionStorage.getInstance().isPlot(player.getLocation())) {
			return "§eZone§7: §ePlot-Welt";
		} else if (RegionStorage.getInstance().isFarm(player.getLocation())) {
			return "§eZone§7: §eFarm-Welt";
		} else if (RegionStorage.getInstance().isPvP(player.getLocation())) {
			Location location = RegionStorage.getInstance()
			                                 .getPvp()
			                                 .stream()
			                                 .filter(location1 -> location1.getWorld()
			                                                               .equals(player.getLocation().getWorld()))
			                                 .findFirst()
			                                 .orElse(null);
			if(location != null){
				if(location.getY() > player.getLocation().getY()){
					return "§eZone§7: §cPvP";
				}else {
					return "§eZone§7: §aSpawn";
				}
			}
		}
		return "§eZone§7: §cUnbekannt";

	}

}
