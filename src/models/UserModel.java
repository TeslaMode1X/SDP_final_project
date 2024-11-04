package models;

import java.util.ArrayList;
import java.util.List;

// Model for storing users
public class UserModel {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public User authenticate(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    public boolean userExists(String email) {
        // Assuming you have a list or a data structure to hold users
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true; // User found
            }
        }
        return false; // User not found
    }

}
