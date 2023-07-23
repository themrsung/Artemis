package oasis.artemis.level;

import oasis.artemis.object.AbstractObject;
import oasis.artemis.object.ArtemisObject;
import oasis.artemis.util.group.Pair;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * <h2>SimpleLevel</h2>
 * <p>A basic level.</p>
 */
public final class SimpleLevel extends AbstractLevel {
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
     * @param uniqueId           Unique identifier of this level
     * @param name               Name of this level
     * @param objects            Set of objects in this level
     * @param gravity            Gravity of this level
     * @param airDensity         Air density of this level
     * @param overlappingObjects Set of overlapping objects in this level
     */
    public SimpleLevel(
            @Nonnull UUID uniqueId,
            @Nonnull String name,
            @Nonnull Set<ArtemisObject> objects,
            @Nonnull Vector gravity,
            @Nonnegative double airDensity,
            @Nonnull List<Pair<ArtemisObject>> overlappingObjects
    ) {
        super(uniqueId, name, objects, gravity, airDensity, overlappingObjects);
    }

    /**
     * Builder constructor.
     *
     * @param builder Builder to use
     */
    private SimpleLevel(@Nonnull Builder builder) {
        super(builder);
    }

    //
    // Builder
    //

    /**
     * Builder class for {@link SimpleLevel}
     */
    public static final class Builder extends AbstractLevel.Builder {
        private Builder() {}

        @Nonnull
        @Override
        public Builder uniqueId(@Nonnull UUID uniqueId) {
            return (Builder) super.uniqueId(uniqueId);
        }

        @Nonnull
        @Override
        public Builder name(@Nonnull String name) {
            return (Builder) super.name(name);
        }

        @Nonnull
        @Override
        public Builder objects(@Nonnull Set<ArtemisObject> objects) {
            return (Builder) super.objects(objects);
        }

        @Nonnull
        @Override
        public Builder object(@Nonnull AbstractObject object) {
            return (Builder) super.object(object);
        }

        @Nonnull
        @Override
        public Builder gravity(@Nonnull Vector gravity) {
            return (Builder) super.gravity(gravity);
        }

        @Nonnull
        @Override
        public Builder airDensity(double density) {
            return (Builder) super.airDensity(density);
        }

        @Nonnull
        @Override
        public SimpleLevel build() throws IllegalArgumentException {
            return new SimpleLevel(this);
        }
    }
}
