// FUNCTIONS
const URL = "http://localhost:8080";
const mainContent = document.querySelector(".main-content");
const allStyles = document.getElementById("allStyles");
const expandButton = document.querySelector(".expand-menu");
const sidebar = document.querySelector(".sidebar");
const menu = document.querySelector(".menu");
const menuItems = document.querySelectorAll(".main-list > li");
const allMenuButtons = document.querySelectorAll('.menu li');
const loading = document.querySelector(".loading");

function loadSelectedPageScript(page, props) {
switch (page) {
        case "start-avaliacao":
            onOpenSelecaoEmpresa();
            break;
        case "avaliacao":
            onOpenAvaliacao(props);
            break;
        default:
//        implement cases to start page js
            break;
    }
    loading.classList.add("hidden");
    mainContent.classList.remove("hidden");
}

function getMainFrameContent(page, props) {
    fetch(`${URL}/${page}`)
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
    if (expandButton.classList.contains("active")) {
        expandButton.classList.remove("active");
        menu.classList.remove("expanded");
    } else {
        expandButton.classList.add("active");
        menu.classList.add("expanded");
    }
}

function menuButtonClicked(event) {
    const button = event.currentTarget;
    const page = button.getAttribute("page");

    if (button.classList.contains("active")) {
        return;
    }

    mainContent.classList.add("hidden");
    loading.classList.remove("hidden");

    allMenuButtons.forEach(button => {
        button.classList.remove("active");
    });

    button.classList.add("active");

    getMainFrameContent(page);
}

function toastAlert(message, type = 'info') {
    const toast = document.createElement('div');
    toast.classList.add('toast', type);
    toast.textContent = message;
    document.body.appendChild(toast);

    setTimeout(() => {
        toast.classList.add('show');
    }, 100);

    setTimeout(() => {
        toast.classList.remove('show');
        setTimeout(() => {
            toast.remove();
        }, 300);
    }, 3000);
}

function frameSetup() {
    document.addEventListener("click", function(event) {
        if (!sidebar.contains(event.target)) {
            if (expandButton.classList.contains("active")) {
                expandButton.classList.remove("active");
                menu.classList.remove("expanded");
            }
        }
    })
    expandButton.addEventListener("click", expandButtonClicked);
    menuItems.forEach(item => {
        const subList = item.nextElementSibling;

        if (subList && subList.classList.contains('sub-list')) {
            item.addEventListener('click', () => {
                subList.classList.toggle('show');
            });
    
            const subListItems = subList.querySelectorAll('li');
    
            subListItems.forEach(subItem => {
                subItem.addEventListener("click", menuButtonClicked);
            });
        } else {
            item.addEventListener("click", menuButtonClicked);
        }
    });
    
}

frameSetup();