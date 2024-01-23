package com.example.DeepCleanMemoryPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class DeepCleanMemoryPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("DeepCleanMemoryPlugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("DeepCleanMemoryPlugin disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("deepcleanmemory")) {
            long beforeMemory = Runtime.getRuntime().freeMemory();

            for (int i = 0; i < 3; i++) {
                System.runFinalization();
                System.gc();
            }

            long afterMemory = Runtime.getRuntime().freeMemory();
            long cleanedMemoryBytes = beforeMemory - afterMemory;
            long cleanedMemoryMB = cleanedMemoryBytes / (1024 * 1024);

            sender.sendMessage("RAM deep cleaned: " + cleanedMemoryMB + " MB");
            return true;
        }
        return false;
    }
}
