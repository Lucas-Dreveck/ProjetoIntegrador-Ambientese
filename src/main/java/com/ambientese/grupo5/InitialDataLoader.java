package com.ambientese.grupo5;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ambientese.grupo5.Model.*;
import com.ambientese.grupo5.Model.Enums.EixoEnum;
import com.ambientese.grupo5.Model.Enums.PorteEnum;
import com.ambientese.grupo5.Repository.*;
import com.github.javafaker.Faker;

@Component
public class InitialDataLoader implements CommandLineRunner {
    String[] perguntasAmbiental = {
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

    String[] perguntasSocial = {
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

    String[] perguntasEconomico = {
            "A empresa possui uma estratégia clara de sustentabilidade econômica?",
            "Como a empresa integra considerações de sustentabilidade em seu planejamento financeiro?",
            "A empresa investe em inovação para melhorar sua eficiência econômica?",
            "A empresa possui uma política de governança corporativa forte e transparente?",
            "Como a empresa lida com a gestão de riscos econômicos e financeiros?",
            "Quais são as práticas de responsabilidade fiscal da empresa?",
            "A empresa possui uma estratégia de crescimento econômico sustentável?",
            "Como a empresa mede e gerencia seu impacto econômico nas comunidades locais?",
            "A empresa é transparente em relação à sua estrutura de custos e margens de lucro?",
            "A empresa investe em desenvolvimento de infraestrutura sustentável?",
            "A empresa possui práticas de compras responsáveis e sustentáveis?",
            "A empresa mantém uma relação ética e transparente com seus investidores e acionistas?",
            "A empresa promove práticas de comércio justo em sua cadeia de suprimentos?",
            "A empresa apoia o desenvolvimento de pequenas e médias empresas em sua cadeia de valor?",
            "Como a empresa gerencia suas dívidas e obrigações financeiras?",
            "A empresa tem um histórico de estabilidade econômica e financeira?",
            "A empresa realiza auditorias financeiras independentes e regulares?",
            "Como a empresa planeja e investe em pesquisa e desenvolvimento?",
            "A empresa possui uma política de distribuição justa de lucros entre acionistas e reinvestimento?",
            "Quais são as práticas de transparência financeira da empresa em relação aos seus impactos econômicos e sociais?"
        };

    private final Faker faker = new Faker(new Locale("pt-BR"));

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

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        UsuarioModel rootUser = userRepository.findByLogin("root");

        if (rootUser == null) {
            // Popular tabela empresa
            int numberOfCompaniesToGenerate = 20;

            for (int i = 0; i < numberOfCompaniesToGenerate; i++) {
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
            for (int i = 0; i < 20; i++) {
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
                    case Econômico:
                        perguntasArray = perguntasEconomico;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + eixo);
                }
                for (int i = 0; i < 20; i++) {
                    PerguntasModel pergunta = new PerguntasModel();
                    pergunta.setDescricao(perguntasArray[i]);
                    pergunta.setPerguntasEixo(eixo);
                    perguntasRepository.save(pergunta);
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
}
