package com.reyga.dev.utils.validations;

import com.reyga.dev.annotations.NumberValidation;
import com.reyga.dev.utils.CommonLogger;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

/**
 * The type Bdm number validation processor.
 */
public class NumberValidationProcessor implements ConstraintValidator<NumberValidation, Object> {

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
    private long min;
    /**
     * The Max.
     */
    private long max;
    /**
     * The Max integer digits.
     */
    private int maxIntegerDigits;
    /**
     * The Max fraction digits.
     */
    private int maxFractionDigits;
    /**
     * The Not null.
     */
    private boolean notNull;

    private final CommonLogger logger = new CommonLogger();

    /**
     * Initialize.
     *
     * @param annotation the annotation
     */
    @Override
    public void initialize(NumberValidation annotation) {
        this.message = annotation.message();
        this.fieldName = annotation.fieldName();
        this.min = annotation.min();
        this.max = annotation.max();
        this.maxIntegerDigits = annotation.integer();
        this.maxFractionDigits = annotation.fraction();
        this.notNull = annotation.notNull();
    }

    /**
     * Is valid boolean.
     *
     * @param value   the value
     * @param context the context
     * @return the boolean
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String message;
        if (notNull && value == null) {
            if (this.message.isEmpty() || this.message.isBlank()) {
                message = String.format("Input %s %s", fieldName, "Mandatory");
            } else {
                message = this.message;
            }
            buildConstraintViolation(context, message);
            return false;
        }

        if (value == null) {
            return true;
        }

        try {
            BigDecimal number;

            if (value instanceof String) {
                String strValue = (String) value;
                if (this.message.isEmpty() || this.message.isBlank()) {
                    message = String.format("Input %s tidak boleh kosong", fieldName);
                } else {
                    message = this.message;
                }
                if (strValue.trim().isEmpty()) {
                    buildConstraintViolation(context, message);
                    return false;
                }
                number = new BigDecimal(strValue);
            } else if (value instanceof Number) {
                number = new BigDecimal(value.toString());
            } else {
                if (this.message.isEmpty() || this.message.isBlank()) {
                    message = String.format("Input %s harus berupa angka", fieldName);
                } else {
                    message = this.message;
                }
                buildConstraintViolation(context, message);
                return false;
            }

            long numericValue = number.longValue();
            if (numericValue < min) {
                if (this.message.isEmpty() || this.message.isBlank()) {
                    message = String.format("Input %s minimal %d", fieldName, min);
                } else {
                    message = this.message;
                }
                buildConstraintViolation(context,
                        message);
                return false;
            }

            if (numericValue > max) {
                if (this.message.isEmpty() || this.message.isBlank()) {
                    message = String.format("Input %s maksimal %d", fieldName, max);
                } else {
                    message = this.message;
                }
                buildConstraintViolation(context,
                        message);
                return false;
            }

            int integerDigits = number.precision() - number.scale();
            int fractionDigits = Math.max(number.scale(), 0);

            if (integerDigits > maxIntegerDigits) {
                if (this.message.isEmpty() || this.message.isBlank()) {
                    message = String.format("Jumlah digit angka sebelum desimal pada %s melebihi batas maksimum (%d)", fieldName, maxIntegerDigits);
                } else {
                    message = this.message;
                }
                buildConstraintViolation(context,
                        message);
                return false;
            }

            if (fractionDigits > maxFractionDigits) {
                if (this.message.isEmpty() || this.message.isBlank()) {
                    message = String.format("Jumlah digit angka setelah desimal pada %s melebihi batas maksimum (%d)", fieldName, maxFractionDigits);
                } else {
                    message = this.message;
                }
                buildConstraintViolation(context,
                        message);
                return false;
            }

        } catch (NumberFormatException e) {
            if (this.message.isEmpty() || this.message.isBlank()) {
                    message = String.format("Input %s harus berupa angka yang valid", fieldName);
                } else {
                    message = this.message;
                }
            logger.info("NumberFormatException :", e.getMessage());
            buildConstraintViolation(context, message);
            return false;
        }

        return true;
    }

    /**
     * Build constraint violation.
     *
     * @param context the context
     * @param message the message
     */
    private void buildConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
