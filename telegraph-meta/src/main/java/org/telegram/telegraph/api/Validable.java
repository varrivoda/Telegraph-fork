package org.telegram.telegraph.api;

import org.springframework.stereotype.Component;
import org.telegram.telegraph.api.methods.exceptions.TelegraphValidationException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
@Component
public interface Validable {
    /**
     * Validates that mandatory fields are filled and optional objects
     * @throws TelegraphValidationException If any mandatory field is invalid
     */
    void validate() throws TelegraphValidationException;
}
