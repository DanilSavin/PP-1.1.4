package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static jm.task.core.jdbc.util.Util.executeUpdate;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        executeUpdate("CREATE TABLE IF NOT EXISTS `new_schema`.`user` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT(3) NOT NULL,\n" +
                "  PRIMARY KEY (`id`))");
    }


    public void dropUsersTable() throws SQLException {
        executeUpdate("DROP TABLE IF EXISTS user;");
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        executeUpdate(
                "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)",
                Map.of(1, name, 2, lastName, 3, age));
    }

    public void removeUserById(long id) throws SQLException {
        executeUpdate(
                "DELETE FROM user WHERE id = ?;",
                Map.of(1, id));

    }

    public List<User> getAllUsers() throws SQLException {

        return Util.executeQuery("SELECT * FROM user",
                resultSet -> {
                    List<User> allUsers = new ArrayList<>();
                    try {
                        while (resultSet.next()) {
                            User user = new User();
                            user.setId(resultSet.getLong("id"));
                            user.setName(resultSet.getString("name"));
                            user.setLastName(resultSet.getString("lastName"));
                            user.setAge(resultSet.getByte("age"));

                            allUsers.add(user);
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    return allUsers;

                });
    }

    public void cleanUsersTable() throws SQLException {
        executeUpdate("DELETE FROM user");
    }
}
