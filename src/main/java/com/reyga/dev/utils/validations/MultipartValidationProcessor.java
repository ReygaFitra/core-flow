package com.reyga.dev.utils.validations;

import com.reyga.dev.annotations.MultipartValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Onboard multipart validation processor.
 */
public class MultipartValidationProcessor implements ConstraintValidator<MultipartValidation, MultipartFile> {
    /**
     * The Message.
     */
    private String message;
    /**
     * The Field name.
     */
    private String fieldName;
    /**
     * The Max size.
     */
    private long maxSize;
    /**
     * The Allowed types.
     */
    private String[] allowedTypes;
    /**
     * The Required.
     */
    private boolean required;

    /**
     * The constant MIME_TYPE_TO_EXTENSION.
     */
    private static final Map<String, String> MIME_TYPE_TO_EXTENSION = new HashMap<>();

    static {
        MIME_TYPE_TO_EXTENSION.put("application/pdf", "pdf");
        MIME_TYPE_TO_EXTENSION.put("image/jpeg", "jpg");
        MIME_TYPE_TO_EXTENSION.put("image/png", "png");
        MIME_TYPE_TO_EXTENSION.put("image/svg+xml", "svg");
        MIME_TYPE_TO_EXTENSION.put("image/svg", "svg");
        MIME_TYPE_TO_EXTENSION.put("image/jpg", "jpg");
    }

    /**
     * Initialize.
     *
     * @param annotation the annotation
     */
    @Override
    public void initialize(MultipartValidation annotation) {
        this.message = annotation.message();
        this.fieldName = annotation.fieldName();
        this.maxSize = annotation.maxSize();
        this.allowedTypes = annotation.allowedTypes();
        this.required = annotation.required();
    }

    /**
     * Is valid boolean.
     *
     * @param file    the file
     * @param context the context
     * @return the boolean
     */
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        String message;
        if (file == null) {
            if (required) {
                if (this.message.isEmpty() || this.message.isBlank()) {
                    message = String.format("Dokumen %s Mandatory.", fieldName);
                } else {
                    message = this.message;
                }
                buildConstraintViolation(context, message);
                return false;
            }
            return true;
        }

        if (required && file.isEmpty()) {
             if (this.message.isEmpty() || this.message.isBlank()) {
                    message = String.format("Dokumen %s Mandatory.", fieldName);
                } else {
                    message = this.message;
                }
            buildConstraintViolation(context, message);
            return false;
        }

        if (file.getSize() > maxSize) {
             if (this.message.isEmpty() || this.message.isBlank()) {
                    message = String.format("Ukuran %s melebihi batas maksimum", fieldName);
                } else {
                    message = this.message;
                }
            buildConstraintViolation(context, message);
            return false;
        }

        if (allowedTypes.length > 0 && !isAllowedContentType(file.getContentType())) {
            String allowedTypesList = getAllowedExtensions();
            if (this.message.isEmpty() || this.message.isBlank()) {
                message = String.format("Format %s wajib %s", fieldName, allowedTypesList);
            } else {
                message = this.message;
            }
            buildConstraintViolation(context, message);
            return false;
        }

        return true;
    }

    /**
     * Is allowed content type boolean.
     *
     * @param contentType the content type
     * @return the boolean
     */
    private boolean isAllowedContentType(String contentType) {
        for (String allowedType : allowedTypes) {
            if (allowedType.equalsIgnoreCase(contentType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets allowed extensions.
     *
     * @return the allowed extensions
     */
    private String getAllowedExtensions() {
        StringBuilder extensions = new StringBuilder();
        for (String allowedType : allowedTypes) {
            String allowedExtension = MIME_TYPE_TO_EXTENSION.getOrDefault(allowedType, "");
            if (!allowedExtension.isEmpty()) {
                if (extensions.length() > 0) {
                    extensions.append(", ");
                }
                extensions.append(allowedExtension);
            }
        }
        return extensions.toString();
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
