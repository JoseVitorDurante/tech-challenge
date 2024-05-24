# Tech-challenge

Este é um projeto desenvolvido como parte do MBA da FIAP. O projeto é uma aplicação Java que utiliza MySQL para gerenciamento de dados e Maven para gerenciamento de dependências. A arquitetura do projeto é baseada no estilo arquitetônico Hexagonal (Ports and Adapters).

## Sobre o Projeto

O projeto é uma aplicação de pedidos, onde os usuários podem criar, atualizar e excluir pedidos. Cada pedido pode ter vários itens e cada item está associado a um pedido.

O projeto utiliza o framework Spring Boot para a criação da aplicação e o Hibernate para o mapeamento objeto-relacional. Além disso, o projeto utiliza o ModelMapper para mapear entre objetos de domínio e DTOs.

O projeto foi desenvolvido utilizando Java 21 e Maven 3.8.6. O banco de dados utilizado é MySQL.

É possível visualizar o Event Storming da solução pela ferramenta Miro atraves do link [Fast Food Event Storming](https://miro.com/app/board/uXjVKPW0siQ=/).

### Arquitetura Hexagonal

A arquitetura Hexagonal, também conhecida como Ports and Adapters, é um padrão de design de software que promove a separação de preocupações. Ela divide o sistema em três partes principais:

- **Core**: Contém a lógica de negócios do aplicativo e é independente de qualquer tecnologia específica (como banco de dados, UI, etc).
- **Ports**: São interfaces que fornecem uma maneira para o Core interagir com outros sistemas ou componentes (como um banco de dados, por exemplo).
- **Adapters**: Implementam os Ports e fornecem a ligação entre o Core e os componentes externos.

No contexto deste projeto, temos as seguintes pastas:

- **adapters**: Contém os adaptadores que implementam os ports definidos no core. Isso inclui coisas como implementações de repositório que interagem com o banco de dados.
- **core**: Contém a lógica de negócios do aplicativo. Isso inclui coisas como entidades, serviços e repositórios.

## Como Rodar o Projeto

O projeto é configurado para rodar em um container Docker. Para rodar o projeto, você precisará ter o Docker instalado em seu sistema.

1. Clone o repositório para o seu sistema local.

2. Navegue até o diretório do projeto.

3. Rode o Docker Compose com o seguinte comando:

```bash
docker compose up --build --force-recreate
```

A aplicação agora deve estar rodando em `localhost:8080`.

## Documentação da API

A documentação da API está disponível através do Swagger UI. Você pode acessá-la em http://localhost:8080/swagger-ui/index.html#/

## Dados de Teste
O projeto já possui dados salvos por padrão no banco para facilitar as operações de teste. Todas as entidades, exceto pedidos, possuem dados pré-carregados.

## Estrutura do Projeto

O projeto é estruturado da seguinte maneira:

- `src/main/java/br/com/techChallenge`: Contém o código fonte Java do projeto.
- `src/main/resources`: Contém os recursos do projeto, como arquivos de configuração e scripts SQL.
- `README.md`: Este arquivo.