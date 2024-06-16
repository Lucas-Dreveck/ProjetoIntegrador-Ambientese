const onOpenLogin = () => {
    const form = document.querySelector('#login');
    form.addEventListener('submit', (event) => {
        event.preventDefault();
        login();
    });

    const forgotPassword = document.querySelector('#forgotPass');
    forgotPassword.addEventListener('click', () => {
        getMainFrameContent('forgot-password');
    });
}

const login = async () => {
    const login = document.querySelector('#user').value;
    const password = document.querySelector('#password').value;

    await fetch(`${URL}/login`, {
        method: 'POST',
        headers,
        body: JSON.stringify({ login, password })
    })
    .then(async response => {
        return {
            ok: response.ok,
            token: await response.text()
        }
    })
    .then(data => {
        if (!data.ok) {
            throw new Error(data.token);
        }
        if (data.token) {
            sessionStorage.setItem('token', data.token);
            headers.append('Authorization', `Bearer ${data.token}`);
            token = data.token;
            toastAlert("Login bem-sucedido", "success");
            loginLogout.textContent = "Sair";
            getMainFrameContent('ranking');
        } else {
            toastAlert("Usuario ou senha incorretos", "error");
        }
        })
        .catch(error => {
            console.error('Error:', error);
            toastAlert(error, "error");
        });
}