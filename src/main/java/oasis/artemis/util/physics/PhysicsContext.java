package oasis.artemis.util.physics;

import oasis.artemis.annotation.Numeric;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * <h2>PhysicsContext</h2>
 * <p>
 *     Contains contextual information required when performing physics tasks.
 * </p>
 */
@Immutable
public final class PhysicsContext {
    //
    // Constructors
    //

    /**
     * Creates a blank physics context.
     * All physics calculations will fail with this data.
     */
    public PhysicsContext() {
        this(Vector.ZERO, 0, 0);
    }

    /**
     * Creates a new physics context.
     * @param gravity Gravity vector
     * @param fluidDensity Fluid density
     * @param heightFromGround Height from ground
     */
    public PhysicsContext(@Nonnull Vector gravity, @Nonnegative double fluidDensity, @Numeric double heightFromGround) {
        this.gravity = gravity;
        this.fluidDensity = fluidDensity;
        this.heightFromGround = heightFromGround;
    }

    //
    // Variables
    //

    @Nonnull
    private final Vector gravity;
    @Nonnegative
    private final double fluidDensity;
    @Nonnegative
    private final double heightFromGround;

    //
    // Getters
    //

    /**
     * Gets the gravity vector.
     * @return Gravity
     */
    @Nonnull
    public Vector getGravity() {
        return gravity;
    }

    /**
     * Gets the gravitational acceleration.
     * This is equal to the magnitude of {@link PhysicsContext#gravity}.
     * @return Gravity
     */
    @Nonnegative
    public double getGravitationalAcceleration() {
        return gravity.getMagnitude();
    }

    /**
     * Gets the fluid density.
     * @return Density of fluid
     */
    @Nonnegative
    public double getFluidDensity() {
        return fluidDensity;
    }

    /**
     * Gets the height from ground.
     * @return Height from ground
     */
    @Numeric
    public double getHeightFromGround() {
        return heightFromGround;
    }
}
