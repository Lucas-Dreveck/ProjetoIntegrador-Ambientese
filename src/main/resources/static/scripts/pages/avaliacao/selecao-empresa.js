const users = [
    {
        id: 1,
        name: 'João',
        email: 'joao@example.com',
        company: 'ACME Inc.'
    },
    {
        id: 2,
        name: 'Maria',
        email: 'maria@example.com',
        company: 'ACME Inc.'
    },
    {
        id: 3,
        name: 'José',
        email: 'jose@example.com',
        company: 'ACME Inc.'
    },
]

let isDropdownOpened = false;

const fetchEmpresas = (input, dropdown) => {
    const filter = input.value.toLowerCase();
    dropdown.innerHTML = '';
    const filteredUsers = users.filter(user => {
        return user.name.toLowerCase().includes(filter)
    });
    filteredUsers.slice(0, 10).forEach(user => {
        const li = document.createElement('li');
        li.textContent = user.name;
        li.addEventListener('click', () => {
            input.value = user.name;
            dropdown.innerHTML = '';
        });
        dropdown.appendChild(li);
    });
}

const onOpenSelecaoEmpresa = () => {
    const input = document.querySelector('.search');
    const dropdown = document.querySelector('.dropdown-content');
    const content = document.querySelector('.content-container');

    input.addEventListener('input', () => {
        fetchEmpresas(input, dropdown)
        isDropdownOpened = true;
    });

    input.addEventListener('focus', () => {
        if (!isDropdownOpened) {
            fetchEmpresas(input, dropdown);
            isDropdownOpened = true;
        }
    });

    input.addEventListener('keydown', function(event) {
        if (event.key === 'Enter') {
            const selected = dropdown.querySelector('li');
            if (selected) {
                input.value = selected.textContent;
                dropdown.innerHTML = '';
                isDropdownOpened = false;
            }
        }
    });

    content.addEventListener('click', function(event) {
        if (!input.contains(event.target) && !dropdown.contains(event.target)) {
            dropdown.innerHTML = '';
            isDropdownOpened = false;
        }
    });

    const btnNext = document.querySelector('.btn-next');
    btnNext.addEventListener('click', () => {
        const selected = users.find(user => user.name === input.value);
        if (selected) {
            console.log(selected);
            getMainFrameContent('avaliacao', selected);
        }
    });
}
