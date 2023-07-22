package oasis.artemis.task;

import oasis.artemis.task.lifecycle.Scheduler;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>TaskAdapter</h2>
 * <p>
 * An adapter class for {@link Task}.
 * Extend this class for easier implementation.
 * </p>
 */
public abstract class TaskAdapter implements Task {
    @Nonnull
    @Override
    public Duration getInterval() {
        // This returns 1ms since 0ms can cause the delta to be zero, which can break time-sensitive tasks
        return new Duration(1);
    }

    @Override
    public void onRegistered(@Nonnull Scheduler scheduler) {

    }

    @Override
    public void onUnregistered(@Nonnull Scheduler scheduler) {

    }
}
