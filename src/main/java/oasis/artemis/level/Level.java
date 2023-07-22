package oasis.artemis.level;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>Level</h2>
 * <p>
 * A level represents an in-game level.
 * Levels can be dynamic (e.g. worlds) or static (e.g. start screens).
 * </p>
 */
public interface Level {
    /**
     * Gets the unique identifier of this level.
     * Unique IDs must be consistent within the scope of its runtime.
     *
     * @return Unique identifier of this level
     */
    @Nonnull
    UUID getUniqueId();
}
