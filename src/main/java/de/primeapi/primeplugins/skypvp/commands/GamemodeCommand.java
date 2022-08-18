package de.primeapi.primeplugins.skypvp.commands;


import de.primeapi.primeplugins.skypvp.messages.Message;
import de.primeapi.primeplugins.spigotapi.api.PrimePlayer;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.Command;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SenderField;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SingleAttribute;
import de.primeapi.primeplugins.spigotapi.api.command.reflections.annotations.SubCommand;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@Command(name = "gamemode", permission = "skypvp.gamemode")
public class GamemodeCommand {
    @SubCommand(name = "<gamemode> <player>")

    public void changeGamemode (@SenderField Player primePlayer, @SingleAttribute(name = "gamemode") Integer gamemode, @SingleAttribute(name = "player", required = false) Player target){
        Player player;
        GameMode gm;

        if (gamemode == 0) {
            gm = GameMode.SURVIVAL;
        } else if (gamemode == 1) {
            gm = GameMode.CREATIVE;
        } else if (gamemode == 2) {
            gm = GameMode.ADVENTURE;
        } else if (gamemode == 3) {
            gm = GameMode.SPECTATOR;
        } else {
            Message.GAMEMODE_INVALID_INPUT.send(primePlayer);
            return;
        }

        if(target == null) player = primePlayer;
        else {
            player = target;
            Message.GAMEMODE_TOGGLE_OTHER.replace("player", player.getName()).replace("gamemode", gm.toString()).send(primePlayer);
        }

        player.setGameMode(gm);
        Message.GAMEMODE_SET.replace("gamemode", gm.toString()).send(player);


    }

}
