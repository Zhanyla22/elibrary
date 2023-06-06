# elibrary
1. Стянуть проект - перейти на ветку test

2. Создать БД elibrary(или любое):
   Например в psql : CREATE DATABASE elibrary;
   - Поменять кредо БД в application-dev.properties
   - Поменять upload.dir на локальный путь к папке static внутри проекта
3. Или можно указать в application.properties spring.profiles.active=prod для подключения к серсервной бд
4. Запустить проект
5. Можно по желанию где настройки email-sender написать свою почту 
   и где password получить специальный пароль в пароли приложений(gmail) для email-sender
