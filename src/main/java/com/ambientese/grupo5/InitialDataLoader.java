package com.ambientese.grupo5;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ambientese.grupo5.Model.*;
import com.ambientese.grupo5.Model.Enums.*;
import com.ambientese.grupo5.Repository.*;
import com.github.javafaker.Faker;

@Component
public class InitialDataLoader implements CommandLineRunner {
    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PerguntasRepository perguntasRepository;

    @Autowired
    private FormularioRepository formularioRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    private final Faker faker = new Faker(new Locale("pt-BR"));

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        UsuarioModel rootUser = userRepository.findByLogin("root");

        if (rootUser == null) {
            // Popular tabela endereco e empresa
            int numberToGenerate = 20;

            for (int i = 0; i < numberToGenerate; i++) {
                EmpresaModel empresa = new EmpresaModel();
                empresa.setNomeFantasia(faker.company().name());
                empresa.setNomeSolicitante(faker.name().fullName());
                String cellPhone = faker.phoneNumber().cellPhone();
                if (cellPhone.length() > 15) {
                    cellPhone = cellPhone.substring(0, 15);
                }
                empresa.setTelefoneSolicitante(cellPhone);
                empresa.setRazaoSocial(faker.company().name());
                empresa.setCnpj(faker.number().digits(14));
                empresa.setInscricaoSocial(faker.number().digits(20));
                empresa.setEmail(faker.internet().emailAddress());
                String telefone = faker.phoneNumber().phoneNumber();
                if (telefone.length() > 15) {
                    telefone = telefone.substring(0, 15);
                }
                empresa.setTelefoneEmpresas(telefone);
                empresa.setRamo(faker.company().industry());
                empresa.setPorteEmpresas(getRandomPorte());

                EnderecoModel endereco = new EnderecoModel();
                endereco.setCep(faker.address().zipCode());
                endereco.setNumero(faker.number().numberBetween(1, 1000));
                endereco.setLogradouro(faker.address().streetName());
                endereco.setComplemento(faker.address().secondaryAddress());
                endereco.setCidade(faker.address().cityName());
                endereco.setBairro(faker.address().streetName());
                endereco.setUF(faker.address().stateAbbr());

                empresa.setEndereco(endereco);

                empresaRepository.save(empresa);
            }

            // Popular tabela de cargos
            CargoModel cargoAdm = new CargoModel();
            cargoAdm.setDescricao("Administrador");
            cargoAdm = cargoRepository.save(cargoAdm);

            CargoModel cargoConsultor = new CargoModel();
            cargoConsultor.setDescricao("Consultor");
            cargoConsultor = cargoRepository.save(cargoConsultor);

            // Popular tabela de funcionarios
            for (int i = 0; i < numberToGenerate; i++) {
                UsuarioModel usuario = new UsuarioModel();
                usuario.setLogin(faker.name().username());
                usuario.setPassword(faker.internet().password());
                usuario.setIsAdmin(false);
                usuario = userRepository.save(usuario);

                FuncionarioModel funcionario = new FuncionarioModel();
                funcionario.setUsuario(usuario);
                funcionario.setNome(faker.name().fullName());
                funcionario.setCpf(faker.regexify("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"));
                funcionario.setEmail(faker.internet().emailAddress());
                funcionario.setDataNascimento(faker.date().birthday().toString());
                funcionario.setCargo(cargoConsultor);

                funcionarioRepository.save(funcionario);
            }

            // Popular tabela de perguntas
            for (EixoEnum eixo : EixoEnum.values()) {
                String[] perguntasArray;
                switch (eixo) {
                    case Ambiental:
                        perguntasArray = perguntasAmbiental;
                        break;
                    case Social:
                        perguntasArray = perguntasSocial;
                        break;
                    case Governamental:
                        perguntasArray = perguntasGovernamental;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + eixo);
                }
                for (int i = 0; i < perguntasArray.length; i++) {
                    PerguntasModel pergunta = new PerguntasModel();
                    pergunta.setDescricao(perguntasArray[i]);
                    pergunta.setPerguntasEixo(eixo);
                    perguntasRepository.save(pergunta);
                }
            }

