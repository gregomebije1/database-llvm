# A database written in Java

`“What I cannot create, I do not understand”` - Richard Feynman
## 
Step-by-Step Plan:
- Table: This class will represent a table, containing rows (records).
    Database: This class will hold multiple tables and manage operations on them.
    Row: This class will represent a record in the table, with column names and values.
    Main Application: Provide a simple CLI to interact with the database.

## Example Workflow:

    Create a Table:
        Input: Create table users (id, name, age)
        Action: Creates a table named users with columns id, name, and age.

    Insert Rows:
        Input: Insert row into users table.
        Action: Adds a new row like (id: 1, name: "Alice", age: 25).

    Select Rows:
        Input: Select * from users where name="Alice"
        Action: Fetches rows where the column name matches "Alice".

    Update Row:
        Input: Update a row in the users table (by index or condition

## Setting up the Maven Project
`mvn archetype:generate -DgroupId=com.gregomebije.database -DartifactId=simple-database -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false`

`cd simple-database`
`mvn test`
`mvn package`
java -jar target/simple-database-1.0-SNAPSHOT.jar




## Setting up Maven
- First install Java and set the JAVA_HOME environment variable see https://www.digitalocean.com/community/tutorials/install-maven-mac-os
- Download from here https://maven.apache.org/download.cgi

- Run this `tar -xvf apache-maven-3.6.3-bin.tar.gz`

- Then this
```export M2_HOME="/Users/gregomebije/apache-maven-3.9.9"
PATH="${M2_HOME}/bin:${PATH}"
export PATH
```

- Test that it is running with `mvn -version`

## CLI testing
```
Database> CREATE TABLE users (id, name, age)
Table 'USERS' created.
Database> INSERT INTO users (id, name, age) VALUES (1, 'Alice', 30)
Row inserted into table 'USERS'.
Database> SELECT * FROM users
{id=1, name=Alice, age=30}

Database> DROP TABLE non_existent_table
Table 'NON_EXISTENT_TABLE' not found.

```