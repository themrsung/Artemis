package oasis.artemis.util.geometry.profile;

import oasis.artemis.object.ArtemisObject;
import oasis.artemis.util.geometry.solid.Solid;

import javax.annotation.Nonnull;

/**
 * <h2>GeometricProfile</h2>
 * <p>
 * Represents the potential geometry of an object.
 * Unlike solids, geometric profiles do not have discrete coordinates.
 * Instead, they use a relative coordinate system, which can be translated to discrete
 * solids in real-time. (with respect to their parent object's location and rotation)
 * </p>
 */
public interface GeometricProfile {
    //
    // Building
    //

    /**
     * Builds this geometric profile into a discrete solid.
     *
     * @param parent The parent object of this geometric profile
     * @return Built solid
     */
    @Nonnull
    Solid build(@Nonnull ArtemisObject parent);
}
