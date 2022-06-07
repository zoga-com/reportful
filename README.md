# Reportful

**Бот предназначен для помощи в модерации Discord-серверов.**

Данный бот содержит в себе всего две команды:

/report @<пользователь> <причина> - подать репорт на пользователя в канале. Данный репорт придет в указаный в bot.config канал. (обязательно настройте его.)
Пример пришедшего репорта:
![reportExample](https://i.imgur.com/hD6jCas.jpg)

/history @<пользователь> - получить 50 последних сообщений пользователя.
Пример ответа:
![historyExample](https://i.imgur.com/gWiAXGt.jpg)





**Гайд по компиляции**

1. Откройте папку с проектом
2. Установите Maven (если он не установлен)
3. Откройте терминал/командную строку
4. Введите команду
 Windows: mvnw clean compile assembly:single
 Linux: mvn clean compile assembly:single
5. После успешной компиляции .jar будет находится по пути target/reportful-1.0-jar-with-dependencies.jar
