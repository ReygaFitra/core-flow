package com.reyga.dev.utils.validations;

import com.reyga.dev.annotations.CustomPatternValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * The type Onboard custom pattern processor.
 */
public class CustomPatternValidationProcessor implements ConstraintValidator<CustomPatternValidation, String> {

    /**
     * The Message.
     */
    private String message;
    /**
     * The Regex.
     */
    private String regex;
    /**
     * The Field name.
     */
    private String fieldName;

    /**
     * Instantiates a new Custom pattern validation processor.
     */
    public CustomPatternValidationProcessor() {
    }

    /**
     * Instantiates a new Custom pattern validation processor.
     *
     * @param message   the message
     * @param regex     the regex
     * @param fieldName the field name
     */
    public CustomPatternValidationProcessor(String message, String regex, String fieldName) {
        this.message = message;
        this.regex = regex;
        this.fieldName = fieldName;
    }

    /**
     * Initialize.
     *
     * @param constraintAnnotation the constraint annotation
     */
    @Override
    public void initialize(CustomPatternValidation constraintAnnotation) {
        this.message = constraintAnnotation.message();
        this.regex = constraintAnnotation.regex();
        this.fieldName = constraintAnnotation.fieldName();
    }

    /**
     * Is valid boolean.
     *
     * @param value   the value
     * @param context the context
     * @return the boolean
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String message;
        if (value == null || value.isEmpty()) {
            return true;
        }

        boolean matches = value.matches(regex);
        if (!matches) {
            if (this.message.isEmpty() || this.message.isBlank()) {
                message = String.format("%s %s %s", fieldName, " Can't contains Special Characters ", regex);
            } else {
                message = this.message;
            }
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            message)
                    .addConstraintViolation();
        }
        return matches;
    }
}
