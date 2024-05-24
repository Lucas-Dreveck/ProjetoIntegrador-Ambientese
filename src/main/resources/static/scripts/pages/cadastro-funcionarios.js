function onOpenFuncionario() {
    // const priorBtn = document.getElementById('priorBtn');
    // const nextBtn = document.getElementById('nextBtn');
    const overlay = document.querySelector('.overlay');
    const divAdd = document.querySelector('.divAddFunc');
    const divEdit = document.querySelector('.divEditFunc');
    const divDelete = document.querySelector('.divDeleteFunc');

    nextDataPage()

    // nextBtn.addEventListener('click', () => {
    //     currentPage++;
    //     nextDataPage();
    // });

    // priorBtn.addEventListener('click', () => {
    //     currentPage--;
    //     if (currentPage < 0) {
    //         currentPage = 0;
    //     }
    //     nextDataPage();
    // });

    document.getElementById('confirmDelete').addEventListener('click', () => {
        fetch('/funcionario/delete', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({id})
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('Funcionário deletado com sucesso!');
                    window.location.reload();
                } else {
                    alert('Erro ao deletar funcionário!', data.error);
                    console.error(data.error);
                }
            })
            .catch(error => {
                alert('Erro ao deletar funcionário!', error);
                console.error(error);
            });
    })

    document.querySelector('.tableFunc').addEventListener('click', (event) => {

        if (event.target.classList.contains('imgEdit')) {
            const {
                currentNome, currentCpf,
                currentDataNasc, currentLogin, currentCargo,
                currentEmail, currentGenero
            } = processEvent(event);
            divEdit.style.display = 'block';
            overlay.style.display = 'block';
            document.getElementById('nomeEdit').value = currentNome;
            document.getElementById('cpfEdit').value = currentCpf;
            document.getElementById('dataNascEdit').value = currentDataNasc;
            document.getElementById('loginEdit').value = currentLogin;
            document.getElementById('cargoEdit').value = currentCargo;
            document.getElementById('emailEdit').value = currentEmail;
            updateCheckbox(currentGenero);
        } else if (event.target.classList.contains('imgDelete')) {
            const {currentId} = processEvent(event);
            divDelete.style.display = 'block';
            overlay.style.display = 'block';
            document.querySelector('.deleteMsg').innerHTML = `Deseja deletar o funcionário de id ${currentId}?`;
        }
    });

    document.querySelector('.btnAdd').addEventListener('click', () => {
        divAdd.style.display = 'block';
        overlay.style.display = 'block';
    });

    document.querySelectorAll('.btnsCancel').forEach(btn => {
        btn.addEventListener('click', () => {
            overlay.style.display = 'none';
            divAdd.style.display = 'none';
            divEdit.style.display = 'none';
            divDelete.style.display = 'none';
        });
    });

    overlay.addEventListener('click', () => {
        overlay.style.display = 'none';
        divAdd.style.display = 'none';
        divEdit.style.display = 'none';
        divDelete.style.display = 'none';
    });

    document.getElementById('confirmAdd').addEventListener('click', () => {
        const nome = document.getElementById('nomeAdd').value;
        const cpf = document.getElementById('cpfAdd').value;
        const dataNasc = document.getElementById('dataNascAdd').value;
        const login = document.getElementById('loginAdd').value;
        const cargo = document.getElementById('cargoAdd').value;
        const email = document.getElementById('emailAdd').value;
        const genero = document.querySelector('input[name="genero"]:checked').value;

        if (nome === '' || cpf === '' || dataNasc === '' || login === '' || cargo === '' || email === '' || genero === '') {
            alert('Preencha todos os campos!');
            return
        }

        const data = {
            nome,
            cpf,
            dataNasc,
            login,
            cargo,
            email,
            genero
        };

        fetch('/funcionario/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('Funcionário cadastrado com sucesso!');
                    window.location.reload();
                } else {
                    alert('Erro ao cadastrar funcionário!', data.error);
                    console.error(data.error);
                }
            })
            .catch(error => {
                alert('Erro ao cadastrar funcionário!', error);
                console.error(error);
            });
    });
}

// function disableBtns(data) {
//     priorBtn.disabled = (currentPage <= 1);
//     nextBtn.disabled = (currentPage >= data);
// }

function updateCheckbox(option) {
    var checkboxes = document.getElementsByName("genero");
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].value === option) {
            checkboxes[i].checked = true;
        } else {
            checkboxes[i].checked = false;
        }
    }
}

