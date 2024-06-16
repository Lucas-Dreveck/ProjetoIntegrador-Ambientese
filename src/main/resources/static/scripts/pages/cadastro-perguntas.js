let currentPagePerg = 0;
function onOpenPerguntas() {
    const priorBtn = document.getElementById('priorBtnPerg');
    const nextBtn = document.getElementById('nextBtnPerg');
    const overlay = document.querySelector('.overlay');
    const divAdd = document.querySelector('.divAddPerg');
    const divEdit = document.querySelector('.divEditPerg');
    const divDelete = document.querySelector('.divDeletePerg');
    let currentid;

    nextDataPagePerg();

    nextBtn.addEventListener('click', () => {
        currentPage++;
        nextDataPagePerg();
    });

    priorBtn.addEventListener('click', () => {
        currentPage--;
        if (currentPage < 0) {
            currentPage = 0;
        }
        nextDataPagePerg();
    });

    document.querySelector('.tablePerg').addEventListener('click', (event) => {

        const { currentPergunta, currentEixo } = processEventPerguntas(event);
        if(event.target.classList.contains('imgEdit')) {

            currentid = event.target.getAttribute('data-id');
            document.getElementById('perguntaEdit').value = currentPergunta;
            document.getElementById('eixoPergEdit').value = currentEixo;

            divEdit.style.display = 'block';
            overlay.style.display = 'block';

        } else if(event.target.classList.contains('imgDelete')) {
            currentid = event.target.getAttribute('data-id');;
            divDelete.style.display = 'block';
            overlay.style.display = 'block';
            document.querySelector('.deleteMsg').innerText = `Deseja deletar a pergunta: \n "${currentPergunta}" \n \n`;
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

    document.getElementById('confirmAddPerg').addEventListener('click', () => {
        const pergunta = document.getElementById('perguntaAdd').value;
        const eixo = document.getElementById('eixoPergAdd').value;

        if(pergunta === '' || eixo === '') {
            toastAlert('Preencha todos os campos', 'error');
            return;
        }

        const data = {
            descricao: pergunta,
            eixo,
        };

        fetch(`${URL}/auth/Perguntas/Add`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao adicionar Pergunta');
                }
                return response.json();
            })
            .then(data => {
                toastAlert('Pergunta adicionada com sucesso!', 'success');
                divAdd.style.display = 'none';
                overlay.style.display = 'none';
                nextDataPagePerg();
            })
            .catch(error => {
                const errorMessage = error.message ? error.message : 'Ocorreu um erro ao processar a solicitação';
                toastAlert(errorMessage, 'error');
            });
    });

    document.getElementById('confirmEditPerg').addEventListener('click', () => {
        const pergunta = document.getElementById('perguntaEdit').value;
        const eixo = document.getElementById('eixoPergEdit').value;

        const data = {
            descricao: pergunta,
            eixo,
        };

        let id = parseInt(currentid);

        fetch(`${URL}/auth/Perguntas/Edit/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao editar Pergunta');
                }
                return response.json();
            })
            .then(data => {
                toastAlert('Pergunta editada com sucesso!', 'success');
                currentPage = 0;
                nextDataPagePerg();
                divEdit.style.display = 'none';
                overlay.style.display = 'none';
            })
            .catch(error => {
                const errorMessage = error.message ? error.message : 'Ocorreu um erro ao processar a solicitação';
                toastAlert(errorMessage, 'error');
            });
    });

    document.getElementById('confirmDelete').addEventListener('click' , () => {
        const id = parseInt(currentid);
        fetch(`${URL}/auth/Perguntas/Delete/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao deletar Pergunta');
                }
                return response.text();
            })
            .then(data => {
                toastAlert('Pergunta deletada com sucesso!', 'success');
                currentPage = 0;
                nextDataPagePerg();
                divDelete.style.display = 'none';
                overlay.style.display = 'none';
            })
            .catch(error => {
                const errorMessage = error.message ? error.message : 'Ocorreu um erro ao processar a solicitação';
                toastAlert(errorMessage, 'error');
            });
    });

    overlay.addEventListener('click', () => {
        overlay.style.display = 'none';
        divAdd.style.display = 'none';
        divEdit.style.display = 'none';
        divDelete.style.display = 'none';
    });
}

function addTableLinesPerguntas(data) {
    const table = document.querySelector('.tablePerg>tbody');
    const nextBtn = document.getElementById('nextBtnPerg');

    if (data.length === 0) {
        nextBtn.setAttribute('disabled', 'true');
        const newLine = document.createElement('tr');
        newLine.innerHTML = `<td class="thStyle" colspan="5">Nenhuma pergunta encontrada</td>`;
        table.appendChild(newLine);
        return;
    }
    nextBtn.removeAttribute('disabled');

    let count = 0;

    data.forEach((pergunta, index) => {
        const newLine = document.createElement('tr');

        const classe = count % 2 === 0 ? 'azul' : '';
        count++;

        newLine.innerHTML = `
            <td class="thStyle thImg ${classe}">
                <img src="/icons/Cadastro-Perguntas/Pergunta.png" alt="Pergunta" class="thImg">
            </td>
            <td class="thStyle ${classe}">${pergunta.id}</td>
            <td class="thStyle ${classe}">${pergunta.descricao}</td>
            <td class="thStyle ${classe}">${pergunta.eixo}</td>
            <td class="thStyle ${classe}">
                <img src="/icons//Cadastro-Empresa/edit.png" 
                    data-id="${pergunta.id}"
                    data-Pergunta="${pergunta.descricao}"
                    data-Eixo="${pergunta.eixo}"
                alt="Editar" class="imgEdit imgStyle">
                <img src="/icons//Cadastro-Empresa/delete.png"
                    data-id="${pergunta.id}"
                    data-Pergunta="${pergunta.descricao}"
                alt="Deletar" class="imgDelete imgStyle">                
            </td>
        `;

        table.appendChild(newLine);
    });
}

function processEventPerguntas(event) {
    const currentid = event.target.getAttribute('data-id');
    const currentPergunta = event.target.getAttribute('data-Pergunta');
    const currentEixo = event.target.getAttribute('data-Eixo');

    return {
        currentid,
        currentPergunta,
        currentEixo,
    };
}

function nextDataPagePerg () {
    const queryParams = new URLSearchParams();
    queryParams.append('page', currentPage);

    fetch(`${URL}/auth/Perguntas/search?${queryParams.toString()}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao buscar Perguntas');
            }
            return response.json();
        })
        .then(data => {
            const table = document.querySelector('.tablePerg>tbody');
            const trs = Array.from(table.children);
            trs.forEach(tag => {
                    tag.parentNode.removeChild(tag);
                }
            );
            addTableLinesPerguntas(data);
        })
        .catch(error => {
            const errorMessage = error.message ? error.message : 'Ocorreu um erro ao processar a solicitação';
            toastAlert(errorMessage, 'error');
        });
}