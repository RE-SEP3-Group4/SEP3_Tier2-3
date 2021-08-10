package database;

import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserRepositoryImpl extends JDBCRepository implements UserRepository{
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try(Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                int personID = resultSet.getInt(1);
                int securityLevel = resultSet.getInt(4);
                return new User(personID, username, password, securityLevel);
            } else {
                throw new IllegalArgumentException("No such user");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findUserById(int id) {
        try(Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getInt("securityLevel"));
            } else {
                throw new IllegalArgumentException("No such user");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User createUser(String username, String password) {
        try(Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users(username, password, securityLevel) VALUES(?,?,?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, 2);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findUserByUsernameAndPassword(username, password);
    }

    @Override
    public User updateUser(int id, String username, String password) {
        try(Connection connection = connect()){
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET username = ?, password = ? WHERE id = ?;");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findUserByUsernameAndPassword(username, password);
    }

    @Override
    public boolean deleteUser(int id) {
        try(Connection connection = connect()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                users.add(new User(resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getInt("securityLevel")));
            }
        } catch (SQLException e) {
            System.out.println("There was an error retrieving all the users" + e);
        }
        return users;
    }
}
