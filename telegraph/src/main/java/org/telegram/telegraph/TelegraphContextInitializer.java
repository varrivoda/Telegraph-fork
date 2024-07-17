package org.telegram.telegraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegraph.executors.DefaultTelegraphExecutorFactory;
import org.telegram.telegraph.executors.TelegraphExecutorFactory;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
@Component
public final class TelegraphContextInitializer {
    @Autowired
    ApplicationContext context;

    private TelegraphContextInitializer() {
    }

    public static void init() {
        TelegraphContext.register(TelegraphExecutorFactory.class, DefaultTelegraphExecutorFactory.class);
    }
}
