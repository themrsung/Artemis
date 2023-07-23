package oasis.artemis.command.game.list;

import oasis.artemis.Artemis;
import oasis.artemis.command.CommandExecutor;
import oasis.artemis.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>ListCommandExecutor</h2>
 * <p>Executes {@link ListCommand}</p>
 */
public final class ListCommandExecutor implements CommandExecutor {
    @Override
    public void onCommand(@Nonnull CommandSender sender, @Nonnull List<String> params) {
        sender.sendMessage("There are " + Artemis.getSessionManager().getPlayers().size() + " players online.");
        Artemis.getSessionManager().getPlayers().forEach(p -> sender.sendMessage("- " + p.getName()));
    }
}
