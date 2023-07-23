package oasis.artemis.command;

import javax.annotation.Nonnull;

/**
 * <h2>ConsoleCommandSender</h2>
 * <p>The {@link CommandSender} for server-only instances.</p>
 */
public class ConsoleCommandSender implements CommandSender {
    @Override
    public void sendMessage(@Nonnull String message) {
        System.out.println(message);
    }
}
