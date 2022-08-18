package de.primeapi.primeplugins.skypvp.commands;

import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SenderField;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SingleAttribute;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import org.bukkit.entity.Player;

@Command(name = "teleport", permission = "skypvp.teleport")
public class TeleportCommand {
    @SubCommand(name = "<player> <tpplayer>")
    public void teleportToPlayer(@SenderField Player primePlayer, @SingleAttribute(name = "player", required = true) Player target, @SingleAttribute(name = "tpplayer", required = false) Player tpplayer) {

        if (tpplayer == null) {
            primePlayer.teleport(target);
            Message.TELEPORT_TP.replace("spieler", primePlayer.getName()).send(primePlayer);
        } else {
            target.teleport(tpplayer);
            Message.TELEPORT_PP.replace("staff", primePlayer.getName()).replace("tplayer", tpplayer.getName()).send(target);
        }
    }
}
