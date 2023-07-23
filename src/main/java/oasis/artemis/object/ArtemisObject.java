package oasis.artemis.object;

import oasis.artemis.annotation.Numeric;
import oasis.artemis.util.geometry.profile.GeometricProfile;
import oasis.artemis.util.geometry.solid.Solid;
import oasis.artemis.util.math.Quaternion;
import oasis.artemis.util.math.Vector;
import org.joda.time.Duration;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>ArtemisObject</h2>
 * <p>Objects can be placed in worlds and interact with other objects.</p>
 */
public interface ArtemisObject {
    //
    // Identification
    //

    /**
     * Gets the unique identifier of this object.
     *
     * @return Unique ID
     */
    @Nonnull
    UUID getUniqueId();

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
    // Properties
    //

    /**
     * Gets the mass of this object.
     *
     * @return Mass
     */
    @Nonnegative
    double getMass();

    /**
     * Gets the density of this object.
     *
     * @return Density
     */
    @Nonnegative
    double getDensity();

    /**
     * Gets the geometric profile of this object.
     *
     * @return Geometric profile
     */
    @Nonnull
    GeometricProfile getGeometry();

    /**
     * Gets the discrete solid of this object.
     *
     * @return Solid
     */
    @Nonnull
    Solid getSolid();

    /**
     * Sets the mass of this object.
     *
     * @param mass Mass
     */
    void setMass(@Nonnegative double mass);

    /**
     * Sets the geometric profile of this object.
     *
     * @param geometry Geometric profile
     */
    void setGeometry(@Nonnull GeometricProfile geometry);

    /**
     * Checks if given point is within this object's bounds.
     *
     * @param point Point to check
     * @return {@code true} if this object's solid contains given point
     */
    boolean contains(@Nonnull Vector point);

    /**
     * Checks if given object overlaps with {@code this}.
     *
     * @param other Object to check
     * @return {@code true} if the two objects overlap
     */
    boolean overlaps(@Nonnull ArtemisObject other);

    //
    // Location
    //

    /**
     * Gets the location of this object.
     *
     * @return Location
     */
    @Nonnull
    Vector getLocation();

    /**
     * Gets the acceleration of this object.
     *
     * @return Acceleration
     */
    @Nonnull
    Vector getAcceleration();

    /**
     * Gets the velocity of this object.
     *
     * @return Velocity
     */
    @Nonnegative
    double getVelocity();

    /**
     * Gets the rotation of this object.
     *
     * @return Rotation
     */
    @Nonnull
    Quaternion getRotation();

    /**
     * Gets the rate of rotation of this oobject.
     *
     * @return Rate of rotation
     */
    @Nonnull
    Quaternion getRotationRate();

    /**
     * Sets the location of this object.
     *
     * @param location Location
     */
    void setLocation(@Nonnull Vector location);

    /**
     * Sets the acceleration of this object.
     *
     * @param acceleration Acceleration
     */
    void setAcceleration(@Nonnull Vector acceleration);

    /**
     * Sets the rotation of this object.
     *
     * @param rotation Rotation
     */
    void setRotation(@Nonnull Quaternion rotation);

    /**
     * Sets the rate of rotation of this object.
     *
     * @param rate Rate of rotation
     */
    void setRotationRate(@Nonnull Quaternion rate);

    /**
     * Moves this object by given delta.
     *
     * @param delta Delta to apply
     */
    void move(@Nonnull Vector delta);

    /**
     * Rotates this object by given rotation.
     *
     * @param rotation Rotation to apply
     */
    void rotate(@Nonnull Quaternion rotation);

    /**
     * Accelerates this object.
     *
     * @param acceleration Acceleration to apply
     */
    void accelerate(@Nonnull Vector acceleration);

    /**
     * Accelerates this object.
     *
     * @param direction Direction of acceleration
     * @param force     Force of acceleration
     */
    void accelerate(@Nonnull Vector direction, @Numeric double force);

    /**
     * Decelerates this object.
     * If the object has no motion, this will do nothing.
     *
     * @param force Force of deceleration
     */
    void decelerate(@Numeric double force);
}
