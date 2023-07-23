package oasis.artemis.level;

import oasis.artemis.object.ArtemisObject;
import oasis.artemis.util.math.Vector;
import org.joda.time.Duration;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Set;
import java.util.UUID;

/**
 * <h2>Level</h2>
 * <p>Represents an in-game level.</p>
 */
public interface Level {
    //
    // Constants
    //

    /**
     * The gravity of Earth.
     */
    Vector EARTH_GRAVITY = new Vector(0, -9.807, 0);

    /**
     * The air density of Earth.
     */
    double EARTH_AIR_DENSITY = 1.293;

    //
    // Identification
    //

    /**
     * Gets the unique identifier of this level.
     *
     * @return Unique ID
     */
    @Nonnull
    UUID getUniqueId();

    /**
     * Gets the name of this level.
     *
     * @return Name
     */
    @Nonnull
    String getName();

    //
    // Tick
    //

    /**
     * Called every tick.
     *
     * @param delta Duration between the last tick and now
     */
    void tick(@Nonnull Duration delta);

    //
    // Objects
    //

    /**
     * Gets a set of objects in this level.
     *
     * @return Set of objects
     */
    @Nonnull
    Set<ArtemisObject> getObjects();

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

    //
    // Physics
    //

    /**
     * Gets the gravity vector of this level.
     *
     * @return Gravity
     */
    @Nonnull
    Vector getGravity();

    /**
     * Gets the air density of this level.
     *
     * @return Air density
     */
    @Nonnegative
    double getAirDensity();

    /**
     * Sets the gravity vector of this level.
     *
     * @param gravity Gravity
     */
    void setGravity(@Nonnull Vector gravity);

    /**
     * Sets the air density of this world.
     *
     * @param density Air density
     */
    void setAirDensity(@Nonnegative double density);

}
