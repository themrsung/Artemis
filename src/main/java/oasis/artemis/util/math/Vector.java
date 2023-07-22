package oasis.artemis.util.math;

import oasis.artemis.annotation.Numeric;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.io.Serializable;

/**
 * <h2>Vector</h2>
 * <p>
 * A vector has a direction and magnitude.
 * Vectors are built for 3D operations, but can be used in
 * two-dimensional context if the one of the axes is fixed to zero.
 * </p>
 */
@Immutable
public class Vector implements Comparable<Vector>, Serializable {
    //
    // Constants
    //

    /**
     * Absolute zero. Represents a vector with no direction or magnitude.
     */
    public static final Vector ZERO = new Vector(0, 0, 0);

    /**
     * A unit vector denoting positive X.
     */
    public static final Vector POSITIVE_X = new Vector(1, 0, 0);

    /**
     * A unit vector denoting positive Y.
     */
    public static final Vector POSITIVE_Y = new Vector(0, 1, 0);

    /**
     * A unit vector denoting positive Z.
     */
    public static final Vector POSITIVE_Z = new Vector(0, 0, 1);

    /**
     * A unit vector denoting negative X.
     */
    public static final Vector NEGATIVE_X = new Vector(-1, 0, 0);

    /**
     * A unit vector denoting negative Y.
     */
    public static final Vector NEGATIVE_Y = new Vector(0, -1, 0);

