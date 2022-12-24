package de.simon.bungeehack;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.lang.reflect.Field;
import java.util.UUID;

public class setUUIDCommand extends Command {


    public setUUIDCommand(String name) {
        super(name);
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("§cDu kannst den Befehl nur Ingame verwenden!");
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (args.length == 1) {
            UUID newUUID = UUID.fromString(args[0]);

            PendingConnection pendingConnection = player.getPendingConnection();
            Class<?> initialHandlerClass = pendingConnection.getClass();
            Field uniqueIdField = null;
            try {
                uniqueIdField = initialHandlerClass.getDeclaredField("uniqueId");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            uniqueIdField.setAccessible(true);
            try {
                uniqueIdField.set(pendingConnection, newUUID);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            player.sendMessage("§4Bungee§cHack §8- §aDeine UUID ist nun§8: §e" + newUUID);

        } else {
            player.sendMessage("§cSyntax: §e/setUUID [UUID]");
        }
    }
}
