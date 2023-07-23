package oasis.artemis;

import oasis.artemis.event.lifecycle.EventManager;
import oasis.artemis.level.Level;
import oasis.artemis.level.SimpleLevel;
import oasis.artemis.level.lifecycle.LevelManager;
import oasis.artemis.listener.physics.CollisionListener;
import oasis.artemis.object.ArtemisObject;
import oasis.artemis.object.SimpleObject;
import oasis.artemis.session.SessionManager;
import oasis.artemis.task.TaskAdapter;
import oasis.artemis.task.lifecycle.AsyncScheduler;
import oasis.artemis.task.lifecycle.Scheduler;
import oasis.artemis.task.lifecycle.SyncScheduler;
import oasis.artemis.ui.component.viewport.Viewport;
import oasis.artemis.ui.component.viewport.ViewportRenderContext;
import oasis.artemis.ui.listener.ExitOnCloseListener;
import oasis.artemis.ui.window.UIWindow;
import oasis.artemis.util.geometry.profile.SphereProfile;
import oasis.artemis.util.math.Quaternion;
import oasis.artemis.util.math.Vector;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.List;

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
        // Register listeners
        eventManager.registerListeners(
                new CollisionListener()
        );

        // Start modules
        eventManager.start();
        levelManager.start();

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

        final Level level = SimpleLevel.builder()
                .name("SimpleLevel")
                .gravity(Level.EARTH_GRAVITY)
                .build();

        levelManager.addLevel(level);

        final ArtemisObject object = SimpleObject.builder()
                .geometry(new SphereProfile(10))
                .location(new Vector(0, 0, 500))
                .mass(100)
                .acceleration(new Vector(0, 0, -60))
                .rotationRate(Quaternion.fromAxisAngle(new Vector(1, 2, 0.3), Math.toRadians(12)))
                .build();

        level.addObject(object);

        final ArtemisObject object2 = SimpleObject.builder()
                .geometry(new SphereProfile(10))
                .location(new Vector(0, 0, -500))
                .mass(100)
                .acceleration(new Vector(0, 0, 60))
                .build();

        level.addObject(object2);

        final Viewport viewport = new Viewport();
        window.add(viewport);
        viewport.setSize(window.getSize());
        viewport.setVisible(true);

        getSyncScheduler().registerTask(new TaskAdapter() {
            @Override
            public void execute(@Nonnull Duration delta) {
                viewport.render(new ViewportRenderContext(
                        level,
                        object2.getLocation(),
                        object2.getRotation(),
                        List.of(object2)
                ));
            }
        });


        getAsyncScheduler().registerTask(new TaskAdapter() {
            @Override
            public void execute(@Nonnull Duration delta) {
                System.out.println("Object 1: " + object.getLocation() + " " + object.getAcceleration());
                System.out.println("Object 2: " + object2.getLocation() + " " + object2.getAcceleration());
            }

            @Nonnull
            @Override
            public Duration getInterval() {
                return new Duration(1000);
            }
        });

        final boolean FALSE = false; // Put breakpoint here for easy debugging

        /////////////////////////////////////////////////
        //////////////// END OF DEBUG CODE //////////////
        /////////////////////////////////////////////////

    }

    /**
     * Stops the engine.
     */
    public static void stop() {
        // Stop modules
        eventManager.stop();
        levelManager.stop();

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