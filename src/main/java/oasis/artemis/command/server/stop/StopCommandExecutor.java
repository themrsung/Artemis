package oasis.artemis.command.server.stop;

import oasis.artemis.Artemis;
import oasis.artemis.command.CommandExecutor;
import oasis.artemis.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>StopCommandExecutor</h2>
 * <p>Executes {@link StopCommand}.</p>
 */
public final class StopCommandExecutor implements CommandExecutor {
    @Override
    public void onCommand(@Nonnull CommandSender sender, @Nonnull List<String> params) {
        sender.sendMessage("Stopping game.");
        Artemis.stop();
    }
}
