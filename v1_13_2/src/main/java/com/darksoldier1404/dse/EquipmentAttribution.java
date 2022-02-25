package com.darksoldier1404.dse;

import com.darksoldier1404.dppc.DPPCore;
import com.darksoldier1404.dppc.utils.ConfigUtils;
import com.darksoldier1404.dse.commands.DSECommand;
import com.darksoldier1404.dse.events.DSEEvent;
import com.darksoldier1404.dse.functions.AttributeCalculator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

@SuppressWarnings("all")
public class EquipmentAttribution extends JavaPlugin {
    public DPPCore core;
    private static EquipmentAttribution plugin;
    public YamlConfiguration config;
    public String prefix;
    public boolean useOverCriticalChance;
    public double defaultCriticalAmount;
    public long attributeCheckInterval;
    public BukkitTask task;

    public static EquipmentAttribution getInstance() {
        return plugin;
    }

    public void onEnable() {
        plugin = this;
        Plugin pl = getServer().getPluginManager().getPlugin("DPP-Core");
        if (pl == null) {
            getLogger().warning("DPP-Core 플러그인이 설치되어있지 않습니다.");
            getLogger().warning("DP-EquipmentAttribution 플러그인을 비활성화 합니다.");
            plugin.setEnabled(false);
            return;
        }
        core = (DPPCore) pl;
        config = ConfigUtils.loadDefaultPluginConfig(plugin);
        prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Settings.Prefix"));
        useOverCriticalChance = config.getBoolean("Settings.useOverCriticalChance");
        defaultCriticalAmount = config.getDouble("Settings.defaultCriticalAmount");
        attributeCheckInterval = config.getLong("Settings.attributeCheckInterval");
        getCommand("dse").setExecutor(new DSECommand());
        getServer().getPluginManager().registerEvents(new DSEEvent(), plugin);
        task = getServer().getScheduler().runTaskTimer(plugin, new Runnable() {
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> {
                    AttributeCalculator.applyTotalHealth(p);
                    AttributeCalculator.applyTotalSpeed(p);
                });
            }
        }, 0, attributeCheckInterval);
    }

}
