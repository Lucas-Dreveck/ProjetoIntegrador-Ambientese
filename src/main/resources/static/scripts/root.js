const URL = "http://localhost:8080";
const enviornment = "dev";
let isAuthenticated = sessionStorage.getItem("auth") ? sessionStorage.getItem("auth") === 'true' : false;
sessionStorage.setItem('auth', isAuthenticated);

const headers = new Headers();
headers.append('X-Requested-With', 'InsideApplication');

const options = {
    method: 'GET',
    headers: headers
};

const mainContent = document.querySelector(".main-content");
const allStyles = document.getElementById("allStyles");
const expandButton = document.querySelector(".expand-menu");
const sidebar = document.querySelector(".sidebar");
const menu = document.querySelector(".menu");
const menuItems = document.querySelectorAll(".main-list > li");
const allMenuButtons = document.querySelectorAll('.menu li');
const loginLogout = document.querySelector('.login-logout');
const loading = document.querySelector(".loading");

function loadSelectedPageScript(page, props) {
    switch (page) {
        case "login":
            onOpenLogin();
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

function updateURLParameter(page) {
    const param = "page";
    let searchParams = new URLSearchParams(window.location.search);
    searchParams.set(param, page);
    let newUrl = window.location.pathname + '?' + searchParams.toString();
    window.history.pushState({ path: newUrl }, '', newUrl);
}

function getMainFrameContent(page, props) {
    const currentPage = new URLSearchParams(window.location.search).get('page');

    if (!isAuthenticated && page !== 'ranking' && page !== 'login') {
        toastAlert("Você precisa estar logado para acessar essa página", "error");
        updateURLParameter(currentPage);
        loading.classList.add("hidden");
        mainContent.classList.remove("hidden");
        return;
    }

    if (page === 'login') {
        isAuthenticated = false;
        sessionStorage.setItem('auth', isAuthenticated);
    }

    if (isAuthenticated) {
        loginLogout.textContent = "Sair";
    } else {
        loginLogout.textContent = "Login";
    }

    fetch(`${URL}/${page}`, options)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro ao recuperar tela: ${page}`);
            }
            return response.text();
        })
        .then(data => {
            const tempElement = document.createElement("div");
            tempElement.innerHTML = data;

            const contentDiv = tempElement.querySelector('content');
            const stylesDiv = tempElement.querySelector('styles');

            if (contentDiv) {
                mainContent.innerHTML = contentDiv.innerHTML;
                loadSelectedPageScript(page, props);
                updateURLParameter(page);
            }

            if (stylesDiv) {
                allStyles.innerHTML = stylesDiv.innerHTML;
            }
        })
        .catch(error => {
            console.error(error);
            loading.classList.add("hidden");
            mainContent.classList.add("hidden");
        });
}

function expandButtonClicked() {
    expandButton.classList.toggle("active");
    menu.classList.toggle("expanded");
}

function menuButtonClicked(event) {
    const button = event.currentTarget;
    const page = button.getAttribute("page");

    if (!isAuthenticated && (page !== 'ranking' && page !== 'login')) {
        toastAlert("Você precisa estar logado para acessar essa página", "error");
        return;
    }

    if (button.classList.contains("active")) {
        return;
    }

    mainContent.classList.add("hidden");
    loading.classList.remove("hidden");

    allMenuButtons.forEach(button => button.classList.remove("active"));

    if (page !== 'login') {
        button.classList.add("active");
    }

    getMainFrameContent(page);
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
    getMainFrameContent("ranking");
}

frameSetup();