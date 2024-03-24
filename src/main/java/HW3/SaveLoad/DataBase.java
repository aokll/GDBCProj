package HW3.SaveLoad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    List<String> list;
    private final ISave iSave;
    private final ILoad iLoad;

    public DataBase() {
        this.iSave = new Save();
        this.iLoad = new Load();
        list = new ArrayList<>();
    }



    public void Connect(){
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test"))
        {
            finalMethod(connection);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void finalMethod(Connection connection){
     createTable(connection);
     insertTable(connection);
     printTable(connection);
     updateTable(connection);
     printTable(connection);
    }

    private void printTable(Connection connection){
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, first_name, second_name, age FROM Students");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String second_name = resultSet.getString("second_name");
                int age = resultSet.getInt("age");

                String str = "id = " + id + "first_name = " + first_name + "second_name  = " + second_name + "age = " + age;
                list.add(str + "\n");
                System.out.println(str);
            }
                iSave.save(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void createTable(Connection connection){
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE Students ( id INT PRIMARY KEY, first_name VARCHAR(255), second_name VARCHAR(255), age INT)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertTable(Connection connection){
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
    private void deleteStudent(Connection connection){
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM Students WHERE id IN (2,3)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void updateTable(Connection connection){
        try (Statement statement = connection.createStatement()) {
            statement.execute("UPDATE Students SET first_name = 'Stanislav' WHERE id = 1");
            iLoad.load(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
