package oasis.artemis.util.group;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * <h2>Dyad</h2>
 * <p>A dyad is a container class which holds two entries of different types.</p>
 * @param <A> Type of entry A
 * @param <B> Type of entry B
 */
@Immutable
public class Dyad<A, B> {
    //
    // Constructors
    //

    /**
     * Creates a new dyad.
     * @param a Element A
     * @param b Element B
     */
    public Dyad(@Nonnull A a, @Nonnull B b) {
        this.a = a;
        this.b = b;
    }

    //
    // Variables
    //

    @Nonnull
    private final A a;
    @Nonnull
    private final B b;

    //
    // Getters
    //

    /**
     * Gets the element A of this dyad.
     * @return A
     */
    @Nonnull
    public A getA() {
        return a;
    }

    /**
     * Gets the element B of this dyad.
     * @return B
     */
    @Nonnull
    public B getB() {
        return b;
    }

    //
    // Comparison
    //

    /**
     * Checks for equality between two dyads.
     * @param other Dyad to compare to
     * @return {@code true} if the elements are equal
     */
    public boolean equals(@Nonnull Dyad<A, B> other) {
        return a.equals(other.a) && b.equals(other.b);
    }

    //
    // Util
    //

    /**
     * Checks if this dyad contains given element.
     * @param element Element to check
     * @return {@code true} if this dyad contains given element
     * @param <C> Type of element
     */
    public <C> boolean contains(@Nonnull C element) {
        boolean contains = false;

        if (a.getClass().isInstance(element)) {
            if (a.equals(a.getClass().cast(element))) contains = true;
        }

        if (b.getClass().isInstance(element)) {
            if (b.equals(b.getClass().cast(element))) contains = true;
        }

        return contains;
    }

    /**
     * Flips this dyad around.
     * @return Flipped dyad
     */
    @Nonnull
    public Dyad<B, A> flip() {
        return new Dyad<>(b, a);
    }
}
