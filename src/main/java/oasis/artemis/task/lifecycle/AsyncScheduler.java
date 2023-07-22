package oasis.artemis.task.lifecycle;

import oasis.artemis.task.Task;
import oasis.artemis.util.iteration.Counter;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

/**
 * <h2>AsyncScheduler</h2>
 * <p>
 *     An asynchronous scheduler.
 *     Tasks are not guaranteed to be executed in sequence.
 * </p>
 */
public final class AsyncScheduler implements Scheduler {
    /**
     * An asynchronous scheduler is essentially a container of multiple synchronous schedulers.
     */
    @Nonnull
    private final SyncScheduler[] schedulers = {
            new SyncScheduler(),
            new SyncScheduler(),
            new SyncScheduler(),
            new SyncScheduler()
    };

    /**
     * A counter to manager task distribution.
     */
    @Nonnull
    private final Counter counter = new Counter(schedulers.length);

    @Override
    public void start() {
        Arrays.stream(schedulers).forEach(SyncScheduler::start);
    }

    @Override
    public void stop() {
        Arrays.stream(schedulers).forEach(SyncScheduler::stop);
    }

    @Override
    public void registerTask(@Nonnull Task task) {
        schedulers[counter.get()].registerTask(task);
    }

    @Override
    public void registerTasks(@Nonnull Task... tasks) {
        Arrays.stream(tasks).forEach(this::registerTask);
    }

    @Override
    public void registerTasks(@Nonnull List<Task> tasks) {
        tasks.forEach(this::registerTask);
    }

    @Override
    public void unregisterTask(@Nonnull Task task) {
        Arrays.stream(schedulers).forEach(s -> s.unregisterTask(task));
    }

    @Override
    public void unregisterTasks(@Nonnull Task... tasks) {
        Arrays.stream(tasks).forEach(this::unregisterTask);
    }

    @Override
    public void unregisterTasks(@Nonnull List<Task> tasks) {
        tasks.forEach(this::unregisterTask);
    }
}
