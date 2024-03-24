package HW3;

import java.sql.*;

public class DB {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test"))
        {
            finalMethod(connection);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private static void finalMethod(Connection connection){
        createTable(connection);
        insertTable(connection);
        printTable(connection);
        System.out.println();
        deleteStudent(connection);
        printTable(connection);
        System.out.println();
        set(connection);
        printTable(connection);
    }

    private static void printTable(Connection connection){
        try (Statement statement = connection.createStatement()) {
           ResultSet resultSet = statement.executeQuery("SELECT id, first_name, second_name, age FROM Students");
           while (resultSet.next()){
               int id = resultSet.getInt("id");
               String first_name = resultSet.getString("first_name");
               String second_name = resultSet.getString("second_name");
               int age = resultSet.getInt("age");

               System.out.println("id = " + id + "first_name = " + first_name + "second_name  = " + second_name + "age = " + age);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void createTable(Connection connection){
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE Students ( id INT PRIMARY KEY, first_name VARCHAR(255), second_name VARCHAR(255), age INT)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertTable(Connection connection){
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO Students(id, first_name, second_name, age)" +
                    " VALUES (1, 'Petr', 'Tolstoy', 34)," +
                    "(2, 'Alexey', 'Petrov', 23)," +
                    "(3, 'Ivan', 'Ivanov', 35)," +
                    "(4, 'Vitaliy', 'Andreev', 21) ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void deleteStudent(Connection connection){
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM Students WHERE id IN (2,3)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void set(Connection connection){
        try (Statement statement = connection.createStatement()) {
            statement.execute("UPDATE Students SET first_name = 'Stanislav' WHERE id = 1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
