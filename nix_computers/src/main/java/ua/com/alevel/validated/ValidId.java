package ua.com.alevel.validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {
        ValidIdConstraintValidator.class
})
@Documented
@Target({
        ElementType.FIELD,
        ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidId {

    String message() default "must be number";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
