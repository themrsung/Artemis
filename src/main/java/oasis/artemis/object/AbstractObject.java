package oasis.artemis.object;

import oasis.artemis.util.geometry.profile.GeometricProfile;
import oasis.artemis.util.geometry.profile.SphereProfile;
import oasis.artemis.util.geometry.solid.Solid;
import oasis.artemis.util.math.Quaternion;
import oasis.artemis.util.math.Vector;
import org.joda.time.Duration;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>AbstractObject</h2>
 * <p>An object with default behavior.</p>
 */
public abstract class AbstractObject implements ArtemisObject {
    //
    // Constructors
    //

    /**
     * All-args constructor.
     *
     * @param uniqueId     Unique identifier of this object
     * @param mass         Mass of this object
     * @param geometry     Geometric profile of this object
     * @param location     Location of this object
     * @param acceleration Acceleration of this object
     * @param rotation     Rotation of this object
     * @param rotationRate Rate of rotation of this object
     */
    public AbstractObject(
            @Nonnull UUID uniqueId,
            @Nonnegative double mass,
            @Nonnull GeometricProfile geometry,
            @Nonnull Vector location,
            @Nonnull Vector acceleration,
            @Nonnull Quaternion rotation,
            @Nonnull Quaternion rotationRate
    ) {
        this.uniqueId = uniqueId;
        this.mass = mass;
        this.geometry = geometry;
        this.location = location;
        this.acceleration = acceleration;
        this.rotation = rotation;
        this.rotationRate = rotationRate;
    }

    /**
     * Builder constructor.
     *
     * @param builder Builder to use
     */
    protected AbstractObject(@Nonnull Builder builder) {
        this(
                builder.uniqueId,
                builder.mass,
                builder.geometry,
                builder.location,
                builder.acceleration,
                builder.rotation,
                builder.rotationRate
        );
    }

    //
    // Builder
    //

    /**
     * Builder class for {@link AbstractObject}.
     */
    public abstract static class Builder {
        protected Builder() {
            this.uniqueId = UUID.randomUUID();
            this.mass = 0;
            this.geometry = new SphereProfile(Double.MIN_VALUE);
            this.location = Vector.ZERO;
            this.acceleration = Vector.ZERO;
            this.rotation = Quaternion.IDENTITY_QUATERNION;
            this.rotationRate = Quaternion.IDENTITY_QUATERNION;
        }

        private UUID uniqueId;
        private double mass;
        private GeometricProfile geometry;
        private Vector location;
        private Vector acceleration;
        private Quaternion rotation;
        private Quaternion rotationRate;

        /**
         * Sets the unique identifier of this object.
         *
         * @param uniqueId Unique iD
         * @return {@code this}
         */
        @Nonnull
        public Builder uniqueId(@Nonnull UUID uniqueId) {
            this.uniqueId = uniqueId;
            return this;
        }

        /**
         * Sets the mass of this object.
         *
         * @param mass Mass
         * @return {@code this}
         */
        @Nonnull
        public Builder mass(@Nonnegative double mass) {
            this.mass = mass;
            return this;
        }

        /**
         * Sets the geometric profile of this object.
         *
         * @param geometry Geometric profile
         * @return {@code this}
         */
        @Nonnull
        public Builder geometry(@Nonnull GeometricProfile geometry) {
            this.geometry = geometry;
            return this;
        }

        /**
         * Sets the location of this object.
         *
         * @param location Location
         * @return {@code this}
         */
        @Nonnull
        public Builder location(@Nonnull Vector location) {
            this.location = location;
            return this;
        }

        /**
         * Sets the acceleration of this object.
         *
         * @param acceleration Acceleration
         * @return {@code this}
         */
        @Nonnull
        public Builder acceleration(@Nonnull Vector acceleration) {
            this.acceleration = acceleration;
            return this;
        }

        /**
         * Sets the rotation of this object.
         *
         * @param rotation Rotation
         * @return {@code this}
         */
        @Nonnull
        public Builder rotation(@Nonnull Quaternion rotation) {
            this.rotation = rotation;
            return this;
        }

        /**
         * Sets the rate of rotation of this object.
         *
         * @param rate Rate of rotation
         * @return {@code this}
         */
        @Nonnull
        public Builder rotationRate(@Nonnull Quaternion rate) {
            this.rotationRate = rate;
            return this;
        }

        /**
         * Finalizes the building sequence and builds the object.
         *
         * @return Built object
         * @throws IllegalArgumentException When at least one of the parameters have not been initialized
         */
        @Nonnull
        public abstract AbstractObject build() throws IllegalArgumentException;
    }

    //
    // Tick
    //

    @Override
    public void tick(@Nonnull Duration delta) {
        // Convert delta to seconds
        final double seconds = delta.getMillis() / 1000d;

        // Handle location change
        move(getAcceleration().multiply(seconds));
        rotate(getRotationRate().scale(seconds));
    }

    //
    // Variables
    //
    @Nonnull
    private final UUID uniqueId;
    @Nonnegative
    private double mass;
    @Nonnull
    private GeometricProfile geometry;
    @Nonnull
    private Vector location;
    @Nonnull
    private Vector acceleration;
    @Nonnull
    private Quaternion rotation;
    @Nonnull
    private Quaternion rotationRate;

    //
    // Getters
    //

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public double getDensity() {
        final double v = getSolid().getVolume();
        if (v <= 0) return 0;

        return mass / v;
    }

    @Override
    public double getDragCoefficient() {
        return getSolid().getDragCoefficient(getAcceleration().negate());
    }

    @Override
    public double getCrossSection() {
        return getSolid().getCrossSection(getAcceleration().negate());
    }

    @Override
    @Nonnull
    public GeometricProfile getGeometry() {
        return geometry;
    }

    @Nonnull
    @Override
    public Solid getSolid() {
        return geometry.build(this);
    }

    @Override
    @Nonnull
    public Vector getLocation() {
        return location;
    }

    @Override
    @Nonnull
    public Vector getAcceleration() {
        return acceleration;
    }

    @Override
    public double getVelocity() {
        return acceleration.getMagnitude();
    }

    @Override
    @Nonnull
    public Quaternion getRotation() {
        return rotation;
    }

    @Override
    @Nonnull
    public Quaternion getRotationRate() {
        return rotationRate;
    }

    //
    // Setters
    //


    @Override
    public void setMass(@Nonnegative double mass) {
        this.mass = mass;
    }

    @Override
    public void setGeometry(@Nonnull GeometricProfile geometry) {
        this.geometry = geometry;
    }

    @Override
    public void setLocation(@Nonnull Vector location) {
        this.location = location;
    }

    @Override
    public void setAcceleration(@Nonnull Vector acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public void setRotation(@Nonnull Quaternion rotation) {
        this.rotation = rotation;
    }

    @Override
    public void setRotationRate(@Nonnull Quaternion rotationRate) {
        this.rotationRate = rotationRate;
    }

    //
    // Methods
    //


    @Override
    public boolean contains(@Nonnull Vector point) {
        return getSolid().contains(point);
    }

    @Override
    public boolean overlaps(@Nonnull ArtemisObject other) {
        return getSolid().overlaps(other.getSolid());
    }

    @Override
    public void move(@Nonnull Vector delta) {
        setLocation(getLocation().add(delta));
    }

    @Override
    public void rotate(@Nonnull Quaternion rotation) {
        setRotation(rotation.multiply(getRotation()));
    }

    @Override
    public void accelerate(@Nonnull Vector acceleration) {
        setAcceleration(getAcceleration().add(acceleration));
    }
}
