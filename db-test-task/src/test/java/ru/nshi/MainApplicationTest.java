package ru.nshi;

import org.junit.jupiter.api.Test;

import java.sql.*;

//@SpringBootTest
public class MainApplicationTest {
    @Test
    void testCreateConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:devDb;" +
            "MODE=PostgreSQL;" +
            "DATABASE_TO_LOWER=TRUE");
        System.out.println(connection.getClass());


        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS tag (\n" +
                "    id SERIAL PRIMARY KEY,\n" +
                "    name VARCHAR(63)\n" +
                ");");

            statement.execute("INSERT INTO tag (name) VALUES 'compile';");
            ResultSet rs = statement.executeQuery("SELECT * FROM tag;");

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                System.out.println(id + " " + name);
            }
        }
        connection.close();
    }

    @Test
    void testCreatePreparedStatement() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:devDb;" +
            "MODE=PostgreSQL;" +
            "DATABASE_TO_LOWER=TRUE");
        System.out.println(connection.getClass());


        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS tag (\n" +
                "    id SERIAL PRIMARY KEY,\n" +
                "    name VARCHAR(63)\n" +
                ");");

            String value = "'; DROP TABLE tag; SELECT '";
            PreparedStatement prepStat =
                connection.prepareStatement("INSERT INTO tag (name) VALUES ?");

            prepStat.setString(1, value);
            prepStat.addBatch();

            prepStat.setString(1, "compile");
            prepStat.addBatch();

            prepStat.executeBatch();
            prepStat.close();

            prepStat =
                connection.prepareStatement("SELECT * FROM tag");
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                System.out.println(id + " " + name);
            }
        }
        connection.close();
    }
}
