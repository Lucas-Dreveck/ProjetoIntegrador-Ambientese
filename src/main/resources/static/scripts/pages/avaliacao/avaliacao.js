const questions = [
    { idPerguntas: 1, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 2, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 3, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 4, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 5, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 6, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 7, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 8, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 9, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 10, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 11, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 12, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 13, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 14, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 15, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 16, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 17, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 18, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 19, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 20, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 21, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 22, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 23, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 24, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 25, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 26, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 27, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 28, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 29, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },
    { idPerguntas: 30, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "ambiental" },

    { idPerguntas: 31, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 32, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 33, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 34, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 35, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 36, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 37, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 38, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 39, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 40, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 41, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 42, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 43, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 44, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 45, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 46, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 47, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 48, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 49, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 50, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 51, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 52, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 53, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 54, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 55, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 56, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 57, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 58, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 59, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },
    { idPerguntas: 60, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "governamental" },

    { idPerguntas: 61, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 62, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 63, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 64, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 65, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 66, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 67, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 68, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 69, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 70, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 71, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 72, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 73, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 74, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 75, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 76, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 77, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 78, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 79, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 80, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 81, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 82, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 83, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 84, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 85, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 86, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 87, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 88, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 89, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" },
    { idPerguntas: 90, descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", eixo: "social" }
];

const addFile = () => {
    alert('W.I.P');
}

const renderQuestion = (question) => {
    const form = document.querySelector('.form-avaliacao');
    const div = document.createElement('div');
    const FwhatsChecked = () => {
        const randomNumber = Math.random();

        if (randomNumber < 0.4) {
            return 1; // Retorna 1 com 40% de probabilidade
        } else if (randomNumber < 0.8) {
            return -1; // Retorna -1 com 40% de probabilidade
        } else {
            return 0; // Retorna 0 com 20% de probabilidade
        }
    }
    const whatsChecked = FwhatsChecked();
    div.classList.add('form-question');
    div.innerHTML = `
        <h2>${question.descricao}</h2>
        <div class="form-answer">
            <label>
                <input type="radio" name="answer-${question.idPerguntas}" value="1" ${whatsChecked === 1 ? 'checked' : null}>
                <p>Conforme</p>
            </label>
            <br>
            <label>
                <input type="radio" name="answer-${question.idPerguntas}" value="-1" ${whatsChecked === -1 ? 'checked' : null}>
                <p>Não conforme</p>
            </label>
            <br>
            <label>
                <input type="radio" name="answer-${question.idPerguntas}" value="0" ${whatsChecked === 0 ? 'checked' : null}>
                <p>Não aplicavel</p>
            </label>
            <!-- <div class="add-file">
                <button class="btn-file" onClick="addFile()">Adicionar Arquivo</button>
            </div> -->
        </div>
    `;
    return form.appendChild(div);
}

const isAllQuestionsAnswered = () => {
    const unansweredQuestions = questions.filter(question => {
        const answer = document.querySelector(`input[name="answer-${question.idPerguntas}"]:checked`);
        return !answer;
    });

    return unansweredQuestions.length === 0;
};

const onOpenAvaliacao = (props) => {
    console.log(props)
    const form = document.querySelector('.form-avaliacao')
    form.addEventListener('submit', (event) => {
        if (event.explicitOriginalTarget !== document.getElementById("btn-submit")) {
            event.preventDefault();
        }
    });
    const governamental = [];
    const ambiental = [];
    const social = [];

    fetch(`${URL}/resto`)
        .then(response => {
            return questions;
            if (!response.ok) {
                throw new Error('Erro ao recuperar dados');
            }
            return response.json();
        })
        .then(data => {
            data.forEach(item => {
                if (item.eixo === 'governamental') {
                    if (governamental.length < questionNumbers) {
                        governamental.push(item);
                    }
                } else if (item.eixo === 'ambiental') {
                    if (ambiental.length < questionNumbers) {
                        ambiental.push(item);
                    }    
                } else if (item.eixo === 'social') {
                    if (social.length < questionNumbers) {
                        social.push(item);
                    }
                }
            });

            governamental.forEach(item => renderQuestion(item));

            ambiental.forEach(item => renderQuestion(item));

            social.forEach(item => renderQuestion(item));
            
            const btnSubmit = document.createElement('button');
            btnSubmit.id = 'btn-submit';
            btnSubmit.classList.add('btn-submit');
            btnSubmit.textContent = 'Próximo';
            btnSubmit.addEventListener('click', () => {
                if (isAllQuestionsAnswered()) {
                    const answers = [];
                    questions.forEach(question => {
                        const answer = document.querySelector(`input[name="answer-${question.idPerguntas}"]:checked`);
                        if (answer) {
                            answers.push({
                                idPerguntas: question.idPerguntas,
                                pergunta: question.descricao,
                                resposta: answer.value,
                                eixo: question.eixo,
                            });
                        }
                    });
                    getMainFrameContent('result-avaliacao', {answers: answers, company: props});
                } else {
                    toastAlert('Por favor, responda todas as perguntas antes de prosseguir', 'error');
                }
            });
            form.appendChild(btnSubmit);
        })
        .catch(err => {
            console.error(err);
        });
};