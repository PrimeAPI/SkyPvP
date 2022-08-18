package de.primeapi.primeplugins.skypvp.managers;

import com.github.juliarn.npc.NPC;
import com.github.juliarn.npc.NPCPool;
import com.github.juliarn.npc.event.PlayerNPCInteractEvent;
import com.github.juliarn.npc.profile.Profile;
import de.primeapi.primeplugins.skypvp.SkyPvP;
import de.primeapi.primeplugins.skypvp.data.oop.subclasses.NPCStorage;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.*;

/**
 * @author Lukas S. PrimeAPI
 * created on 18.08.2022
 * crated for SkyPvP
 */
public class NPCManager {

	public static List<UUID> deleter = new ArrayList<>();
	NPCPool npcPool;
	HashMap<Integer, String> actions = new HashMap<>();
	List<UUID> cool = new ArrayList<>();

	public NPCManager() {
		npcPool = NPCPool.builder(SkyPvP.getInstance())
		                 .spawnDistance(60)
		                 .actionDistance(30)
		                 .tabListRemoveTicks(20)
		                 .build();

		for (NPCStorage.NPCEntry npc : NPCStorage.getInstance().getNpcs()) {
			spawnNPC(npc);
		}


		Bukkit.getPluginManager().registerEvents(new Listener() {
			@EventHandler
			public void onPlayerNPCInteract(PlayerNPCInteractEvent event) {

				if (deleter.contains(event.getPlayer().getUniqueId())) {
					NPCStorage.getInstance()
					          .removeNPC(NPCStorage.getInstance()
					                               .getNpcs()
					                               .stream()
					                               .filter(entry -> entry.getDisplayName()
					                                                     .equals(event.getNPC().getProfile().getName()))
					                               .findAny()
					                               .get());
					npcPool.removeNPC(event.getNPC().getEntityId());
					deleter.remove(event.getPlayer().getUniqueId());
					return;
				}

				if (cool.contains(event.getPlayer().getUniqueId())) return;
				String command = actions.get(event.getNPC().getEntityId());
				if (command != null) {
					event.getPlayer().chat("/" + command);
				}
				cool.add(event.getPlayer().getUniqueId());
				Bukkit.getScheduler()
				      .runTaskLater(SkyPvP.getInstance(), () -> cool.remove(event.getPlayer().getUniqueId()), 10L);
			}
		}, SkyPvP.getInstance());

	}

	public void spawnNPC(NPCStorage.NPCEntry entry) {
		Profile profile = new Profile(entry.getProfile());
		profile.complete();
		profile.setName(entry.getDisplayName());
		profile.setUniqueId(new UUID(new Random().nextLong(), 0));

		NPC npc = NPC.builder()
		             .profile(profile)
		             .location(entry.getLocation())
		             .imitatePlayer(entry.isImitate())
		             .lookAtPlayer(entry.isLook())
		             .build(npcPool);
		actions.put(npc.getEntityId(), entry.getInteractCommand());
	}


}
