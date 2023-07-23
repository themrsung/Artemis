package oasis.artemis.listener.physics;

import oasis.artemis.event.listener.EventHandler;
import oasis.artemis.event.listener.HandlerPriority;
import oasis.artemis.event.listener.Listener;
import oasis.artemis.event.physics.CollisionEvent;
import oasis.artemis.object.ArtemisObject;
import oasis.artemis.util.group.Pair;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>CollisionListener</h2>
 * <p>Listens for collision events and handles them.</p>
 */
public final class CollisionListener implements Listener {
    @EventHandler(priority = HandlerPriority.POST_MONITOR)
    public void onCollision(@Nonnull CollisionEvent event) {
        if (event.isCancelled()) return; // Respect cancellation

        final ArtemisObject o1 = event.getObjects().getFirst();
        final ArtemisObject o2 = event.getObjects().getSecond();

        final Pair<Vector> velocities =
                getFinalVelocities(o1.getAcceleration(), o2.getAcceleration(), o1.getMass(), o2.getMass());

        o1.setAcceleration(velocities.getFirst());
        o2.setAcceleration(velocities.getSecond());
    }

    /**
     * Calculates the final velocities post-collision.
     *
     * @param u1 Initial velocity of first object
     * @param u2 Initial velocity of second object
     * @param m1 Mass of first object
     * @param m2 Mass of second object
     * @return Pair of final velocities which correspond to each object
     */
    @Nonnull
    private Pair<Vector> getFinalVelocities(
            @Nonnull Vector u1,
            @Nonnull Vector u2,
            @Nonnegative double m1,
            @Nonnegative double m2
    ) {
        return new Pair<>(
                // TODO Filter out arithmetic exceptions instead of salting Double.MIN_VALUE
                u1.multiply(m1 - m2).divide(m1 + m2 + Double.MIN_VALUE).add(u2.multiply(2 * m2).divide(m1 + m2 + Double.MIN_VALUE)),
                u1.multiply(2 * m1).divide(m1 + m2 + Double.MIN_VALUE).add(u2.multiply(m2 - m1).divide(m1 + m2 + Double.MIN_VALUE))
        );
    }
}
