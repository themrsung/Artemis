package oasis.artemis.command.game.list;

import oasis.artemis.command.Command;

import java.util.List;

/**
 * <h2>ListCommand</h2>
 * <p>Shows a list of players online.</p>
 */
public final class ListCommand extends Command {
    public ListCommand() {
        super("list", List.of("ls", "li", "online"), new ListCommandExecutor());
    }
}
