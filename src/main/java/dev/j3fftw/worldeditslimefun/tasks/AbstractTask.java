package dev.j3fftw.worldeditslimefun.tasks;

import dev.j3fftw.worldeditslimefun.WorldEditSlimefun;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractTask extends BukkitRunnable {
    protected final SlimefunItem sfItem;
    protected final Set<BlockPosition> blocks = new HashSet<>();
    protected int timeout = 20 * 60 * 5; // Default timeout of 5 minutes
    protected int ticks = 0;

    public AbstractTask(SlimefunItem sfItem) {
        this.sfItem = sfItem;
    }

    @Override
    public final void run() {
        if (blocks.isEmpty()) {
            cancel();
            return;
        }

        if (ticks >= timeout) {
            cancel();
            return;
        }

        for (BlockPosition position : new HashSet<>(blocks)) {
            Block block = position.getBlock();
            if (!BlockStorage.check(block, sfItem.getId())) {
                blocks.remove(position);
                continue;
            }
            runTask(block);
        }
        ticks += 10;
    }

    public abstract void runTask(Block block);

    public void addBlock(Block block) {
        if (ticks == 0 && blocks.isEmpty()) {
            runTaskTimer(WorldEditSlimefun.getInstance(), 20, 10);
        }
        blocks.add(new BlockPosition(block));
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
