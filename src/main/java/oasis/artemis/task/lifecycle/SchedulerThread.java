package oasis.artemis.task.lifecycle;

import oasis.artemis.task.Task;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h2>SchedulerThread</h2>
 * <p>Used by a parent scheduler, a scheduler thread handles the execution of tasks.</p>
 */
public final class SchedulerThread extends Thread {
    @SuppressWarnings("BusyWait")
    public SchedulerThread(@Nonnull AbstractScheduler scheduler, @Nonnegative long accuracy) {
        super(() -> {
            final List<Task> tasks = scheduler.tasks;
            final List<Task> addCache = scheduler.addCache;
            final List<Task> removeCache = scheduler.removeCache;

            final Map<Task, DateTime> executionTimes = new HashMap<>();

            while (true) {
                try {
                    if (!addCache.isEmpty()) {
                        tasks.addAll(addCache);
                        addCache.clear();
                    }
                } catch (ConcurrentModificationException e) {
                    // Move on
                }

                try {
                    if (!removeCache.isEmpty()) {
                        tasks.removeAll(removeCache);
                        removeCache.forEach(executionTimes::remove); // Remove execution time cache
                        removeCache.clear();
                    }
                } catch (ConcurrentModificationException e) {
                    // Move on
                }

                try {
                    tasks.forEach(t -> {
                        final DateTime now = DateTime.now(); // Cache time for consistency
                        final DateTime lastExecution;
                        if (executionTimes.containsKey(t)) {
                            lastExecution = executionTimes.get(t);
                        } else {
                            executionTimes.put(t, now);
                            lastExecution = now;
                        }

                        final Duration delta = new Duration(lastExecution, now);

                        // Respect intervals
                        if (delta.isShorterThan(t.getInterval())) return;

                        // Call task
                        t.execute(delta);

                        // Keep time
                        executionTimes.put(t, now);
                    });
                } catch (ConcurrentModificationException e) {
                    // Move on
                }

                try {
                    Thread.sleep(accuracy);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
    }
}
