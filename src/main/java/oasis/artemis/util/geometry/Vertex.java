package oasis.artemis.util.geometry;

import oasis.artemis.annotation.Numeric;
import oasis.artemis.util.math.Quaternion;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * <h2>Vertex</h2>
 * <p>
 * A vertex represents a renderable triangle.
 * Vertices can have one color which represents its shade.
 * </p>
 */
@Immutable
public class Vertex implements Iterable<Vector> {
    //
    // Constructors
    //

    /**
     * Creates a new vertex.
     *
     * @param p1    Point 1
     * @param p2    Point 2
     * @param p3    Point 3
     * @param color Color of this vertex
     */
    public Vertex(@Nonnull Vector p1, @Nonnull Vector p2, @Nonnull Vector p3, @Nonnull Color color) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.color = color;
    }

    //
    // Variables
    //

    @Nonnull
    private final Vector p1;
    @Nonnull
    private final Vector p2;
    @Nonnull
    private final Vector p3;
    @Nonnull
    private final Color color;

    //
    // Getters
    //

    /**
     * Gets the first point of this vertex.
     *
     * @return Point 1
     */
    @Nonnull
    public Vector getP1() {
        return p1;
    }

    /**
     * Gets the second point of this vertex.
     *
     * @return Point 2
     */
    @Nonnull
    public Vector getP2() {
        return p2;
    }

    /**
     * Gets the third point of this vertex.
     *
     * @return Point 3
     */
    @Nonnull
    public Vector getP3() {
        return p3;
    }

    /**
     * Gets the geometric centroid of this vertex.
     *
     * @return Centroid
     */
    @Nonnull
    public Vector getCentroid() {
        return p1.add(p2).add(p3).divide(3);
    }

    /**
     * Gets the color of this vertex.
     *
     * @return Color
     */
    @Nonnull
    public Color getColor() {
        return color;
    }

    //
    // Util
    //

    /**
     * Gets the iterator of the three vectors of this vertex.
     *
     * @return Iterator
     */
    @Override
    @Nonnull
    public Iterator<Vector> iterator() {
        return List.of(p1, p2, p3).iterator();
    }

    /**
     * Inflates this vertex to better represents its size.
     *
     * @param scale Scale to inflate by
     * @return Inflated vertex
     */
    @Nonnull
    public Vertex inflate(@Numeric double scale) {
        return new Vertex(
                p1.multiply(scale),
                p2.multiply(scale),
                p3.multiply(scale),
                color
        );
    }

    /**
     * Transforms this vertex to a relative coordinate system,
     * in respect to provided origin and angle.
     *
     * @param origin Origin to view this vertex from
     * @param angle  Angle to view this vertex from
     * @return Transformed vertex
     */
    @Nonnull
    public Vertex transform(@Nonnull Vector origin, @Nonnull Quaternion angle) {
        return new Vertex(
                p1.subtract(origin).rotate(angle),
                p2.subtract(origin).rotate(angle),
                p3.subtract(origin).rotate(angle),
                color
        );
    }
}
