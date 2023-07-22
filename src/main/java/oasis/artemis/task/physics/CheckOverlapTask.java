package oasis.artemis.task.physics;

import oasis.artemis.Artemis;
import oasis.artemis.level.Level;
import oasis.artemis.task.TaskAdapter;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>CheckOverlapTask</h2>
 * <p>Handles checking for overlapping objects in levels.</p>
 */
public final class CheckOverlapTask extends TaskAdapter {
    @Override
    public void execute(@Nonnull Duration delta) {
        Artemis.getLevelManager().getLevels().forEach(Level::checkOverlaps);
    }
}
