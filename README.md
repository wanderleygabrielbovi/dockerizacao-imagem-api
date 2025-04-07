# Sobre

Esse projeto é um container Docker de uma API em Kotlin sem integração com banco de dados externo, e que faz operações CRUD.

## Implementações

- [X] Buscar por id
- [X] Buscar usuários
- [X] Criar novo usuário
- [X] Atualizar por id
- [X] Deletar por id

## Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com/), [IntelliJ](https://www.jetbrains.com/), [Docker](https://www.docker.com/).

## :game_die: Rodando o projeto
```bash
# Clone este repositório
$ git clone <https://github.com/wanderleygabrielbovi/dockerizacao-imagem-api.git>

# Acesse a pasta do projeto no terminal/cmd
$ cd {nome da pasta}/dockerizacao-imagem-api

# Execute a linha de comando para criar a imagem Docker do projeto
$ docker build -t {nome-da-imagem} .

# Execute a linha de comando para rodar a imagem Docker do projeto
$ docker run -p 8080:8080 {nome-da-imagem}

# O servidor iniciará na porta:8080 - acesse <http://localhost:8080>
```

## :hammer_and_wrench: Tecnologias

As seguintes ferramentas foram usadas para a construção do projeto:

- [Kotlin](https://kotlinlang.org/)
- [Ktor](https://ktor.io/)
- [Graddle](https://gradle.org/)
- [Docker](https://www.docker.com/)

---

Made with :blue_heart: by WANDER [See My LinkedIn](https://github.com/wanderleygabrielbovi)
