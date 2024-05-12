const createGraphs = (answers) => {
    const governamentalYes = answers.filter(answer => answer.eixo === 'governamental' && answer.resposta === '1');
    const governamentalNo = answers.filter(answer => answer.eixo === 'governamental' && answer.resposta === '-1');
    const socialYes = answers.filter(answer => answer.eixo === 'social' && answer.resposta === '1');
    const socialNo = answers.filter(answer => answer.eixo === 'social' && answer.resposta === '-1');
    const ambientalYes = answers.filter(answer => answer.eixo === 'ambiental' && answer.resposta === '1');
    const ambientalNo = answers.filter(answer => answer.eixo === 'ambiental' && answer.resposta === '-1');
    const governamentalPercentage = (governamentalYes.length /  (governamentalNo.length + governamentalYes.length )) * 100;
    const socialPercentage = (socialYes.length /  (socialNo.length + socialYes.length )) * 100;
    const ambientalPercentage = (ambientalYes.length /  (ambientalNo.length + ambientalYes.length )) * 100;
    return (`
        <div class="graph">
            <svg viewBox="0 0 36 36" class="circular-chart">
                <path class="circle social-graph"
                stroke-dasharray="${socialPercentage.toFixed(2)}, 100"
                d="M18 2.0845
                    a 15.9155 15.9155 0 0 1 0 31.831
                    a 15.9155 15.9155 0 0 1 0 -31.831"
                />
                <text x="18" y="20" text-anchor="middle" class="percentage">${socialPercentage.toFixed(2)}%</text>
            </svg>
            <h2 class="subtitle">Social</h2>
        </div>
        <div class="graph">
            <svg viewBox="0 0 36 36" class="circular-chart">
                <path class="circle governamental-graph"
                stroke-dasharray="${governamentalPercentage.toFixed(2)}, 100"
                d="M18 2.0845
                    a 15.9155 15.9155 0 0 1 0 31.831
                    a 15.9155 15.9155 0 0 1 0 -31.831"
                />
                <text x="18" y="20" text-anchor="middle" class="percentage">${governamentalPercentage.toFixed(2)}%</text>
            </svg>
            <h2 class="subtitle">Governamental</h2>
        </div>
        <div class="graph">
            <svg viewBox="0 0 36 36" class="circular-chart">
                <path class="circle ambiental-graph"
                stroke-dasharray="${ambientalPercentage.toFixed(2)}, 100"
                d="M18 2.0845
                    a 15.9155 15.9155 0 0 1 0 31.831
                    a 15.9155 15.9155 0 0 1 0 -31.831"
                />
                <text x="18" y="20" text-anchor="middle" class="percentage">${ambientalPercentage.toFixed(2)}%</text>
            </svg>
            <h2 class="subtitle">Ambiental</h2>
        </div>
    `);
}

const createTable = (eixo, data) => {
    const table = document.createElement('table');
    const thead = document.createElement('thead');
    const tbody = document.createElement('tbody');

    table.classList.add('table');
    table.classList.add(`table-${eixo}`);

    thead.innerHTML = `
        <tr>
            <th class="question">Pergunta</th>
            <th class="answer">Resposta</th>
        </tr>
    `;
    table.appendChild(thead);

    data.forEach((answer, index) => {
        let resposta = '';
        switch (answer.resposta) {
            case '-1':
                resposta = "Não conforme";
                break;
            case '0':
                resposta = "Não aplicavel";
                break;
            case '1':
                resposta ="Conforme";
                break;
        }
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td class="question">${answer.pergunta}</td>
            <td class="answer">${resposta}</td>
        `;
        tr.classList.add(index % 2 === 0 ? 'even-row' : 'odd-row')
        tbody.appendChild(tr);
    });

    table.appendChild(tbody);

    return table.outerHTML;
}

const renderTables = (answers) => {
    
    const socialData = answers.filter(answer => answer.eixo === 'social');
    const governamentalData = answers.filter(answer => answer.eixo === 'governamental');
    const ambientalData = answers.filter(answer => answer.eixo === 'ambiental');
    
    const socialTable = createTable('social', socialData);
    const governamentalTable = createTable('governamental', governamentalData);
    const ambientalTable = createTable('ambiental', ambientalData);
    
    return socialTable + governamentalTable + ambientalTable;
}

const onOpenResultAvaliacao = (props) => {
    console.log(props)
    const result = document.querySelector('#result-content');
    const h1 = document.createElement('h1');
    h1.classList.add('title');
    h1.textContent = `Resultados ${props.company.nomeFantasia}`;
    result.appendChild(h1);

    const graphics = document.createElement('div');
    graphics.classList.add('graphics');
    graphics.innerHTML = createGraphs(props.answers);
    result.appendChild(graphics);
    
    const tables = document.createElement('div');
    tables.classList.add('tables');
    tables.innerHTML = renderTables(props.answers);
    result.appendChild(tables);
}