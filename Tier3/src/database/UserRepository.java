package database;

import domain.User;

import java.util.List;

public interface UserRepository {
    User findUserByUsernameAndPassword(String username, String password);
    User findUserById(int id);
    User createUser(String username, String password);
    User updateUser(int id, String username, String password);
    boolean deleteUser(int id);
    List<User> getAllUsers();
}
