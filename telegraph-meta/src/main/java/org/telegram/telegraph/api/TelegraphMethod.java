package org.telegram.telegraph.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.telegram.telegraph.api.methods.exceptions.TelegraphException;
import org.telegram.telegraph.api.methods.exceptions.TelegraphRequestException;
import org.telegram.telegraph.executors.TelegraphExecutorFactory;

import javax.annotation.PostConstruct;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public abstract class TelegraphMethod<T extends TelegraphObject> implements Validable, ApplicationContextAware {
    @JsonIgnore
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    ApplicationContext applicationContext;

    @PostConstruct
    public void setApplicationContext(ApplicationContext context){
        this.applicationContext = context;
    }

    /**
     * Deserialize a json answer to the response type to a method
     * @param answer Json answer received
     * @return Answer for the method
     */
    public abstract T deserializeResponse(String answer) throws TelegraphRequestException;

    /**
     * Return the path to perform Https request
     * @return Path that needs to be appended to the base url
     */
    @JsonIgnore
    public abstract String getUrlPath();

    /**
     * Execute this method directly
     * @return Result of method execution
     * @throws TelegraphException If request or validation fails.
     */
    public T execute() throws TelegraphException {
        validate();
//        return TelegraphContext.getInstance(TelegraphExecutorFactory.class)
//                .getExecutor().execute(this);
        //Object o = applicationContext.getBean(TelegraphExecutorFactory.class);
        //System.out.println(o);
        return applicationContext.getBean(TelegraphExecutorFactory.class).getExecutor().execute(this);
    }
}
