package oasis.artemis.listener.physics;

import oasis.artemis.event.listener.EventHandler;
import oasis.artemis.event.listener.HandlerPriority;
import oasis.artemis.event.listener.Listener;
import oasis.artemis.event.physics.CollisionEvent;
import oasis.artemis.object.ArtemisObject;
import oasis.artemis.util.group.Pair;

import javax.annotation.Nonnull;

/**
 * <h2>CollisionListener</h2>
 * <p>Listens for collision events and handles them.</p>
 */
public final class CollisionListener implements Listener {
    @EventHandler(priority = HandlerPriority.POST_MONITOR)
    public void onCollision(@Nonnull CollisionEvent event) {
        if (event.isCancelled()) return; // Respect cancellation

        final Pair<ArtemisObject> objects = event.getObjects();
    }
}