    /**
     * A unit vector denoting negative Z.
     */
    public static final Vector NEGATIVE_Z = new Vector(0, 0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector from given values.
     *
     * @param x X value of this vector
     * @param y Y value of this vector
     * @param z Z value of this vector
     */
    public Vector(@Numeric double x, @Numeric double y, @Numeric double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //
    // Variables
    //

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
     * Gets the X value of this vector.
     *
     * @return X value
     */
    @Numeric
    public double getX() {
        return x;
    }

    /**
     * Gets the Y value of this vector.
     *
     * @return Y value
     */
    @Numeric
    public double getY() {
        return y;
    }

    /**
     * Gets the Z value of this vector.
     *
     * @return Z value
     */
    @Numeric
    public double getZ() {
        return z;
    }

    /**
     * Gets the magnitude of this vector.
     *
     * @return Magnitude
     */
    @Numeric
    public double getMagnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * Gets the magnitude of this vector in Manhattan-metric.
     * This is a sum of all the component values' absolutes.
     *
     * @return Manhattan magnitude
     */
    @Numeric
    public double getManhattanMagnitude() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    //
    // Setters
    //

    /**
     * Sets the X value of this vector.
     * This does not change the value of this instance, but returns a new modified instance.
     *
     * @param x X value to set to
     * @return Resulting vector
     */
    @Nonnull
    public Vector setX(@Numeric double x) {
        return new Vector(x, y, z);
    }

    /**
     * Sets the Y value of this vector.
     * This does not change the value of this instance, but returns a new modified instance.
     *
     * @param y Y value to set to
     * @return Resulting vector
     */
    @Nonnull
    public Vector setY(@Numeric double y) {
        return new Vector(x, y, z);
    }

    /**
     * Sets the Z value of this vector.
     * This does not change the value of this instance, but returns a new modified instance.
     *
     * @param z Z value to set to
     * @return Resulting vector
     */
    @Nonnull
    public Vector setZ(@Numeric double z) {
        return new Vector(x, y, z);
    }

    //
    // Vector-scalar arithmetic
    //

    /**
     * Adds a scalar to this vector.
     *
     * @param s Scalar to add
     * @return Resulting vector
     */
    @Nonnull
    public Vector add(@Numeric double s) {
        return new Vector(x + s, y + s, z + s);
    }

    /**
     * Subtracts a scalar from this vector.
     *
     * @param s Scalar to subtract
     * @return Resulting vector
     */
    @Nonnull
    public Vector subtract(@Numeric double s) {
        return new Vector(x - s, y - s, z - s);
    }

    /**
     * Performs vector-scalar multiplication.
     *
     * @param s Scalar to multiply with
     * @return Resulting vector
     */
    @Nonnull
    public Vector multiply(@Numeric double s) {
        return new Vector(x * s, y * s, z * s);
    }

    /**
     * Performs vector-scalar division.
     *
     * @param s Scalar to use as denominator
     * @return Resulting vector
     * @throws ArithmeticException When denominator is zero
     */
    @Nonnull
    public Vector divide(@Numeric double s) throws ArithmeticException {
        if (s == 0) throw new ArithmeticException("Cannot divide by zero.");
        return new Vector(x / s, y / s, z / s);
    }

    //
    // Vector-vector arithmetic
    //

    /**
     * Adds another vector to this vector.
     *
     * @param v Vector to add
     * @return Resulting vector
     */
    @Nonnull
    public Vector add(@Nonnull Vector v) {
        return new Vector(x + v.x, y + v.y, z + v.z);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v Vector to remove
     * @return Resulting vector
     */
    @Nonnull
    public Vector subtract(@Nonnull Vector v) {
        return new Vector(x - v.x, y - v.y, z - v.z);
    }

    /**
     * Gets the dot product of this vector and {@code v}.
     *
     * @param v Vector to multiply with
     * @return Dot product of two vectors
     */
    @Numeric
    public double dot(@Nonnull Vector v) {
        return x * v.x + y * v.y + z * v.z;
    }

    /**
     * Gets the cross product of this vector and {@code v}.
     * This is a cross product operation where {@code this} is on the left.
     *
     * @param v Vector to multiply with
     * @return Cross product of two vectors.
     */
    @Nonnull
    public Vector cross(@Nonnull Vector v) {
        return new Vector(
                y * v.z - z * v.y,
                z * v.x - x * v.z,
                x * v.y - y * v.x
        );
    }

    //
    // Comparison
    //

    /**
     * Compares this vector to another.
     *
     * @param other Vector to compare to
     * @return {@code 0} if the magnitudes are equal, {@code -1} this vector's magnitude is smaller,
     * {@code 1} if this vector's magnitude is larger
     */
    @Override
    public int compareTo(@Nonnull Vector other) {
        return Double.compare(getMagnitude(), other.getMagnitude());
    }

    /**
     * Checks for equality between two vectors.
     *
     * @param other Vector to compare to
     * @return {@code true} if the vectors are equal
     */
    public boolean equals(@Nonnull Vector other) {
        return x == other.x && y == other.y && z == other.z;
    }

    //
    // Util
    //

    /**
     * Negates this vector.
     *
     * @return Negated vector
     */
    @Nonnull
    public Vector negate() {
        return multiply(-1);
    }

    /**
     * Gets the distance from {@code this} to another vector.
     *
     * @param v Vector to get distance of
     * @return Distance between two vectors
     */
    @Numeric
    public double distanceTo(@Nonnull Vector v) {
        return subtract(v).getMagnitude();
    }

    /**
     * Rotates this vector by a rotation quaternion.
     * If the given quaternion is not a unit quaternion, the magnitude of this vector will be altered.
     *
     * @param rq Rotation quaternion to rotate by
     * @return Rotated vector
     */
    @Nonnull
    public Vector rotate(@Nonnull Quaternion rq) {
        return rq.multiply(toPureQuaternion()).multiply(rq.getConjugate()).getVectorPart();
    }

    /**
     * Converts this vector to a unit vector.
     * If a unit vector cannot be derived (if the magnitude is zero), this will return {@link Vector#ZERO}.
     *
     * @return Unit vector of {@code this}
     */
    @Nonnull
    public Vector toUnitVector() {
        try {
            return divide(getMagnitude());
        } catch (ArithmeticException e) {
            return ZERO;
        }
    }

    /**
     * Converts this vector to a pure quaternion.
     *
     * @return Pure quaternion of {@code this}
     */
    @Nonnull
    public Quaternion toPureQuaternion() {
        return new Quaternion(0, this);
    }

    /**
     * Converts this vector to a string.
     *
     * @return Stringified vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
