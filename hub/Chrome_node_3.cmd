rem curent directory
set CURPATH=%cd%
set REMOTESERVER=localhost
set REMOTE_SERVER_PORT=4445
set SELENIUM_SERVER=selenium-server-standalone-3.9.1.jar
set NODE_PORT=5554

title Chrome_node
java -jar -Dwebdriver.chrome.driver="%CURPATH%\chromedriver.exe" %CURPATH%\%SELENIUM_SERVER% -port %NODE_PORT% -role node -hub http://%REMOTESERVER%:%REMOTE_SERVER_PORT%/grid/register