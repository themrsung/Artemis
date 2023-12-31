package oasis.artemis.util.group;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * <h2>Pair</h2>
 * <p>
 * A pair of two objects of the same type.
 * Equality only considers the contents, and not their order.
 * {@code null} values are not supported.
 * </p>
 *
 * @param <T> Type of elements to hold
 */
@Immutable
public class Pair<T> implements Iterable<T> {
    //
    // Constructors
    //

    /**
     * Creates a new pair.
     *
     * @param first  First element of this pair
     * @param second Second element of this pair
     */
    public Pair(@Nonnull T first, @Nonnull T second) {
        this.first = first;
        this.second = second;
    }

    //
    // Variables
    //

    @Nonnull
    private final T first;
    @Nonnull
    private final T second;

    //
    // Getters
    //

    /**
     * Gets the first element of this pair.
     *
     * @return First element
     */
    @Nonnull
    public T getFirst() {
        return first;
    }

    /**
     * Gets the second element of this pair.
     *
     * @return Second element
     */
    @Nonnull
    public T getSecond() {
        return second;
    }

    //
    // Elements
    //

    /**
     * Checks if this pair contains given object.
     *
     * @param object Object to check
     * @return {@code true} if ths object is a member of this pair
     */
    public boolean contains(@Nonnull T object) {
        return first.equals(object) || second.equals(object);
    }

    //
    // Equality
    //

    /**
     * Checks for equality without regards to the elements' order.
     *
     * @param other Pair to compare to
     * @return {@code true} if the contents are equal
     */
    public boolean equals(@Nonnull Pair<T> other) {
        return (first.equals(other.first) && second.equals(other.second))
                || (second.equals(other.first) && first.equals(other.second));
    }

    /**
     * Checks for equality without regards to the elements' order.
     *
     * @param o other object
     * @return {@code true} if the contents are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair<?> pair)) return false;
        return (Objects.equals(first, pair.first) && Objects.equals(second, pair.second))
                || (Objects.equals(second, pair.first) && Objects.equals(first, pair.second));
    }

    //
    // Util
    //

    /**
     * Gets the other element when given one element.
     *
     * @param element Element to check
     * @return The other element
     * @throws IllegalArgumentException When the given element is not in this pair
     */
    @Nonnull
    public T other(@Nonnull T element) throws IllegalArgumentException {
        if (first.equals(element)) return second;
        if (second.equals(element)) return first;

        throw new IllegalArgumentException("Given element is not a member of this pair.");
    }

    /**
     * Gets the iterator of this pair.
     *
     * @return Iterator of pair
     */
    @Override
    @Nonnull
    public Iterator<T> iterator() {
        return List.of(first, second).iterator();
    }
}
