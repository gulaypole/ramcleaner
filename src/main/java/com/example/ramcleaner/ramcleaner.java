package com.example.ramcleaner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ramcleaner extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("RamCleaner запущен!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("RamCleaner отключен!");
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

    private void cleanAndNotify(CommandSender sender) {
        sender.sendMessage("Поднимаю очиститель мусора...");
        System.gc();
        sender.sendMessage("Ждем...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long afterMemory = Runtime.getRuntime().freeMemory();
        long cleanedMemoryBytes = afterMemory;
        long cleanedMemoryMB = cleanedMemoryBytes / (1024 * 1024);
        sender.sendMessage("Памяти очищено: " + cleanedMemoryMB + " MB");
    }

    private void displayRamStatus(CommandSender sender) {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long usedMemory = totalMemory - freeMemory;
        sender.sendMessage("Статус ОЗУ:");
        sender.sendMessage("Всего: " + (totalMemory / (1024 * 1024)) + " MB");
        sender.sendMessage("Использовано: " + (usedMemory / (1024 * 1024)) + " MB");
        sender.sendMessage("Свободно: " + (freeMemory / (1024 * 1024)) + " MB");
    }
}
