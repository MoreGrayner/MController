package io.github.moregrayner.plugins;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PeacetimeModule implements Listener {
    boolean Protection = true;

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event){
        if (Protection){event.setCancelled(true);}
    }
}
