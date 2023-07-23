package oasis.artemis.command;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Command</h2>
 * <p>Commands can be sent by a {@link CommandSender}.</p>
 */
public abstract class Command {
    //
    // Constructors
    //

    /**
     * Creates a new command.
     * @param name Name of command
     */
    public Command(@Nonnull String name) {
        this(name, new ArrayList<>(), null);
    }

    /**
     * Creates a new command.
     * @param name Name of command
     * @param aliases List of aliases
     */
    public Command(@Nonnull String name, @Nonnull List<String> aliases) {
        this(name, aliases, null);
    }

    /**
     * Creates a new command.
     * @param name Name of command
     * @param executor Executor of command
     */
    public Command(@Nonnull String name, @Nullable CommandExecutor executor) {
        this(name, new ArrayList<>(), executor);
    }

    /**
     * Creates a new command.
     * @param name Name of command
     * @param aliases List of aliases
     * @param executor Executor of command
     */
    public Command(@Nonnull String name, @Nonnull List<String> aliases, @Nullable CommandExecutor executor) {
        this.name = name;
        this.aliases = aliases;
        this.executor = executor;
    }

    //
    // Variables
    //

    @Nonnull
    private final String name;
    @Nonnull
    private final List<String> aliases;
    @Nullable
    private CommandExecutor executor;

    //
    // Getters
    //

    /**
     * Gets the name of this command.
     * @return Name
     */
    @Nonnull
    public String getName() {
        return name;
    }

    /**
     * Gets a list of aliases of this command.
     * @return List of aliases
     */
    @Nonnull
    public List<String> getAliases() {
        return new ArrayList<>(aliases);
    }

    /**
     * Gets the executor of this command.
     * @return {@link CommandExecutor} if found, {@code null} if not
     */
    @Nullable
    public CommandExecutor getExecutor() {
        return executor;
    }

    //
    // Setters
    //

    /**
     * Sets the executor of this command.
     * @param executor Executor
     */
    public void setExecutor(@Nullable CommandExecutor executor) {
        this.executor = executor;
    }
}
