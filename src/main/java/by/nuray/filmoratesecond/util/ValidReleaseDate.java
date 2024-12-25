package by.nuray.filmoratesecond.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ReleaseDateValidator.class)
public @interface ValidReleaseDate {

    String message() default "The release date is no earlier than December 28, 1895";
    Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};


}
