# Tech-challenge

Para Rodar no ngrok e testar a aplicação, siga os passos abaixo:

1. Rodar o comando

```bash
ngrok http --domain=pure-daily-grackle.ngrok-free.app 8088 
```

Este é um projeto desenvolvido como parte do MBA da FIAP. O projeto é uma aplicação Java que utiliza MySQL para
gerenciamento de dados e Maven para gerenciamento de dependências. A arquitetura do projeto é baseada no estilo
arquitetônico Hexagonal (Ports and Adapters).

## Sobre o Projeto

O projeto é uma aplicação de pedidos, onde os usuários podem criar, atualizar e excluir pedidos. Cada pedido pode ter
vários itens e cada item está associado a um pedido.

O projeto utiliza o framework Spring Boot para a criação da aplicação e o Hibernate para o mapeamento objeto-relacional.
Além disso, o projeto utiliza o ModelMapper para mapear entre objetos de domínio e DTOs.

O projeto foi desenvolvido utilizando Java 21 e Maven 3.8.6. O banco de dados utilizado é PostgreSQL.

É possível visualizar o Event Storming da solução pela ferramenta Miro atraves do
link [Fast Food Event Storming](https://miro.com/app/board/uXjVKPW0siQ=/).

### Arquitetura Limpa (Clean Architecture)

A arquitetura Limpa (Clean Architecture) promove a separação de preocupações e a independência de frameworks e
tecnologias específicas. Ela divide o sistema em camadas com responsabilidades bem definidas.

## Como Rodar o Projeto

O projeto é configurado para rodar em um container Docker. Para rodar o projeto, você precisará ter o Docker instalado
em seu sistema.

1. Clone o repositório para o seu sistema local.

2. Navegue até o diretório do projeto.

3. Rode o Docker Compose com o seguinte comando:

```bash
docker compose up --build --force-recreate
```

Ou para com kubernetes:

```bash
kubectl apply -f ./k8
```

## Documentação da API

A documentação da API está disponível através do Swagger UI. Você pode acessá-la
em http://localhost:8080/swagger-ui/index.html#/

## Dados de Teste

O projeto já possui dados salvos por padrão no banco para facilitar as operações de teste. Todas as entidades, exceto
pedidos, possuem dados pré-carregados.

## Estrutura do Projeto

O projeto é estruturado da seguinte maneira:

- `techChallenge/k8s`: Contém os arquivos de configuração do Kubernetes (K8s).
- `src/main/java/br/com/techChallenge`: Contém o código fonte Java do projeto.
- `src/main/resources`: Contém os recursos do projeto, como arquivos de configuração e scripts SQL.
- `README.md`: Este arquivo.