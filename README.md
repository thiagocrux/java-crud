# Java CRUD - Sistema de Gerenciamento de Usuários

Uma aplicação Java console para gerenciamento de usuários (CRUD - Create, Read, Update, Delete) conectada ao PostgreSQL.

## 📋 Sobre o Projeto

Este projeto demonstra uma aplicação CRUD completa em Java com as seguintes características:

- Menu interativo via console
- Operações completas de CRUD (Create, Read, Update, Delete)
- Conexão com PostgreSQL via JDBC
- Configuração via variáveis de ambiente
- Docker Compose para fácil inicialização do banco de dados
- Gerenciamento de dependências com Gradle

## 🚀 Tecnologias

- **Java 17+**
- **PostgreSQL 16**
- **Gradle 8.14**
- **JDBC** - Para conexão com o banco de dados
- **dotenv-java** - Para gerenciamento de variáveis de ambiente
- **Docker & Docker Compose** - Para containerização do banco de dados

## 📦 Pré-requisitos

Antes de começar, você precisará ter instalado em sua máquina:

- [Java JDK 17+](https://www.oracle.com/java/technologies/downloads/)
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

> **Nota:** O Gradle Wrapper está incluído no projeto, então não é necessário instalar o Gradle separadamente.

## ⚙️ Instalação

### 1. Clone o repositório

```bash
git clone https://github.com/thiagocrux/java-crud.git
cd java-crud
```

### 2. Configure as variáveis de ambiente (opcional)

O projeto inclui um arquivo `.env` com configurações padrão. Se necessário, você pode modificá-las:

```env
POSTGRES_USER=root
POSTGRES_PASSWORD=root
POSTGRES_DATABASE=java_crud_db
POSTGRES_HOST=localhost
POSTGRES_HOST_PORT=5432
POSTGRES_CONTAINER_PORT=5432
```

### 3. Inicie o banco de dados

```bash
docker compose up -d
```

Este comando irá:
- Baixar a imagem do PostgreSQL 16
- Criar o banco de dados `java_crud_db`
- Executar o script de inicialização que cria a tabela `users`
- Expor o banco na porta 5432

### 4. Compile o projeto

**No Linux/Mac:**
```bash
./gradlew build
```

**No Windows:**
```bash
.\gradlew.bat build
```

### 5. Execute a aplicação

**No Linux/Mac:**
```bash
./gradlew run
```

**No Windows:**
```bash
.\gradlew.bat run
```

## 💻 Como Usar

Após executar a aplicação, você verá um menu interativo:

```
Menu:
1. Adicionar usuário
2. Atualizar usuário
3. Deletar usuário
4. Listar todos os usuários
5. Sair
Escolha uma opção:
```

### Exemplos de uso:

**Adicionar um novo usuário:**
1. Digite `1` e pressione Enter
2. Digite o nome do usuário
3. Digite o email do usuário

**Listar todos os usuários:**
1. Digite `4` e pressione Enter

**Atualizar um usuário:**
1. Digite `2` e pressione Enter
2. Será exibida a lista de usuários
3. Digite o ID do usuário que deseja atualizar
4. Digite o novo nome
5. Digite o novo email

**Deletar um usuário:**
1. Digite `3` e pressione Enter
2. Digite o ID do usuário que deseja deletar

## 🗄️ Estrutura do Banco de Dados

### Tabela: users

| Campo | Tipo         | Descrição                |
|-------|--------------|--------------------------|
| id    | SERIAL       | Chave primária           |
| name  | VARCHAR(50)  | Nome do usuário          |
| email | VARCHAR(50)  | Email do usuário         |

## 📁 Estrutura do Projeto

```
java-crud/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── todoapp/
│                   ├── Main.java       # Ponto de entrada e menu interativo
│                   ├── User.java       # Modelo de dados
│                   └── UserDAO.java    # Data Access Object (CRUD)
├── db/
│   └── init/
│       └── schema.sql                  # Script de inicialização do BD
├── .env                                # Variáveis de ambiente
├── docker-compose.yml                  # Configuração do Docker
├── build.gradle.kts                    # Configuração do Gradle
└── README.md
```

## 🔧 Comandos Úteis

### Docker

```bash
# Iniciar o banco de dados
docker compose up -d

# Parar o banco de dados
docker compose down

# Parar e remover volumes (apaga todos os dados)
docker compose down -v

# Ver logs do banco de dados
docker compose logs -f db
```

### Gradle

```bash
# Compilar o projeto
./gradlew build

# Executar a aplicação
./gradlew run

# Executar testes
./gradlew test

# Limpar arquivos de build
./gradlew clean

# Atualizar dependências
./gradlew --refresh-dependencies
```

## 🛠️ Solução de Problemas

### Erro de conexão com o banco de dados

1. Verifique se o Docker está rodando:
   ```bash
   docker ps
   ```

2. Verifique os logs do container:
   ```bash
   docker compose logs db
   ```

3. Certifique-se de que a porta 5432 não está em uso:
   ```bash
   # Linux/Mac
   lsof -i :5432
   
   # Windows
   netstat -ano | findstr :5432
   ```

### Banco de dados não é criado

Se o banco de dados não for criado automaticamente:

1. Pare e remova os volumes existentes:
   ```bash
   docker compose down -v
   ```

2. Inicie novamente:
   ```bash
   docker compose up -d
   ```

### Erro ao compilar

Certifique-se de que está usando Java 17 ou superior:

```bash
java -version
```

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 🔗 Links Relacionados

- [Documentação do PostgreSQL](https://www.postgresql.org/docs/)
- [JDBC Documentation](https://docs.oracle.com/javase/tutorial/jdbc/)
- [Gradle User Guide](https://docs.gradle.org/current/userguide/userguide.html)
- [Docker Documentation](https://docs.docker.com/)

## 👤 Autor

Desenvolvido por [Thiago Cruz](https://github.com/thiagocrux)

---

⭐️ Se este projeto foi útil para você, considere dar uma estrela!
