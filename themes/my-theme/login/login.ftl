<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8" />
    <title>Login - Clínica Dental</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${url.resourcesPath}/css/styles.css" />
</head>
<body>
<div class="custom-login">
    <h1>Bem-vindo ao Dental Insurance</h1>
    <form id="kc-form-login" onsubmit="login.disabled = true; return true;" action="${url.loginAction}" method="post">
        <div>
            <label for="username">Usuário</label>
            <input type="text" id="username" name="username" value="${username!}" required />
        </div>
        <div>
            <label for="password">Senha</label>
            <input type="password" id="password" name="password" required />
        </div>
        <div>
            <input type="submit" value="Entrar" />
        </div>
    </form>
</div>
</body>
</html>