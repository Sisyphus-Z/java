@echo off
%1 mshta vbscript:CreateObject("Shell.Application").ShellExecute("cmd.exe","/c %~s0 ::","","runas",1)(window.close)&&exit
cd /d "%~dp0"

set /p MyJavaPath=<MyJavaPath.txt

:: /s 是代表删除所有子目录跟其中的档案 /q是不要它在删除档案或目录时，不再问我 Yes or No 的动作
rmdir /s/q classfiles
mkdir classfiles

%MyJavaPath%javac -d .\classfiles *.java -encoding UTF-8 -cp ".;.\lib\*"

del run.jar

cd classfiles
IF "%MyJavaPath%"=="" (
    jar cfm ..\run.jar ..\META-INF\MANIFEST.MF *.class
) ELSE (
    ..\%MyJavaPath%jar cfm ..\run.jar ..\META-INF\MANIFEST.MF *.class
)

:: cd ..
:: %MyJavaPath%java -jar run.jar