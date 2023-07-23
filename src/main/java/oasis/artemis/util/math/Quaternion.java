package oasis.artemis.util.math;

import oasis.artemis.annotation.Numeric;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.io.Serializable;

/**
 * <h2>Quaternion</h2>
 * <p>
 * Developed by William Rowan Hamilton in 1843, quaternions
 * are used in the modern day to calculate the rotation of points in 3D space.
 * </p>
 * <p>
 * Due to its efficient and flawless nature, most modern game engines including
 * Artemis utilize some form of quaternion-based rotation.
 * </p>
 */
@Immutable
public class Quaternion implements Comparable<Quaternion>, Serializable {
    //
    // Constants
    //

    /**
     * Absolute zero.
     */
    public static final Quaternion ZERO = new Quaternion(0, 0, 0, 0);

    /**
     * Identity quaternion. Represents no rotation.
     */
    public static final Quaternion IDENTITY_QUATERNION = new Quaternion(1, 0, 0, 0);

    //
    // Static initializers
    //

    /**
     * Returns a rotation quaternion constructed from an axis/angle notation.
     * Axis is automatically converted to a unit vector.
     * When there is no rotation, this will return {@link Quaternion#IDENTITY_QUATERNION}.
     *
     * @param axis  Axis of rotation
     * @param angle Angle of rotation (in radians)
     * @return Rotation quaternion of given axis/angle
     */
    @Nonnull
    public static Quaternion fromAxisAngle(@Nonnull Vector axis, @Numeric double angle) {
        final Vector unit = axis.toUnitVector();
        if (unit.equals(Vector.ZERO)) {
            return IDENTITY_QUATERNION; // No rotation
        }

        return new Quaternion(Math.cos(angle / 2), unit.multiply(Math.sin(angle / 2)));
    }


    //
    // Constructors
    //

