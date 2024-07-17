package org.telegram.telegraph.executors;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Implement this factory and register it in TelegraphContext to perform a way of getting an executor that
 * will allow executing a telegraph method.
 */
@Component
public interface TelegraphExecutorFactory {
    /**
     * Return an implementation of TelegraphExecutor
     * @return TelegraphExecutor to execute methods
     */
    TelegraphExecutor getExecutor();
}
