package oasis.artemis.util.math;

import oasis.artemis.annotation.Numeric;

import javax.annotation.Nonnull;

/**
 * <h2>RotationBuilder</h2>
 * <p>
 * Due to the abstractness of rotation quaternions,
 * a rotation builder can be used to easily create rotations from either
 * axis/angle representation, or Euler angles.
 * </p>
 * <p>
 * Definitions for Euler angles is as follows.
 * </p>
 * <p>
 * Yaw is defined as rotation along the Y axis.
 * 0 degrees is defined as facing towards positive Z, and increments counter-clockwise.
 * </p>
 * <p>
 * Pitch is defined as rotation along the X axis.
 * 0 degrees is defined as facing towards positive Z, and increments upwards.
 * </p>
 * <p>
 * Roll is defined as rotation along the Z axis.
 * 0 degrees is defined as facing towards positive X, and increments downwards.
 * </p>
 */
public final class RotationBuilder {
    //
    // Static constructors
    //

    /**
     * Starts building a rotation from axis/angle.
     *
     * @param axis         Axis of rotation
     * @param angleRadians Angle in radians
     * @return {@link RotationBuilder} instance
     */
    @Nonnull
    public static RotationBuilder fromAxisAngle(@Nonnull Vector axis, @Numeric double angleRadians) {
        return new RotationBuilder(Quaternion.fromAxisAngle(axis, angleRadians));
    }

    /**
     * Starts building a rotation from yaw in degrees.
     *
     * @param deg Yaw in degrees
     * @return {@link RotationBuilder} instance
     */
    @Nonnull
    public static RotationBuilder fromYawDegrees(@Numeric double deg) {
        return new RotationBuilder(Quaternion.fromAxisAngle(Vector.POSITIVE_Y, Math.toRadians(deg)));
    }

    /**
     * Starts building a rotation from yaw in radians.
     *
     * @param rads Yaw in radians
     * @return {@link RotationBuilder} instance
     */
    @Nonnull
    public static RotationBuilder fromYawRadians(@Numeric double rads) {
        return new RotationBuilder(Quaternion.fromAxisAngle(Vector.POSITIVE_Y, rads));
    }

    /**
     * Starts building a rotation from pitch in degrees.
     *
     * @param deg Pitch in degrees
     * @return {@link RotationBuilder} instance
     */
    @Nonnull
    public static RotationBuilder fromPitchDegrees(@Numeric double deg) {
        return new RotationBuilder(Quaternion.fromAxisAngle(Vector.POSITIVE_X, Math.toRadians(deg)));
    }

    /**
     * Starts building a rotation from pitch in radians.
     *
     * @param rads Pitch in radians
     * @return {@link RotationBuilder} instance
     */
    @Nonnull
    public static RotationBuilder fromPitchRadians(@Numeric double rads) {
        return new RotationBuilder(Quaternion.fromAxisAngle(Vector.POSITIVE_X, rads));
    }

    /**
     * Starts building a rotation from roll in degrees.
     *
     * @param deg Roll in degrees
     * @return {@link RotationBuilder} instance
     */
    @Nonnull
    public static RotationBuilder fromRollDegrees(@Numeric double deg) {
        return new RotationBuilder(Quaternion.fromAxisAngle(Vector.POSITIVE_Z, Math.toRadians(deg)));
    }

    /**
     * Starts building a rotation from roll in radians.
     *
     * @param rads Roll in radians
     * @return {@link RotationBuilder} instance
     */
    @Nonnull
    public static RotationBuilder fromRollRadians(@Numeric double rads) {
        return new RotationBuilder(Quaternion.fromAxisAngle(Vector.POSITIVE_Z, rads));
    }

    //
    // Constructors
    //

    /**
     * Starts the builder with no rotation.
     */
    public RotationBuilder() {
        this(Quaternion.IDENTITY_QUATERNION);
    }

    /**
     * Starts the builder with a rotation quaternion.
     *
     * @param quaternion Quaternion to use
     */
    public RotationBuilder(@Nonnull Quaternion quaternion) {
        this.quaternion = quaternion;
    }

    //
    // Variables
    //
    @Nonnull
    private Quaternion quaternion;

    /**
     * Adds radians to this rotation.
     *
     * @param axis Axis of rotation
     * @param rads Radians
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder addRadians(@Nonnull Vector axis, @Numeric double rads) {
        quaternion = Quaternion.fromAxisAngle(axis, rads).multiply(quaternion);
        return this;
    }

    /**
     * Adds degrees to this rotation.
     *
     * @param axis Axis of rotation
     * @param deg  Degrees
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder addDegrees(@Nonnull Vector axis, @Numeric double deg) {
        quaternion = Quaternion.fromAxisAngle(axis, Math.toRadians(deg)).multiply(quaternion);
        return this;
    }

    /**
     * Adds yaw to this rotation.
     *
     * @param deg Degrees of yaw
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder addYawDegrees(@Numeric double deg) {
        quaternion = Quaternion.fromAxisAngle(Vector.POSITIVE_Y, Math.toRadians(deg)).multiply(quaternion);
        return this;
    }

    /**
     * Adds yaw to this rotation.
     *
     * @param rads Radians of yaw
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder addYawRadians(@Numeric double rads) {
        quaternion = Quaternion.fromAxisAngle(Vector.POSITIVE_Y, rads).multiply(quaternion);
        return this;
    }

    /**
     * Adds pitch to this rotation.
     *
     * @param deg Degrees of pitch
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder addPitchDegrees(@Numeric double deg) {
        quaternion = Quaternion.fromAxisAngle(Vector.POSITIVE_X, Math.toRadians(deg)).multiply(quaternion);
        return this;
    }

    /**
     * Adds pitch to this rotation.
     *
     * @param rads Radians of pitch
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder addPitchRadians(@Numeric double rads) {
        quaternion = Quaternion.fromAxisAngle(Vector.POSITIVE_X, rads).multiply(quaternion);
        return this;
    }

    /**
     * Adds roll to this rotation.
     *
     * @param deg Degrees of roll
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder addRollDegrees(@Numeric double deg) {
        quaternion = Quaternion.fromAxisAngle(Vector.POSITIVE_Z, Math.toRadians(deg)).multiply(quaternion);
        return this;
    }

    /**
     * Adds roll to this rotation.
     *
     * @param rads Radians of roll
     * @return {@code this}
     */
    @Nonnull
    public RotationBuilder addRollRadians(@Numeric double rads) {
        quaternion = Quaternion.fromAxisAngle(Vector.POSITIVE_Z, rads).multiply(quaternion);
        return this;
    }


    //
    // Build
    //

    /**
     * Finalizes the building sequence and builds the rotation.
     *
     * @return Rotation quaternion
     */
    @Nonnull
    public Quaternion build() {
        return quaternion;
    }
}
