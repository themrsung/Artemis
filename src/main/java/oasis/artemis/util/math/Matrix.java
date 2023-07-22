package oasis.artemis.util.math;

import oasis.artemis.annotation.Numeric;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

/**
 * <h2>Matrix</h2>
 * <p>A rectangular array of {@link Double}.</p>
 */
public class Matrix implements Iterable<Double>, Serializable {
    //
    // Constructors
    //

    /**
     * Creates a new matrix with given initial size.
     *
     * @param rows    Number of rows
     * @param columns Number of columns
     */
    public Matrix(@Nonnegative int rows, @Nonnegative int columns) {
        this(new double[rows][columns]);
    }

    /**
     * Creates a new matrix with given dimensions and initial value.
     *
     * @param rows         Number of rows
     * @param columns      Number of columns
     * @param initialValue Initial value
     */
    public Matrix(@Nonnegative int rows, @Nonnegative int columns, @Numeric double initialValue) {
        this(rows, columns);
        fill(initialValue);
    }

    /**
     * Creates a new matrix with given values.
     *
     * @param values Values to use
     */
    public Matrix(@Nonnull double[][] values) {
        this.values = values;

        forEach(d -> {
            if (!Double.isFinite(d)) {
                throw new IllegalArgumentException("Cannot put a non-numeric value into a matrix.");
            }
        });
    }


    //
    // Variables
    //

    @Nonnull
    private final double[][] values;

    //
    // Getters
    //

    /**
     * Gets the value in specified position.
     *
     * @param r Index of row
     * @param c Index of column
     * @return Value of position
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Numeric
    public double get(@Nonnegative int r, @Nonnegative int c) throws IndexOutOfBoundsException {
        return values[r][c];
    }

    /**
     * Gets the values in this matrix.
     *
     * @return Values
     */
    @Nonnull
    public double[] getValues() {
        final double[] mapped = new double[getSize()];
        int i = 0;

        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getColumns(); c++) {
                mapped[i] = get(r, c);
                i++;
            }
        }

        return mapped;
    }

    /**
     * Gets the size of this matrix. (rows * columns)
     *
     * @return Size of matrix
     */
    @Nonnegative
    public int getSize() {
        return getRows() * getColumns();
    }

    /**
     * Gets the number of rows (height) of this matrix.
     *
     * @return Number of rows
     */
    @Nonnegative
    public int getRows() {
        return values.length;
    }

    /**
     * Gets the number of columns (width) of this matrix.
     *
     * @return Number of columns
     */
    @Nonnegative
    public int getColumns() {
        try {
            return values[0].length;
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    //
    // Setters
    //

    /**
     * Assigns a value to given position.
     *
     * @param r Index of row
     * @param c Index of column
     * @param v Value to put
     * @throws IndexOutOfBoundsException When index is out of bounds
     */
    public void set(@Nonnegative int r, @Nonnegative int c, @Numeric double v) throws IndexOutOfBoundsException {
        values[r][c] = v;
    }

    /**
     * Fills this matrix with given value.
     *
     * @param v Value to fill with
     */
    public void fill(@Numeric double v) {
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getColumns(); c++) {
                set(r, c, v);
            }
        }
    }

    //
    // Util
    //

    /**
     * Returns a resized matrix.
     *
     * @param r Number of rows
     * @param c Number of columns
     * @return Resized matrix
     */
    @Nonnull
    public Matrix resize(@Nonnegative int r, @Nonnegative int c) {
        final double[][] resized = new double[r][c];

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                try {
                    resized[i][j] = get(i, j);
                } catch (IndexOutOfBoundsException e) {
                    // Move on
                }
            }
        }

        return new Matrix(resized);
    }

    /**
     * Gets the iterator of this matrix.
     *
     * @return Iterator of matrix
     */
    @Override
    @Nonnull
    public Iterator<Double> iterator() {
        return Arrays.stream(getValues()).iterator();
    }

    /**
     * Converts this matrix to a string.
     *
     * @return Stringified matrix
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder builder = new StringBuilder("Matrix{rows={");

        for (int r = 0; r < getRows(); r++) {
            builder.append(r).append("=").append(Arrays.toString(values[r]));

            if (r < getRows() - 1) builder.append(", ");
        }

        return builder.append("}}").toString();
    }
}
