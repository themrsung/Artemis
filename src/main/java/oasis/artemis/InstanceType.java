package oasis.artemis;

/**
 * <h2>InstanceType</h2>
 * <p>The type of an Artemis instance.</p>
 */
public enum InstanceType {
    /**
     * A server has no local player, and can accept remote connections.
     */
    SERVER,

    /**
     * A client has a local player, and cannot accept remote connections.
     */
    CLIENT;
}
