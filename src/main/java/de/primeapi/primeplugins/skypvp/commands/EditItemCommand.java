package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.MultiAttribute;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SenderField;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Command(name = "edititem")
public class EditItemCommand {
    @SubCommand(name = "repair", permission = "skypvp.edititem.repair")
    public void repairCommand(@SenderField Player player) {
        ItemStack item = player.getItemInHand();
        if (item == null || item.getType() == Material.AIR) {
            Message.EditItem_ItemInHand.send(player);
            return;
        }
        item.setDurability((short) 0);
    }

    @SubCommand(name = "setname <name>", permission = "skypvp.edititem.setname")
    public void setNameCommand(@SenderField Player player, @MultiAttribute(name = "name", required = true) String name) {
        ItemStack item = player.getItemInHand();
        if (item == null || item.getType() == Material.AIR) {
            Message.EditItem_ItemInHand.send(player);
            return;
        }
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name.replace("&", "§"));
        item.setItemMeta(meta);

        Message.EditItem_SetItemName.send(player);
    }

    @SubCommand(name = "lore add <text>", permission = "skypvp.edititem.setlore")
    public void addLore(@SenderField Player player, @MultiAttribute(name = "text", required = true) String text) {
        ItemStack item = player.getItemInHand();
        if (item == null || item.getType() == Material.AIR) {
            Message.EditItem_ItemInHand.send(player);
            return;
        }
        ItemMeta meta = item.getItemMeta();
        List<String> list = meta.getLore();
        list.add(text.replace("&", "§"));
        meta.setLore(list);
        item.setItemMeta(meta);
        player.sendMessage("123 It works!");
    }

//    List<String> list = meta.getLore();
//                list.add(name.replace("&", "§"));
//        meta.setLore(list);
    //Entfernen meta.setLore(new ArrayList<>());

}
