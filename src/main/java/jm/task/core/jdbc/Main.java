package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Elena", "Son", (byte) 24);
        System.out.println("User с именем – Elena добавлен в базу данных");

        userService.saveUser("Vlad", "Iun", (byte) 25);
        System.out.println("User с именем – Vlad добавлен в базу данных");

        userService.saveUser("Artur", "An", (byte) 3);
        System.out.println("User с именем – Artur добавлен в базу данных");

        userService.saveUser("Milana", "Kvon", (byte) 15);
        System.out.println("User с именем – Milana добавлен в базу данных");

        userService.getAllUsers();

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
