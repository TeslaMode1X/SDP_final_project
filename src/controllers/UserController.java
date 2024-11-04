package controllers;

import models.User;
import models.UserModel;
import views.MainView;
import views.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserController {
    private UserModel model;
    private UserView view;
    private MainView mainView;

    public UserController(UserModel model, UserView view) {
        this.model = model;
        this.view = view;

        view.addLoginListener(new LoginListener());
        view.addRegisterListener(new RegisterListener());
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmail();
            String password = view.getPassword();

            User user = model.authenticate(email, password);
            if (user != null) {
                view.showMessage("Welcome, " + user.getName() + "!");
                openMainView(user); // Pass the User object
            } else {
                view.showMessage("Invalid email or password.");
            }
        }
    }

    class RegisterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmail();
            String password = view.getPassword();
            String name = view.getName();

            // Check if user already exists
            if (model.userExists(email)) {
                view.showMessage("User with this email already exists. Please log in.");
                return; // Exit the method if the user exists
            }

            if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty()) {
                model.addUser(new User(email, password, name));
                view.showMessage("Registration successful!");
                openMainView(new User(email, password, name)); // Pass the registered User
            } else {
                view.showMessage("Please fill in all fields.");
            }
        }
    }

    private void openMainView(User user) {
        mainView = new MainView(user.getName(), user); // Pass name and User object
        mainView.setVisible(true);
        mainView.addLogoutListener(new LogoutListener());
        view.dispose(); // Close the login/register window
    }

    class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainView.dispose(); // Close the main view
            view.setVisible(true); // Show the UserView again
        }
    }
}
