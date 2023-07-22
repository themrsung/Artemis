package oasis.artemis.util.geometry.solid;

import oasis.artemis.util.geometry.Vertex;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>Solid</h2>
 * <p>
 * A solid represents a three-dimensional area of space.
 * </p>
 */
public interface Solid {
    //
    // Properties
    //

    /**
     * Gets the geometric centroid of this solid.
     *
     * @return Centroid
     */
    @Nonnull
    Vector getCentroid();

    /**
     * Gets the volume of this solid.
     *
     * @return Volume
     */
    @Nonnegative
    double getVolume();

    /**
     * Gets the surface area of this solid.
     *
     * @return Surface area
     */
    @Nonnegative
    double getSurfaceArea();

    /**
     * Gets the coefficient of drag.
     * @param angle Angle the drag force will be applied to
     * @return Coefficient of drag
     */
    @Nonnegative
    double getDragCoefficient(@Nonnull Vector angle);

    /**
     * Gets the cross-section when viewed from given angle.
     * @param angle Angle to view this solid from
     * @return Cross-section
     */
    @Nonnegative
    double getCrossSection(@Nonnull Vector angle);

    /**
     * Gets a list of corners of this solid.
     *
     * @return List of corners
     */
    @Nonnull
    List<Vector> getCorners();

    //
    // Vertices
    //

    /**
     * Gets the vertices of this solid.
     *
     * @return List of vertices
     */
    @Nonnull
    List<Vertex> getVertices();

    //
    // Util
    //

    /**
     * Whether this solid contains given point.
     *
     * @param point Point to check
     * @return {@code true} if the point is within this solid's bounds
     */
    boolean contains(@Nonnull Vector point);

    /**
     * Whether this solid shares at least one point with the other.
     *
     * @param other Other solid
     * @return {@code true} if the solids overlap
     */
    boolean overlaps(@Nonnull Solid other);
}
