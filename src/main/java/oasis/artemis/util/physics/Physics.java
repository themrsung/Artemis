package oasis.artemis.util.physics;

import oasis.artemis.annotation.Numeric;
import oasis.artemis.object.ArtemisObject;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnull;

/**
 * <h2>Physics</h2>
 * <p>Contains physics utility functions.</p>
 */
public final class Physics {
    /**
     * Gets the kinetic energy of an object.
     *
     * @param object Object
     * @return Kinetic energy
     */
//    @Nonnegative
//    public static double kineticEnergy(@Nonnull Physical object) {
//        return 0.5 * object.getMass() * object.getVelocity();
//    }
//
//    /**
//     * Gets the potential energy of an object.
//     *
//     * @param object  Object
//     * @param context Physics context
//     * @return Potential energy
//     */
//    @Numeric
//    public static double potentialEnergy(@Nonnull Physical object, @Nonnull PhysicsContext context) {
//        return object.getMass() * context.getGravitationalAcceleration() * context.getHeightFromGround();
//    }
//
//    /**
//     * Gets the mechanical energy of an object.
//     *
//     * @param object  Object
//     * @param context Physics context
//     * @return Mechanical energy
//     */
//    @Numeric
//    public static double mechanicalEnergy(@Nonnull Physical object, @Nonnull PhysicsContext context) {
//        return kineticEnergy(object) + potentialEnergy(object, context);
//    }
//
//    /**
//     * Gets the terminal velocity of an object.
//     *
//     * @param object  Object
//     * @param context Physics context
//     * @return Terminal velocity
//     */
//    @Nonnegative
//    public static double terminalVelocity(@Nonnull Physical object, @Nonnull PhysicsContext context) {
//        return (object.getMass() * context.getGravitationalAcceleration())
//                - (0.5 * context.getFluidDensity()
//                * Math.pow(object.getVelocity(), 2)
//                * object.getCrossSection()
//                * object.getDragCoefficient());
//    }
//
//    /**
//     * Gets the drag force an object is being subject to.
//     *
//     * @param object  Object
//     * @param context Physics context
//     * @return Drag force
//     */
//    @Nonnegative
//    public static double dragForce(@Nonnull Physical object, @Nonnull PhysicsContext context) {
//        return 0.5 * object.getDragCoefficient()
//                * context.getFluidDensity()
//                * object.getCrossSection()
//                * Math.pow(object.getVelocity(), 2);
//    }
//

    /**
     * Converts force to acceleration.
     *
     * @param object    Object to accelerate
     * @param direction Direction to apply acceleration in
     * @param force     Force to apply
     * @return Acceleration vector
     */
    @Nonnull
    public static Vector acceleration(@Nonnull ArtemisObject object, @Nonnull Vector direction, @Numeric double force) {
        return direction.toUnitVector().multiply(force * object.getMass());
    }
}
