package oasis.artemis.object.lighting;

import oasis.artemis.object.AbstractObject;
import oasis.artemis.util.geometry.profile.GeometricProfile;
import oasis.artemis.util.math.Quaternion;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>MovableLightSource</h2>
 * <p>A light source which is movable.</p>
 */
public class MovableLightSource extends AbstractObject implements LightSource {
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
     * @param uniqueId       Unique identifier of this light source
     * @param mass           Mass of this light source
     * @param geometry       Geometric profile of this light source
     * @param location       Location of this light source
     * @param acceleration   Acceleration of this light source
     * @param rotation       Rotation of this light source
     * @param rotationRate   Rate of rotation of this light source
     * @param lightOffset    Light origin offset of this light source
     * @param lightDirection Direction of this light source
     * @param lightDiffusion Diffusion coefficient of this light source
     * @param lightIntensity Intensity of this light source
     */
    public MovableLightSource(
            @Nonnull UUID uniqueId,
            @Nonnegative double mass,
            @Nonnull GeometricProfile geometry,
            @Nonnull Vector location,
            @Nonnull Vector acceleration,
            @Nonnull Quaternion rotation,
            @Nonnull Quaternion rotationRate,
            @Nonnull Vector lightOffset,
            @Nonnull Vector lightDirection,
            @Nonnegative double lightDiffusion,
            @Nonnegative double lightIntensity
    ) {
        super(uniqueId, mass, geometry, location, acceleration, rotation, rotationRate);

        this.lightOffset = lightOffset;
        this.lightDirection = lightDirection;
        this.lightDiffusion = lightDiffusion;
        this.lightIntensity = lightIntensity;
    }

    /**
     * Builder constructor.
     *
     * @param builder Builder to use
     */
    public MovableLightSource(@Nonnull Builder builder) {
        super(builder);

        this.lightOffset = builder.lightOffset;
        this.lightDirection = builder.lightDirection;
        this.lightDiffusion = builder.lightDiffusion;
        this.lightIntensity = builder.lightIntensity;
    }

    //
    // Builder
    //

    /**
     * Builder class for {@link MovableLightSource}.
     */
    public static class Builder extends AbstractObject.Builder {
        private Builder() {}

        private Vector lightOffset;
        private Vector lightDirection;
        private double lightDiffusion;
        private double lightIntensity;

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

        /**
         * Sets the light origin offset of this light source.
         *
         * @param offset Light origin offset
         * @return {@code this}
         */
        @Nonnull
        public Builder lightOffset(@Nonnull Vector offset) {
            this.lightOffset = offset;
            return this;
        }

        /**
         * Sets the direction of light of this light source.
         *
         * @param direction Direction of light
         * @return {@code this}
         */
        @Nonnull
        public Builder lightDirection(@Nonnull Vector direction) {
            this.lightDirection = direction;
            return this;
        }

        /**
         * Gets the diffusion of this light source.
         *
         * @param diffusion Diffusion
         * @return {@code this}
         */
        @Nonnull
        public Builder lightDiffusion(@Nonnegative double diffusion) {
            this.lightDiffusion = diffusion;
            return this;
        }

        /**
         * Gets the intensity of this light source.
         *
         * @param intensity Intensity
         * @return {@code this}
         */
        @Nonnull
        public Builder lightIntensity(@Nonnegative double intensity) {
            this.lightIntensity = intensity;
            return this;
        }

        @Nonnull
        @Override
        public MovableLightSource build() throws IllegalArgumentException {
            return new MovableLightSource(this);
        }
    }

    //
    // Variables
    //
    @Nonnull
    private Vector lightOffset;
    @Nonnull
    private Vector lightDirection;
    @Nonnegative
    private double lightDiffusion;
    @Nonnegative
    private double lightIntensity;

    //
    // Getters
    //

    @Override
    @Nonnull
    public Vector getLightOffset() {
        return lightOffset;
    }

    @Override
    @Nonnull
    public Vector getLightDirection() {
        return lightDirection;
    }

    @Override
    @Nonnegative
    public double getLightDiffusion() {
        return lightDiffusion;
    }

    @Override
    @Nonnegative
    public double getLightIntensity() {
        return lightIntensity;
    }

    //
    // Setters
    //

    @Override
    public void setLightOffset(@Nonnull Vector lightOffset) {
        this.lightOffset = lightOffset;
    }

    @Override
    public void setLightDirection(@Nonnull Vector lightDirection) {
        this.lightDirection = lightDirection;
    }

    @Override
    public void setLightDiffusion(@Nonnegative double lightDiffusion) {
        this.lightDiffusion = lightDiffusion;
    }

    @Override
    public void setLightIntensity(@Nonnegative double lightIntensity) {
        this.lightIntensity = lightIntensity;
    }
}
