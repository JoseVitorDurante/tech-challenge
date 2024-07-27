# Tech Challenge

Este é um projeto desenvolvido como parte do MBA da FIAP. Trata-se de uma aplicação Java para gerenciamento de pedidos,
utilizando MySQL para persistência de dados e Maven para gerenciamento de dependências. O projeto segue a arquitetura
Hexagonal (Ports and Adapters) para promover a separação de preocupações.

## Sobre o Projeto

O projeto é uma aplicação de pedidos, onde os usuários podem criar, atualizar e excluir pedidos. Cada pedido pode conter
vários itens, e cada item está associado a um pedido.

### Diagrama de Arquitetura

Abaixo está o diagrama de arquitetura do projeto, que ilustra a estrutura e os componentes principais da aplicação:

![Diagrama de Arquitetura](tech_challenge_architecture.svg)

### Tecnologias Utilizadas

- **Java 21**
- **Maven 3.8.6**
- **Spring Boot**
- **Hibernate**
- **ModelMapper**
- **Docker**
- **Kubernetes**
- **MySQL**

### Arquitetura Hexagonal (Clean Architecture)

A arquitetura Hexagonal promove a separação de preocupações e a independência de frameworks e tecnologias específicas.
Ela divide o sistema em camadas com responsabilidades bem definidas, garantindo que as regras de negócio não sejam
afetadas por mudanças em frameworks ou interfaces externas.

## Como Rodar o Projeto

O projeto está configurado para ser executado em um container Docker. Para executar o projeto, siga os passos abaixo:

1. **Clone o repositório** para o seu sistema local:

```bash
  git clone [URL_DO_REPOSITORIO]
```

2. **Navegue até o diretório do projeto:**:

```bash
  cd tech-challenge
```

3. **Execute o comando para subir o container Docker**:

```bash
  docker compose up --build --force-recreate
```

Alternativamente, para execução com Kubernetes:

```bash
  kubectl apply -f ./k8s
```

### Expondo a Aplicação com Ngrok

Para expor a aplicação para a internet e realizar testes externos, utilize o Ngrok:

```bash
  ngrok http --domain=pure-daily-grackle.ngrok-free.app 8088
```

## Documentação da API

A documentação da API está disponível no Swagger, e pode ser acessada através do link:

```
  http://localhost:8088/swagger-ui.html
```

## Dados de Teste

O projeto inclui dados de teste pré-carregados para facilitar as operações. Todas as entidades, exceto pedidos, possuem
dados de exemplo.

## Teste de Estresse com K6

Para realizar testes de estresse na aplicação, utilizamos o K6. Siga os passos abaixo para executar o teste:

Instale o K6 no seu sistema. Para instruções detalhadas, visite a documentação oficial do K6.

### Executando o Teste de Estresse

```bash
  k6 run load-load-test.js
```

**Observação:**
Certifique-se de que todos os pré-requisitos estão instalados e configurados corretamente antes de iniciar o projeto.
Para qualquer dúvida ou problema, consulte a documentação oficial das ferramentas utilizadas ou entre em contato com o
responsável pelo projeto.