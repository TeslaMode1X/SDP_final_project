package controllers;

import models.User;
import models.UserModel;
import views.MainView;
import views.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserController implements UserActionObserver {
    private UserModel model;
    private UserView view;
    private MainView mainView;

    public UserController(UserModel model, UserView view) {
        this.model = model;
        this.view = view;

        // Register this controller as an observer to UserView
        view.addObserver(this);
    }

    // Implementation of Observer methods
    @Override
    public void onLogin(String email, String password) {
        User user = model.authenticate(email, password);
        if (user != null) {
            view.showMessage("Welcome, " + user.getName() + "!");
            openMainView(user);
        } else {
            view.showMessage("Invalid email or password.");
        }
    }

    @Override
    public void onRegister(String email, String password, String name) {
        if (model.userExists(email)) {
            view.showMessage("User with this email already exists. Please log in.");
            return;
        }

        if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty()) {
            model.addUser(new User(email, password, name));
            view.showMessage("Registration successful!");
            openMainView(new User(email, password, name));
        } else {
            view.showMessage("Please fill in all fields.");
        }
    }

    private void openMainView(User user) {
        mainView = new MainView(user.getName(), user);
        mainView.setVisible(true);
        mainView.addLogoutListener(new LogoutListener());
        view.dispose();
    }

    class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainView.dispose();
            view.setVisible(true);
        }
    }
}

// Define a UserAction interface for strategy pattern
interface UserAction {
    void execute(UserController controller, String email, String password, String name);
}

// Concrete strategy for LoginAction
class LoginAction implements UserAction {
    @Override
    public void execute(UserController controller, String email, String password, String name) {
        controller.onLogin(email, password);
    }
}

// Concrete strategy for RegisterAction
class RegisterAction implements UserAction {
    @Override
    public void execute(UserController controller, String email, String password, String name) {
        controller.onRegister(email, password, name);
    }
}