            // Popular tabela de formulários
            List<EmpresaModel> empresas = empresaRepository.findAll();
            List<PerguntasModel> perguntasAmbientalList = perguntasRepository.findByPerguntasEixo(EixoEnum.Ambiental);
            List<PerguntasModel> perguntasSocialList = perguntasRepository.findByPerguntasEixo(EixoEnum.Social);
            List<PerguntasModel> perguntasGovernamentalList = perguntasRepository.findByPerguntasEixo(EixoEnum.Governamental);

            for (EmpresaModel empresa : empresas) {
                FormularioModel formulario = new FormularioModel();
                formulario.setEmpresa(empresa);
                formulario.setPontuacaoFinal(faker.number().numberBetween(0, 100));
                formulario.setPontuacaoSocial(faker.number().numberBetween(0, 100));
                formulario.setPontuacaoAmbiental(faker.number().numberBetween(0, 100));
                formulario.setPontuacaoGovernamental(faker.number().numberBetween(0, 100));
                formulario.setCertificado(NivelCertificadoEnum.values()[faker.number().numberBetween(0, NivelCertificadoEnum.values().length)]);
                formulario.setDataRespostas(new Date());

                formularioRepository.save(formulario);

                // Popular tabela de respostas com 10 perguntas de cada eixo
                List<PerguntasModel> perguntasSelecionadas = getRandomQuestions(perguntasAmbientalList, 10);
                perguntasSelecionadas.addAll(getRandomQuestions(perguntasSocialList, 10));
                perguntasSelecionadas.addAll(getRandomQuestions(perguntasGovernamentalList, 10));

                for (PerguntasModel pergunta : perguntasSelecionadas) {
                    RespostaId respostaId = new RespostaId(formulario.getId(), pergunta.getId());
                    RespostaModel resposta = new RespostaModel(respostaId, formulario, pergunta, RespostasEnum.values()[faker.number().numberBetween(0, RespostasEnum.values().length)]);
                    respostaRepository.save(resposta);
                }
            }

            // Criação do usuário root
            UsuarioModel newUser = new UsuarioModel();
            newUser.setLogin("root");
            newUser.setPassword("root");
            newUser.setIsAdmin(true);

            userRepository.save(newUser);

