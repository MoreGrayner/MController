package io.github.moregrayner.plugins;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PeacetimeModule implements Listener {
    boolean Protection = true;

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event){
        if (Protection&& event.getEntity() instanceof Player){event.setCancelled(true);}}


    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (Protection && event.getEntity() instanceof Player) {event.setCancelled(true);}
    }

    void peace(int arg) {
        final int[] Peacetime = {arg};
        new BukkitRunnable(){
            @Override
            public void run() {
                Peacetime[0]--;
                String message = (ChatColor.RED + "남은 자원 시간: " + Peacetime[0]);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    TextComponent actionBarMessage = new TextComponent(message);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, actionBarMessage);
                }
                if (Peacetime[0] <= 0){
                    Protection = false;
                    cancel();
                }
            }
        }.runTaskTimer(Controller.getInstance(), 0L, 20L);
    }

}
