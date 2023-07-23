package oasis.artemis.command.lifecycle;

import oasis.artemis.Artemis;
import oasis.artemis.command.Command;
import oasis.artemis.command.CommandExecutor;
import oasis.artemis.command.CommandSender;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <h2>CommandManager</h2>
 * <p>Manages the lifecycle of commands.</p>
 */
public class CommandManager {
    //
    // Controls
    //

    /**
     * Starts listening to commands.
     */
    public void start() {
        running = true;
        thread.start();
    }

    /**
     * Stops listening to commands.
     */
    public void stop() {
        running = false;
        thread.interrupt();
    }

    //
    // Events
    //

    /**
     * Handles notifying command executors.
     *
     * @param sender Sender of command
     * @param input Raw input string
     */
    public void onCommand(@Nonnull CommandSender sender, @Nonnull String input) {
        final String[] split = input.split(" ");
        if (split.length < 1) return;

        final String command = split[0];
        final List<String> params = split.length > 1 ? List.of(Arrays.copyOfRange(split, 1, split.length)) : List.of();

        commands.forEach(c -> {
            if (!c.getName().equalsIgnoreCase(command) && !c.getAliases().contains(command.toLowerCase())) return;

            final CommandExecutor executor = c.getExecutor();
            if (executor == null) return;

            executor.onCommand(sender, params);
        });
    }

    //
    // Commands
    //

    /**
     * Gets a set of all commands currently registered.
     * @return Set of commands
     */
    @Nonnull
    public Set<Command> getCommands() {
        return new HashSet<>(commands);
    }

    /**
     * Gets a command by name.
     * @param name Name of command
     * @return {@link Command}
     * @throws NullPointerException When command cannot be found
     */
    @Nonnull
    public Command getCommand(@Nonnull String name) throws NullPointerException {
        for (Command command : commands) {
            if (command.getName().equalsIgnoreCase(name)) return command;
        }

        throw new NullPointerException("Command with given name was not found.");
    }

    /**
     * Adds a command.
     * @param command Command to add
     * @throws IllegalArgumentException When a command with the same name already exists
     */
    public void addCommand(@Nonnull Command command) throws IllegalArgumentException {
        for (Command cmd: commands) {
            if (cmd.getName().equalsIgnoreCase(command.getName())) {
                throw new IllegalArgumentException("Command with the same name already exists.");
            }
        }

        commands.add(command);
    }

    /**
     * Removes a command.
     * @param command Command to remove
     */
    public void removeCommand(@Nonnull Command command) {
        commands.remove(command);
    }

    //
    // Variables
    //
    @Nonnull
    private final Set<Command> commands = new HashSet<>();
    private boolean running = false;

    private final Thread thread = new Thread(() -> {
        final BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)
        );

        while (running) {
            switch (Artemis.INSTANCE_TYPE) {
                case SERVER -> {
                    try {
                        onCommand(Artemis.getConsoleCommandSender(), reader.readLine());
                    } catch (IOException e) {
                        // Move on
                    }
                }
                case CLIENT -> {
                    final CommandSender sender = Artemis.getSessionManager().getLocalPlayer();
                    // TODO
                }
            }
        }
    });
}
