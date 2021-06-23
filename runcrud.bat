echo building project...
call gradlew build
if "%ERRORLEVEL%" == "0" goto rename
echo.
echo gradlew build has errors - braking work
goto fail

:rename
echo.
echo renaming file...
del build\libs\crud.war
ren build\libs\tasks-0.0.1-SNAPSHOT.war crud.war
if "%ERRORLEVEL%" == "0" goto stoptomcat
echo cannot rename file
goto fail

:stoptomcat
echo stopping tomcat...
call %CATALINA_HOME%\bin\shutdown.bat

:copyfile
echo copying file...
copy build\libs\crud.war %CATALINA_HOME%\webapps
if "%ERRORLEVEL%" == "0" goto runtomcat
echo cannot copy file
goto fail

:runtomcat
echo running tomcat...
call %CATALINA_HOME%\bin\startup.bat
goto end

:fail
echo.
echo there were errors

:end
echo.
echo Work is finished.