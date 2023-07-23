package oasis.artemis.session.player;

import oasis.artemis.object.ArtemisObject;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>LocalPlayer</h2>
 * <p>A player playing locally.</p>
 */
public class LocalPlayer extends AbstractPlayer {
    /**
     * Creates a new player.
     *
     * @param uniqueId Unique identifier of player
     * @param name     Name of player
     * @param pawn     Pawn of player
     */
    public LocalPlayer(@Nonnull UUID uniqueId, @Nonnull String name, @Nonnull ArtemisObject pawn) {
        super(uniqueId, name, pawn);
    }

    @Override
    public void sendMessage(@Nonnull String message) {
        System.out.println(message);
    }
}
