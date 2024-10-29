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
                openMainView(user.getName());  // Pass the username
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

            if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty()) {
                model.addUser(new User(email, password, name));
                view.showMessage("Registration successful!");
                openMainView(name);  // Pass the name to the main view
            } else {
                view.showMessage("Please fill in all fields.");
            }
        }
    }

    private void openMainView(String userName) {
        mainView = new MainView(userName);  // Initialize with the user's name
        mainView.setVisible(true);
        mainView.addLogoutListener(new LogoutListener());
        view.dispose();  // Close the login/registration window
    }

    class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainView.dispose();
            view.setVisible(true);  // Show the login/registration window again
        }
    }
}
