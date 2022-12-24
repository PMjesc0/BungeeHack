package de.simon.bungeehack;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class BungeeHack extends Plugin {

    @Override
    public void onEnable() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new setUUIDCommand("setuuid"));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new getUUIDCommand("getuuid"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
