package oasis.artemis.plugin;

import oasis.artemis.plugin.lifecycle.PluginManager;

import javax.annotation.Nonnull;

/**
 * <h2>ArtemisPlugin</h2>
 * <p>A superinterface for Artemis plugins.</p>
 */
public interface ArtemisPlugin {
    //
    // Identification
    //

    /**
     * Gets the name of this plugin.
     * Plugin names are case-sensitive, and must be unique within the scope of its runtime.
     *
     * @return Name of this plugin
     */
    @Nonnull
    String getName();

    /**
     * Gets the version of this plugin.
     *
     * @return Version
     */
    @Nonnull
    String getVersion();

    //
    // Events
    //

    /**
     * Called upon registration to a plugin manager.
     *
     * @param manager Manager this plugin was registered to
     */
    void onRegistered(@Nonnull PluginManager manager);

    /**
     * Called upon unregistration from a plugin manager.
     *
     * @param manager Manager this plugin was unregistered from
     */
    void onUnregistered(@Nonnull PluginManager manager);

    /**
     * Called when engine is about to start.
     */
    void onEngineStarting();

    /**
     * Called when engine has fully started.
     */
    void onEngineStarted();

    /**
     * Called when engine is about to stop.
     */
    void onEngineStopping();

    /**
     * Called when engine has completely stopped.
     */
    void onEngineStopped();
}
