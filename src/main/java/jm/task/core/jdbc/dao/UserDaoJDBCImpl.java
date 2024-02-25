package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final Connection conn = Util.getConnection();

    public UserDaoJDBCImpl() {
    //Пустой конструктор по умолчанию
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                "Id SERIAL PRIMARY KEY NOT NULL," +
                "Name CHARACTER VARYING(30)," +
                "LastName CHARACTER VARYING(30)," +
                "Age INTEGER);";
        try (Statement st = conn.createStatement()) {

            st.executeUpdate(sql);
            System.out.println("Cоздана таблица Users...");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Во время создании таблицы Users возникла ошибка...");
        }
    }
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";
        try (PreparedStatement pr = conn.prepareStatement(sql)) {

            pr.executeUpdate();
            System.out.println("Таблица Users была успешно удалена...");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Возникла ошибка при удалении таблицы Users...");
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users(Name, Lastname, Age) VALUES (?,?,?)";

        try (PreparedStatement pr = conn.prepareStatement(sql)) {

            pr.setString(1, name);
            pr.setString(2, lastName);
            pr.setInt(3, age);

            pr.executeUpdate();
            System.out.println("Добавление пользователей в таблицу Users прошло успешно...");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Во время добавления пользователей в таблицу Users произошла ошибка...");
        }
    }
    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE Id = ?";

        try (PreparedStatement pr = conn.prepareStatement(sql)) {

            pr.setLong(1, id);

            pr.executeUpdate();
            System.out.println("Удаление пользователя из таблицы Users прошло успешно...");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Во время удаления пользователя из таблицы Users произошла ошибка...");
        }
    }
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (PreparedStatement pr = conn.prepareStatement(sql);
             ResultSet res = pr.executeQuery()) {

            while (res.next()) {
                long id = res.getLong("Id");
                String name = res.getString("Name");
                String lastName = res.getString("LastName");
                byte age = res.getByte("Age");

                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка при получении списка пользователей.");
            return null;
        }
        return users;
    }
    public void cleanUsersTable() {
        String sql = "DELETE FROM Users";

        try (PreparedStatement pr = conn.prepareStatement(sql)) {
            pr.executeUpdate();
            System.out.println("Очистка пользователей из таблицы Users прошло успешно...");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Во время очистки всех пользователей из таблицы Users произошла ошибка...");
        }
    }
}
