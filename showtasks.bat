call runcrud
if "%ERRORLEVEL%" == "0" goto start_browser
echo errors occurred.

:start_browser
start "C:\Program Files\Mozilla Firefox\firefox.exe" http://localhost:8080/crud/v1/task/getTasks
echo Success.
