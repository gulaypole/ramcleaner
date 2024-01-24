package com.example.ramcleaner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ramcleaner extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("RamCleaner enabled!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("RamCleaner disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ramclean")) {
            cleanAndNotify(sender);
            return true;
        } else if (cmd.getName().equalsIgnoreCase("ramstatus")) {
            displayRamStatus(sender);
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Welcome to the server!");
        cleanAndNotify(player);
        displayRamStatus(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Goodbye!");
        cleanAndNotify(player);
        displayRamStatus(player);
    }

    private void cleanAndNotify(CommandSender sender) {
        sender.sendMessage("Waiting for garbage collection to clean up memory...");
        System.gc();
        sender.sendMessage("Waiting for a moment...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long afterMemory = Runtime.getRuntime().freeMemory();
        long cleanedMemoryBytes = afterMemory;
        long cleanedMemoryMB = cleanedMemoryBytes / (1024 * 1024);
        sender.sendMessage("RAM cleaned: " + cleanedMemoryMB + " MB");
    }

    private void displayRamStatus(CommandSender sender) {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long usedMemory = totalMemory - freeMemory;
        sender.sendMessage("RAM Status:");
        sender.sendMessage("Total Memory: " + (totalMemory / (1024 * 1024)) + " MB");
        sender.sendMessage("Used Memory: " + (usedMemory / (1024 * 1024)) + " MB");
        sender.sendMessage("Free Memory: " + (freeMemory / (1024 * 1024)) + " MB");
    }
}
