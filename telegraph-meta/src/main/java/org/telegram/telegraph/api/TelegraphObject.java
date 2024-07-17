package org.telegram.telegraph.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * An object from the Telegraph API received from Telegraph Servers
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public interface TelegraphObject extends Serializable {
}
