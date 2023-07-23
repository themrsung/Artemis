package oasis.artemis;

import oasis.artemis.command.CommandSender;
import oasis.artemis.command.ConsoleCommandSender;
import oasis.artemis.command.lifecycle.CommandManager;
import oasis.artemis.command.game.list.ListCommand;
import oasis.artemis.command.game.stop.StopCommand;
import oasis.artemis.event.lifecycle.EventManager;
import oasis.artemis.level.lifecycle.LevelManager;
import oasis.artemis.listener.physics.CollisionListener;
import oasis.artemis.object.DummyObject;
import oasis.artemis.session.SessionManager;
import oasis.artemis.session.player.LocalPlayer;
import oasis.artemis.task.lifecycle.AsyncScheduler;
import oasis.artemis.task.lifecycle.Scheduler;
import oasis.artemis.task.lifecycle.SyncScheduler;
import oasis.artemis.ui.component.start.StartScreen;
import oasis.artemis.ui.listener.ExitOnCloseListener;
import oasis.artemis.ui.window.UIWindow;

import javax.annotation.Nonnull;
import java.util.UUID;

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

    /**
     * The type of this Artemis instance.
     */
    public static final InstanceType INSTANCE_TYPE = InstanceType.SERVER;

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
        // Load data
        load();

        // Register listeners
        eventManager.registerListeners(
                new CollisionListener()
        );

        // Start modules
        eventManager.start();
        levelManager.start();
        commandManager.start();

        // Start schedulers
        syncScheduler.start();
        asyncScheduler.start();

        switch (INSTANCE_TYPE) {
            // Client setup
            case CLIENT -> {
                // Initialize local player
                initializeLocalPlayer();

                // Open window
                openWindow();

                // Show start screen
                showStartScreen();
            }

            // Server setup
            case SERVER -> {
                // Initialize levels
                initializeLevels();

                // Open network
                openNetwork();
            }
        }

        // Register commands
        registerCommands();

        /////////////////////////////////////////////////
        ////////////// START OF DEBUG CODE //////////////
        /////////////////////////////////////////////////

        final boolean FALSE = false; // Put breakpoint here for easy debugging

        /////////////////////////////////////////////////
        //////////////// END OF DEBUG CODE //////////////
        /////////////////////////////////////////////////

    }

    /**
     * Stops the engine.
     */
    public static void stop() {
        // Dispose window
        window.removeAll();
        window.dispose();

        // Stop modules
        eventManager.stop();
        levelManager.stop();
        commandManager.stop();

        // Stop schedulers
        syncScheduler.stop();
        asyncScheduler.stop();

        // Save data
        save();
    }

    /**
     * Loads data from disk.
     */
    public static void load() {

    }

    /**
     * Saves data from disk.
     */
    public static void save() {

    }

    //
    // Client setup
    //

    /**
     * Initializes local player.
     */
    private static void initializeLocalPlayer() {
        sessionManager.setLocalPlayer(new LocalPlayer(
                UUID.randomUUID(),
                "ArtemisPlayer",
                new DummyObject()
        ));
    }

    /**
     * Opens the main window.
     */
    private static void openWindow() {
        window.setSize(1920, 1080);
        window.addWindowListener(new ExitOnCloseListener()); // Use this instead of setting behavior to EXIT_ON_CLOSE
        window.setVisible(true);
    }

    /**
     * Initializes the start screen.
     */
    private static void showStartScreen() {
        final StartScreen startScreen = new StartScreen();
        window.add(startScreen);

        startScreen.setSize(window.getSize());
        startScreen.setVisible(true);
    }

    //
    // Server setup
    //

    /**
     * Initializes levels.
     */
    private static void initializeLevels() {

    }

    /**
     * Opens network to allow players to join.
     */
    private static void openNetwork() {

    }

    //
    // Common setup
    //

    private static void registerCommands() {
        commandManager.addCommand(new StopCommand());
        commandManager.addCommand(new ListCommand());
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

    /**
     * Gets the command manager.
     * @return {@link CommandManager}
     */
    @Nonnull
    public static CommandManager getCommandManager() {
        return commandManager;
    }

    //
    // Modules
    //
    private static final Scheduler syncScheduler = new SyncScheduler();
    private static final Scheduler asyncScheduler = new AsyncScheduler();
    private static final EventManager eventManager = new EventManager();
    private static final LevelManager levelManager = new LevelManager();
    private static final SessionManager sessionManager = new SessionManager();
    private static final CommandManager commandManager = new CommandManager();

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

    //
    // Command getters
    //

    /**
     * Gets the console command sender.
     * @return {@link CommandSender}
     */
    @Nonnull
    public static CommandSender getConsoleCommandSender() {
        return consoleCommandSender;
    }

    //
    // Commands
    //
    private static final CommandSender consoleCommandSender = new ConsoleCommandSender();
}