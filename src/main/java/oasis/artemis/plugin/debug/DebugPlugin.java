package oasis.artemis.plugin.debug;

import oasis.artemis.plugin.SimplePlugin;

/**
 * <h2>DebugPlugin</h2>
 * <p>Plugin used for debugging.</p>
 */
public final class DebugPlugin extends SimplePlugin {
    public DebugPlugin() {
        super("ArtemisEngineDebugPlugin", "1.0");
    }

    @Override
    public void onEngineStarted() {
        System.out.println("Engine has started!");
    }
}
