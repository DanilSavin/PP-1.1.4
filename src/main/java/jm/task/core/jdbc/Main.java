package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserServiceImpl methodsForUsers = new UserServiceImpl();
        methodsForUsers.createUsersTable();
        methodsForUsers.saveUser("Катя", "Вторая", (byte) 24);
        methodsForUsers.saveUser("Настя", "Третья", (byte) 25);
        methodsForUsers.saveUser("Дима", "Первый", (byte) 19);
        methodsForUsers.saveUser("Альфред", "Восьмой", (byte) 4);
        for (User user : methodsForUsers.getAllUsers()) {
            System.out.println(user);
        }
        methodsForUsers.cleanUsersTable();
        methodsForUsers.dropUsersTable();
    }
    }

