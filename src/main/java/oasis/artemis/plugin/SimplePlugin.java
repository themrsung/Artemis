package oasis.artemis.plugin;

import oasis.artemis.plugin.lifecycle.PluginManager;

import javax.annotation.Nonnull;

/**
 * <h2>SimplePlugin</h2>
 * <p>
 * An adapter class for {@link ArtemisPlugin}.
 * Extend this class for easier implementation.
 * </p>
 */
public abstract class SimplePlugin implements ArtemisPlugin {
    //
    // Constructors
    //

    /**
     * Creates a new plugin.
     *
     * @param name    Name of plugin
     * @param version Version of plugin
     */
    public SimplePlugin(@Nonnull String name, @Nonnull String version) {
        this.name = name;
        this.version = version;
    }

    //
    // Variables
    //
    @Nonnull
    private final String name;
    @Nonnull
    private final String version;

    //
    // Getters
    //

    @Override
    @Nonnull
    public String getName() {
        return name;
    }

    @Override
    @Nonnull
    public String getVersion() {
        return version;
    }

    //
    // Methods
    //


    @Override
    public void onRegistered(@Nonnull PluginManager manager) {}

    @Override
    public void onUnregistered(@Nonnull PluginManager manager) {}

    @Override
    public void onEngineStarting() {}

    @Override
    public void onEngineStarted() {}

    @Override
    public void onEngineStopping() {}

    @Override
    public void onEngineStopped() {}
}
