function onOpenEmpresa() {

    const priorBtn = document.getElementById('priorBtnEmp');
    const nextBtn = document.getElementById('nextBtnEmp');
    const overlay = document.querySelector('.overlay');
    const divAdd = document.querySelector('.divAddEmp');
    const divEdit = document.querySelector('.divEditEmp');
    const divDelete = document.querySelector('.divDeleteEmp');
    let currentId;
    let currentPage = 1;

    nextDataPage();

    nextBtn.addEventListener('click', () => {
        currentPage++;
        nextDataPage();
    });

    priorBtn.addEventListener('click', () => {
        currentPage--;
        if (currentPage < 1) {
            currentPage = 1;
        }
        nextDataPage();
    });

    document.querySelector('.tableEmp').addEventListener('click', (event) => {

        if(event.target.classList.contains('imgEdit')) {
            const { currentNomeFa, currentNomeSo,
                currentTelSo, currentRazao, currentCnpj,
                currentInscricao, currentEmail, currentTelEmp,
                currentRamo, currentPorte, currentDataAlt, currentCNAE } = processEvent(event);

            divEdit.style.display = 'block';
            overlay.style.display = 'block';
            document.getElementById('nomeFaEdit').value = currentNomeFa;
            document.getElementById('nomeSoEdit').value = currentNomeSo;
            document.getElementById('telSoEdit').value = currentTelSo;
            document.getElementById('razaoEdit').value = currentRazao;
            document.getElementById('cnpjEdit').value = currentCnpj;
            document.getElementById('inscricaoEdit').value = currentInscricao;
            document.getElementById('emailEdit').value = currentEmail;
            document.getElementById('telEmpEdit').value = currentTelEmp;
            document.getElementById('ramoEdit').value = currentRamo;
            document.getElementById('porteEdit').value = currentPorte;
            document.getElementById('cnaeEdit').value = currentCNAE;
            document.getElementById('dataAltEdit').value = currentDataAlt;
        } else if(event.target.classList.contains('imgDelete')) {
            const { currentId } = processEvent(event);
            divDelete.style.display = 'block';
            overlay.style.display = 'block';
            document.querySelector('.deleteMsg').innerHTML = `Deseja deletar a empresa de id ${currentId}?`;
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

    document.getElementById('confirmAddEmp').addEventListener('click', () => {
        const nome_fantasia = document.getElementById('nomeFa').value;
        const nome_solicitante = document.getElementById('nomeSo').value;
        const telefone_solicitante = document.getElementById('telSo').value;
        const razao_social = document.getElementById('razao').value;
        const cnpj = document.getElementById('CNPJ').value;
        const inscricao_social = document.getElementById('inscricao').value;
        const email = document.getElementById('email').value;
        const telefone_empresa = document.getElementById('telEmp').value;
        const ramo = document.getElementById('ramo').value;
        const porte = document.getElementById('porte').value;
        const CNAE = document.getElementById('CNAE').value;
        const data_alteracao = document.getElementById('dataAtl').value;

        if (nome_fantasia === '' || nome_solicitante === '' || telefone_solicitante === '' || razao_social === '' || cnpj === '' || inscricao_social === '' || email === '' || telefone_empresa === '' || ramo === '' || porte === '' || CNAE === '' || data_alteracao === '') {
            alert('Preencha todos os campos!');
            return
        }

        const data = {
            nome_fantasia,
            nome_solicitante,
            telefone_solicitante,
            razao_social,
            CNPJ,
            inscricao_social,
            email,
            telefone_empresa,
            ramo,
            porte,
            data_alteracao
        };

        const empresaData = {
            nomeFantasia: "Empresa XYZ",
            nomeSolicitante: "João Silva",
            telefoneSolicitante: "1234567890",
            razaoSocial: "Empresa XYZ Ltda.",
            cnpj: "59887447000177",
            inscricaoSocial: "12345",
            endereco: {
                cep: "01001000",
                numero: "123",
                logradouro: "Rua Exemplo",
                cidade: "São Paulo",
                bairro: "Centro",
                uf: "SP"
            },
            email: "contato@empresa.com",
            telefoneEmpresas: "1234567890",
            ramo: "Tecnologia",
            porteEmpresas: "Pequeno",
            cnae: "123456",
            dataAlteracao: "2023-05-20"
        };

        fetch('/Empresa/Add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(empresaData)
        })
            .then(response => response.text())
            .then(text => {
                console.log(text);
            })
            .catch(error => {
                alert('Erro ao cadastrar empresa!', error);
                console.error(error);
            });
    });

    document.getElementById('confirmEditEmp').addEventListener('click', () => {
        const nome_fantasia = document.getElementById('nomeFaEdit').value;
        const nome_solicitante = document.getElementById('nomeSoEdit').value;
        const telefone_solicitante = document.getElementById('telSoEdit').value;
        const razao_social = document.getElementById('razaoEdit').value;
        const cnpj = document.getElementById('cnpjEdit').value;
        const inscricao_social = document.getElementById('inscricaoEdit').value;
        const email = document.getElementById('emailEdit').value;
        const telefone_empresa = document.getElementById('telEmpEdit').value;
        const ramo = document.getElementById('ramoEdit').value;
        const porte = document.getElementById('porteEdit').value;
        const CNAE = document.getElementById('cnaeEdit').value;
        const data_alteracao = document.getElementById('dataAltEdit').value;

        const data = {
            nome_fantasia,
            nome_solicitante,
            telefone_solicitante,
            razao_social,
            cnpj,
            inscricao_social,
            email,
            telefone_empresa,
            ramo,
            porte,
            CNAE,
            data_alteracao
        };

        fetch(`/Empresas/Edit/${currentId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('Empresa editada com sucesso!');
                    window.location.reload();
                } else {
                    alert('Erro ao editar empresa!', data.error);
                    console.error(data.error);
                }
            })
            .catch(error => {
                alert('Erro ao editar empresa!', error);
                console.error(error);
            });
    });

    document.getElementById('confirmDelete').addEventListener('click' , () => {
        fetch(`/empresas/delete/${currentId}`, {
            method: 'DELETE'
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('Empresa deletada com sucesso!');
                    window.location.reload();
                } else {
                    alert('Erro ao deletar empresa!', data.error);
                    console.error(data.error);
                }
            })
            .catch(error => {
                alert('Erro ao deletar empresa!', error);
                console.error(error);
            });
    });

    overlay.addEventListener('click', () => {
        overlay.style.display = 'none';
        divAdd.style.display = 'none';
        divEdit.style.display = 'none';
        divDelete.style.display = 'none';
    });
}

function addTableLines(data) {
    const table = document.querySelector('.tableEmp');

    if (data.length === 0) {
        const newLine = document.createElement('tr');
        newLine.innerHTML = `<td class="thStyle" colspan="6">Nenhuma empresa cadastrada</td>`;
        table.appendChild(newLine);
        return;
    }

    let count = 0;

    data.forEach(empresa => {
        const newLine = document.createElement('tr');

        const classe = count % 2 === 0 ? 'azul' : '';
        count++;

        newLine.innerHTML = `
            <td class="thStyle thImg ${classe}">
                <img th:src="@{/icons/Empresa.png}" alt="Empresa" class="thImg">
            </td>
            <td class="thStyle ${classe}">${empresa.empresa_id}</td>
            <td class="thStyle ${classe}">${empresa.nome_fantasia}</td>
            <td class="thStyle ${classe}">${empresa.ramo}</td>
            <td class="thStyle ${classe}">${empresa.porte}</td>
            <td class="thStyle ${classe}">
                <img th:src="@{/icons/edit.png}" 
                    data-id="${empresa.Id}"
                    data-nomeFa="${empresa.nome_fantasia}"
                    data-nomeSo="${empresa.nome_solicitante}"
                    data-telSo="${empresa.telefone_solicitante}"
                    data-razao="${empresa.razao_social}"
                    data-cnpj="${empresa.cnpj}"
                    data-inscricao="${empresa.inscricao_social}"
                    data-email="${empresa.email}"
                    data-telEmp="${empresa.telefone_empresa}"
                    data-ramo="${empresa.ramo}"
                    data-porte="${empresa.porte}"
                    data-alt="${empresa.data_alteracao}"
                    data-cnae="${empresa.CNAE}"
                alt="Editar" class="imgEdit imgStyle">
                <img th:src="@{/icons/delete.png}"
                    data-id="${empresa.Id}"
                alt="Deletar" class="imgDelete imgStyle">                
            </td>
        `;

        table.appendChild(newLine);
    });
}

function processEvent(event) {
    const currentId = event.target.getAttribute('data-id');
    const currentNomeFa = event.target.getAttribute('data-nomeFa');
    const currentNomeSo = event.target.getAttribute('data-nomeSo');
    const currentTelSo = event.target.getAttribute('data-telSo');
    const currentRazao = event.target.getAttribute('data-razao');
    const currentCnpj = event.target.getAttribute('data-cnpj');
    const currentInscricao = event.target.getAttribute('data-inscricao');
    const currentEmail = event.target.getAttribute('data-email');
    const currentTelEmp = event.target.getAttribute('data-telEmp');
    const currentRamo = event.target.getAttribute('data-ramo');
    const currentPorte = event.target.getAttribute('data-porte');
    const currentDataAlt = event.target.getAttribute('data-alt');
    const currentCNAE = event.target.getAttribute('data-cnae');

    return {
        currentId,
        currentNomeFa,
        currentNomeSo,
        currentTelSo,
        currentRazao,
        currentCnpj,
        currentInscricao,
        currentEmail,
        currentTelEmp,
        currentRamo,
        currentPorte,
        currentDataAlt,
        currentCNAE
    };
}

function nextDataPage () {
    const urlPage = '/Empresa/search';

    fetch(urlPage, {
       method: 'GET',
         headers: {
             'Content-Type': 'application/json'
         }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            addTableLines(data);
        })
        .catch(error => {
            alert('Erro ao buscar empresas!', error);
            console.error(error);
        });
}