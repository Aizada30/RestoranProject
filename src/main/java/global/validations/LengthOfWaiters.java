package global.validations;

import global.validations.valid.PhoneNumberValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Abdyrazakova Aizada
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
public @interface LengthOfWaiters {
    String message() default "The restaurant must have a maximum of 15 employees";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