    /**
     * Creates a new quaternion from four scalars.
     *
     * @param w W value of this quaternion
     * @param x X value of this quaternion
     * @param y Y value of this quaternion
     * @param z Z value of this quaternion
     */
    public Quaternion(@Numeric double w, @Numeric double x, @Numeric double y, @Numeric double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new quaternion from a scalar and a vector.
     *
     * @param s Scalar to use
     * @param v Vector to use
     */
    public Quaternion(@Numeric double s, @Nonnull Vector v) {
        this(s, v.getX(), v.getY(), v.getZ());
    }


    //
    // Variables
    //

    @Numeric
    private final double w;
    @Numeric
    private final double x;
    @Numeric
    private final double y;
    @Numeric
    private final double z;

    //
    // Getters
    //

    /**
     * Gets the W value of this quaternion.
     *
     * @return W value
     */
    @Numeric
    public double getW() {
        return w;
    }

    /**
     * Gets the X value of this quaternion.
     *
     * @return X value
     */
    @Numeric
    public double getX() {
        return x;
    }

    /**
     * Gets the Y value of this quaternion.
     *
     * @return Y value
     */
    @Numeric
    public double getY() {
        return y;
    }

    /**
     * Gets the Z value of this quaternion.
     *
     * @return Z value
     */
    @Numeric
    public double getZ() {
        return z;
    }

    /**
     * Gets the vector part of this quaternion.
     *
     * @return Vector part
     */
    @Nonnull
    public Vector getVectorPart() {
        return new Vector(x, y, z);
    }

    /**
     * Gets the magnitude of this quaternion.
     *
     * @return Magnitude
     */
    @Numeric
    public double getMagnitude() {
        return Math.sqrt(Math.pow(w, 2) + Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    //
    // Setters
    //

    /**
     * Sets the W value of this quaternion.
     * This does not change the value of this instance, but returns a new modified instance.
     *
     * @param w W value to set to
     * @return Resulting quaternion
     */
    @Nonnull
    public Quaternion setW(@Numeric double w) {
        return new Quaternion(w, x, y, z);
    }

    /**
     * Sets the X value of this quaternion.
     * This does not change the value of this instance, but returns a new modified instance.
     *
     * @param x X value to set to
     * @return Resulting quaternion
     */
    @Nonnull
    public Quaternion setX(@Numeric double x) {
        return new Quaternion(w, x, y, z);
    }

    /**
     * Sets the Y value of this quaternion.
     * This does not change the value of this instance, but returns a new modified instance.
     *
     * @param y Y value to set to
     * @return Resulting quaternion
     */
    @Nonnull
    public Quaternion setY(@Numeric double y) {
        return new Quaternion(w, x, y, z);
    }

    /**
     * Sets the Z value of this quaternion.
     * This does not change the value of this instance, but returns a new modified instance.
     *
     * @param z Z value to set to
     * @return Resulting quaternion
     */
    @Nonnull
    public Quaternion setZ(@Numeric double z) {
        return new Quaternion(w, x, y, z);
    }

    /**
     * Sets the vector part of this quaternion.
     * This does not change the value of this instance, but returns a new modified instance.
     *
     * @param v Vector part to use
     * @return Resulting quaternion
     */
    @Nonnull
    public Quaternion setVectorPart(@Nonnull Vector v) {
        return new Quaternion(w, v);
    }

    //
    // Quaternion-scalar arithmetic
    //

    /**
     * Adds a scalar to this quaternion.
     *
     * @param s Scalar to add
     * @return Resulting quaternion
     */
    @Nonnull
    public Quaternion add(@Numeric double s) {
        return new Quaternion(w + s, x + s, y + s, z + s);
    }

    /**
     * Subtracts a scalar from this quaternion.
     *
     * @param s Scalar to subtract
     * @return Resulting quaternion
     */
    @Nonnull
    public Quaternion subtract(@Numeric double s) {
        return new Quaternion(w - s, x - s, y - s, z - s);
    }

    /**
     * Performs quaternion-scalar multiplication.
     *
     * @param s Scalar to multiply with
     * @return Resulting quaternion
     */
    @Nonnull
    public Quaternion multiply(@Numeric double s) {
        return new Quaternion(w * s, x * s, y * s, z * s);
    }

    /**
     * Scales a rotation quaternion by given scalar.
     * This effectively scales the rotation by a modifier.
     *
     * @param s Scalar to scale to
     * @return Scaled quaternion
     */
    @Nonnull
    public Quaternion scale(@Numeric double s) {
        // No need to scale identity quaternions
        if (w == 1) return IDENTITY_QUATERNION;

        // This is a simplified equation of converting this to axis/angle, scaling the angle, then converting it back
        final double acos = Math.acos(w);
        return new Quaternion(
                Math.cos(acos * s),
                getVectorPart().divide(Math.sin(acos)).multiply(Math.sin(acos * s))
        );
    }

    /**
     * Performs quaternion-scalar division.
     *
     * @param s Scalar to use as denominator
     * @return Resulting quaternion
     * @throws ArithmeticException When the denominator is zero
     */
    @Nonnull
    public Quaternion divide(@Numeric double s) throws ArithmeticException {
        if (s == 0) throw new ArithmeticException("Cannot divide by zero.");
        return new Quaternion(w / s, x / s, y / s, z / s);
    }

    //
    // Quaternion-quaternion arithmetic
    //

    /**
     * Performs quaternion-quaternion multiplication.
     * This is an operation where {@code this} is on the left, and {@code q} is on the right.
     *
     * @param q Quaternion to multiply with
     * @return Left-product of two quaternions
     */
    @Nonnull
    public Quaternion multiply(@Nonnull Quaternion q) {
        final double s = (w * q.w) - getVectorPart().dot(q.getVectorPart());
        final Vector v = q.getVectorPart().multiply(w)
                .add(getVectorPart().multiply(q.w))
                .add(q.getVectorPart().cross(getVectorPart()));

        return new Quaternion(s, v);
    }

    //
    // Comparison
    //

    /**
     * Compares this quaternion to another.
     * Only the magnitudes are compared.
     *
     * @param other Quaternion to compare to
     * @return {@code 0} if the magnitudes are equal, {@code -1} if this quaternion's magnitude is smaller,
     * {@code 1} if this quaternion's magnitude is larger
     */
    @Override
    public int compareTo(@Nonnull Quaternion other) {
        return Double.compare(getMagnitude(), other.getMagnitude());
    }

    /**
     * Checks for equality between two quaternions.
     *
     * @param other Quaternion to compare to
     * @return {@code true} if quaternions are equal
     */
    public boolean equals(@Nonnull Quaternion other) {
        return w == other.w && x == other.x && y == other.y && z == other.z;
    }

    //
    // Util
    //

    /**
     * Gets the conjugate of this quaternion.
     *
     * @return Conjugate
     */
    @Nonnull
    public Quaternion getConjugate() {
        return new Quaternion(w, -x, -y, -z);
    }

    /**
     * Gets the inverse of this quaternion.
     * If an inverse cannot be derived (the magnitude is zero), this will return {@link Quaternion#ZERO}.
     *
     * @return Inverse
     */
    @Nonnull
    public Quaternion getInverse() {
        try {
            return getConjugate().divide(Math.pow(getMagnitude(), 2));
        } catch (ArithmeticException e) {
            return ZERO;
        }
    }


    /**
     * Converts this quaternion to a string.
     *
     * @return Stringified quaternion
     */
    @Override
    @Nonnull
    public String toString() {
        return "Quaternion{" +
                "w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
