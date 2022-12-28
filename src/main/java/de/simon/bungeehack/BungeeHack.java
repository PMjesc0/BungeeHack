package de.simon.bungeehack;

import de.simon.bungeehack.command.connectCommand;
import de.simon.bungeehack.command.getUUIDCommand;
import de.simon.bungeehack.command.setUUIDCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class BungeeHack extends Plugin {

    @Override
    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new connectCommand("connect"));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new getUUIDCommand("getuuid"));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new setUUIDCommand("setuuid"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
