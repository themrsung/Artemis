package oasis.artemis;

import oasis.artemis.event.lifecycle.EventManager;
import oasis.artemis.level.lifecycle.LevelManager;
import oasis.artemis.network.SessionManager;
import oasis.artemis.task.lifecycle.AsyncScheduler;
import oasis.artemis.task.lifecycle.Scheduler;
import oasis.artemis.task.lifecycle.SyncScheduler;
import oasis.artemis.task.physics.*;
import oasis.artemis.ui.listener.ExitOnCloseListener;
import oasis.artemis.ui.window.UIWindow;
import oasis.artemis.util.math.RotationBuilder;
import oasis.artemis.util.math.Vector;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;

/**
 * <h2>Artemis</h2>
 * <p>Main class of Artemis.</p>
 */
public final class Artemis {
    //
    // Constants
    //

    /**
     * The title of your game.
     */
    public static final String GAME_TITLE = "ArtemisEngine";

    /**
     * The version of your game.
     */
    public static final String GAME_VERSION = "1.0";

    //
    // Public methods
    //

    /**
     * Main method.
     *
     * @param args Arguments
     */
    public static void main(@Nonnull String[] args) {
        start();
    }

    /**
     * Starts the engine.
     */
    public static void start() {
        // Register first party tasks
        getSyncScheduler().registerTasks(
                new CheckOverlapTask(),
                new GravityTask(),
                new MovementTask(),
                new ResistanceTask(),
                new RotationTask()
        );

        // Start the event manager
        eventManager.start();

        // Start schedulers
        syncScheduler.start();
        asyncScheduler.start();

        // Open window
        window.setSize(1920, 1080);
        window.addWindowListener(new ExitOnCloseListener()); // Use this instead of setting behavior to EXIT_ON_CLOSE
        window.setVisible(true);

        /////////////////////////////////////////////////
        ////////////// START OF DEBUG CODE //////////////
        /////////////////////////////////////////////////

        Vector v = new Vector(10, 0, 10);
        final Vector p = v.rotate(RotationBuilder.fromRollDegrees(90).build());

        System.out.println(p);

        System.out.println(DateTime.now());

        final boolean FALSE = false; // Put breakpoint here for easy debugging

        /////////////////////////////////////////////////
        //////////////// END OF DEBUG CODE //////////////
        /////////////////////////////////////////////////

    }

    /**
     * Stops the engine.
     */
    public static void stop() {
        // Stop schedulers
        syncScheduler.stop();
        asyncScheduler.stop();
    }

    //
    // Module instance getters
    //

    /**
     * Gets the synchronous scheduler.
     *
     * @return {@link Scheduler}
     */
    @Nonnull
    public static Scheduler getSyncScheduler() {
        return syncScheduler;
    }

    /**
     * Gets the asynchronous scheduler.
     *
     * @return {@link Scheduler}
     */
    @Nonnull
    public static Scheduler getAsyncScheduler() {
        return asyncScheduler;
    }

    /**
     * Gets the event manager.
     *
     * @return {@link EventManager}
     */
    @Nonnull
    public static EventManager getEventManager() {
        return eventManager;
    }

    /**
     * Gets the level manager.
     *
     * @return {@link LevelManager}
     */
    @Nonnull
    public static LevelManager getLevelManager() {
        return levelManager;
    }

    /**
     * Gets the session manager.
     *
     * @return {@link SessionManager}
     */
    @Nonnull
    public static SessionManager getSessionManager() {
        return sessionManager;
    }

    //
    // Modules
    //
    private static final Scheduler syncScheduler = new SyncScheduler();
    private static final Scheduler asyncScheduler = new AsyncScheduler();
    private static final EventManager eventManager = new EventManager();
    private static final LevelManager levelManager = new LevelManager();
    private static final SessionManager sessionManager = new SessionManager();

    //
    // UI getters
    //

    /**
     * Gets the main window of Artemis.
     *
     * @return Main window
     */
    @Nonnull
    public static UIWindow getWindow() {
        return window;
    }

    //
    // UI
    //
    private static final UIWindow window = new UIWindow(GAME_TITLE + " " + GAME_VERSION);
}