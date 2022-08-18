package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SenderField;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SingleAttribute;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import org.bukkit.entity.Player;

@Command(name = "heal", permission = "skypvp.heal")
public class HealCommand {
    @SubCommand(name = "<player>")
    public void healPlayer(@SenderField Player primePlayer, @SingleAttribute(name = "player", required = false) Player target) {

        if (target == null) {
            primePlayer.setHealth(20.0);
            primePlayer.setFoodLevel(23);
            Message.HEAL_PLAYER.send(primePlayer);
        } else {
            target.setHealth(20.0);
            target.setFoodLevel(23);
            Message.HEAL_OPLAYER.replace("player", target.getName()).send(primePlayer);
            Message.HEAL_ME.replace("staff", primePlayer.getName()).send(target);
        }
    }
}
