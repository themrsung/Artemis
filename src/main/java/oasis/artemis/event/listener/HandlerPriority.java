package oasis.artemis.event.listener;

/**
 * <h2>HandlerPriority</h2>
 * <p>The priority of an event handler.</p>
 */
public enum HandlerPriority {
    PRE_EARLY, // Called first
    EARLY,
    POST_EARLY,
    PRE_NORMAL,
    NORMAL,
    POST_NORMAL,
    PRE_MONITOR,
    MONITOR,
    POST_MONITOR; // Called last
}
