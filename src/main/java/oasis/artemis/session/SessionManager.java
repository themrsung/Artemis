package oasis.artemis.session;

import oasis.artemis.session.player.LocalPlayer;
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

        throw new NullPointerException("Player of given UUID does not exist.");
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
     * Gets the local player of this session.
     *
     * @return {@link LocalPlayer}
     */
    @Nullable
    public LocalPlayer getLocalPlayer() {
        return localPlayer;
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
     * Sets the local player.
     * <p>
     * If the local player has not been registered yet,
     * this will automatically register the player.
     * </p>
     * <p>
     * If given player is {@code null},
     * the existing player will be automatically unregistered.
     * </p>
     *
     * @param localPlayer Local player to set to
     */
    public void setLocalPlayer(@Nullable LocalPlayer localPlayer) {
        if (localPlayer != null) {
            addPlayer(localPlayer);
        } else if (this.localPlayer != null) {
            removePlayer(this.localPlayer);
        }

        this.localPlayer = localPlayer;
    }

    /**
     * A set of players.
     */
    @Nonnull
    private final Set<Player> players = new HashSet<>();

    /**
     * The local player of this session.
     */
    @Nullable
    private LocalPlayer localPlayer = null;
}
