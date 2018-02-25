rem curent directory
set CURPATH=%cd%
set REMOTE_SERVER_PORT=4445
set SELENIUM_SERVER=selenium-server-standalone-3.9.1.jar

title Hub_for_chrome
java -jar %CURPATH%\%SELENIUM_SERVER% -role hub -port %REMOTE_SERVER_PORT%