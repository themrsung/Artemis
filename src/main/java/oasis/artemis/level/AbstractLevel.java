package oasis.artemis.level;

import oasis.artemis.Artemis;
import oasis.artemis.event.physics.CollisionEvent;
import oasis.artemis.object.AbstractObject;
import oasis.artemis.object.ArtemisObject;
import oasis.artemis.util.group.Pair;
import oasis.artemis.util.math.Vector;
import org.joda.time.Duration;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class AbstractLevel implements Level {
    //
    // Constructors
    //

    /**
     * All-args constructor.
     *
     * @param uniqueId           Unique identifier of this level
     * @param name               Name of this level
     * @param objects            Set of objects in this level
     * @param gravity            Gravity vector of this level
     * @param airDensity         Air density of this level
     * @param overlappingObjects Set of overlapping objects
     */
    public AbstractLevel(
            @Nonnull UUID uniqueId,
            @Nonnull String name,
            @Nonnull Set<ArtemisObject> objects,
            @Nonnull Vector gravity,
            @Nonnegative double airDensity,
            @Nonnull Set<Pair<ArtemisObject>> overlappingObjects
    ) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.objects = objects;
        this.gravity = gravity;
        this.airDensity = airDensity;
        this.overlappingObjects = overlappingObjects;
    }

    /**
     * Builder constructor.
     *
     * @param builder Builder to use
     */
    protected AbstractLevel(@Nonnull Builder builder) {
        this(
                builder.uniqueId,
                builder.name,
                builder.objects,
                builder.gravity,
                builder.airDensity,
                new HashSet<>()
        );
    }

    //
    // Builder
    //

    /**
     * Builder class for {@link AbstractLevel}.
     */
    public static abstract class Builder {
        protected Builder() {
            this.uniqueId = UUID.randomUUID();
            this.name = "";
            this.objects = new HashSet<>();
            this.gravity = Vector.ZERO;
            this.airDensity = 0;
        }

        private UUID uniqueId;
        private String name;
        private final Set<ArtemisObject> objects;
        private Vector gravity;
        private double airDensity;

        /**
         * Sets the unique identifier of this level.
         *
         * @param uniqueId Unique ID
         * @return {@code this}
         */
        @Nonnull
        public Builder uniqueId(@Nonnull UUID uniqueId) {
            this.uniqueId = uniqueId;
            return this;
        }

        /**
         * Sets the name of this level.
         *
         * @param name Name
         * @return {@code this}
         */
        @Nonnull
        public Builder name(@Nonnull String name) {
            this.name = name;
            return this;
        }

        /**
         * Adds multiple objects to this level.
         *
         * @param objects Set of objects to add
         * @return {@code this}
         */
        @Nonnull
        public Builder objects(@Nonnull Set<ArtemisObject> objects) {
            this.objects.addAll(objects);
            return this;
        }

        /**
         * Adds one object to this level.
         *
         * @param object Object to add
         * @return {@code this}
         */
        @Nonnull
        public Builder object(@Nonnull AbstractObject object) {
            this.objects.add(object);
            return this;
        }

        /**
         * Sets the gravity of this level.
         *
         * @param gravity Gravity
         * @return {@code this}
         */
        @Nonnull
        public Builder gravity(@Nonnull Vector gravity) {
            this.gravity = gravity;
            return this;
        }

        /**
         * Sets the air density of this level.
         *
         * @param density Air density
         * @return {@code this}
         */
        @Nonnull
        public Builder airDensity(@Nonnegative double density) {
            this.airDensity = density;
            return this;
        }

        /**
         * Finalizes the building sequence and builds the level.
         *
         * @return Built level
         * @throws IllegalArgumentException When at least one of the parameters have not been initialized
         */
        @Nonnull
        public abstract AbstractLevel build() throws IllegalArgumentException;
    }


    //
    // Tick
    //

    @Override
    public void tick(@Nonnull Duration delta) {
        // Uses copied set to prevent concurrent modification exception
        final Set<ArtemisObject> objects = getObjects();

        // Apply gravity
        objects.forEach(o -> o.accelerate(gravity));

        // Tick objects
        objects.forEach(o -> o.tick(delta));

        // Handle collisions
        final Set<Pair<ArtemisObject>> pairs = Pair.pairsOfSet(objects);
        pairs.forEach(pair -> {
            final ArtemisObject o1 = pair.getFirst();
            final ArtemisObject o2 = pair.getSecond();

            if (o1.overlaps(o2)) {
                if (overlappingObjects.add(pair)) {
                    Artemis.getEventManager().callEvent(new CollisionEvent(pair));
                }
            } else {
                overlappingObjects.remove(pair);
            }
        });
    }

    //
    // Variables
    //
    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private final String name;
    @Nonnull
    private final Set<ArtemisObject> objects;
    @Nonnull
    private Vector gravity;
    @Nonnegative
    private double airDensity;

    //
    // Internal variables
    //

    @Nonnull
    protected final Set<Pair<ArtemisObject>> overlappingObjects;

    //
    // Getters
    //


    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    @Nonnull
    public String getName() {
        return name;
    }

    @Override
    @Nonnull
    public Set<ArtemisObject> getObjects() {
        return new HashSet<>(objects);
    }

    @Override
    @Nonnull
    public Vector getGravity() {
        return gravity;
    }

    @Override
    @Nonnegative
    public double getAirDensity() {
        return airDensity;
    }

    //
    // Setters
    //


    @Override
    public void addObject(@Nonnull ArtemisObject object) {
        objects.add(object);
    }

    @Override
    public void removeObject(@Nonnull ArtemisObject object) {
        objects.remove(object);
    }

    @Override
    public void setGravity(@Nonnull Vector gravity) {
        this.gravity = gravity;
    }

    @Override
    public void setAirDensity(@Nonnegative double airDensity) {
        this.airDensity = airDensity;
    }
}
