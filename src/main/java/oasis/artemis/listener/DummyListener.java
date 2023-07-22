package oasis.artemis.listener;

import oasis.artemis.event.DummyEvent;
import oasis.artemis.event.listener.EventHandler;
import oasis.artemis.event.listener.HandlerPriority;
import oasis.artemis.event.listener.Listener;

import javax.annotation.Nonnull;

/**
 * <h2>DummyListener</h2>
 * <p>Listens for dummy events and handles them.</p>
 */
public final class DummyListener implements Listener {
    @EventHandler(priority = HandlerPriority.POST_MONITOR)
    public void onDummyEvent(@Nonnull DummyEvent event) {
        System.out.println("Dummy event has been triggered!");
    }
}
