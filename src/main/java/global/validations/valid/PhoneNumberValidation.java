package global.validations.valid;

import global.validations.PhoneNumberValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidation implements ConstraintValidator<PhoneNumberValid,String > {

    @Override
    public boolean isValid(String p, ConstraintValidatorContext constraintValidatorContext) {
        return p.startsWith("+9965") || p.startsWith("+9967") || p.startsWith("+9963") && p.length()==13 ;
    }
}
