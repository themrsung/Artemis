package oasis.artemis.task.lifecycle;

import oasis.artemis.task.Task;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>Scheduler</h2>
 * <p>Schedulers handle calling tasks on a regular basis.</p>
 */
public interface Scheduler {
    //
    // Tasks
    //

    /**
     * Registers a task to this scheduler.
     *
     * @param task Task to register
     */
    void registerTask(@Nonnull Task task);

    /**
     * Registers multiple tasks to this scheduler.
     *
     * @param tasks Tasks to register
     */
    void registerTasks(@Nonnull Task... tasks);

    /**
     * Registers multiple tasks to this scheduler.
     *
     * @param tasks List of tasks to register
     */
    void registerTasks(@Nonnull List<Task> tasks);

    /**
     * Unregisters a task from this scheduler.
     *
     * @param task Task to unregister
     */
    void unregisterTask(@Nonnull Task task);

    /**
     * Unregisters multiple tasks from this scheduler.
     *
     * @param tasks Tasks to unregister
     */
    void unregisterTasks(@Nonnull Task... tasks);

    /**
     * Unregisters multiple tasks from this scheduler.
     *
     * @param tasks List of tasks to unregister
     */
    void unregisterTasks(@Nonnull List<Task> tasks);

    //
    // Controls
    //

    /**
     * Starts the scheduler.
     */
    void start();

    /**
     * Stops the scheduler.
     */
    void stop();
}
