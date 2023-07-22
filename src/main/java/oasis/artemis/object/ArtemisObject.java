package oasis.artemis.object;

import oasis.artemis.annotation.Numeric;
import oasis.artemis.util.geometry.profile.GeometricProfile;
import oasis.artemis.util.geometry.solid.Solid;
import oasis.artemis.util.math.Quaternion;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>ArtemisObject</h2>
 * <p>
 * Objects can exist in levels, and interact with other objects placed in the same level.
 * </p>
 */
public interface ArtemisObject {

    //
    // Getters
    //

    /**
     * Gets the unique identifier of this object.
     *
     * @return Unique ID
     */
    @Nonnull
    UUID getUniqueId();

    /**
     * Gets the location of this object.
     *
     * @return Location
     */
    @Nonnull
    Vector getLocation();

    /**
     * Gets the rotation of this object.
     *
     * @return Rotation
     */
    @Nonnull
    Quaternion getRotation();

    /**
     * Gets the geometry of this object.
     *
     * @return Geometry
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

    //
    // Setters
    //

    /**
     * Sets the location of this object.
     *
     * @param location Location
     */
    void setLocation(@Nonnull Vector location);

    /**
     * Sets the rotation of this object.
     *
     * @param rotation Rotation
     */
    void setRotation(@Nonnull Quaternion rotation);

    /**
     * Sets the geometry of this object.
     *
     * @param geometry Geometry
     */
    void setGeometry(@Nonnull GeometricProfile geometry);

    //
    // Util
    //

    /**
     * Moves this object by given delta.
     *
     * @param delta Delta to apply
     */
    void move(@Nonnull Vector delta);

    /**
     * Rotates this object by given rotation.
     *
     * @param rotation Rotation to rotate by
     */
    void rotate(@Nonnull Quaternion rotation);

    /**
     * Rotates this object.
     *
     * @param axis  Axis of rotation
     * @param angle Angle of rotation
     */
    void rotate(@Nonnull Vector axis, @Numeric double angle);
}
