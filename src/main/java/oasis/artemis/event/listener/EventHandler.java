package oasis.artemis.event.listener;

import javax.annotation.Nonnull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h2>EventHandler</h2>
 * <p>This marks a method to be called when the event is triggered.</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    /**
     * Gets the priority of this handler.
     * Defaults to {@link HandlerPriority#NORMAL}.
     *
     * @return {@link HandlerPriority}
     */
    @Nonnull
    HandlerPriority priority() default HandlerPriority.NORMAL;
}
