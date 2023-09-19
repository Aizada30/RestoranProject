package global.validations;

import global.validations.valid.EmailValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Abdyrazakova Aizada
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
public @interface EmailValid {

    String message() default "EmailValid mush ended with '@' and '.com";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
