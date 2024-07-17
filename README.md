## Telegraph Java Library
## Written using Google Guice. Let's try to migrate it to Spring

Forked from [https://github.com/rubenlagus/Telegraph](https://github.com/rubenlagus/Telegraph)
[![MIT License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](https://github.com/rubenlagus/Telegraph/blob/master/LICENSE)



Для начала, из коробки не работает. 
Надо поправить версию Guice на 5.0.1
А, еще исходный проект написан на java8... Ну да ладно, работает, обойдусь без List.of() :)

Первым делом надо обратить внимание на TelegraphContext, а именно на конфиг ApiModule.
Ничего необычного, инициализация бинов и синглтонов. По идее можно просто все вырезать, и чинить с помощью Спринга все что сломается.

добавим в pom Спринг 5.2.25.RELEASE, потому что ону меня уже есть скачанный :)

в демке уберем контекст Джуса и создадим спринговый контекст, зарегистрируем туда все что надо, везде где можно ставим аннотации @Component, пишем конфиг класс....

