package oasis.artemis.task.physics;

import oasis.artemis.Artemis;
import oasis.artemis.object.Physical;
import oasis.artemis.task.TaskAdapter;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>MovementTask</h2>
 * <p>Handles applying acceleration to objects.</p>
 */
public final class MovementTask extends TaskAdapter {
    @Override
    public void execute(@Nonnull Duration delta) {
        Artemis.getLevelManager().getLevels().forEach(l -> l.getObjects().stream()
                .filter(Physical.class::isInstance)
                .map(Physical.class::cast)
                .forEach(o -> o.move(o.getAcceleration().multiply((double) delta.getMillis() / 1000))));
    }
}
