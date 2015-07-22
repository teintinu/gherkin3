@pushd %~dp0
set BERP_PATH=..\bin
%BERP_PATH%\berp.exe -g ..\gherkin.berp -t ../javascript/gherkin-javascript.razor -o ../javascript/lib/gherkin/parser.js
@popd