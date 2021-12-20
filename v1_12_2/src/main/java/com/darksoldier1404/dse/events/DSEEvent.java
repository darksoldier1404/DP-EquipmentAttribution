package com.darksoldier1404.dse.events;

import com.darksoldier1404.dse.functions.AttributeCalculator;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DSEEvent implements Listener {

    @EventHandler
    public void onPlayerAttackAnEntity(EntityDamageByEntityEvent e) {
        // Player -> LivingEntity (not Player)
        if (e.getDamager() instanceof Player && e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof Player)) {
            e.setDamage(e.getDamage() + AttributeCalculator.getTotalDamage((Player) e.getDamager()));
            System.out.println("[DSE] p to le Damage: " + e.getDamage());
            return;
        }
        // Player -> Player
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            double finalDamage = e.getDamage() + AttributeCalculator.getTotalDamage((Player) e.getDamager()) - AttributeCalculator.getTotalArmor((Player) e.getEntity());
            e.setDamage(finalDamage);
            System.out.println("[DSE] p to p Damage: " + e.getDamage());
            return;
        }
        // LivingEntity -> Player
        if (e.getDamager() instanceof LivingEntity && e.getEntity() instanceof Player) {
            double finalDamage = e.getDamage() - AttributeCalculator.getTotalArmor((Player) e.getEntity());
            e.setDamage(finalDamage);
            System.out.println("[DSE] le to p Damage: " + e.getDamage());
        }
    }
}
