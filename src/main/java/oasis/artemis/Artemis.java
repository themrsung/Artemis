package oasis.artemis;

import oasis.artemis.event.DummyEvent;
import oasis.artemis.event.lifecycle.EventManager;
import oasis.artemis.listener.DummyListener;
import oasis.artemis.task.lifecycle.Scheduler;
import oasis.artemis.task.lifecycle.SyncScheduler;

import javax.annotation.Nonnull;

/**
 * <h2>Artemis</h2>
 * <p>Main class of Artemis.</p>
 */
public final class Artemis {
    /**
     * Main method.
     * @param args Arguments
     */
    public static void main(@Nonnull String[] args) {
        start();
    }

    /**
     * Starts the engine.
     */
    public static void start() {
        eventManager.start();

        // This should be called last
        syncScheduler.start();

        /////////////////////////////////////////////////
        ////////////// START OF DEBUG CODE //////////////
        /////////////////////////////////////////////////

        eventManager.registerListener(new DummyListener());
        eventManager.callEvent(new DummyEvent());

        /////////////////////////////////////////////////
        //////////////// END OF DEBUG CODE //////////////
        /////////////////////////////////////////////////

    }

    /**
     * Stops the engine.
     */
    public static void stop() {
        // This stops all first-party modules
        syncScheduler.stop();
    }

    /**
     * Gets the synchronous scheduler.
     * @return {@link Scheduler}
     */
    @Nonnull
    public static Scheduler getSyncScheduler() {
        return syncScheduler;
    }

    //
    // Modules
    //
    private static final Scheduler syncScheduler = new SyncScheduler();
    private static final EventManager eventManager = new EventManager();
}