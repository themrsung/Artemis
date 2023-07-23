package oasis.artemis.ui.component.viewport;

import oasis.artemis.level.Level;
import oasis.artemis.object.ArtemisObject;
import oasis.artemis.util.math.Quaternion;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * <h2>ViewportRenderContext</h2>
 * <p>Contextual data given to a viewport to render a level.</p>
 *
 * @param level           Level to render
 * @param origin          Origin of viewport
 * @param angle           Angle to view level
 * @param renderBlacklist List of objects to not render
 */
public record ViewportRenderContext(
        @Nonnull Level level,
        @Nonnull Vector origin,
        @Nonnull Quaternion angle,
        @Nonnull List<ArtemisObject> renderBlacklist
) {
}
