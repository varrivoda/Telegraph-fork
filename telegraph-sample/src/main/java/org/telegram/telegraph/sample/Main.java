package org.telegram.telegraph.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.telegram.telegraph.ExecutorOptions;
import org.telegram.telegraph.TelegraphLogger;
import org.telegram.telegraph.api.TelegraphMethod;
import org.telegram.telegraph.api.methods.*;
import org.telegram.telegraph.api.objects.*;
import org.telegram.telegraph.api.methods.exceptions.TelegraphException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

/**
 * 123
 * @author Ruben Bermudez
 * @version 1.0
 */

@ComponentScan(basePackages={"org.telegram.telegraph"})
@Lazy(false)
public class Main {
    @Autowired ConfigurableListableBeanFactory beanFactory;

    public static void main(String[] args) {
        // Set up logger
        TelegraphLogger.setLevel(Level.ALL);
        TelegraphLogger.registerLogger(new ConsoleHandler());



        // Initialize context
//        TelegraphContextInitializer.init();
//        TelegraphContext.registerInstance(ExecutorOptions.class, new ExecutorOptions());
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean(ExecutorOptions.class, new ExecutorOptions());
        context.registerBean(TelegraphMethod.class);




        try {
            // Create account
            Account account = new CreateAccount("TestTelegraphApi")
                    .setAuthorName("TelegraphApi")
                    .execute();

            // Edit account
            Account editedAccount = new EditAccountInfo(account.getAccessToken())
                    .setAuthorName("Default user")
                    .setShortName("Short name")
                    .execute();

            // Get account info
            editedAccount = new GetAccountInfo(account.getAccessToken())
                    .execute();

            Node contentNode= new NodeText("My content");
            List<Node> content = new ArrayList<>();
            content.add(contentNode);

            // Create new account
            Page page = new CreatePage(account.getAccessToken(), "My title", content)
                    .setAuthorName("Random author")
                    .setReturnContent(true)
                    .execute();

            // Get page
            page = new GetPage(page.getPath()).setReturnContent(true).execute();

            Node tagNode = new NodeElement("p", new HashMap<>(), content);
            List<Node> tagContent = new ArrayList<>();
            tagContent.add(tagNode);

            // Edit page
            Page editedPage = new EditPage(account.getAccessToken(), page.getPath(), page.getTitle(), tagContent)
                    .setAuthorName("New Author")
                    .execute();

            // Get page list
            PageList pageList = new GetPageList(account.getAccessToken())
                    .setLimit(10)
                    .execute();

            // Get page view
            PageViews views = new GetViews(page.getPath())
                    .setYear(2016)
                    .execute();

            // Revoke account token
            Account revokedAccount = new RevokeAccessToken(account.getAccessToken()).execute();

            System.out.println(editedPage.getUrl());

        } catch (TelegraphException e) {
            TelegraphLogger.severe("MAIN", e);
        }
    }
}
