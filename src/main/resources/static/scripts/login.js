const onOpenLogin = () => {
    const form = document.querySelector('#login');
    form.addEventListener('submit', (event) => {
        event.preventDefault();
        login();
    });
}

const login = async () => {
    const login = document.querySelector('#user').value;
    const password = document.querySelector('#password').value;

    fetch(`${URL}/login`, {
        method: 'POST',
        headers,
        body: JSON.stringify({ login, password })
    })
    .then(response => response.json())
    .then(data => {
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
            toastAlert("Usuario ou senha incorretos", "error");
        });
}