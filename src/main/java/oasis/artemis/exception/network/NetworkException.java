package oasis.artemis.exception.network;

import javax.annotation.Nonnull;

/**
 * <h2>NetworkException</h2>
 * <p>A superclass for Artemis network exceptions.</p>
 */
public class NetworkException extends RuntimeException {
    /**
     * Default constructor.
     */
    public NetworkException() {
    }

    /**
     * Constructor with message.
     *
     * @param message Error message
     */
    public NetworkException(@Nonnull String message) {
        super(message);
    }

    /**
     * Constructor with message and cause.
     *
     * @param message Error message
     * @param cause   Cause of exception
     */
    public NetworkException(@Nonnull String message, @Nonnull Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with cause.
     *
     * @param cause Cause of exception
     */
    public NetworkException(@Nonnull Throwable cause) {
        super(cause);
    }

    /**
     * All-args constructor.
     *
     * @param message            Error message
     * @param cause              Cause of exception
     * @param enableSuppression  Whether to enable suppression
     * @param writableStackTrace Whether the stack trace should be writable
     */
    public NetworkException(@Nonnull String message, @Nonnull Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
