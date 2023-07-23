package oasis.artemis.command.game.stop;

import oasis.artemis.command.Command;

/**
 * <h2>StopCommand</h2>
 * <p>A command which stops the engine when called.</p>
 */
public final class StopCommand extends Command {
    public StopCommand() {
        super("stop", new StopCommandExecutor());
    }
}
