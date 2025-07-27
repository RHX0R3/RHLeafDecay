package eu.raidersheaven.rhleafdecay.main;

import eu.raidersheaven.metrics.Metrics;
import eu.raidersheaven.rhleafdecay.configurations.ConfigFile;
import eu.raidersheaven.rhleafdecay.handlers.DecayHandler;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main instance;
    private final ConfigFile config = new ConfigFile(this);
    MiniMessage miniMessage = MiniMessage.miniMessage();


    /**
     * Startup
     */
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        config.set();
        loadMetrics();
        getServer().getPluginManager().registerEvents(new DecayHandler(), this);
        Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize(" "));
        Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize("<gradient:#2CFA4B:#298D2B><bold>   __           __                __    ___                         </bold></gradient>"));
        Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize("<gradient:#2CFA4B:#298D2B><bold>  /__\\  /\\  /\\ / /   ___   __ _  / _|  /   \\ ___   ___  __ _  _   _ </bold></gradient>"));
        Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize("<gradient:#2CFA4B:#298D2B><bold> / \\// / /_/ // /   / _ \\ / _` || |_  / /\\ // _ \\ / __|/ _` || | | |</bold></gradient>"));
        Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize("<gradient:#2CFA4B:#298D2B><bold>/ _  \\/ __  // /___|  __/| (_| ||  _|/ /_//|  __/| (__| (_| || |_| |</bold></gradient>"));
        Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize("<gradient:#2CFA4B:#298D2B><bold>\\/ \\_/\\/ /_/ \\____/ \\___| \\__,_||_| /___,'  \\___| \\___|\\__,_| \\__, |</bold></gradient>"));
        Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize("<gradient:#2CFA4B:#298D2B><bold>                                                              |___/ </bold></gradient>"));
        Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize("<gray>                                                           (" + getPluginMeta().getVersion() + "<gray>)"));
        Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize("<dark_gray>«<white>*<dark_gray>» Increases the decay of leaves \uD83C\uDF3F Customizable!"));
        Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize("<dark_gray>«<white>*<dark_gray>» Made with \uD83D\uDC95 by <gray>X0R3"));
        Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize(" "));
    }


    /**
     * Shutdown
     */
    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize("<dark_gray>Disabling <gradient:#2CFA4B:#298D2B><bold>RHSignItem</bold></gradient><dark_gray>."));
    }


    /**
     * Get plugin instance
     *
     * @return Plugin instance
     */
    public static Main get() {
        return instance;
    }


    /**
     * Load bstats Metrics
     */
    public void loadMetrics() {
        int pluginId = 11383;
        Metrics metrics = new Metrics(this, pluginId);
    }
}
