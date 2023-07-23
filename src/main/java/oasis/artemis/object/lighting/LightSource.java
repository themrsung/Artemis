package oasis.artemis.object.lighting;

import oasis.artemis.object.ArtemisObject;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * <h2>LightSource</h2>
 * <p>A light source can produce light.</p>
 */
public interface LightSource extends ArtemisObject {
    //
    // Lighting
    //

    /**
     * Gets the direction of this light source.
     * @return Direction of light
     */
    @Nonnull
    Vector getLightDirection();

    /**
     * Gets the intensity of this light source.
     * @return Intensity of light
     */
    @Nonnegative
    double getLightIntensity();

    /**
     * Sets the direction of this light source.
     * @param direction Direction of light
     */
    void setLightDirection(@Nonnull Vector direction);

    /**
     * Sets the intensity of this light source.
     * @param intensity Intensity
     */
    void setLightIntensity(@Nonnegative double intensity);
}
