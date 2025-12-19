# 🚀 SGP API em Spring Boot - Sistema de Gerenciamento de Projetos

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

## Funcionalidades

- **Gerenciamento de Usuários**.
- **Controle de Projetos**.
- **Organização de Tarefas**.
  - CRUD completo de tarefas.
  - Filtros por **Prioridade** (Alta, Média, Baixa), **Status** e **Título**.

## Tecnologias e dependências utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL Database**
- **Lombok**
- **Validation**
- **Maven**

## Como rodar o projeto

### Pré-requisitos
- Java 17
- MySQL
  
1. Clone o repositório;
2. Configure o banco no application.properties e insira suas credenciais do MySQL
3. Execute a aplicação na sua IDE (utilizei VsCode)

### Testando no Postman

**Base URL:** `http://localhost:8080`

**1. Criar Usuário**
* `POST /usuarios`
```json
{
    "nome": "Admin da silva",
    "cpf": "444.555.999-40",
    "email": "adminsilva@gmail.com",
    "senha": "SenhaForte123!",
    "dataNascimento": "1990-01-01",
    "status": "ATIVO"
}
```
**2. Criar Projeto**
* `POST /projetos`
```json
{
    "nome": "Sistema de Vendas",
    "descricao": "Desenvolvimento do novo sistema web",
    "dataInicio": "2024-01-01",
    "status": "ATIVO",
    "responsavel": {
        "id": 1
    }
}
```

**3. Criar Tarefa (vinculada a projeto e usuário)**
* `POST /tarefas`
```json

{
    "titulo": "Criar Banco de Dados",
    "descricao": "Modelar as tabelas no MySQL",
    "dataCriacao": "2024-02-01",
    "prioridade": "ALTA",
    "status": "PENDENTE",
    "projeto": {
        "id": 1
    },
    "usuario": {
        "id": 1
    }
}
```
**4. Busque um Usuário por ID, CPF ou Email**
* `GET /usuarios`
```bash
http://localhost:8080/usuarios/1
```
**5. Busque Tarefas por ID, Título, Status ou Prioridade**
* `GET /tarefas`
```bash
http://localhost:8080/tarefas/buscaPorTitulo?titulo=Criar Banco de Dados
```
**6. Busque Projetos por ID ou Status**
* `GET /projetos`
```bash
http://localhost:8080/projetos/buscaPorStatus?status=ATIVO
```
**7. Atualize Usuários, Projetos e Tarefas por ID**
(Envie o JSON completo com os dados atualizados)

* `PUT /usuarios/{id}`
```bash
http://localhost:8080/usuarios/1
```

**8. Delete Usuário, Projeto ou Tarefas por ID**

* `DELETE /usuarios/{id}`
```bash
http://localhost:8080/usuarios/1
```
