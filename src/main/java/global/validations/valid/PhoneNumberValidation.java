package global.validations.valid;

import global.validations.PhoneNumberValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Abdyrazakova Aizada
 */
public class PhoneNumberValidation implements ConstraintValidator<PhoneNumberValid,String > {

    @Override
    public boolean isValid(String p, ConstraintValidatorContext constraintValidatorContext) {
        return p.startsWith("+996") && p.length()==13;
    }
}
