# Задание: Проверка Юридических лиц.
Реализовать консольное приложение выполняющее запрос к API http://npchk.nalog.ru/
На вход приложению подается ИНН  ЮЛ/ФЛ приложение возвращает один из возможных вариантов признака 
состояния описанных в документации [(ws2.doc)](http://npchk.nalog.ru/ws2.doc).  

# Used technologies

+ Java 8
+ Apache CXF
+ XML
+ JUnit

# Install
git clone https://github.com/a11exe/soap-client-nalog

# Run
mvn clean install

chcp 65001 (если запуск в console Windows UTF-8)

java -jar target/fns.jar

