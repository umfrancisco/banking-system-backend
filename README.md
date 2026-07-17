# Banking API - Spring Boot

API REST desenvolvida com **Spring Boot**, utilizando **MySQL** como banco de dados e **Docker** para containerização da aplicação.

O projeto simula uma estrutura bancária simples, com gerenciamento de clientes e contas, servindo como base para estudos de arquitetura backend, organização de camadas e integração com banco de dados.

---

## Tecnologias utilizadas

* Java 17
* Spring Boot 4
* Spring Data JPA
* MySQL
* Docker
* Maven

---

## Funcionalidades

Atualmente, a API possui operações básicas para:

### Customer

* `GET /customers` → Lista todos os clientes
* `POST /customers` → Cria um novo cliente

### Account

* `GET /accounts` → Lista todas as contas
* `POST /accounts` → Cria uma nova conta

---

## Modelos da aplicação

O projeto possui três entidades principais:

* **Customer** → Representa o cliente do banco
* **Account** → Representa a conta bancária vinculada a um cliente
* **BankTransaction** → Representa transações bancárias (estrutura já criada para expansão futura)

---

## Como rodar o projeto com Docker

### 1. Clonar o repositório

```bash
git clone <url-do-repositorio>
cd <nome-do-projeto>
```

### 2. Subir os containers

```bash
docker-compose up --build
```

A aplicação estará disponível em:

```
http://localhost:8080
```

---

## Configuração do banco de dados

O projeto utiliza MySQL via Docker. As configurações padrão podem ser encontradas no arquivo:

```
application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:mysql://mysql:3306/bankdb
spring.datasource.username=root
spring.datasource.password=root
```

---

## 📄 Objetivo do projeto

Este projeto foi desenvolvido com foco em aprendizado prático de:

* Construção de APIs REST com Spring Boot
* Integração com banco de dados relacional
* Uso de Docker para padronização de ambiente
* Organização de código em camadas

---

