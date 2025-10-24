# Social Adapter (sem tests)
- Projeto limpo, sem `src/test`.
- Rode no VS Code (F5) com a config **Run MainDemo (com env)** ou compile com `javac`.

## VS Code (F5)
Instale **Extension Pack for Java**. Abra `MainDemo.java` e pressione F5.

## Terminal sem Maven
```powershell
Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName } > sources.txt
javac -d out @sources.txt
$env:TWITTER_TOKEN="token123"
$env:INSTAGRAM_TOKEN="token456"
$env:LINKEDIN_TOKEN="token789"
$env:LINKEDIN_ORG_ID="org-abc"
$env:TIKTOK_TOKEN="token000"
java -cp out br.edu.trabalho.social.app.MainDemo
```
## Diagrama de Classes
<img width="4562" height="4364" alt="diagrama de classes" src="https://github.com/user-attachments/assets/28c41e28-6ea2-44f4-ac8c-96a916f9fdaf" />
