# Java CRUD

A simple Java application demonstrating CRUD (Create, Read, Update, Delete) operations using JDBC with a PostgreSQL database.

## Technologies

These are some of the technologies used in this project:

- `Java 17`: A widely-used, object-oriented programming language for building robust and scalable applications.
- `Gradle`: A powerful build automation tool for managing dependencies and building Java projects.
- `JDBC`: Java Database Connectivity API for connecting and executing queries with databases.
- `PostgreSQL`: A powerful, open-source relational database system.
- `PostgreSQL JDBC Driver`: The official JDBC driver for connecting Java applications to PostgreSQL databases.

_For more information about other dependencies, see the `build.gradle.kts` file._

## Prerequisites

Before installing and running this project, make sure you have the following:

- **Java Development Kit (JDK)**: Install JDK 17 or higher from [Oracle](https://www.oracle.com/java/technologies/downloads/) or use [Microsoft OpenJDK](https://learn.microsoft.com/en-us/java/openjdk/download).
- **Docker**: You must have Docker installed and configured on your computer.
    - The recommended way to run PostgreSQL for this project is using Docker, which simplifies setup and management.
    - If you do not have Docker, you will need to install PostgreSQL manually and execute the SQL queries found in `src/database/schema.sql` to set up the database schema.

## Installation

1. Clone the repository:

```bash
git clone https://github.com/thiagocrux/java-crud.git
```

2. Browse to the project folder:

```bash
cd java-crud
```

3. Create a `.env` file in the root of the project and set the environment variables as described below:

```bash
# POSTGRES_DATABASE: The name of your PostgreSQL database.
# The value you set here will be used by Docker Compose to create the database when the container starts,
# and it will also be used by the application to connect to the database.
POSTGRES_DATABASE="java_crud_db"

# POSTGRES_USER: The username for your PostgreSQL database.
# Update as needed to match your database user.
POSTGRES_USER="root"

# POSTGRES_PASSWORD: The password for your PostgreSQL database.
# Update as needed to match your database password.
POSTGRES_PASSWORD="root"

# POSTGRES_HOST: The host address for your PostgreSQL database.
# Default is 'localhost', change if your database is hosted elsewhere.
POSTGRES_HOST="localhost"

# POSTGRES_HOST_PORT: The port on your host machine mapped to the PostgreSQL container.
# Default PostgreSQL port is 5432, update if you use a different port.
POSTGRES_HOST_PORT=5432

# POSTGRES_CONTAINER_PORT: The port inside the PostgreSQL container.
# Default PostgreSQL port is 5432, update if you use a different port.
POSTGRES_CONTAINER_PORT=5432
```

4. Execute the command below to execute a PostgreSQL container and run the queries inside `src/database/schema.sql`:

```bash
docker compose up -d

```

5. If you need to stop and remove the container, use:

```bash
docker compose down
```

6. Build the project:

```bash
.\gradlew.bat build
```

7. Run the application in your IDE.

## Related links

- [PostgreSQL JDBC Driver Documentation](https://jdbc.postgresql.org/documentation/)
- [Gradle Documentation](https://docs.gradle.org/)

## License

[MIT](https://choosealicense.com/licenses/mit/)