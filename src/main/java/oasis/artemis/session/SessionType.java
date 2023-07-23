package oasis.artemis.session;

/**
 * <h2>SessionType</h2>
 * <p>The type of a session.</p>
 */
public enum SessionType {
    /**
     * A singleplayer session with no server.
     */
    CLIENT_ONLY,

    /**
     * A client in a multiplayer server.
     */
    CLIENT,

    /**
     * A client hosting the multiplayer server.
     */
    SERVER,

    /**
     * A pure server instance without a client.
     */
    SERVER_ONLY;
}
