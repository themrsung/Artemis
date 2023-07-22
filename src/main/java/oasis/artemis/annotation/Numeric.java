package oasis.artemis.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h2>Numeric</h2>
 * <p>
 * An annotation which denotes explicitly that
 * a given field is not allowed to be NaN.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Numeric.NumericValidator.class)
@Target({ElementType.TYPE_USE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
public @interface Numeric {
    /**
     * The validator of {@link Numeric}.
     */
    class NumericValidator implements ConstraintValidator<Numeric, Number> {
        @Override
        public boolean isValid(Number value, ConstraintValidatorContext context) {
            if (value instanceof Double d) {
                return Double.isFinite(d);
            } else if (value instanceof Float f) {
                return Float.isFinite(f);
            }

            return true;
        }
    }
}
