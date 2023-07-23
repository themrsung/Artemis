package oasis.artemis.command;

import javax.annotation.Nonnull;

/**
 * <h2>CommandSender</h2>
 * <p>A command sender can send commands to the console.</p>
 */
public interface CommandSender {
    /**
     * Sends a message to this command sender.
     *
     * @param message Message to send
     */
    void sendMessage(@Nonnull String message);
}
