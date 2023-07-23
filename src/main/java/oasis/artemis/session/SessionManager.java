package oasis.artemis.session;

import oasis.artemis.session.player.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * <h2>SessionManager</h2>
 * <p>Handles session management.</p>
 */
public class SessionManager {
    //
    // Players
    //

    /**
     * Gets a set of all currently online players.
     *
     * @return Set of players
     */
    @Nonnull
    public Set<Player> getPlayers() {
        return new HashSet<>(players);
    }

    /**
     * Gets a player by unique identifier.
     *
     * @param uniqueId Unique ID of player
     * @return {@link Player}
     * @throws NullPointerException When the player cannot be found
     */
    @Nonnull
    public Player getPlayer(@Nonnull UUID uniqueId) throws NullPointerException {
        for (Player player : players) {
            if (player.getUniqueId().equals(uniqueId)) return player;
        }

        throw new NullPointerException();
    }

    /**
     * Gets a player by name.
     * Player names are not case-sensitive.
     *
     * @param name Name of player
     * @return {@link Player} if found, {@code null} if not
     */
    @Nullable
    public Player getPlayer(@Nonnull String name) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) return player;
        }

        return null;
    }

    /**
     * Adds a player to this session.
     *
     * @param player Player to add
     */
    public void addPlayer(@Nonnull Player player) {
        players.add(player);
    }

    /**
     * Removes a player from this session.
     *
     * @param player Player to remove
     */
    public void removePlayer(@Nonnull Player player) {
        players.remove(player);
    }

    /**
     * A set of players.
     */
    @Nonnull
    private final Set<Player> players = new HashSet<>();
}
