package oasis.artemis.object;

import oasis.artemis.util.geometry.profile.GeometricProfile;
import oasis.artemis.util.geometry.profile.SphereProfile;
import oasis.artemis.util.math.Quaternion;
import oasis.artemis.util.math.Vector;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>DummyObject</h2>
 * <p>
 * An object which does nothing.
 * All functional methods are overloaded to do nothing.
 * This can be used as a placeholder instead of {@code null}.
 * </p>
 */
public final class DummyObject extends AbstractObject {
    /**
     * Creates a new dummy object with a random unique identifier.
     */
    public DummyObject() {
        this(UUID.randomUUID());
    }

    /**
     * Creates a new dummy object with specified unique identifier.
     *
     * @param uniqueId Unique identifier
     */
    public DummyObject(@Nonnull UUID uniqueId) {
        super(
                uniqueId,
                0,
                new SphereProfile(Double.MIN_VALUE),
                Vector.ZERO,
                Vector.ZERO,
                Quaternion.IDENTITY_QUATERNION,
                Quaternion.IDENTITY_QUATERNION
        );
    }

    //
    // Overloads
    //


    @Override
    public void tick(@Nonnull Duration delta) {}

    @Override
    public void setMass(double mass) {}

    @Override
    public void setGeometry(@Nonnull GeometricProfile geometry) {}

    @Override
    public boolean contains(@Nonnull Vector point) {return false;}

    @Override
    public boolean overlaps(@Nonnull ArtemisObject other) {return false;}

    @Override
    public void setLocation(@Nonnull Vector location) {}

    @Override
    public void setAcceleration(@Nonnull Vector acceleration) {}

    @Override
    public void setRotation(@Nonnull Quaternion rotation) {}

    @Override
    public void setRotationRate(@Nonnull Quaternion rotationRate) {}

    @Override
    public void move(@Nonnull Vector delta) {}

    @Override
    public void accelerate(@Nonnull Vector acceleration) {}
}
