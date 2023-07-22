package oasis.artemis.task.lifecycle;

import javax.annotation.Nonnull;

/**
 * <h2>SyncScheduler</h2>
 * <p>
 *     A perfectly synchronous scheduler.
 *     Tasks are guaranteed to be executed in sequence.
 * </p>
 */
public final class SyncScheduler extends AbstractScheduler {
    /**
     * Scheduler thread. Default accuracy is 1ms.
     */
    @Nonnull
    private final SchedulerThread thread = new SchedulerThread(this, 1);

    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }
}
