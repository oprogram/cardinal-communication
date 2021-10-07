package org.librenote.mc.cardinal.communication;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;

public class events implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!event.isCancelled()) {
            Player player = event.getPlayer();

            try {
                DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/895703359183532062/X7ou-ze4v8xuYi1ZQDX16Fq1uLL1z9kVERFsFDt1snJKQM3YCPRDr9zBPAAzmplZpXP0");
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
