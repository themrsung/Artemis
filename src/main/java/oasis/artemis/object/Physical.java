package oasis.artemis.object;

import oasis.artemis.annotation.Numeric;
import oasis.artemis.util.math.Quaternion;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>Physical</h2>
 * <p>
 * A physical object can interact physically with other physical objects.
 * Physical objects have real-life physical properties such as mass and acceleration.
 * </p>
 */
public interface Physical extends ArtemisObject {
    //
    // Getters
    //

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
     * Gets the rate of rotation of this object.
     *
     * @return Rate of rotation
     */
    @Nonnull
    Quaternion getRotationRate();

    /**
     * Gets the mass of this object.
     *
     * @return Mass of object
     */
    @Nonnegative
    double getMass();

    /**
     * Gets the current drag coefficient of this object.
     *
     * @return Coefficient of drag
     */
    @Nonnegative
    double getDragCoefficient();

    /**
     * Gets the current cross-section of this object
     * when viewed from an opposing vector of this object's acceleration.
     *
     * @return Cross-section
     */
    @Nonnegative
    double getCrossSection();

    //
    // Setters
    //

    /**
     * Sets the acceleration of this object.
     *
     * @param acceleration Acceleration
     */
    void setAcceleration(@Nonnull Vector acceleration);

    /**
     * Sets the rate of rotation of this object.
     *
     * @param rate Rate of rotation
     */
    void setRotationRate(@Nonnull Quaternion rate);

    /**
     * Sets the mass of this object.
     *
     * @param mass Mass
     */
    void setMass(@Nonnegative double mass);

    //
    // Util
    //

    /**
     * Accelerates this object.
     *
     * @param direction Direction to apply acceleration
     * @param force     Force of acceleration
     */
    void accelerate(@Nonnull Vector direction, @Numeric double force);

    /**
     * Decelerates this object.
     * If the object is not moving, this will do nothing.
     *
     * @param force Force of deceleration
     */
    void decelerate(@Numeric double force);
}
