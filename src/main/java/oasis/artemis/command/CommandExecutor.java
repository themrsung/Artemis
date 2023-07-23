package oasis.artemis.command;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>CommandExecutor</h2>
 * <p>Handles the execution of a command.</p>
 */
public interface CommandExecutor {
    /**
     * Called on command
     * @param sender Sender of command
     * @param params List of parameters
     */
    void onCommand(@Nonnull CommandSender sender, @Nonnull List<String> params);
}
