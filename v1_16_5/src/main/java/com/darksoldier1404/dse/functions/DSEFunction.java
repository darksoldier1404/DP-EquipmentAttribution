package com.darksoldier1404.dse.functions;

import com.darksoldier1404.dse.EquipmentAttribution;
import com.darksoldier1404.dse.enums.AttributeType;
import com.darksoldier1404.dppc.utils.NBT;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("all")
public class DSEFunction {
    private static final EquipmentAttribution plugin = EquipmentAttribution.getInstance();
    private static final String prefix = plugin.prefix;

    public static void setAttribution(Player p, String sType, String sValue) {
        if (p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) {
            p.sendMessage(prefix + "손에 아이템을 들고 있지 않습니다.");
            return;
        }
        AttributeType type;
        double value;
        try {
            type = AttributeType.valueOf(sType.toUpperCase());
        } catch (Exception e) {
            p.sendMessage(prefix + "잘못된 어트리뷰트 입니다.");
            return;
        }
        try {
            value = Double.parseDouble(sValue);
        } catch (Exception e) {
            p.sendMessage(prefix + "잘못된 값입니다.");
            return;
        }
        ItemStack item = p.getItemInHand();
        item = NBT.setDoubleTag(item, "dse_" + type.name(), value);
        p.setItemInHand(item);
        p.sendMessage(prefix + type + " 어트리뷰트 값을 " + value + "로 설정했습니다.");
    }

    public static void getAttribution(Player p, String sType) {
        if (p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) {
            p.sendMessage(prefix + "손에 아이템을 들고 있지 않습니다.");
            return;
        }
        AttributeType type;
        try {
            type = AttributeType.valueOf(sType.toUpperCase());
        } catch (Exception e) {
            p.sendMessage(prefix + "잘못된 어트리뷰트 입니다.");
            return;
        }
        ItemStack item = p.getItemInHand();
        double value = NBT.getDoubleTag(item, "dse_" + type.name());
        p.sendMessage(prefix + type + " 어트리뷰트 값은 " + value + " 입니다.");
    }

    public static void getAllAttribution(Player p) {
        if (p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) {
            p.sendMessage(prefix + "손에 아이템을 들고 있지 않습니다.");
            return;
        }
        ItemStack item = p.getItemInHand();
        double[] values = new double[AttributeType.values().length];
        for (int i = 0; i < AttributeType.values().length; i++) {
            values[i] = NBT.getDoubleTag(item, "dse_" + AttributeType.values()[i].name());
        }
        p.sendMessage(prefix + "아이템의 어트리뷰트 값은 다음과 같습니다.");
        for (int i = 0; i < AttributeType.values().length; i++) {
            p.sendMessage(prefix + AttributeType.values()[i] + ": " + values[i]);
        }
    }

    public static void removeAttribution(Player p, String sType) {
        if (p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) {
            p.sendMessage(prefix + "손에 아이템을 들고 있지 않습니다.");
            return;
        }
        AttributeType type;
        try {
            type = AttributeType.valueOf(sType.toUpperCase());
        } catch (Exception e) {
            p.sendMessage(prefix + "잘못된 어트리뷰트 입니다.");
            return;
        }
        ItemStack item = p.getItemInHand();
        item = NBT.removeTag(item, "dse_" + type.name());
        p.setItemInHand(item);
        p.sendMessage(prefix + type + " 어트리뷰트 값을 삭제했습니다.");
    }

    public static void removeAllAttribution(Player p) {
        if (p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) {
            p.sendMessage(prefix + "손에 아이템을 들고 있지 않습니다.");
            return;
        }
        ItemStack item = p.getItemInHand();
        for (int i = 0; i < AttributeType.values().length; i++) {
            item = NBT.removeTag(item, "dse_" + AttributeType.values()[i].name());
        }
        p.setItemInHand(item);
        p.sendMessage(prefix + "아이템의 어트리뷰트 값을 삭제했습니다.");
    }

    public static void addAttribution(Player p, String sType, String sValue) {
        if (p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) {
            p.sendMessage(prefix + "손에 아이템을 들고 있지 않습니다.");
            return;
        }
        AttributeType type;
        double value;
        try {
            type = AttributeType.valueOf(sType.toUpperCase());
        } catch (Exception e) {
            p.sendMessage(prefix + "잘못된 어트리뷰트 입니다.");
            return;
        }
        try {
            value = Double.parseDouble(sValue);
        } catch (Exception e) {
            p.sendMessage(prefix + "잘못된 값입니다.");
            return;
        }
        ItemStack item = p.getItemInHand();
        double tmp_value = NBT.getDoubleTag(item, "dse_" + type.name());
        if (tmp_value == 0) {
            setAttribution(p, sType, sValue);
            return;
        }
        item = NBT.setDoubleTag(item, "dse_" + type.name(), tmp_value + value);
        p.setItemInHand(item);
        p.sendMessage(prefix + type + " 어트리뷰트 값이 더해져 " + value + " 으로 설정됬습니다.");
    }

    public static void subAttribution(Player p, String sType, String sValue) {
        if (p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) {
            p.sendMessage(prefix + "손에 아이템을 들고 있지 않습니다.");
            return;
        }
        AttributeType type;
        double value;
        try {
            type = AttributeType.valueOf(sType.toUpperCase());
        } catch (Exception e) {
            p.sendMessage(prefix + "잘못된 어트리뷰트 입니다.");
            return;
        }
        try {
            value = Double.parseDouble(sValue);
        } catch (Exception e) {
            p.sendMessage(prefix + "잘못된 값입니다.");
            return;
        }
        ItemStack item = p.getItemInHand();
        item = NBT.setDoubleTag(item, "dse_" + type.name(), NBT.getDoubleTag(item, "dse_" + type.name()) == 0 ? 0 : NBT.getDoubleTag(item, "dse_" + type.name()) - value <= 0 ? 0 : NBT.getDoubleTag(item, "dse_" + type.name()) - value);
        if (NBT.getDoubleTag(item, "dse_" + type.name()) == 0) {
            item = NBT.removeTag(item, "dse_" + type.name());
            p.setItemInHand(item);
            p.sendMessage(prefix + "값이 0이거나 0이하 이므로 어트리뷰트가 제거되었습니다.");
            return;
        }
        p.setItemInHand(item);
        p.sendMessage(prefix + type + " 어트리뷰트 값이 빼져 " + value + " 으로 설정됬습니다.");
    }
}
