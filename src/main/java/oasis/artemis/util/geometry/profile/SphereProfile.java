package oasis.artemis.util.geometry.profile;

import oasis.artemis.object.ArtemisObject;
import oasis.artemis.util.geometry.solid.Solid;
import oasis.artemis.util.geometry.solid.Sphere;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * <h2>SphereProfile</h2>
 * <p>A spherical geometric profile.</p>
 */
@Immutable
public class SphereProfile implements GeometricProfile {
    /**
     * Creates a new spherical profile.
     * @param radius Radius of this sphere
     */
    public SphereProfile(double radius) {
        this.radius = radius;
    }

    @Nonnegative
    private final double radius;

    /**
     * Gets the radius of this spherical profile.
     * @return Radius
     */
    @Nonnegative
    public double getRadius() {
        return radius;
    }

    @Nonnull
    @Override
    public Solid build(@Nonnull ArtemisObject parent) {
        return new Sphere(parent.getLocation(), parent.getRotation(), radius);
    }
}
