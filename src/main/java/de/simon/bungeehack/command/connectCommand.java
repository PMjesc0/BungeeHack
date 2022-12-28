package de.simon.bungeehack.command;

import de.simon.bungeehack.util.Data;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ThreadLocalRandom;

public class connectCommand extends Command {


    public connectCommand(String name) {
        super(name);
    }


    @Override
    public void execute(CommandSender sender, String[] args) {

        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(Data.ONLY_INGAME);
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (args.length == 1) {
            String localBungeeServerName = args[0];

            ServerInfo newUServer = ProxyServer.getInstance().constructServerInfo(localBungeeServerName, InetSocketAddress.createUnresolved(getIP(args[0]), getPort(args[0])), "Example MOTD", false);
            ProxyServer.getInstance().getServers().put(localBungeeServerName, newUServer);

            player.connect(newUServer);
        } else {
            sender.sendMessage(Data.USAGE + "/connect [SERVER-IP:PORT]");
        }
    }

    String getIP(String serverIPwithPort) {
        String[] parts = serverIPwithPort.split(":");
        String serverIP = parts[0];
        return serverIP;
    }

    Integer getPort(String serverIPwithPort) {
        String[] parts = serverIPwithPort.split(":");
        Integer serverPort = Integer.valueOf(parts[1]);
        return serverPort;
    }
}
