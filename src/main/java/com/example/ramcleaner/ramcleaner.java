package com.example.ramcleaner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ramcleaner extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("ramcleaner enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("ramcleaner disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ramclean")) {

            sender.sendMessage("Waiting for garbage collection to clean up memory...");
          
            System.gc();

            sender.sendMessage("RAM is being cleared, wait...");
        
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

          
            long afterMemory = Runtime.getRuntime().freeMemory();
            long cleanedMemoryBytes = afterMemory;
            long cleanedMemoryMB = cleanedMemoryBytes / (1024 * 1024);

            sender.sendMessage("RAM cleaned: " + cleanedMemoryMB + " MB");
            return true;
        }
        return false;
    }
}
