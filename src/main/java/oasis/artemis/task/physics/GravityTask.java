package oasis.artemis.task.physics;

import oasis.artemis.Artemis;
import oasis.artemis.object.Physical;
import oasis.artemis.task.TaskAdapter;
import oasis.artemis.util.math.Vector;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>GravityTask</h2>
 * <p>Handles applying gravity to objects.</p>
 */
public final class GravityTask extends TaskAdapter {
    @Override
    public void execute(@Nonnull Duration delta) {
        Artemis.getLevelManager().getLevels().forEach(l -> {
            final Vector gravity = l.getGravity();

            l.getObjects().stream()
                    .filter(Physical.class::isInstance)
                    .map(Physical.class::cast).toList()
                    .forEach(o -> o.setAcceleration(o.getAcceleration().add(gravity)));
        });
    }
}
