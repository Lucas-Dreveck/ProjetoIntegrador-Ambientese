const URL = "http://localhost:8080";
const enviornment = "dev";

const mainContent = document.querySelector(".main-content");
const allStyles = document.getElementById("allStyles");
const expandButton = document.querySelector(".expand-menu");
const sidebar = document.querySelector(".sidebar");
const menu = document.querySelector(".menu");
const menuItems = document.querySelectorAll(".main-list > li");
const allMenuButtons = document.querySelectorAll('.menu li');
const loginLogout = document.querySelector('.login-logout');
const loading = document.querySelector(".loading");

const headers = new Headers();
headers.append("X-Requested-With", "InsideApplication");
headers.append('Content-Type', 'application/json');
let token = sessionStorage.getItem('token');
if (token) {
    loginLogout.textContent = "Sair";
    headers.append('Authorization', `Bearer ${token}`);
}

const options = {
    method: 'GET',
    headers: headers
};

function loadSelectedPageScript(page, props) {
    switch (page) {
        case "login":
            onOpenLogin();
            break;
        case "forgot-password":
            onOpenForgotPassword();
            break;
        case "ranking":
            onOpenRanking();
            break;
        case "start-avaliacao":
            onOpenSelecaoEmpresa();
            break;
        case "avaliacao":
            onOpenAvaliacao(props);
            break;
        case "result-avaliacao":
            onOpenResultAvaliacao(props);
            break;
        case "empresas":
            onOpenEmpresa();
            break;
        case "funcionarios":
            onOpenFuncionario();
            break;
        default:
            break;
    }
    loading.classList.add("hidden");
    mainContent.classList.remove("hidden");
}

function updateURLParameter(page, addToHistory = true) {
    const param = "page";
    let searchParams = new URLSearchParams(window.location.search);
    searchParams.set(param, page);
    let newUrl = window.location.pathname + '?' + searchParams.toString();
    
    if (addToHistory) {
        window.history.pushState({ path: newUrl }, '', newUrl);
    } else {
        window.history.replaceState({ path: newUrl }, '', newUrl);
    }
}

function getMainFrameContent(page, props, addToHistory = true) {
    if (page === 'login') {
        sessionStorage.removeItem('token');
        headers.delete('Authorization');
        loginLogout.textContent = "Login";
    }

    fetch(`${URL}/${page}`, options)
        .then(response => {
            if (!response.ok) {
                if (response.status === 401) {
                    if (sessionStorage.getItem('token') === null) {
                        toastAlert("Você precisa estar logado para acessar essa página", "error");
                    } else {
                        toastAlert("Sua sessão expirou, faça login novamente", "error");
                    }
                }
                throw new Error(`Erro ao recuperar tela: ${page}`);
            }
            return response.text();
        })
        .then(data => {
            allMenuButtons.forEach(button => button.classList.remove("active"));

            if (page !== 'login' && page !== 'avaliacao' && page !== 'result-avaliacao' && page !== 'forgot-password') {
                const selectedButton = document.querySelector(`.menu li[page="${page}"]`);
                selectedButton.classList.add("active");
            }
            const tempElement = document.createElement("div");
            tempElement.innerHTML = data;

            const contentDiv = tempElement.querySelector('content');
            const stylesDiv = tempElement.querySelector('styles');

            if (contentDiv) {
                mainContent.innerHTML = contentDiv.innerHTML;
                loadSelectedPageScript(page, props);
                updateURLParameter(page, addToHistory);
            }

            if (stylesDiv) {
                allStyles.innerHTML = stylesDiv.innerHTML;
            }
            return true;
        })
        .catch(error => {
            console.error(error);
            headers.delete('Authorization');
            sessionStorage.removeItem('token');
            getMainFrameContent("login");
        });
}

function expandButtonClicked() {
    expandButton.classList.toggle("active");
    menu.classList.toggle("expanded");
}

function menuButtonClicked(event) {
    const button = event.currentTarget;
    const page = button.getAttribute("page");

    if (button.classList.contains("active")) {
        return;
    }

    mainContent.classList.add("hidden");
    loading.classList.remove("hidden");

    getMainFrameContent(page, null, true);
}

function toastAlert(message, type = 'info') {
    const toast = document.createElement('div');
    toast.classList.add('toast', type);
    toast.textContent = message;
    document.body.appendChild(toast);

    setTimeout(() => toast.classList.add('show'), 100);
    setTimeout(() => {
        toast.classList.remove('show');
        setTimeout(() => toast.remove(), 300);
    }, 3000);
}

function frameSetup() {
    document.addEventListener("click", function(event) {
        if (!sidebar.contains(event.target) && expandButton.classList.contains("active")) {
            expandButton.classList.remove("active");
            menu.classList.remove("expanded");
        }
    });

    expandButton.addEventListener("click", expandButtonClicked);
    menuItems.forEach(item => {
        const subList = item.nextElementSibling;

        if (subList && subList.classList.contains('sub-list')) {
            item.addEventListener('click', () => subList.classList.toggle('show'));

            subList.querySelectorAll('li').forEach(subItem => subItem.addEventListener("click", menuButtonClicked));
        } else {
            item.addEventListener("click", menuButtonClicked);
        }
    });

    loginLogout.addEventListener("click", menuButtonClicked);
    getMainFrameContent("ranking", null, false);
}

window.addEventListener('popstate', (event) => {
    const urlParams = new URLSearchParams(window.location.search);
    const page = urlParams.get('page');
    if (page) {
        getMainFrameContent(page, null, false);
    } else {
        getMainFrameContent('ranking', null, false);
    }
});

frameSetup();

document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const page = urlParams.get('page');
    if (page) {
        getMainFrameContent(page, null, false);
    } else {
        getMainFrameContent('ranking', null, false);
    }
});
