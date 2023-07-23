package oasis.artemis.event.physics;

import oasis.artemis.event.Cancellable;
import oasis.artemis.object.ArtemisObject;
import oasis.artemis.util.group.Pair;

import javax.annotation.Nonnull;

/**
 * <h2>CollisionEvent</h2>
 * <p>This is called when two objects overlap for the first time.</p>
 */
public class CollisionEvent implements Cancellable {
    /**
     * Creates a new collision event.
     *
     * @param objects Pair of objects which collided
     */
    public CollisionEvent(@Nonnull Pair<ArtemisObject> objects) {
        this.objects = objects;
        this.cancelled = false;
    }

    @Nonnull
    private final Pair<ArtemisObject> objects;
    private boolean cancelled;

    /**
     * Gets the pair of objects involved in this collision.
     *
     * @return Pair of collided objects
     */
    @Nonnull
    public Pair<ArtemisObject> getObjects() {
        return objects;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
