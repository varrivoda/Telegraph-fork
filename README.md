## Telegraph Java Library
## Written using Google Guice. Let's try to migrate it to Spring

Forked from [https://github.com/rubenlagus/Telegraph](https://github.com/rubenlagus/Telegraph)
[![MIT License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](https://github.com/rubenlagus/Telegraph/blob/master/LICENSE)

### Part 0. Failed to implement Spring forcely...
Первая попытка "в лоб" заменить Guice на аналогичный функцмионал Спринга провалилась.

Вообще, в этой библиотеке применены довольно странные решения, которые во всех кругах признаны антипаттернами. Как-то: инжекция контекста вбизнес-логику и лукап, статические методы, очень странный паттерн executor-помесь с билдером. Создаются классы, которые ведут себякак статические... Надеюсь, во вселенной Guiceэто всё же не является нормой, надо спросить бывалых. Как правило, говорят, что Спринг с таким стилем не дружит, ну вот я и проверил это на себе лично =))

Но при всем этом, идея автора хорошо прослеживается, компоненты хорошо структирированы и инкапсулированы, и скорее всего их легко тестировать.

Короче. Надо взять вот эту замечательную структуру, дата-классы, эксепшены, валидаторы, json-хелперы - и поверх них написать нормальные объекты с нормальными методами.

Пробежимся по функционалу библиотеки:

```
    // Create new account 
    Account account = new CreateAccount("Test").setAuthorName("Apee").execute();
    
    // Edit account
    Account editedAccount = new UserInfo(token).setAuthorName("User").setShortName("U").execute();
    
    // Get account info
    editedAccount = new GetAccountInfo(token).execute();
    
    List<Node> content = List.of(new NodeText("My content"));
    
    // Create new page
    Page page = new CreatePage(token, "Title", content).setAuthorName("Random").setReturnContent(true).execute();
    
    // Get page
    page = new GetPage(page.getPath()).setReturnContent(true).execute();
    
    List<Node> tagContent = List.of(new NodeElement("p", new HashMap<>(), content));
    
    // Edit page
    Page editedPage = new EditPage(token, page.getPath(), page.getTitle(), tagContent).setAuthorName("New Author").execute();
    
    // Get my pages list 
    PageList pageList = new GetPageList(token).setLimit(10).execute();
    
    // Get page view
    PageViews views = new GetViews(page.getPath()).setYear(2016).execute();
    
    // Revoke account token
    Account revokedAccount = new RevokeAccessToken(token).execute();
```

итого, имеем 5 методов, связанных с аккаунтом (create, edit, getInfo, getPagesList, revokeToken) и 4 метода, связанных со страницей (create, edit, get, getViews)

напрашивается делать что-то типа "Active record". Нонадопочитать, как у этого паттерна обстоят дела с удобством тестирования...