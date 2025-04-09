# 1. Como rodar a aplicação

Em uma máquina com o Docker instalado, execute o seguinte comando:

```bash
docker-compose up
```

A aplicação será executada na porta 8080.

# 2. Como testar a aplicação

Para facilitar os testes, foi disponibiliazdo o **Swagger** da aplicação, que pode ser acessado
através da URL `http://localhost:8080/swagger-ui/index.html`.

## 2.1 Requisitos Gerais

### 2.1.1 Autenticação e Autorização

#### A. Autenticação

Para se autenticar na aplicação, é necessário fazer login com usuário e senha. Para fins de teste,
foi disponibilizado um usuário padrão:

```
{
  "username": "seplag",
  "password": "8xON{d]7;B4i"
}
```

No [Swagger](http://localhost:8080/swagger-ui/index.html#/Autentica%C3%A7%C3%A3o), faça login com o
usuário e senha acima no end-point `/api/v1/auth/login`. O retorno será um **token JWT** e um
**refresh token**. 

O token JWT deve ser utilizado para autenticar as requisições na aplicação.
Para isso, copie o token e clique no botão "Authorize" no canto superior direito do Swagger. Cole o
token no campo " Value" e clique em "Authorize". O Swagger irá adicionar o token em todas as requisições.

#### B. Refresh Token

Quando o Token expirar (após 5 minutos), você poderá renová-lo no end-point
`/api/v1/auth/refresh`, utilizando o refresh token que foi retornado no login. O retorno será um
novo token JWT e um novo refresh token.

### 2.1.2 Design da API

#### C. Verbos HTTP

Foram utilizados na API os seguintes métodos HTTP: GET, POST, PUT e DELETE.

#### D. Paginação

A paginação foi implementada em todos os end-points que retornam listas.

### 2.1.3 Conteineirização

Todas as depedências do Docker Compose estão amazenadas em Container Registry na nuvem, inclusive
esta aplicação Java/Spring Boot. Por isso, basta executar o comando `docker-compose up`, facilitando
o processo de instalação e execução da aplicação.

#### D. Banco de dados

A aplicação utiliza o banco de dados **PostgreSQL**, o qual está rodando em um container.

#### F. Docker Compose

Todos os serviços necessários para o correto funcionamento da aplicação estão rodando em
containers e são orquestrados pelo **Docker Compose**. Os serviços orquestrados são os seguintess:

- **PostgreSQL**: banco de dados utilizado pela aplicação.
- **MinIO**: serviço de armazenamento de arquivos, utilizado para armazenar os documentos
  dos servidores e unidades.
- **Backend**: aplicação Spring Boot, que implementa a API REST.

Para subir a aplicação basta executar o comando

```bash
docker-compose up
```

na raiz do projeto. Assim, todos os serviços serão iniciados e estarão prontos para uso.

## 2.2 Requisitos Específicos

### 2.1 Pré-requisito

Os Servidores Efetivos e Temporários, bem como as Unidades, necessitam de um endereço. 
Por sua vez, os endereços estão associados a uma Cidade. 

Dessa forma, é necessário criar uma Cidade antes de criar um Servidor Efetivo ou Temporário, ou uma Unidade.

A Cidade pode ser criada através do end-point disponível no Swagger, na seção Cidades, 
que pode ser acessado por este
[link](http://localhost:8080/swagger-ui/index.html#/Cidades).

### 2.2 CRUD em Servidor Efetivo

Todos os end-points relacionados a CRUD em Servidor Efetivo estão disponíveis no Swagger, na
seção Servidores Efetivos, que podem ser acessados por este
[link](http://localhost:8080/swagger-ui/index.html#/Servidores%20Efetivos).

### 2.3 CRUD em Servidor Temporário

Todos os end-points relacionados a CRUD em Servidor Temporário estão disponíveis no Swagger, na
seção Servidores Temporários, que podem ser acessados por este
[link](http://localhost:8080/swagger-ui/index.html#/Servidores%20Tempor%C3%A1rios).

### 2.4 CRUD em Unidades

Todos os end-points relacionados a CRUD em Unidades estão disponíveis no Swagger, na seção
Unidades, que podem ser acessados por este
[link](http://localhost:8080/swagger-ui/index.html#/Unidades).

### 2.5 CRUD em Lotações

Todos os end-points relacionados a CRUD em Lotações estão disponíveis no Swagger, na seção
Lotações, que podem ser acessados por este
[link](http://localhost:8080/swagger-ui/index.html#/Lota%C3%A7%C3%A3o).

### 2.6 Consultar Servidores Efetivos por Unidade

Esta funcionalidade está disponível no end-point `GET /api/v1/unidades/{unidadeId}/servidores-efetivos`.

### 2.7 Consultar endereço funcional a partir de parte do nome do Servidor Efetivo

Esta funcionalidade está disponível no end-point `GET /api/v1/lotacoes/filtros?nomeServidor={parteNome}`.

### 2.8 Realizar o upload de uma ou mais fotografias

- Em Servidor Efetivo
  - ``POST /api/v1/servidores-efetivos/{id}/fotos``, o qual pode ser acessado no Swagger por 
    este [link](http://localhost:8080/swagger-ui/index.html#/Servidores%20Efetivos/adicionarFoto_1).
- Em Servidor Temporário
  - ``POST /api/v1/servidores-temporarios/{id}/fotos``, o qual pode ser acessador no Swagger 
    por este [link](http://localhost:8080/swagger-ui/index.html#/Servidores%20Tempor%C3%A1rios/adicionarFoto).