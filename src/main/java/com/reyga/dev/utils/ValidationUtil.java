package com.reyga.dev.utils;

import com.reyga.dev.exceptions.CustomValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ValidationUtil<T> {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public void validateSetPattern(T request) {
        Set<ConstraintViolation<T>> violations = validator.validate(request);
        this.violationsSetHandle(violations);
    }

    public void validateSetPattern(T request, Class<?>... validationGroup) {
        Set<ConstraintViolation<T>> violations = validator.validate(request, validationGroup);
        this.violationsSetHandle(violations);
    }

    public void validateMapPattern(T request) {
        Set<ConstraintViolation<T>> violations = validator.validate(request);
        this.violationsMapHandle(violations);
    }

    public void validateMapPattern(T request, Class<?>... validationGroup) {
        Set<ConstraintViolation<T>> violations = validator.validate(request, validationGroup);
        this.violationsMapHandle(violations);
    }

    private void violationsSetHandle(Set<ConstraintViolation<T>> violations) {
        if (!violations.isEmpty()) {
            Set<String> errorMessage = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new CustomValidationException(errorMessage);
        }
    }

    private void violationsMapHandle(Set<ConstraintViolation<T>> violations) {
        if (!violations.isEmpty()) {
            List<Map<String, String>> validationMessage = violations
                    .stream()
                    .map(errors -> new HashMap<String, String>() {{
                                put(errors.getInvalidValue().toString(), errors.getMessage());
                            }}
                    )
                    .collect(Collectors.toList());
            throw new CustomValidationException(validationMessage);
        }
    }

}
