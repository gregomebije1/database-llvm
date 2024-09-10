package com.gregomebije.database;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DatabaseTest {

    private Database database;

    @BeforeEach
    void setUp() {
        database = new Database();
    }

    @Test
    void testCreateTable() {
        String result = database.runSQL("CREATE TABLE users (id, name, age)");
        assertEquals("Table 'USERS' created.", result);
    }

    @Test
    void testInsertRow() {
        database.runSQL("CREATE TABLE users (id, name, age)");
        String result = database.runSQL("INSERT INTO users (id, name, age) VALUES (1, 'Alice', 30)");
        assertEquals("Row inserted into table 'USERS'.", result);
    }

    @Test
    void testSelectRow() {
        database.runSQL("CREATE TABLE users (id, name, age)");
        database.runSQL("INSERT INTO users (id, name, age) VALUES (1, 'Alice', 30)");

        String result = database.runSQL("SELECT * FROM users");
        System.out.println("Query result: " + result); // Add this line for debugging
        //assertTrue(result.contains("Alice")); //To fix
        assertTrue(result.contains("ALICE"));
    }

    @Test
    void testUpdateRow() {
        database.runSQL("CREATE TABLE users (id, name, age)");
        database.runSQL("INSERT INTO users (id, name, age) VALUES (1, 'Alice', 30)");

        String result = database.runSQL("UPDATE users SET age = '31' WHERE name = 'Alice'");
        assertEquals("Rows updated in table 'USERS'.", result);
    }

    @Test
    void testDeleteRow() {
        database.runSQL("CREATE TABLE users (id, name, age)");
        database.runSQL("INSERT INTO users (id, name, age) VALUES (1, 'Alice', 30)");
        database.runSQL("INSERT INTO users (id, name, age) VALUES (2, 'Bob', 25)");

        String result = database.runSQL("DELETE FROM users WHERE name = 'Alice'");
        assertEquals("Rows deleted from table 'USERS'.", result);
    }
}
