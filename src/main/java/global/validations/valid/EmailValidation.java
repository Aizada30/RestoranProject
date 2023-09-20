package global.validations.valid;

import global.validations.EmailValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Abdyrazakova Aizada
 */
public class EmailValidation implements ConstraintValidator<EmailValid,String > {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.endsWith("@gmail.com") ;
    }
}