function addTableLines(data) {
    const table = document.querySelector('.tableFunc');

    if (data.length === 0) {
        const newLine = document.createElement('tr');
        newLine.innerHTML = `<td class="thStyle" colspan="5">Nenhum funcionário cadastrado</td>`;
        table.appendChild(newLine);
        return;
    }

    let count = 0;

    data.forEach(funcionario => {
        const newLine = document.createElement('tr');

        const classe = count % 2 === 0 ? 'azul' : '';
        count++;

        newLine.innerHTML = `
            <td class="func imgFunc ${classe}">
                <img src="/Images/func.png" alt="Funcionario" class="imgFunc">
            </td>
            <td class="func ${classe}">${funcionario.id}</td>
            <td class="func ${classe}">${funcionario.nome}</td>
            <td class="func ${classe}">${funcionario.cargo}</td>
            <td class="func ${classe}">
                <img src="/Images/edit.png" 
                    data-id="${funcionario.id}"
                    data-nome="${funcionario.nome}"
                    data-cpf="${funcionario.cpf}"
                    data-nasc="${funcionario.data_nascimento}"
                    data-cargo="${funcionario.cargo}"
                    data-email="${funcionario.email}"
                alt="Editar" class="imgEdit imgStyle">
                <img src="/Images/delete.png"
                    data-id="${funcionario.id}"
                alt="Deletar" class="imgDelete imgStyle">                
            </td>
        `;

        table.appendChild(newLine);
    });
}

function processEvent(event) {
    currentId = event.target.getAttribute('data-id');
    const currentNome = event.target.getAttribute('data-nome');
    const currentCpf = event.target.getAttribute('data-cpf');
    const currentDataNasc = event.target.getAttribute('data-nasc');
    const currentLogin = event.target.getAttribute('data-login');
    const currentCargo = event.target.getAttribute('data-cargo');
    const currentEmail = event.target.getAttribute('data-email');
    const currentGenero = event.target.getAttribute('data-genero');

    return {
        currentId,
        currentNome,
        currentCpf,
        currentDataNasc,
        currentLogin,
        currentCargo,
        currentEmail,
        currentGenero
    };
}

function nextDataPage() {
    const queryParams = new URLSearchParams();
    queryParams.append('page', currentPage);

    fetch(`${URL}/Funcionario/search?${queryParams.toString()}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao buscar os dados!');
            }
            return response.json();
        })
        .then(data => {
            addTableLines(data);
        })
        .catch(error => console.error('Erro ao buscar os dados:', error));
}

    // document.querySelector('.imgSearch').addEventListener('click', searchData);

    // document.getElementById('search').addEventListener('keydown', (event) => {
    //     if (event.key === 'Enter') {
    //         searchData();
    //     }
    // });

    // document.getElementById('search').addEventListener('input', (event) => {
    //     if (event.target.value === '') {
    //         nextDataPage();
    //     }
    // });

    document.getElementById('confirmEdit').addEventListener('click', () => {
        const nome = document.getElementById('nomeEdit').value;
        const cpf = document.getElementById('cpfEdit').value;
        const dataNasc = document.getElementById('dataNascEdit').value;
        const login = document.getElementById('loginEdit').value;
        const cargo = document.getElementById('cargoEdit').value;
        const email = document.getElementById('emailEdit').value;
        const genero = document.querySelector('input[name="genero"]:checked').value;

        const data = {
            id: currentId,
            nome,
            cpf,
            dataNasc,
            login,
            cargo,
            email,
            genero
        };

        fetch('/funcionario/edit', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('Funcionário editado com sucesso!');
                    window.location.reload();
                } else {
                    alert('Erro ao editar funcionário!', data.error);
                    console.error(data.error);
                }
            })
            .catch(error => {
                alert('Erro ao editar funcionário!', error);
                console.error(error);
            });
    });

// function searchData() {
//     const type = document.getElementById('select').value;
//     const search = document.getElementById('search').value;
//
//     const urlSearch = `/funcionarios/search`;
//     const body = { type, search }
//
//     fetch(urlSearch, {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json'
//         },
//         body: JSON.stringify(body)
//     })
//         .then(response => response.json())
//         .then(data => {
//             addTableLines(data);
//             disableBtns(data.totalPages);
//         })
//         .catch(error => {
//             console.error('Erro ao carregar dados!', error);
//         });
// }