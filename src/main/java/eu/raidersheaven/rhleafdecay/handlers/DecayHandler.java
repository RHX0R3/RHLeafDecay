package eu.raidersheaven.rhleafdecay.handlers;

import eu.raidersheaven.rhleafdecay.configurations.ConfigFile;
import eu.raidersheaven.rhleafdecay.main.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.*;
import java.util.stream.Collectors;

public class DecayHandler implements Listener {

    public static final Set<String> whitelist = new HashSet<>();
    public static final Set<String> blacklist = new HashSet<>();
    //public static final HashSet<Block> scheduledBlocks = new HashSet<>();
    public static final Set<Block> scheduledBlocks = Collections.synchronizedSet(new HashSet<>());
    //public static final EnumSet<BlockFace> NEIGHBORS = EnumSet.allOf(BlockFace.class);
    public static final EnumSet<BlockFace> NEIGHBORS = EnumSet.of(BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN);

    private final BukkitScheduler scheduler = Bukkit.getServer().getScheduler();


    /**
     * Block Break Event
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onBreak(final BlockBreakEvent event) {
        onRemove(event.getBlock(), ConfigFile.ticksBreak);
    }


    /**
     * Leaves Decay Event
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onDecay(final LeavesDecayEvent event) {
        onRemove(event.getBlock(), ConfigFile.ticksDecay);

    }


    /**
     * Remove Logic
     */
    private void onRemove(Block old, long delay) {
        Material type = old.getType();

        if (!Tag.LOGS.isTagged(type) && !Tag.FENCES.isTagged(type) && !Tag.LEAVES.isTagged(type)) {
            return;
        }

        final String worldName = old.getWorld().getName().toLowerCase();
        whitelist.addAll(ConfigFile.filtersWhitelist);
        blacklist.addAll(ConfigFile.filtersBlacklist);

        if (!whitelist.isEmpty()) {
            if (!whitelist.stream().map(String::toLowerCase).collect(Collectors.toSet()).contains(worldName)) {
                return;
            }
        }
        if (!blacklist.isEmpty()) {
            if (blacklist.stream().map(String::toLowerCase).collect(Collectors.toSet()).contains(worldName)) {
                return;
            }
        }
        if (!whitelist.isEmpty() && !whitelist.contains(worldName)) {
            return;
        }
        if (blacklist.contains(worldName)) {
            return;
        }

        for (BlockFace neighborFace : NEIGHBORS) {
            final Block block = old.getRelative(neighborFace);

            if (!Tag.LEAVES.isTagged(block.getType())) continue;

            Leaves leaves = (Leaves) block.getBlockData();

            if (leaves.isPersistent()) continue;
            if (scheduledBlocks.contains(block)) continue;

            int mode = ConfigFile.mode;

            if (mode < 1) {
                mode = 1;
            }
            if (mode == 2) {
                if (scheduledBlocks.isEmpty()) {
                    scheduler.runTaskLater(Main.instance, this::single, delay);
                }
                scheduledBlocks.add(block);
            } else {
                scheduler.runTaskLater(Main.instance, () -> cluster(block), delay);
            }
            scheduledBlocks.add(block);
        }
    }


    /**
     * Default Mode
     */
    private boolean cluster(Block block) {
        Material type = block.getType();
        World world = block.getWorld();
        Location location = block.getLocation();

        if (!scheduledBlocks.remove(block)) {
            return false;
        }
        if (!Tag.LEAVES.isTagged(type)) {
            return false;
        }

        final Leaves leaves = (Leaves) block.getBlockData();
        if (leaves.isPersistent()) {
            return false;
        }
        if (leaves.getDistance() < 7) {
            return false;
        }

        final LeavesDecayEvent event = new LeavesDecayEvent(block);
        Bukkit.getServer().getPluginManager().callEvent(event);

        if (event.isCancelled()) {
            return false;
        }

        boolean drops = ConfigFile.featuresDrops;
        boolean particles = ConfigFile.featuresParticles;
        boolean sounds = ConfigFile.featuresSounds;

        if (sounds) {
            world.playSound(location, Sound.BLOCK_AZALEA_LEAVES_BREAK, 0.05F, 1.2F);
        }
        if (particles) {
            world.spawnParticle(Particle.BLOCK, location.add(0.5, 0.5, 0.5), 8, 0.2, 0.2, 0.2, 0.0, (Object) type.createBlockData());
        }
        if (!drops) {
            block.setType(Material.AIR, false);
        } else {
            block.breakNaturally();
        }
        return true;
    }


    /**
     * Single Mode
     */
    private void singles() {
        boolean decayed;
        do {
            if (scheduledBlocks.isEmpty()) return;
            Block block = scheduledBlocks.stream().findFirst().get();
            decayed = cluster(block);
        } while (!decayed);
        if (!scheduledBlocks.isEmpty()) {
            long delay = ConfigFile.ticksDecay;
            if (delay <= 0) delay = 1L;
            scheduler.runTaskLater(Main.instance, this::single, delay);
        }
    }

    private void single() {
        if (scheduledBlocks.isEmpty()) return;

        Iterator<Block> iterator = scheduledBlocks.iterator();
        boolean decayed = false;

        while (iterator.hasNext()) {
            Block block = iterator.next();
            decayed = cluster(block);
            if (decayed) break;
        }

        if (!scheduledBlocks.isEmpty()) {
            long delay = Math.max(ConfigFile.ticksDecay, 1L);
            scheduler.runTaskLater(Main.instance, this::single, delay);
        }
    }


}
