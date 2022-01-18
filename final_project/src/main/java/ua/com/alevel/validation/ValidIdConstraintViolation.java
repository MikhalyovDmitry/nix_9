package ua.com.alevel.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidIdConstraintViolation implements ConstraintValidator<ValidId, Object> {

    @Override
    public boolean isValid(Object input, ConstraintValidatorContext context) {
        String value = String.valueOf(input);
        if (value.matches("\\d+")) {
            long id = Long.parseLong(value);
            return id > 0;
        }
        return false;
    }
}
