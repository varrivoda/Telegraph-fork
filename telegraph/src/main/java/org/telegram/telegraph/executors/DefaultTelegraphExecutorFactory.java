package org.telegram.telegraph.executors;

import com.google.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegraph.ExecutorOptions;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
@Component
public class DefaultTelegraphExecutorFactory implements TelegraphExecutorFactory {
    private TelegraphExecutor executor;
    private static final Object LOCK = new Object();

    @Autowired
    public DefaultTelegraphExecutorFactory() {
        if (executor == null) {
            synchronized (LOCK) {
                if (executor == null) {
                    this.executor = new DefaultTelegraphExecutor(new ExecutorOptions());
                }
            }
        }
    }

    @Override
    public TelegraphExecutor getExecutor() {
        return executor;
    }
}
