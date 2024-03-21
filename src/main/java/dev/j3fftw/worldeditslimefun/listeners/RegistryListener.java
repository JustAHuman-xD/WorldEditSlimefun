package dev.j3fftw.worldeditslimefun.listeners;

import dev.j3fftw.worldeditslimefun.utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.events.SlimefunItemRegistryFinalizedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class RegistryListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onRegistryInitialized(SlimefunItemRegistryFinalizedEvent event) {
        Utils.init();
    }
}
