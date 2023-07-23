package oasis.artemis.plugin.lifecycle;

import oasis.artemis.plugin.ArtemisPlugin;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

/**
 * <h2>PluginManager</h2>
 * <p>Manages the lifecycle of Artemis plugins.</p>
 */
public final class PluginManager {
    //
    // Plugins
    //

    /**
     * Gets a set of all active plugins.
     *
     * @return Set of plugins
     */
    @Nonnull
    public Set<ArtemisPlugin> getPlugins() {
        return plugins;
    }

    /**
     * Gets a plugin by name.
     *
     * @param name Name of plugin
     * @return {@link ArtemisPlugin}
     * @throws NullPointerException When a plugin of given name cannot be found
     */
    @Nonnull
    public ArtemisPlugin getPlugin(@Nonnull String name) throws NullPointerException {
        for (ArtemisPlugin plugin : plugins) {
            if (plugin.getName().equals(name)) return plugin;
        }

        throw new NullPointerException("Plugin of name " + name + " not found.");
    }

    /**
     * Registers a plugin.
     *
     * @param plugin Plugin to register
     * @throws IllegalArgumentException When a plugin with the same name already exists
     */
    public void registerPlugin(@Nonnull ArtemisPlugin plugin) throws IllegalArgumentException {
        for (ArtemisPlugin p : plugins) {
            if (p.getName().equals(plugin.getName())) {
                throw new IllegalArgumentException("Plugin with name " + plugin.getName() + " already exists.");
            }
        }

        plugins.add(plugin);
    }

    /**
     * Unregisters a plugin.
     *
     * @param plugin Plugin to unregister
     */
    public void unregisterPlugin(@Nonnull ArtemisPlugin plugin) {
        plugins.remove(plugin);
    }

    //
    // Events
    //

    /**
     * Called when engine is about to start.
     */
    public void onEngineStarting() {
        plugins.forEach(ArtemisPlugin::onEngineStarting);
    }

    /**
     * Called when engine has fully started.
     */
    public void onEngineStarted() {
        plugins.forEach(ArtemisPlugin::onEngineStarted);
    }

    /**
     * Called when engine is about to stop.
     */
    public void onEngineStopping() {
        plugins.forEach(ArtemisPlugin::onEngineStopping);
    }

    /**
     * Called when engine has fully stopped.
     */
    public void onEngineStopped() {
        plugins.forEach(ArtemisPlugin::onEngineStopped);
    }

    //
    // Variables
    //

    /**
     * Set of plugins.
     */
    @Nonnull
    private final Set<ArtemisPlugin> plugins = new HashSet<>();
}
