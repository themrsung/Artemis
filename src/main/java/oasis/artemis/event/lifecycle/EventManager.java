package oasis.artemis.event.lifecycle;

import oasis.artemis.Artemis;
import oasis.artemis.event.Event;
import oasis.artemis.event.listener.EventHandler;
import oasis.artemis.event.listener.Listener;
import oasis.artemis.task.TaskAdapter;
import oasis.artemis.util.group.Dyad;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <h2>EventManager</h2>
 * <p>Handles the lifecycle of events.</p>
 */
public final class EventManager {
    //
    // Lifecycle
    //

    /**
     * Calls an event to be handled.
     * @param event Event to call
     * @param <E> Type of event
     */
    public <E extends Event> void callEvent(@Nonnull E event) {
        eventQueue.add(event);
    }

    /**
     * Registers a listener.
     * @param listener Listener to register
     */
    public void registerListener(@Nonnull Listener listener) {
        listeners.add(listener);
    }

    /**
     * Registers multiple listeners.
     * @param listeners Listeners to register
     */
    public void registerListeners(@Nonnull Listener... listeners) {
        Arrays.stream(listeners).forEach(this::registerListener);
    }

    /**
     * Registers multiple listeners.
     * @param listeners List of listeners to register
     */
    public void registerListeners(@Nonnull List<Listener> listeners) {
        listeners.forEach(this::registerListener);
    }

    /**
     * Unregisters a listener.
     * @param listener Listener to unregister
     */
    public void unregisterListener(@Nonnull Listener listener) {
        listeners.remove(listener);
    }

    /**
     * Unregisters multiple listeners.
     * @param listeners Listeners to unregister
     */
    public void unregisterListeners(@Nonnull Listener... listeners) {
        Arrays.stream(listeners).forEach(this::unregisterListener);
    }

    /**
     * Unregisters multiple listeners.
     * @param listeners List of listeners to unregister
     */
    public void unregisterListeners(@Nonnull List<Listener> listeners) {
        listeners.forEach(this::unregisterListener);
    }

    //
    // Controls
    //

    /**
     * Starts the event manager.
     */
    public void start() {
        Artemis.getSyncScheduler().registerTask(task);
    }

    /**
     * Stops the event manager.
     */
    public void stop() {
        Artemis.getSyncScheduler().unregisterTask(task);
    }

    //
    // Variables
    //
    @Nonnull
    private final List<Listener> listeners = new ArrayList<>();
    @Nonnull
    private final Queue<Event> eventQueue = new LinkedList<>();
    @Nonnull
    private final EventTask task = new EventTask(this);

    //
    // Internal methods
    //
    <E extends Event> void executeEvent(@Nonnull E event) {
        final Class<? extends Event> eventClass = event.getClass();
        final List<Dyad<Listener, Method>> handlers = new ArrayList<>();

        // Copies list to prevent concurrent modification exception
        new ArrayList<>(listeners).forEach(l -> Arrays.stream(l.getClass().getDeclaredMethods()).forEach(m -> {
            if (m.getParameters().length != 1) return;
            if (!m.isAnnotationPresent(EventHandler.class)) return;
            if (!m.getParameters()[0].getType().isAssignableFrom(eventClass)) return;

            handlers.add(new Dyad<>(l, m));
        }));

        // Respect priorities
        handlers.sort(Comparator.comparing(h -> h.getB().getAnnotation(EventHandler.class).priority()));
        handlers.forEach(h -> {
            try {
                h.getB().invoke(h.getA(), event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Internal class for handling the event queue.
     */
    private static class EventTask extends TaskAdapter {
        public EventTask(@Nonnull EventManager parent) {
            this.parent = parent;
        }

        @Nonnull
        private final EventManager parent;

        /**
         * Handles one event per tick.
         * Handling too many events in this task may cause other tasks to slow down.
         *
         * @param delta The duration it took between the last execution and now
         */
        @Override
        public void execute(@Nonnull Duration delta) {
            final Event e = parent.eventQueue.poll();
            if (e == null) return;

            parent.executeEvent(e);
        }
    }
}
