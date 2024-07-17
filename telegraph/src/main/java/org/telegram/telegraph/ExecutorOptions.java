package org.telegram.telegraph;

import com.google.inject.Inject;
import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
@Component
public class ExecutorOptions {
    private RequestConfig requestConfig;

    @Autowired
    public ExecutorOptions() {
    }

    public RequestConfig getRequestConfig() {
        return requestConfig;
    }

    /**
     * @implSpec Default implementation assumes no proxy is needed and sets a 75secs timeout
     * @param requestConfig Request config to be used in all Http requests
     */
    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }
}
