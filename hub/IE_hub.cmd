rem curent directory
set CURPATH=%cd%
set REMOTE_SERVER_PORT=4447
set SELENIUM_SERVER=selenium-server-standalone-2.45.0.jar

title Hub_for_IE
java -jar %CURPATH%\%SELENIUM_SERVER% -role hub -port %REMOTE_SERVER_PORT%