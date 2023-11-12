package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        String sql = "create table mybdtest.users (id bigint auto_increment primary key, name varchar(40) , lastName varchar(40) , age tinyint )";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public void dropUsersTable() throws SQLException {
        String sql = "drop table if exists mybdtest.users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "insert into mybdtest.users values(id, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        }

    }

    public void removeUserById(long id) throws SQLException {
        String sql = "delete from mybdtest.users where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "select name, lastName, age from mybdtest.users";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "TRUNCATE TABLE mybdtest.users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
}
