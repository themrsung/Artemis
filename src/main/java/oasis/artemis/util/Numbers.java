package oasis.artemis.util;

import oasis.artemis.util.math.Matrix;
import oasis.artemis.util.math.Quaternion;
import oasis.artemis.util.math.Vector;

import javax.annotation.Nonnull;
import java.util.Arrays;

/**
 * <h2>Numbers</h2>
 * <p>A numerical utility class.</p>
 */
public final class Numbers {
    //
    // Matrix-matrix arithmetic
    //

    /**
     * Adds two matrices together.
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Sum of two matrices
     * @throws ArithmeticException When addition is impossible (the dimensions are different)
     */
    @Nonnull
    public static Matrix add(@Nonnull Matrix m1, @Nonnull Matrix m2) throws ArithmeticException {
        if (m1.getRows() != m2.getRows() || m1.getColumns() != m2.getColumns()) {
            throw new ArithmeticException("Cannot perform addition of matrices with different dimensions.");
        }

        final Matrix result = new Matrix(m1.getRows(), m1.getColumns());

        for (int r = 0; r < m1.getRows(); r++) {
            for (int c = 0; c < m1.getColumns(); c++) {
                result.set(r, c, m1.get(r, c) + m2.get(r, c));
            }
        }

        return result;
    }

    /**
     * Subtracts two matrices.
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Difference of two matrices
     * @throws ArithmeticException When subtraction is impossible (the dimensions are different)
     */
    @Nonnull
    public static Matrix subtract(@Nonnull Matrix m1, @Nonnull Matrix m2) throws ArithmeticException {
        if (m1.getRows() != m2.getRows() || m1.getColumns() != m2.getColumns()) {
            throw new ArithmeticException("Cannot perform subtraction of matrices with different dimensions.");
        }

        final Matrix result = new Matrix(m1.getRows(), m2.getColumns());

        for (int r = 0; r < m1.getRows(); r++) {
            for (int c = 0; c < m1.getColumns(); c++) {
                result.set(r, c, m1.get(r, c) - m2.get(r, c));
            }
        }

        return result;
    }

    /**
     * Multiplies two matrices.
     * If the two matrices' dimensions are different, the resulting matrix will have a dimension of
     * {@code Math.max(m1.getRows(), m2.getRows()} and {@code Math.max(m1.getColumns(), m2.getColumns())}.
     *
     * @param m1 Matrix 1
     * @param m2 Matrix 2
     * @return Product of two matrices
     * @throws ArithmeticException When multiplication is impossible (the size is different)
     */
    @Nonnull
    public static Matrix multiply(@Nonnull Matrix m1, @Nonnull Matrix m2) throws ArithmeticException {
        final int r1 = m1.getRows();
        final int c1 = m1.getColumns();

        final int r2 = m2.getRows();
        final int c2 = m2.getColumns();

        final Matrix result = new Matrix(Math.max(r1, r2), Math.max(c1, c2));

        result.fill(0);

        try {
            for (int r = 0; r < r1; r++) {
                for (int c = 0; c < c2; c++) {
                    for (int i = 0; i < r2; i++) {
                        result.set(r, c, result.get(r, c) + m1.get(r, i) * m2.get(i, c));
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ArithmeticException("Cannot perform multiplication of matrices with different size.");
        }

        return result;
    }

    //
    // Parsing
    //

    /**
     * Parses a string to a vector.
     *
     * @param s String to parse
     * @return Vector parsed from string
     * @throws NumberFormatException When the string is not parsable to a vector
     */
    @Nonnull
    public static Vector parseVector(@Nonnull String s) throws NumberFormatException {
        if (!s.startsWith("Vector{")) throw new NumberFormatException("Given string is not a vector.");

        final String[] strings = s
                .replaceAll("Vector\\{", "")
                .replaceAll("}", "")
                .split(", ");

        final double[] values = {Double.NaN, Double.NaN, Double.NaN};

        Arrays.stream(strings).forEach(string -> {
            final String[] split = string.split("=");
            if (split.length != 2) throw new NumberFormatException("Given string is not a vector.");

            switch (split[0]) {
                case "x" -> values[0] = Double.parseDouble(split[1]);
                case "y" -> values[1] = Double.parseDouble(split[1]);
                case "z" -> values[2] = Double.parseDouble(split[1]);
            }
        });

        Arrays.stream(values).forEach(v -> {
            if (Double.isNaN(v)) throw new NumberFormatException("Cannot parse NaN into a vector.");
        });

        return new Vector(values[0], values[1], values[2]);
    }

    /**
     * Parses q string to a quaternion.
     *
     * @param s String to parse
     * @return Quaternion parsed from string
     * @throws NumberFormatException When the string is not parsable to a quaternion
     */
    @Nonnull
    public static Quaternion parseQuaternion(@Nonnull String s) throws NumberFormatException {
        if (!s.startsWith("Quaternion{")) throw new NumberFormatException("Given string is not a quaternion.");

        final String[] strings = s
                .replaceAll("Quaternion\\{", "")
                .replaceAll("}", "")
                .split(", ");

        final double[] values = {Double.NaN, Double.NaN, Double.NaN, Double.NaN};

        Arrays.stream(strings).forEach(string -> {
            final String[] split = string.split("=");
            if (split.length != 2) throw new NumberFormatException("Given string is not a quaternion.");

            switch (split[0]) {
                case "w" -> values[0] = Double.parseDouble(split[1]);
                case "x" -> values[1] = Double.parseDouble(split[1]);
                case "y" -> values[2] = Double.parseDouble(split[1]);
                case "z" -> values[3] = Double.parseDouble(split[1]);
            }
        });

        Arrays.stream(values).forEach(v -> {
            if (Double.isNaN(v)) throw new NumberFormatException("Cannot parse NaN into a quaternion.");
        });

        return new Quaternion(values[0], values[1], values[2], values[3]);
    }

    /**
     * Parses a string to a matrix.
     *
     * @param s String to parse
     * @return Parsed matrix
     * @throws NumberFormatException When given string is not parsable to a matrix
     */
    @Nonnull
    public static Matrix parseMatrix(@Nonnull String s) throws NumberFormatException {
        if (!s.startsWith("Matrix{rows={")) throw new NumberFormatException("Given string is not a quaternion.");

        final String[] strings = s
                .replaceAll("Matrix\\{rows=\\{", "")
                .replaceAll("}}", "")
                .replaceAll("\\[", "")
                .split("], ");

        final double[][] values;
        try {
            values = new double
                    [strings.length]
                    [strings.length > 0 ? strings[0].split("=")[1].split(", ").length : 0];
        } catch (IndexOutOfBoundsException e) {
            throw new NumberFormatException("Given string is not a matrix.");
        }

        Arrays.stream(strings).forEach(string -> {
            final String[] split = string.split("=");
            if (split.length != 2) throw new NumberFormatException("Given string is not a matrix.");

            final String[] numbers = split[1].replaceAll("]", "").split(", ");
            final int r = Integer.parseInt(split[0]);
            final double[] row = new double[numbers.length];

            for (int i = 0; i < numbers.length; i++) {
                row[i] = Double.parseDouble(numbers[i]);
            }

            values[r] = row;
        });

        return new Matrix(values);
    }
}
