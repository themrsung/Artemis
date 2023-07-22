package oasis.artemis.task.physics;

import oasis.artemis.Artemis;
import oasis.artemis.object.ArtemisObject;
import oasis.artemis.object.Physical;
import oasis.artemis.task.TaskAdapter;
import oasis.artemis.util.group.Pair;
import oasis.artemis.util.physics.Physics;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * <h2>ResistanceTask</h2>
 * <p>Handles applying fluid resistance to objects.</p>
 */
public final class ResistanceTask extends TaskAdapter {
    @Override
    public void execute(@Nonnull Duration delta) {
        Artemis.getLevelManager().getLevels().forEach(l -> {
            final Set<Pair<ArtemisObject>> overlaps = l.getOverlappingObjects();
            l.getObjects().stream()
                    .filter(Physical.class::isInstance)
                    .map(Physical.class::cast)
                    .forEach(p -> p.decelerate(Physics.dragForce(p, l.getPhysicsContext(p))));
        });
    }
}
