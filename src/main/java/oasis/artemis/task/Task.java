package oasis.artemis.task;

import oasis.artemis.task.lifecycle.Scheduler;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>Task</h2>
 * <p>A task can be performed on regular intervals.</p>
 */
public interface Task {
    //
    // Execution
    //

    /**
     * Executes this task.
     *
     * @param delta The duration it took between the last execution and now
     */
    void execute(@Nonnull Duration delta);

    /**
     * Gets the interval of this task the scheduler should respect.
     *
     * @return Interval of this task
     */
    @Nonnull
    Duration getInterval();

    //
    // Events
    //

    /**
     * Called upon registration to a scheduler.
     *
     * @param scheduler Scheduler this task was registered to
     */
    void onRegistered(@Nonnull Scheduler scheduler);

    /**
     * Called upon unregistration from a scheduler.
     *
     * @param scheduler Scheduler this task was unregistered from
     */
    void onUnregistered(@Nonnull Scheduler scheduler);
}
