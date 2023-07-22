package oasis.artemis.task.lifecycle;

import oasis.artemis.task.Task;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h2>AbstractScheduler</h2>
 * <p>Handles the registration/unregistration of tasks only.</p>
 */
public abstract class AbstractScheduler implements Scheduler {
    @Nonnull
    protected final List<Task> tasks = new ArrayList<>();
    @Nonnull
    protected final List<Task> addCache = new ArrayList<>();
    @Nonnull
    protected final List<Task> removeCache = new ArrayList<>();

    @Override
    public void registerTask(@Nonnull Task task) {
        addCache.add(task);
        task.onRegistered(this);
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
        if (!addCache.remove(task)) {
            removeCache.add(task);
        }

        task.onUnregistered(this);

    }

    @Override
    public void unregisterTasks(@Nonnull Task... tasks) {
        Arrays.stream(tasks).forEach(this::unregisterTask);
    }

    @Override
    public void unregisterTasks(@Nonnull List<Task> tasks) {
        tasks.forEach(this::unregisterTasks);
    }
}