            System.out.println("Banco pré preenchido com sucesso.");
            System.out.println("Usuário root criado com sucesso.");
            System.out.println("Login: root");
            System.out.println("Senha: root");
        } else {
            System.out.println("Usuário root já existe.");
            System.out.println("Login: root");
            System.out.println("Senha: root");
        }
    }

    private PorteEnum getRandomPorte() {
        PorteEnum[] portes = PorteEnum.values();
        int randomIndex = faker.random().nextInt(portes.length);
        return portes[randomIndex];
    }

    private List<PerguntasModel> getRandomQuestions(List<PerguntasModel> perguntas, int numberOfQuestions) {
        Random random = new Random();
        List<PerguntasModel> randomQuestions = new ArrayList<>();

        while (randomQuestions.size() < numberOfQuestions) {
            int randomIndex = random.nextInt(perguntas.size());
            PerguntasModel randomQuestion = perguntas.get(randomIndex);
            if (!randomQuestions.contains(randomQuestion)) {
                randomQuestions.add(randomQuestion);
            }
        }

        return randomQuestions;
    }

    private static final String[] perguntasAmbiental = {
        "A empresa possui uma política ambiental clara e documentada?",
        "Quais são as metas de redução de emissões de carbono da empresa?",
        "Como a empresa gerencia seus resíduos sólidos?",
        "A empresa utiliza fontes de energia renovável?",
        "Há programas de eficiência energética implementados na empresa?",
        "A empresa realiza auditorias ambientais regulares?",
        "Qual é o consumo de água da empresa e como ele é gerido?",
        "A empresa tem políticas de reciclagem em vigor?",
        "Como a empresa lida com a poluição do ar e da água gerada por suas operações?",
        "A empresa tem iniciativas para conservar a biodiversidade?",
        "Existem programas de educação ambiental para os funcionários?",
        "A empresa divulga seu desempenho ambiental em relatórios de sustentabilidade?",
        "Há investimentos em tecnologias limpas e sustentáveis?",
        "A empresa possui certificações ambientais, como ISO 14001?",
        "A empresa trabalha para minimizar o impacto ambiental ao longo de sua cadeia de suprimentos?",
        "Como a empresa avalia e mitiga o impacto ambiental de novos projetos?",
        "A empresa participa de iniciativas ou colaborações globais para a sustentabilidade ambiental?",
        "Quais são as estratégias da empresa para lidar com as mudanças climáticas?",
        "A empresa incentiva práticas sustentáveis entre seus clientes e fornecedores?",
        "Qual é o compromisso da empresa com a restauração de ecossistemas afetados por suas operações?"
    };

    private static final String[] perguntasSocial = {
        "A empresa possui políticas de diversidade e inclusão?",
        "Qual é o compromisso da empresa com a saúde e segurança dos funcionários?",
        "A empresa oferece oportunidades de desenvolvimento e capacitação para os empregados?",
        "Como a empresa se engaja com as comunidades locais onde opera?",
        "A empresa promove igualdade de gênero no local de trabalho?",
        "Quais são as práticas de remuneração e benefícios da empresa?",
        "A empresa possui políticas contra assédio e discriminação?",
        "Como a empresa contribui para a educação e formação profissional na comunidade?",
        "A empresa realiza auditorias sociais em sua cadeia de suprimentos?",
        "Existem programas de voluntariado corporativo incentivados pela empresa?",
        "A empresa apoia a saúde e o bem-estar dos funcionários além do local de trabalho?",
        "A empresa tem políticas de trabalho infantil e trabalho forçado em sua cadeia de suprimentos?",
        "Como a empresa lida com as questões de direitos humanos em suas operações globais?",
        "A empresa envolve os funcionários em decisões importantes que os afetam?",
        "Qual é a taxa de retenção de funcionários da empresa?",
        "A empresa promove a participação dos funcionários em iniciativas de responsabilidade social?",
        "A empresa possui práticas transparentes de comunicação com as partes interessadas?",
        "Como a empresa mede e relata seu impacto social?",
        "A empresa apoia iniciativas culturais e esportivas nas comunidades onde atua?",
        "A empresa possui políticas de equilíbrio entre vida pessoal e profissional para os funcionários?"
    };

    private static final String[] perguntasGovernamental = {
        "A empresa possui uma estratégia clara de sustentabilidade ambiental?",
        "Como a empresa integra considerações ambientais em seu planejamento estratégico?",
        "A empresa realiza auditorias ambientais regulares?",
        "Quais são as práticas de gestão de risco ambiental da empresa?",
        "A empresa possui uma política de governança ambiental documentada?",
        "Como a empresa gerencia a conformidade com as leis e regulamentos ambientais?",
        "A empresa divulga relatórios de sustentabilidade ambiental?",
        "Quais são as práticas de transparência da empresa em relação ao seu impacto ambiental?",
        "A empresa possui um comitê de sustentabilidade ou um departamento dedicado ao meio ambiente?",
        "Como a empresa lida com a responsabilidade ambiental na cadeia de suprimentos?",
        "A empresa promove a participação dos stakeholders nas decisões ambientais?",
        "A empresa tem políticas para prevenir e mitigar a poluição ambiental?",
        "Como a empresa gerencia e minimiza seu impacto ambiental ao longo do ciclo de vida dos produtos?",
        "A empresa possui programas de treinamento ambiental para os funcionários?",
        "Quais são as estratégias da empresa para reduzir suas emissões de carbono?",
        "A empresa investe em tecnologias limpas e sustentáveis?",
        "Como a empresa mede e gerencia seu consumo de recursos naturais?",
        "A empresa possui certificações ambientais, como ISO 14001?",
        "Quais são as práticas de responsabilidade ambiental da empresa em relação às comunidades locais?",
        "A empresa tem um histórico de iniciativas e projetos de conservação ambiental?"
    };    
}
