package global.validations;

import global.validations.valid.PasswordValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Abdyrazakova Aizada
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
public @interface PasswordValid {

    String message() default "Password must by length > 4";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
