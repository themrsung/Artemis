package oasis.artemis.util.physics;

/**
 * <h2>Metric</h2>
 * <p>
 * Contains utility functions related to the metric system.
 * Every return value is denoted in SI standard units,
 * unless explicitly specified.
 * </p>
 */
public final class Metric {
    //
    // Distance
    //

    /**
     * Converts millimeter to meter.
     *
     * @param millimeter Millimeter
     * @return Meter
     */
    public static double millimeter(double millimeter) {
        return millimeter / 1000;
    }

    /**
     * Converts centimeter to meter.
     *
     * @param centimeter Centimeter
     * @return Meter
     */
    public static double centimeter(double centimeter) {
        return centimeter / 100;
    }

    /**
     * Converts kilometer to meter.
     *
     * @param kilometer Kilometer
     * @return Meter
     */
    public static double kilometer(double kilometer) {
        return kilometer * 1000;
    }

    /**
     * Converts inch to meter.
     *
     * @param inch Inch
     * @return Meter
     */
    public static double inch(double inch) {
        return inch * 0.0254;
    }

    /**
     * Converts feet to meter.
     *
     * @param feet Feet
     * @return Meter
     */
    public static double feet(double feet) {
        return feet * 0.3048;
    }

    /**
     * Converts yard to meter.
     *
     * @param yard Yard
     * @return Meter
     */
    public static double yard(double yard) {
        return yard * 0.9144;
    }

    /**
     * Converts mile to meter.
     *
     * @param mile Mile
     * @return Meter
     */
    public static double mile(double mile) {
        return mile * 1609.34;
    }

    //
    // Speed
    //

    /**
     * Converts kilometers per hour to meters per second.
     *
     * @param kph km/h
     * @return m/s
     */
    public static double kilometersPerHour(double kph) {
        return kph * 0.277778;
    }

    /**
     * Converts miles per hour to meters per second.
     *
     * @param mph miles/h
     * @return meters/s
     */
    public static double milesPerHour(double mph) {
        return mph * 0.44704;
    }

    /**
     * Converts feet per second to meters per second.
     * Mainly used for bullet velocities.
     *
     * @param fps f/s
     * @return m/s
     */
    public static double feetPerSecond(double fps) {
        return fps * 0.3048;
    }

    //
    // Mass
    //

    /**
     * Converts micrograms to kilograms.
     *
     * @param ug μg
     * @return kg
     */
    public static double microgram(double ug) {
        return ug * 1e-9;
    }

    /**
     * Converts milligrams to kilograms.
     *
     * @param mg mg
     * @return kg
     */
    public static double milligram(double mg) {
        return mg * 1e-6;
    }

    /**
     * Converts grams to kilograms.
     *
     * @param g g
     * @return kg
     */
    public static double gram(double g) {
        return g * 0.001;
    }

    /**
     * Converts metric tons to kilograms.
     *
     * @param t t
     * @return kg
     */
    public static double ton(double t) {
        return t * 1000;
    }

    /**
     * Converts grains to kilograms.
     *
     * @param gr gr
     * @return kg
     */
    public static double grain(double gr) {
        return gr * 6.4799e-5;
    }

    /**
     * Converts ounces to kilograms.
     *
     * @param oz oz
     * @return kg
     */
    public static double ounce(double oz) {
        return oz * 0.0283495;
    }

    /**
     * Converts pounds to kilograms.
     *
     * @param lb lb
     * @return kg
     */
    public static double pound(double lb) {
        return lb * 0.453592;
    }

    /**
     * Converts imperial tons(2,000lbs) to kilograms.
     *
     * @param t t
     * @return kg
     */
    public static double tonne(double t) {
        return t * 907.185;
    }
}
