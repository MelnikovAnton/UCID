## Автор
Anton Melnikov (Антон Мельников)

melnikoffaa@mail.ru

## Описание
Приложение отображает UCID звонка.
<br/>
Для запуска приложения нужно положить exe файл и каталог config в одну дирректорию. 


## Сборка
* Добавить зависимость для JTAPI.<br/>
 **Пример:**
 ````
 mvn install:install-file -Dfile=D:\java\dmcc\JTAPI_SDK_Client\jtapi-sdk-6.3.0.121\lib\ecsjtapia.jar -DgroupId=com.avaya -DartifactId=tsapi -Dversion=6.3.0.121 -Dpackaging=jar -DlocalRepositoryPath=c:\localrep
 ````

* Добавить настройки в файл **resources\TSAPI.PRO**.<br/>
 **Пример:**
 ````
 [Telephony Servers]
 172.27.17.30=450
 ````

* Настроить логирование **resources\log4j.properties**.<br/>

* Собрать приложение:
  ````
  mvn install
  ````
 
## Настройка собранного приложения 
* Изменить файл **app.properties** в каталоге **config**: <br/>
 **Пример:**<br/>
  ```
  station = 5803 //номер телефона
  server = 172.27.17.30 //адрес AES сервера. Не обязателен так-ка берется из TSAPI.PRO 
  login = CTIuser // Имя пользователя на AES
  password = CTIuser_1 // Пароль пользователя на AES
  ```
