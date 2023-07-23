package oasis.artemis.session.player;

import oasis.artemis.object.ArtemisObject;
import oasis.artemis.util.math.Quaternion;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>Player</h2>
 * <p>Represents an in-game player.</p>
 */
public interface Player {
    //
    // Identification
    //

    /**
     * Gets the unique identifier of this player.
     *
     * @return Unique ID
     */
    @Nonnull
    UUID getUniqueId();

    /**
     * Gets the name of this player.
     *
     * @return Name
     */
    @Nonnull
    String getName();

    //
    // Pawn
    //

    /**
     * Gets the object this player is controlling.
     *
     * @return Pawn of player
     */
    @Nonnull
    ArtemisObject getPawn();

    /**
     * Sets the pawn this player is controlling.
     *
     * @param pawn Pawn of player
     */
    void setPawn(@Nonnull ArtemisObject pawn);

    //
    // Delegates
    //

    /**
     * Gets the location of this player.
     *
     * @return Location
     */
    @Nonnull
    Vector getLocation();

    /**
     * Gets the acceleration of this player.
     *
     * @return Acceleration
     */
    @Nonnull
    Vector getAcceleration();

    /**
     * Gets the velocity of this player.
     *
     * @return Velocity
     */
    @Nonnegative
    double getVelocity();

    /**
     * Gets the rotation of this player.
     *
     * @return Rotation
     */
    @Nonnull
    Quaternion getRotation();

    /**
     * Gets the rate of rotation of this player.
     *
     * @return Rate of rotation
     */
    @Nonnull
    Quaternion getRotationRate();

    /**
     * Sets the location of this player.
     *
     * @param location Location
     */
    void setLocation(@Nonnull Vector location);

    /**
     * Sets the acceleration of this player.
     *
     * @param acceleration Acceleration
     */
    void setAcceleration(@Nonnull Vector acceleration);

    /**
     * Sets the rotation of this player.
     *
     * @param rotation Rotation
     */
    void setRotation(@Nonnull Quaternion rotation);

    /**
     * Sets the rate of rotation of this player.
     *
     * @param rate Rate of rotation
     */
    void setRotationRate(@Nonnull Quaternion rate);

    /**
     * Moves this player by given delta.
     *
     * @param delta Delta to apply
     */
    void move(@Nonnull Vector delta);

    /**
     * Rotates this player by given rotation.
     *
     * @param rotation Rotation to apply
     */
    void rotate(@Nonnull Quaternion rotation);

    /**
     * Accelerates this player.
     *
     * @param acceleration Acceleration to apply
     */
    void accelerate(@Nonnull Vector acceleration);
}
