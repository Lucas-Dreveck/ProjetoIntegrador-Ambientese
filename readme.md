### Projeto Integrador Ambiente-se


## Changelog
Informações sobre as versões e mudanças do projeto.

## Versão 1.0.0 - 2024-05-24
### Adicionado
- Sistema de login com salvamento do usuario ativo;
- Tela de ranking 100% funcional, podendo filtrar todas as empresas doranking;
- Tela de avaliação completa das empresas com tela para selecionar a empresa destinada, avalia-la com perguntas salvas randomizadas e após isso visualizar o resultado da empresa;
- Tela de cadastro de empresa (CRUD) funcional;
- Tela de cadastro de funcionários (CRUD) funcional;

## Versão 2.0.0 - 2024-06-23
### Adicionado
- Autenticação por token JWT com validação por front e back end;
- Hashs de todas as senhas da aplicação;
- Rotas protegidas utilizando dos cargos e propriedades dos usuarios;
- Criação de tela de cadastro de perguntas completo;
- Filtro e paginação para as telas de cadastro de empresas, funcionarios e perguntas;
- Paginação de tela de avaliação permitindo salvamento incompleto da mesma com verificações para retornar progresso ou abandonar;
- Sistema de geração de PDF com todos os dados detalhados das avaliações;
- Update de interface em todas as telas;
- Melhoria de feedback de erro em todos os tipos de formulários;
- Configurações docker para melhoria de testes e portabilidade.