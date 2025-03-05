package com.reyga.dev.utils.validations;

import com.reyga.dev.annotations.StringValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * The type Bdm string validation processor.
 */
public class StringValidationProcessor implements ConstraintValidator<StringValidation, String> {
    /**
     * The Message.
     */
    private String message;
    /**
     * The Field name.
     */
    private String fieldName;
    /**
     * The Min.
     */
    private int min;
    /**
     * The Max.
     */
    private int max;
    /**
     * The Not null.
     */
    private boolean notNull;
    /**
     * The Not blank.
     */
    private boolean notBlank;
    /**
     * The Email.
     */
    private boolean email;

    /**
     * The constant EMAIL_REGEX.
     */
    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    /**
     * Initialize.
     *
     * @param annotation the annotation
     */
    @Override
    public void initialize(StringValidation annotation) {
        this.message = annotation.message();
        this.fieldName = annotation.fieldName();
        this.min = annotation.min();
        this.max = annotation.max();
        this.notNull = annotation.notNull();
        this.notBlank = annotation.notBlank();
        this.email = annotation.email();
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
        if (notNull && value == null) {
            if (this.message.isEmpty() || this.message.isBlank()) {
                message = String.format("Input %s %s", fieldName, "Mandatory");
            } else {
                message = this.message;
            }
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }

        if (notBlank && value != null && value.trim().isEmpty()) {
            if (this.message.isEmpty() || this.message.isBlank()) {
                message = String.format("Input %s %s", fieldName, "Tidak boleh kosong");
            } else {
                message = this.message;
            }
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }

        if (value != null && value.length() < min) {
            if (this.message.isEmpty() || this.message.isBlank()) {
                message = String.format("Panjang karakter %s minimal %d karakter", fieldName, min);
            } else {
                message = this.message;
            }
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }

        if (value != null && value.length() > max) {
            if (this.message.isEmpty() || this.message.isBlank()) {
                message = String.format("Panjang karakter %s maksimal %d karakter", fieldName, max);
            } else {
                message = this.message;
            }
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }

        if (value != null && (email && !value.matches(EMAIL_REGEX))) {
            if (this.message.isEmpty() || this.message.isBlank()) {
                message = "Format Email tidak valid";
            } else {
                message = this.message;
            }
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }

        return true;
    }
}
