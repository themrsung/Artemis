package oasis.artemis.session.player;

import oasis.artemis.object.ArtemisObject;
import oasis.artemis.util.math.Quaternion;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>AbstractPlayer</h2>
 * <p>The default implementation superclass of {@link Player}.</p>
 */
public abstract class AbstractPlayer implements Player {
    //
    // Constructors
    //

    /**
     * Creates a new player.
     *
     * @param uniqueId Unique identifier of player
     * @param name     Name of player
     * @param pawn     Pawn of player
     */
    public AbstractPlayer(@Nonnull UUID uniqueId, @Nonnull String name, @Nonnull ArtemisObject pawn) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.pawn = pawn;
    }

    //
    // Variables
    //

    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private final String name;
    @Nonnull
    private ArtemisObject pawn;

    //
    // Methods
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
    public ArtemisObject getPawn() {
        return pawn;
    }

    @Override
    public void setPawn(@Nonnull ArtemisObject pawn) {
        this.pawn = pawn;
    }

    @Nonnull
    @Override
    public Vector getLocation() {
        return pawn.getLocation();
    }

    @Nonnull
    @Override
    public Vector getAcceleration() {
        return pawn.getAcceleration();
    }

    @Override
    public double getVelocity() {
        return pawn.getVelocity();
    }

    @Nonnull
    @Override
    public Quaternion getRotation() {
        return pawn.getRotation();
    }

    @Nonnull
    @Override
    public Quaternion getRotationRate() {
        return pawn.getRotationRate();
    }

    @Override
    public void setLocation(@Nonnull Vector location) {
        pawn.setLocation(location);
    }

    @Override
    public void setAcceleration(@Nonnull Vector acceleration) {
        pawn.setAcceleration(acceleration);
    }

    @Override
    public void setRotation(@Nonnull Quaternion rotation) {
        pawn.setRotation(rotation);
    }

    @Override
    public void setRotationRate(@Nonnull Quaternion rate) {
        pawn.setRotationRate(rate);
    }

    @Override
    public void move(@Nonnull Vector delta) {
        pawn.move(delta);
    }

    @Override
    public void rotate(@Nonnull Quaternion rotation) {
        pawn.rotate(rotation);
    }

    @Override
    public void accelerate(@Nonnull Vector acceleration) {
        pawn.accelerate(acceleration);
    }
}
