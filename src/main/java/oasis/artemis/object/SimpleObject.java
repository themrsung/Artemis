package oasis.artemis.object;

import oasis.artemis.util.geometry.profile.GeometricProfile;
import oasis.artemis.util.math.Quaternion;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>SimpleObject</h2>
 * <p>A simple object.</p>
 */
public class SimpleObject extends AbstractObject {
    //
    // Constructors
    //

    /**
     * Gets a new builder instance.
     *
     * @return {@link Builder}
     */
    @Nonnull
    public static Builder builder() {
        return new Builder();
    }

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
    public SimpleObject(
            @Nonnull UUID uniqueId,
            @Nonnegative double mass,
            @Nonnull GeometricProfile geometry,
            @Nonnull Vector location,
            @Nonnull Vector acceleration,
            @Nonnull Quaternion rotation,
            @Nonnull Quaternion rotationRate
    ) {
        super(uniqueId, mass, geometry, location, acceleration, rotation, rotationRate);
    }

    /**
     * Builder constructor.
     *
     * @param builder Builder to use
     */
    protected SimpleObject(@Nonnull Builder builder) {
        super(builder);
    }

    //
    // Builder
    //

    /**
     * Builder class for {@link SimpleObject}.
     */
    public static class Builder extends AbstractObject.Builder {
        private Builder() {}

        @Nonnull
        @Override
        public Builder uniqueId(@Nonnull UUID uniqueId) {
            return (Builder) super.uniqueId(uniqueId);
        }

        @Nonnull
        @Override
        public Builder mass(double mass) {
            return (Builder) super.mass(mass);
        }

        @Nonnull
        @Override
        public Builder geometry(@Nonnull GeometricProfile geometry) {
            return (Builder) super.geometry(geometry);
        }

        @Nonnull
        @Override
        public Builder location(@Nonnull Vector location) {
            return (Builder) super.location(location);
        }

        @Nonnull
        @Override
        public Builder acceleration(@Nonnull Vector acceleration) {
            return (Builder) super.acceleration(acceleration);
        }

        @Nonnull
        @Override
        public Builder rotation(@Nonnull Quaternion rotation) {
            return (Builder) super.rotation(rotation);
        }

        @Nonnull
        @Override
        public Builder rotationRate(@Nonnull Quaternion rate) {
            return (Builder) super.rotationRate(rate);
        }

        @Nonnull
        @Override
        public SimpleObject build() throws IllegalArgumentException {
            return new SimpleObject(this);
        }
    }
}
