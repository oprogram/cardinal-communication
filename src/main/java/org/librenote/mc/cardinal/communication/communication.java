package org.librenote.mc.cardinal.communication;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class communication extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        getLogger().info("Plugin has started");
        getServer().getPluginManager().registerEvents(this, this);

        this.saveDefaultConfig();

        String urlConfigOption = config.getString("webhookURL");
        if (urlConfigOption.length() < 5 && urlConfigOption != "<Place URL here>") {
            getLogger().info("You must provide a webhook URL for this plugin to function, shutting down...");
            this.getPluginLoader().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin has stopped");
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!event.isCancelled()) {
            Player player = event.getPlayer();

            try {
                String webhookURL = config.getString("webhookURL");
                DiscordWebhook webhook = new DiscordWebhook(webhookURL);
                webhook.setContent(event.getMessage());
                webhook.setAvatarUrl("https://mc-heads.net/avatar/" + player.getUniqueId().toString() + ".png");
                webhook.setUsername(player.getName());
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
