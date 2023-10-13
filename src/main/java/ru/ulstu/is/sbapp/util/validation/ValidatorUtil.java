package ru.ulstu.is.sbapp.util.validation;

import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ValidatorUtil {
    private final Validator validator;

    public ValidatorUtil() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            this.validator = factory.getValidator();
        }
    }

    public <T> void validate(T object) {
        final Set<ConstraintViolation<T>> errors = validator.validate(object);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet()));
        }
    }

}
