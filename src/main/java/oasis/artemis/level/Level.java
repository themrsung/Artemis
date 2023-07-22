package oasis.artemis.level;

import oasis.artemis.annotation.Numeric;
import oasis.artemis.object.ArtemisObject;
import oasis.artemis.util.group.Pair;
import oasis.artemis.util.math.Vector;
import oasis.artemis.util.physics.PhysicsContext;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Set;
import java.util.UUID;

/**
 * <h2>Level</h2>
 * <p>
 * A level represents an in-game level.
 * Levels can be dynamic (e.g. worlds) or static (e.g. start screens).
 * </p>
 */
public interface Level {
    //
    // Identification
    //

    /**
     * Gets the unique identifier of this level.
     * Unique IDs must be consistent within the scope of its runtime.
     *
     * @return Unique identifier of this level
     */
    @Nonnull
    UUID getUniqueId();

    //
    // Objects
    //

    /**
     * Gets a set of objects within this level.
     *
     * @return Set of objects
     */
    @Nonnull
    Set<ArtemisObject> getObjects();

    /**
     * Gets a set of overlapping objects.
     *
     * @return Set of overlapping objects
     */
    @Nonnull
    Set<Pair<ArtemisObject>> getOverlappingObjects();

    /**
     * Adds an object to this level.
     *
     * @param object Object to add
     */
    void addObject(@Nonnull ArtemisObject object);

    /**
     * Removes an object from this level.
     *
     * @param object Object to remove
     */
    void removeObject(@Nonnull ArtemisObject object);

    /**
     * Checks for overlapping objects and handles collisions.
     */
    void checkOverlaps();

    //
    // Physics
    //

    /**
     * Gets the physics context of given object.
     *
     * @param object Object to query
     * @return Physics context of object
     * @throws IllegalArgumentException When the object is not a member of this level
     */
    @Nonnull
    PhysicsContext getPhysicsContext(@Nonnull ArtemisObject object) throws IllegalArgumentException;

    /**
     * Gets the gravity of this level.
     *
     * @return Gravity
     */
    @Nonnull
    Vector getGravity();

    /**
     * Gets the air density of this world.
     *
     * @return Air density
     */
    @Nonnegative
    double getAirDensity();

    /**
     * Gets the ground level of this world.
     *
     * @return Ground level
     */
    @Numeric
    double getGroundLevel();

    /**
     * Sets the gravity of this level.
     *
     * @param gravity Gravity
     */
    void setGravity(@Nonnull Vector gravity);

    /**
     * Sets the air density of this level.
     *
     * @param density Air density
     */
    void setAirDensity(@Nonnegative double density);

    /**
     * Sets the ground level of this level.
     *
     * @param level Ground level
     */
    void setGroundLevel(@Numeric double level);
}
