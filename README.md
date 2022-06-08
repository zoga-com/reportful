# Reportful

**Бот предназначен для помощи в модерации Discord-серверов.**

## Описание
Данный бот содержит в себе всего две команды:<br>
<br>
/report @<пользователь> <причина> - подать репорт на пользователя в канале. Данный репорт придет в указаный в bot.config канал. (обязательно настройте его.)<br>
Пример пришедшего репорта:<br>
![reportExample](https://i.imgur.com/hD6jCas.jpg)

/history @<пользователь> - получить 50 последних сообщений пользователя.<br>
Пример ответа:<br>
![historyExample](https://i.imgur.com/gWiAXGt.jpg)
<br>
## Гайд по компиляции
1. Откройте папку с проектом<br>
2. Установите Maven (если он не установлен)<br>
3. Откройте терминал/командную строку<br>
4. Введите команду<br>
5. Windows: mvnw clean compile assembly:single<br>
6. Linux: mvn clean compile assembly:single<br>
7. После успешной компиляции .jar будет находится по пути target/reportful-1.0-jar-with-dependencies.jar<br>

## Гайд по настройке
Все настройки бота находятся в файле **bot.config**<br>
Поле "token" - отвечает за токен бота. (получить этот токен можно на dev-портале Discord - https://discord.com/developers/applications)<br>
Поле "receiveChannel" - отвечает за канал, в который присылается репорт. (ID канала)<br>
Поле "reportColor" - отвечает за цвет присылаемого репорта. (только HEX-формат)<br>
