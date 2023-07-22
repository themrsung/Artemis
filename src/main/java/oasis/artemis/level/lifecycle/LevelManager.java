package oasis.artemis.level.lifecycle;

import oasis.artemis.level.Level;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

/**
 * <h2>LevelManager</h2>
 * <p>Handles the lifecycle of levels.</p>
 */
public final class LevelManager {
    //
    // Levels
    //

    /**
     * Gets a set of levels in this manager.
     *
     * @return Set of levels
     */
    @Nonnull
    public Set<Level> getLevels() {
        return new HashSet<>(levels);
    }

    /**
     * Adds a level to this manager.
     *
     * @param level Level to add
     */
    void addLevel(@Nonnull Level level) {
        levels.add(level);
    }

    /**
     * Removes a level from this manager.
     *
     * @param level Level to remove
     */
    void removeLevel(@Nonnull Level level) {
        levels.remove(level);
    }

    @Nonnull
    private final Set<Level> levels = new HashSet<>();
}
