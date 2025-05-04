# Projeto GraphQL Spring POC

Este projeto (CRUD básico) foi criado para estudo e demonstração do uso do [GraphQL](https://graphql.org/) com [Spring Boot](https://spring.io/projects/spring-boot) e JPA para gerenciamento de dados.

## O que é GraphQL?

GraphQL é uma linguagem de consulta para APIs desenvolvida pelo Facebook. Em vez de usar múltiplos endpoints REST, o GraphQL permite que o cliente solicite exatamente os dados necessários em uma única requisição. Suas principais características são:
- **Consulta Hierárquica:** Permite realizar consultas complexas e aninhadas.
- **Recuperação de Dados Sob Demanda:** Evita o overfetching (excesso de dados) e underfetching (falta de dados).
- **Tipagem Forte:** Define um esquema que serve como contrato entre cliente e servidor.

## Visão Geral do Projeto

Neste projeto, utilizamos o GraphQL para expor operações sobre a entidade `Livro`. A seguir, são descritas as funcionalidades implementadas:

### Queries

- **buscarPorId:** Retorna um livro baseado em seu identificador único.
- **buscarTodos:** Lista todos os livros cadastrados.
- **buscarPorAutor:** Retorna os livros que correspondem ao nome do autor informado.

### Mutations

- **adicionarLivro:** Cria um novo livro informando título e autor.
- **atualizarLivro:** Atualiza os dados de um livro existente, alterando título e/ou autor.
- **deletarLivro:** Remove um livro a partir do seu identificador.
- **adicionarColecaoDeLivros:** Permite o cadastro de múltiplos livros de uma só vez, utilizando um DTO (`LivroInput`) para validação dos dados.

## Tecnologias Utilizadas

- **Spring Boot:** Framework principal para criação da aplicação.
- **Spring GraphQL:** Integração do GraphQL com Spring Boot.
- **JPA/Hibernate:** Gerenciamento de persistência de dados.
- **Jakarta Validation:** Realiza a validação de entradas nos endpoints, garantindo que os dados enviados estão corretos.

## Como Executar o Projeto

1. **Pré-requisitos:**
   - Java 17 ou superior instalado.
   - Maven instalado.

2. **Clonar o Repositório:**
   ```bash
   git clone https://github.com/ViniHDSouza/graphql_spring_poc.git
   ```

3. **Navegar até a Pasta do Projeto:**
   ```bash
   cd graphql_spring_poc
   ```

4. **Executar a Aplicação:**
   ```bash
   mvn spring-boot:run
   ```

Após a inicialização, a aplicação estará disponível na porta padrão (geralmente 8080). Você pode utilizar clientes como o [GraphiQL](https://github.com/graphql/graphiql) ou o [Altair GraphQL Client](https://altair.sirmuel.design/) para testar os endpoints GraphQL.

## Comentários no Código

No arquivo `LivroController.java`, os comentários ajudam a entender os seguintes pontos:
- **DTO e Entidade:** A utilização de classes para transferência de dados (`LivroInput`) e mapeamento da entidade (`Livro`).
- **Dependência do Repositório:** Injeção de dependência para acesso aos dados (CRUD) dos livros.
- **Fluxo de Dados nas Queries e Mutations:** Explicação de como cada método expõe as operações de consulta e modificação dos dados.

1. **Configuração da Requisição:**
   - **Método:** POST
   - **URL:** http://localhost:8080/graphql
   - **Header:** Content-Type: application/json

2. **Exemplo de Query - Buscar Livro por ID:**
   ```json
   {
       "query": "query { buscarPorId(id: 1) { id titulo autor } }"
   }
   ```

3. **Exemplo de Query - Listar Todos os Livros:**
   ```json
   {
       "query": "query { buscarTodos { id titulo autor } }"
   }
   ```

4. **Exemplo de Mutation - Adicionar Livro:**
   ```json
   {
       "query": "mutation { adicionarLivro(titulo: "Livro Exemplo", autor: "Autor Exemplo") { id titulo autor } }"
   }
   ```

5. **Exemplo de Mutation - Atualizar Livro:**
   ```json
   {
       "query": "mutation { atualizarLivro(id: 1, titulo: "Novo Título", autor: "Novo Autor") { id titulo autor } }"
   }
   ```

6. **Exemplo de Mutation - Deletar Livro:**
   ```json
   {
       "query": "mutation { deletarLivro(id: 1) }"
   }
   ```

7. **Exemplo de Mutation - Adicionar Coleção de Livros:**
   ```json
   {
       "query": "mutation { adicionarColecaoDeLivros(livros: [{titulo: "Livro 1", autor: "Autor 1"}, {titulo: "Livro 2", autor: "Autor 2"}]) { id titulo autor } }"
   }
   ```
8. **Exemplo de Mutation - Buscar Por Autor:**
   ```json
   {
       "query": "query { buscarPorAutor(autor: "Eric Evans") { id titulo } } }" 
   }
   ```
   
## Conclusão

Este projeto demonstra de forma simples e prática como integrar o GraphQL com Spring Boot para construir APIs flexíveis e eficientes.
