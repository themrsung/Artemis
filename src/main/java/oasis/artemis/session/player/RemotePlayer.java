package oasis.artemis.session.player;

import oasis.artemis.object.ArtemisObject;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>RemotePlayer</h2>
 * <p>A player playing remotely.</p>
 */
public class RemotePlayer extends AbstractPlayer {
    /**
     * Creates a new player.
     *
     * @param uniqueId Unique identifier of player
     * @param name     Name of player
     * @param pawn     Pawn of player
     */
    public RemotePlayer(@Nonnull UUID uniqueId, @Nonnull String name, @Nonnull ArtemisObject pawn) {
        super(uniqueId, name, pawn);
    }

    @Override
    public void sendMessage(@Nonnull String message) {
        System.out.println(message);
    }
}
