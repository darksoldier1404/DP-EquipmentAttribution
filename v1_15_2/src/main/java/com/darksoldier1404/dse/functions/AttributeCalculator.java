package com.darksoldier1404.dse.functions;

import com.darksoldier1404.dse.EquipmentAttribution;
import com.darksoldier1404.duc.utils.NBT;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("all")
public class AttributeCalculator {
    private static final EquipmentAttribution plugin = EquipmentAttribution.getInstance();
    private static boolean useOverCriticalChance = plugin.useOverCriticalChance;
    private static double defaultCriticalAmount = plugin.defaultCriticalAmount;

    public static double getTotalDamage(Player p) {
        double totalDamage = 0;
        double criticalChance = 0;
        double criticalAmount = defaultCriticalAmount;
        for(ItemStack armor : p.getInventory().getArmorContents()) {
            if(armor != null && armor.getType() != Material.AIR) {
                if(NBT.hasTagKey(armor, "dse_DAMAGE")) {
                    totalDamage += NBT.getDoubleTag(armor, "dse_DAMAGE");
                }
                if(NBT.hasTagKey(armor, "dse_CRITICAL_CHANCE")) {
                    criticalChance += NBT.getDoubleTag(armor, "dse_CRITICAL_CHANCE");
                }
                if(NBT.hasTagKey(armor, "dse_CRITICAL_AMOUNT")) {
                    criticalAmount += NBT.getDoubleTag(armor, "dse_CRITICAL_AMOUNT");
                }
            }
        }
        if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() != Material.AIR) {
            if(NBT.hasTagKey(p.getInventory().getItemInMainHand(), "dse_DAMAGE")) {
                totalDamage += NBT.getDoubleTag(p.getInventory().getItemInMainHand(), "dse_DAMAGE");
            }
            if(NBT.hasTagKey(p.getInventory().getItemInMainHand(), "dse_CRITICAL_CHANCE")) {
                criticalChance += NBT.getDoubleTag(p.getInventory().getItemInMainHand(), "dse_CRITICAL_CHANCE");
            }
            if(NBT.hasTagKey(p.getInventory().getItemInMainHand(), "dse_CRITICAL_AMOUNT")) {
                criticalAmount += NBT.getDoubleTag(p.getInventory().getItemInMainHand(), "dse_CRITICAL_AMOUNT");
            }
        }
        if(p.getInventory().getItemInOffHand() != null && p.getInventory().getItemInOffHand().getType() != Material.AIR) {
            if(NBT.hasTagKey(p.getInventory().getItemInOffHand(), "dse_DAMAGE")) {
                totalDamage += NBT.getDoubleTag(p.getInventory().getItemInOffHand(), "dse_DAMAGE");
            }
            if(NBT.hasTagKey(p.getInventory().getItemInOffHand(), "dse_CRITICAL_CHANCE")) {
                criticalChance += NBT.getDoubleTag(p.getInventory().getItemInOffHand(), "dse_CRITICAL_CHANCE");
            }
            if(NBT.hasTagKey(p.getInventory().getItemInOffHand(), "dse_CRITICAL_AMOUNT")) {
                criticalAmount += NBT.getDoubleTag(p.getInventory().getItemInOffHand(), "dse_CRITICAL_AMOUNT");
            }
        }
        if(useOverCriticalChance) {
            if(criticalChance > 0) {
                int loop = (int) (criticalChance * 0.01);
                if (loop != 0) {
                    totalDamage *= (criticalAmount * loop);
                    criticalChance = criticalChance - (loop * 100);
                }
                if (Math.random() * 100 <= criticalChance) {
                    totalDamage *= criticalAmount;
                }
            }
        }else{
            if(criticalChance > 0) {
                if (Math.random() * 100 >= criticalChance) {
                    totalDamage *= criticalAmount;
                }
            }
        }
        
        return totalDamage;
    }

    public static double getTotalArmor(Player p) {
        double totalArmor = 0;
        for(ItemStack armor : p.getInventory().getArmorContents()) {
            if(armor != null && armor.getType() != Material.AIR) {
                if(NBT.hasTagKey(armor, "dse_ARMOR")) {
                    totalArmor += NBT.getDoubleTag(armor, "dse_ARMOR");
                }
            }
        }
        if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() != Material.AIR) {
            if(NBT.hasTagKey(p.getInventory().getItemInMainHand(), "dse_ARMOR")) {
                totalArmor += NBT.getDoubleTag(p.getInventory().getItemInMainHand(), "dse_ARMOR");
            }
        }
        if(p.getInventory().getItemInOffHand() != null && p.getInventory().getItemInOffHand().getType() != Material.AIR) {
            if(NBT.hasTagKey(p.getInventory().getItemInOffHand(), "dse_ARMOR")) {
                totalArmor += NBT.getDoubleTag(p.getInventory().getItemInOffHand(), "dse_ARMOR");
            }
        }
        return totalArmor;
    }

    public static void applyTotalHealth(Player p) {
        double totalHealth = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue();
        for(ItemStack armor : p.getInventory().getArmorContents()) {
            if(armor != null && armor.getType() != Material.AIR) {
                if(NBT.hasTagKey(armor, "dse_HEALTH")) {
                    totalHealth += NBT.getDoubleTag(armor, "dse_HEALTH");
                }
            }
        }
        if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() != Material.AIR) {
            if(NBT.hasTagKey(p.getInventory().getItemInMainHand(), "dse_HEALTH")) {
                totalHealth += NBT.getDoubleTag(p.getInventory().getItemInMainHand(), "dse_HEALTH");
            }
        }
        if(p.getInventory().getItemInOffHand() != null && p.getInventory().getItemInOffHand().getType() != Material.AIR) {
            if(NBT.hasTagKey(p.getInventory().getItemInOffHand(), "dse_HEALTH")) {
                totalHealth += NBT.getDoubleTag(p.getInventory().getItemInOffHand(), "dse_HEALTH");
            }
        }
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(totalHealth);
    }

    public static void applyTotalSpeed(Player p) {
        double totalSpeed = 0.1;
        for(ItemStack armor : p.getInventory().getArmorContents()) {
            if(armor != null && armor.getType() != Material.AIR) {
                if(NBT.hasTagKey(armor, "dse_SPEED")) {
                    totalSpeed += NBT.getDoubleTag(armor, "dse_SPEED");
                }
            }
        }
        if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() != Material.AIR) {
            if(NBT.hasTagKey(p.getInventory().getItemInMainHand(), "dse_SPEED")) {
                totalSpeed += NBT.getDoubleTag(p.getInventory().getItemInMainHand(), "dse_SPEED");
            }
        }
        if(p.getInventory().getItemInOffHand() != null && p.getInventory().getItemInOffHand().getType() != Material.AIR) {
            if(NBT.hasTagKey(p.getInventory().getItemInOffHand(), "dse_SPEED")) {
                totalSpeed += NBT.getDoubleTag(p.getInventory().getItemInOffHand(), "dse_SPEED");
            }
        }
        p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(totalSpeed);
    }
}
