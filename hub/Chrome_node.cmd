rem curent directory
set CURPATH=%cd%
set REMOTESERVER=localhost
set REMOTE_SERVER_PORT=4446
set SELENIUM_SERVER=selenium-server-standalone-2.45.0.jar
set NODE_PORT=5555

title Chrome_node
java -jar -Dwebdriver.chrome.driver="%CURPATH%\chromedriver.exe" %CURPATH%\%SELENIUM_SERVER% -port %NODE_PORT% -role node -hub http://%REMOTESERVER%:%REMOTE_SERVER_PORT%/grid/register