@echo off
setlocal

set "TOMCAT_HOME=C:\xampp\tomcat"
set "APP_NAME=FirstServlet"
set "TARGET_WAR=target\FirstServlet-1.0-SNAPSHOT.war"

if not exist "%TARGET_WAR%" (
    echo WAR not found: %TARGET_WAR%
    echo Please run: mvn clean package
    exit /b 1
)

set "DEST_DIR=%TOMCAT_HOME%\webapps"

echo Copying %TARGET_WAR% to %DEST_DIR%...
copy /Y "%TARGET_WAR%" "%DEST_DIR%\%APP_NAME%.war" > nul

if %ERRORLEVEL% EQU 0 (
    echo Deployed %TARGET_WAR% to %DEST_DIR%
    echo Tomcat will automatically extract the WAR file.
    exit /b 0
) else (
    echo Failed to copy WAR. Error code %ERRORLEVEL%
    exit /b %ERRORLEVEL%
)
