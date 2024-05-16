const testRanking = [
    {
        "id": 1,
        "nomeFantasia": "Empresa 1",
        "ramo": "Alimentício",
        "porte": "Pequeno",
        "nota": 100
    },
    {
        "id": 2,
        "nomeFantasia": "Empresa 2",
        "ramo": "Tecnologia",
        "porte": "Médio",
        "nota": 90
    },
    {
        "id": 3,
        "nomeFantasia": "Empresa 3",
        "ramo": "Tecnologia",
        "porte": "Grande",
        "nota": 80
    },
    {
        "id": 4,
        "nomeFantasia": "Empresa 4",
        "ramo": "Varejo",
        "porte": "Pequeno",
        "nota": 70
    },
    {
        "id": 5,
        "nomeFantasia": "Empresa 5",
        "ramo": "Alimentício",
        "porte": "Médio",
        "nota": 60
    },
    {
        "id": 6,
        "nomeFantasia": "Empresa 6",
        "ramo": "Varejo",
        "porte": "Grande",
        "nota": 50
    },
    {
        "id": 7,
        "nomeFantasia": "Empresa 7",
        "ramo": "Alimentício",
        "porte": "Pequeno",
        "nota": 40
    },
    {
        "id": 8,
        "nomeFantasia": "Empresa 8",
        "ramo": "Tecnologia",
        "porte": "Médio",
        "nota": 30
    },
    {
        "id": 9,
        "nomeFantasia": "Empresa 9",
        "ramo": "Tecnologia",
        "porte": "Grande",
        "nota": 20
    },
    {
        "id": 10,
        "nomeFantasia": "Empresa 10",
        "ramo": "Tecnologia",
        "porte": "Pequeno",
        "nota": 10
    }
]

const createFirstPlace = (company, content) => {
    const firstPlace = document.createElement('div');
    firstPlace.classList.add('first-place');
    firstPlace.innerHTML = `
        <h2 class="company-name">${company.nomeFantasia}</h2>
        <h1 class="first-place-title">1º lugar - ${company.nota} pontos</h1>
        <p class="branch">${company.ramo}</p>
        <img src="/icons/Medals/gold-medal.svg" alt="Medal" class="medal-icon">
    `;
    content.appendChild(firstPlace);
}

const createRanking = (companys, content, update) => {
    const ranking = document.querySelector('.ranking-content');
    if (document.querySelector('.ranking-table')) {
        document.querySelector('.ranking-table').remove();
    }
    const rankingTable = document.createElement('table');
    rankingTable.classList.add('ranking-table');

    const tbody = document.createElement('tbody');
    companys.forEach((company, index) => {
        if (index === 0 && !update) return;
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td class="ranking">${index + 1}º - ${company.nomeFantasia}</td>
            <td class="branch">${company.ramo}</td>
            <td class="size">${company.nota} pontos</td>
        `;
        tr.classList.add(index % 2 === 0 ? 'even-row' : 'odd-row');
        tbody.appendChild(tr);
    });
    
    rankingTable.appendChild(tbody);
    if (ranking)
        ranking.appendChild(rankingTable);
    else
        content.appendChild(rankingTable);
}

const addOptions = (companys, content) => {
    const ramoDropdown = document.createElement('select');
    ramoDropdown.classList.add('ramo-dropdown');
    ramoDropdown.innerHTML = '<option value="">Ramo da empresa</option>';

    const porteDropdown = document.createElement('select');
    porteDropdown.classList.add('porte-dropdown');
    porteDropdown.innerHTML = '<option value="">Porte da empresa</option>'; 

    const ramoOptions = new Set();
    const porteOptions = new Set();


    companys.forEach(company => {
        ramoOptions.add(company.ramo);
        porteOptions.add(company.porte);
    });

    ramoOptions.forEach(option => {
        const ramo = document.createElement('option');
        ramo.value = option;
        ramo.innerHTML = option;
        ramoDropdown.appendChild(ramo);
    });

    porteOptions.forEach(option => {
        const porte = document.createElement('option');
        porte.value = option;
        porte.innerHTML = option;
        porteDropdown.appendChild(porte);
    });

    ramoDropdown.addEventListener('change', updateRanking);
    porteDropdown.addEventListener('change', updateRanking);

    content.appendChild(ramoDropdown);
    content.appendChild(porteDropdown);
}

const updateRanking = () => {
    const ramo = document.querySelector('.ramo-dropdown').value;
    const porte = document.querySelector('.porte-dropdown').value;
    const search = document.querySelector('.search-bar').value;
    const queryParams = new URLSearchParams();
    if(ramo) {
        queryParams.append('ramo', ramo);
    }
    if(porte) {
        queryParams.append('porte', porte);
    }
    if(search) {
        queryParams.append('search', search);
    }
    const fullUrl = `${URL}/ranking?${queryParams.toString()}`;
    fetch(fullUrl, options)
        .then(response => {
            return testRanking.filter(ranking => {
                if (!ramo && !porte && !search) return true;
                return (ramo ? ranking.ramo === ramo : true) &&
                    (porte ? ranking.porte === porte : true) &&
                    (search ? ranking.nomeFantasia === search : true);
            });
            if (!response.ok) {
                throw new Error('Erro ao buscar ranking');
            }
            return response.json();
        })
        .then(response => {
            createRanking(response, null, true);
        });
}

const createSearch = (content) => {
    const search = document.createElement('div');
    search.classList.add('search');
    const searchBar = document.createElement('input');
    searchBar.classList.add('search-bar');
    searchBar.placeholder = 'Nome da empresa';
    const searchButton = document.createElement('button');
    searchButton.classList.add('search-button');
    searchButton.innerHTML = `
        <img src="/icons/search.svg" alt="Search" class="search-icon">
    `;

    searchBar.addEventListener('keydown', (event) => {
        if (event.key === 'Enter') {
            updateRanking();
        }
    });
    searchButton.addEventListener('click', updateRanking);

    search.appendChild(searchBar);
    search.appendChild(searchButton);

    content.appendChild(search);
}

const onOpenRanking = async () => {
    const content = document.querySelector('.content-container');

    const sideContent = document.createElement('div');
    sideContent.classList.add('side-content');
    const ranking = document.createElement('div');
    ranking.classList.add('ranking-content');

    await fetch(`${URL}/ranking`, options)
        .then(response => {
            return testRanking;
            if (!response.ok) {
                throw new Error('Erro ao buscar ranking');
            }
            return response.json();
        })
        .then(response => {
            createFirstPlace(response[0], sideContent);
            createRanking(response, ranking, false);
            addOptions(response, sideContent);
            createSearch(sideContent);
        });

    content.appendChild(sideContent);
    content.appendChild(ranking)
}