package global.validations.valid;

import global.validations.LengthOfWaiters;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Abdyrazakova Aizada
 */
public class LengthOfWaitersValidation implements ConstraintValidator<LengthOfWaiters,String > {

    @Override
    public boolean isValid(String p, ConstraintValidatorContext constraintValidatorContext) {
        return p.length()<=15;
    }
}
