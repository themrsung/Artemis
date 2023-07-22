package oasis.artemis.event;

/**
 * <h2>Cancellable</h2>
 * <p>
 * A cancellable event can be declared as cancelled.
 * The cancellation should be respected by higher-priority listeners and not handled.
 * </p>
 */
public interface Cancellable extends Event {
    /**
     * Checks if this event has been cancelled by a lower-priority listener.
     *
     * @return {@code true} if this event was cancelled
     */
    boolean isCancelled();

    /**
     * Sets the cancellation state of this event.
     *
     * @param cancelled {@code true} to cancel the event
     */
    void setCancelled(boolean cancelled);
}
