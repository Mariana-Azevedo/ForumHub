# FórumHub API


## 📖 Sobre o Projeto

**FórumHub** é uma API REST desenvolvida como parte do Challenge de Back-End do programa Oracle Next Education (ONE) em parceria com a Alura. O objetivo do projeto é simular o back-end de um fórum de discussões, permitindo que usuários autenticados criem, visualizem, atualizem e deletem tópicos.

A API foi construída seguindo os princípios do modelo de maturidade de Richardson, utilizando um banco de dados relacional para persistência de dados e um sistema de autenticação via token JWT para garantir a segurança dos endpoints.

---

## ✨ Funcionalidades

-   **Autenticação e Autorização:**
    -   [x] Registro de novos usuários.
    -   [x] Autenticação de usuários existentes para gerar um token JWT (Bearer Token).
    -   [x] Proteção dos endpoints para garantir que apenas usuários autenticados possam interagir com os tópicos.
-   **CRUD Completo de Tópicos:**
    -   [x] **Criar** um novo tópico.
    -   [x] **Listar** todos os tópicos existentes com paginação e ordenação.
    -   [x] **Detalhar** um tópico específico pelo seu ID.
    -   [x] **Atualizar** as informações de um tópico (título e mensagem).
    -   [x] **Deletar** um tópico (utilizando exclusão lógica).
-   **Validações e Tratamento de Erros:**
    -   [x] Validações de negócio (ex: não permitir tópicos duplicados, emails já cadastrados).
    -   [x] Tratamento de erros centralizado para fornecer respostas claras e códigos de status HTTP corretos para o cliente.

---

## 🛠️ Tecnologias Utilizadas

O projeto foi desenvolvido com as seguintes tecnologias:

-   **Java 17**
-   **Spring Boot 3**
-   **Spring Web:** Para a construção dos endpoints REST.
-   **Spring Data JPA:** Para a persistência de dados.
-   **Spring Security:** Para a implementação da segurança e autenticação.
-   **Maven:** Como gerenciador de dependências.
-   **MySQL:** Como banco de dados relacional.
-   **Flyway:** Para o versionamento e migração do schema do banco de dados.
-   **Lombok:** Para reduzir o código boilerplate nas entidades e DTOs.
-   **JWT (JSON Web Token):** Para a geração e validação dos tokens de autenticação.

---

## Endpoints da API

A seguir, a documentação dos endpoints disponíveis na API.

| Funcionalidade              | Verbo HTTP | Endpoint               | Autenticação Requerida? | Corpo da Requisição (Exemplo)                                                                              | Resposta de Sucesso                                    |
| --------------------------- | ---------- | ---------------------- | ----------------------- | ---------------------------------------------------------------------------------------------------------- | ------------------------------------------------------ |
| **Registrar Usuário** | `POST`     | `/usuarios`            | Não                     | `{"nome": "Ana Silva", "email": "ana@email.com", "senha": "123456"}`                                       | `201 Created` com os dados do usuário (sem senha)      |
| **Autenticar Usuário** | `POST`     | `/login`               | Não                     | `{"email": "ana@email.com", "senha": "123456"}`                                                             | `200 OK` com o token JWT                               |
| **Criar Tópico** | `POST`     | `/topicos`             | Sim (Bearer Token)      | `{"titulo": "Dúvida", "mensagem": "Como usar...", "idAutor": 1, "idCurso": 1}`                              | `201 Created` com os detalhes do tópico criado         |
| **Listar Todos os Tópicos** | `GET`      | `/topicos`             | Sim (Bearer Token)      | N/A                                                                                                        | `200 OK` com uma lista paginada de tópicos             |
| **Detalhar um Tópico** | `GET`      | `/topicos/{id}`        | Sim (Bearer Token)      | N/A                                                                                                        | `200 OK` com os detalhes do tópico especificado        |
| **Atualizar um Tópico** | `PUT`      | `/topicos/{id}`        | Sim (Bearer Token)      | `{"titulo": "Título corrigido", "mensagem": "Mensagem atualizada"}`                                        | `200 OK` com os detalhes do tópico atualizado          |
| **Deletar um Tópico** | `DELETE`   | `/topicos/{id}`        | Sim (Bearer Token)      | N/A                                                                                                        | `204 No Content`                                       |

---

## ⚙️ Configuração do Ambiente

Siga os passos abaixo para rodar o projeto localmente.

### Pré-requisitos
-   Java 17 ou superior
-   Maven 3.8 ou superior
-   MySQL Server

### 1. Clonar o Repositório
```bash
git clone [https://github.com/seu-usuario/forumhub.git](https://github.com/seu-usuario/forumhub.git)
cd forumhub
