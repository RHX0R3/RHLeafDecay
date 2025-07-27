package eu.raidersheaven.rhleafdecay.configurations;

import eu.raidersheaven.rhleafdecay.main.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigFile {
    private final Main main;

    public ConfigFile(Main main) {
        this.main = main;
    }


    /**
     * Variables from config.yml
     */
    public static int mode;
    public static long ticksBreak;
    public static long ticksDecay;
    public static boolean featuresDrops;
    public static boolean featuresParticles;
    public static boolean featuresSounds;
    public static List<String> filtersWhitelist;
    public static List<String> filtersBlacklist;


    /**
     * Getting values
     */
    public void set() {
        FileConfiguration cfg = main.getConfig();
        mode = cfg.getInt("settings.decay.mode");
        ticksBreak = cfg.getLong("settings.decay.ticks.break");
        ticksDecay = cfg.getLong("settings.decay.ticks.check");
        featuresDrops = cfg.getBoolean("settings.features.drops");
        featuresParticles = cfg.getBoolean("settings.features.particles");
        featuresSounds = cfg.getBoolean("settings.features.sounds", true);
        filtersWhitelist = cfg.getStringList("settings.filters.whitelist");
        filtersBlacklist = cfg.getStringList("settings.filters.blacklist");
    }
}
