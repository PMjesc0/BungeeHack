package de.simon.bungeehack.command;

import de.simon.bungeehack.util.Data;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

import java.net.InetSocketAddress;

public class addServerCommand extends Command {


    public addServerCommand(String name) {
        super(name);
    }


    @Override
    public void execute(CommandSender sender, String[] args) {

        if (args.length == 3) {
            String newServerName = args[0];
            String newServerIP = args[1];
            Integer newServerPORT = Integer.valueOf(args[2]);
            ServerInfo info = ProxyServer.getInstance().constructServerInfo(newServerName, InetSocketAddress.createUnresolved(newServerIP, newServerPORT), "Server created using /addserver of (BungeeHACK by SimonDE2107)", false);
            ProxyServer.getInstance().getServers().put(newServerName, info);
            String serverCreatedMSG = Data.PREFIX + "Ein neuer Server wurde erstellt. §e" + newServerName + "§8: §e" + newServerIP + "§8:§e" + newServerPORT;

            if (sender instanceof ProxiedPlayer) {
                ProxiedPlayer player = (ProxiedPlayer) sender;
                TextComponent textComponent = new TextComponent();

                textComponent.setText(serverCreatedMSG);
                textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/server " + newServerName));
                textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Klicke, um Dich mit §e" + newServerName + " §azu verbinden.").create()));
                player.sendMessage(textComponent);
            } else {
                sender.sendMessage(serverCreatedMSG);
            }

        } else {
            sender.sendMessage(Data.USAGE + "/addServer [NAME] [IP] [PORT]");
        }
    }
}
