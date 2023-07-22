package oasis.artemis.task.physics;

import oasis.artemis.Artemis;
import oasis.artemis.object.Physical;
import oasis.artemis.task.TaskAdapter;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>RotationTask</h2>
 * <p>Handles applying rotation to objects.</p>
 */
public final class RotationTask extends TaskAdapter {
    @Override
    public void execute(@Nonnull Duration delta) {
        Artemis.getLevelManager().getLevels().forEach(l -> l.getObjects().stream()
                .filter(Physical.class::isInstance)
                .map(Physical.class::cast).toList()
                .forEach(o -> o.rotate(o.getRotationRate().divide(1000).multiply(delta.getMillis()))));
    }
}
