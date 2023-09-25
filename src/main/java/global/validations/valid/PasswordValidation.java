package global.validations.valid;

import global.validations.PasswordValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Abdyrazakova Aizada
 */
public class PasswordValidation implements ConstraintValidator<PasswordValid,String > {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length()>=4;
    }
}
