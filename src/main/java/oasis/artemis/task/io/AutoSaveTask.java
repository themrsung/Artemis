package oasis.artemis.task.io;

import oasis.artemis.Artemis;
import oasis.artemis.task.TaskAdapter;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>AutoSaveTask</h2>
 * <p>Handles auto-saving.</p>
 */
public final class AutoSaveTask extends TaskAdapter {
    @Override
    public void execute(@Nonnull Duration delta) {
        Artemis.save();
    }

    @Nonnull
    @Override
    public Duration getInterval() {
        return Duration.standardMinutes(5);
    }
}
