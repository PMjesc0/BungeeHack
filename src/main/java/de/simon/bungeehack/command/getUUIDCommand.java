package de.simon.bungeehack.command;

import de.simon.bungeehack.util.Data;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.lang.reflect.Field;
import java.util.UUID;

public class getUUIDCommand extends Command {


    public getUUIDCommand(String name) {
        super(name);
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(Data.ONLY_INGAME);
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (args.length == 0) {
            player.sendMessage(Data.PREFIX+"Deine UUID ist folgende§8: §e" + player.getUniqueId());

        } else {
            player.sendMessage(Data.USAGE + "/getUUID [Spieler]");
        }
    }
}
