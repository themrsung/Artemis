package oasis.artemis.level.lifecycle;

import oasis.artemis.Artemis;
import oasis.artemis.level.Level;
import oasis.artemis.task.TaskAdapter;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * <h2>LevelManager</h2>
 * <p>Handles the lifecycle of levels.</p>
 */
public final class LevelManager {
    //
    // Levels
    //

    /**
     * Gets a set of all levels in this manager.
     *
     * @return Set of levels
     */
    @Nonnull
    public Set<Level> getLevels() {
        return new HashSet<>(levels);
    }

    /**
     * Gets a level by unique identifier.
     *
     * @param uniqueId Unique ID
     * @return Specified level
     * @throws NullPointerException When a level matching given unique ID cannot be found
     */
    @Nonnull
    public Level getLevel(@Nonnull UUID uniqueId) throws NullPointerException {
        for (Level level : levels) {
            if (level.getUniqueId().equals(uniqueId)) return level;
        }

        throw new NullPointerException("Level of given unique identifier does not exist.");
    }

    /**
     * Gets a level by name.
     *
     * @param name Name of level
     * @return Specified level if found, {@code null} if not
     */
    @Nullable
    public Level getLevel(@Nonnull String name) {
        for (Level level : levels) {
            if (level.getName().equals(name)) return level;
        }

        return null;
    }

    /**
     * Adds a level to this manager.
     *
     * @param level Level to add
     */
    public void addLevel(@Nonnull Level level) {
        levels.add(level);
    }

    /**
     * Removes a level from this manager.
     *
     * @param level Level to remove
     */
    public void removeLevel(@Nonnull Level level) {
        levels.remove(level);
    }

    @Nonnull
    private final Set<Level> levels = new HashSet<>();

    //
    // Tick
    //

    /**
     * Starts ticking levels.
     */
    public void start() {
        Artemis.getAsyncScheduler().registerTask(ticker);
    }

    /**
     * Stops ticking levels.
     */
    public void stop() {
        Artemis.getAsyncScheduler().unregisterTask(ticker);
    }

    private final LevelTickTask ticker = new LevelTickTask(this);

    /**
     * Handles ticking levels.
     */
    private static final class LevelTickTask extends TaskAdapter {
        public LevelTickTask(@Nonnull LevelManager parent) {
            this.parent = parent;
        }

        @Nonnull
        private final LevelManager parent;

        @Override
        public void execute(@Nonnull Duration delta) {
            parent.levels.forEach(l -> l.tick(delta));
        }
    }
}
