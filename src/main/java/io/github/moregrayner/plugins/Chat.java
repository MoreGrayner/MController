package io.github.moregrayner.plugins;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Chat implements Listener {
    HashSet<String> bannedWords = new HashSet<>();
    private final HashMap<UUID, Long> mutedPlayers = new HashMap<>();
    long mute;
    boolean Chat = true;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();

        if (mutedPlayers.containsKey(playerId)) {
            long unmuteTime = mutedPlayers.get(playerId);
            if (System.currentTimeMillis() < unmuteTime) {
                event.getPlayer().sendMessage(ChatColor.RED + "채팅이 금지되었습니다.");
                event.setCancelled(true);
                return;
            } else {mutedPlayers.remove(playerId);}
        }

        for (String word : bannedWords) {
            if (event.getMessage().toLowerCase().contains(word.toLowerCase())) {
                event.setCancelled(true);
                mutedPlayers.put(playerId, System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(mute));
                event.getPlayer().sendMessage(ChatColor.RED + "부적절한 단어를 사용하여 " + mute + "초 동안 채팅이 금지됩니다.");
                return;
            }
        }
    }

}