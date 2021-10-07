package org.librenote.mc.cardinal.communication;

import org.bukkit.plugin.java.JavaPlugin;

public class communication extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Plugin has started");
        getServer().getPluginManager().registerEvents(new events(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin has stopped");
    }
}
