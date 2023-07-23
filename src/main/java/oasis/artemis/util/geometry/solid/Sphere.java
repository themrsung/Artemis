package oasis.artemis.util.geometry.solid;

import oasis.artemis.util.geometry.Vertex;
import oasis.artemis.util.math.Quaternion;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Sphere</h2>
 * <p>A spherical solid.</p>
 */
@Immutable
public class Sphere implements Solid {
    //
    // Constructors
    //

    /**
     * Creates a new sphere.
     *
     * @param centroid Centroid of the sphere
     * @param rotation Rotation of this sphere
     * @param radius   Radius of the sphere
     */
    public Sphere(@Nonnull Vector centroid, @Nonnull Quaternion rotation, double radius) {
        this.centroid = centroid;
        this.rotation = rotation;
        this.radius = radius;
    }

    //
    // Variables
    //
    @Nonnull
    private final Vector centroid;
    @Nonnull
    private final Quaternion rotation;
    @Nonnegative
    private final double radius;

    //
    // Getters
    //

    @Override
    @Nonnull
    public Vector getCentroid() {
        return centroid;
    }

    /**
     * Gets the rotation of this sphere.
     *
     * @return Rotation
     */
    @Nonnull
    public Quaternion getRotation() {
        return rotation;
    }

    /**
     * Gets the radius of this sphere.
     *
     * @return Radius
     */
    @Nonnegative
    public double getRadius() {
        return radius;
    }

    @Override
    public double getVolume() {
        return 4.0 / 3.0 * Math.PI * radius;
    }

    @Override
    public double getSurfaceArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getDragCoefficient(@Nonnull Vector angle) {
        return 0.5;
    }

    @Override
    public double getCrossSection(@Nonnull Vector angle) {
        return Math.PI * Math.pow(radius, 2);
    }

    @Nonnull
    @Override
    public List<Vector> getCorners() {
        return new ArrayList<>();
    }

    @Nonnull
    @Override
    public List<Vertex> getVertices() {
        final Vector a = centroid.add(new Vector(radius, 0, 0).rotate(rotation));
        final Vector b = centroid.add(new Vector(-radius, 0, 0).rotate(rotation));
        final Vector c = centroid.add(new Vector(0, radius, 0).rotate(rotation));
        final Vector d = centroid.add(new Vector(0, -radius, 0).rotate(rotation));
        final Vector e = centroid.add(new Vector(0, 0, radius).rotate(rotation));
        final Vector f = centroid.add(new Vector(0, 0, -radius).rotate(rotation));

        return List.of(
                new Vertex(a, b, f, Color.RED),
                new Vertex(a, d, f, Color.BLUE),
                new Vertex(c, b, f, Color.GREEN),
                new Vertex(d, b, f, Color.CYAN),
                new Vertex(c, e, a, Color.PINK),
                new Vertex(d, e, a, Color.GRAY),
                new Vertex(c, b, e, Color.DARK_GRAY),
                new Vertex(d, b, e, Color.YELLOW)
        );
    }

    @Override
    public boolean contains(@Nonnull Vector point) {
        return centroid.distanceTo(point) <= radius;
    }

    @Override
    public boolean overlaps(@Nonnull Solid other) {
        if (other instanceof Sphere sphere) {
            return centroid.distanceTo(sphere.centroid) <= (radius + sphere.radius);
        } else {
            for (Vector corner : other.getCorners()) {
                if (contains(corner)) return true;
            }

            return false;
        }
    }
}
