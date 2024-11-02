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
                openMainView(user); // Передаем объект User
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
                openMainView(new User(email, password, name)); // Передаем зарегистрированного пользователя
            } else {
                view.showMessage("Please fill in all fields.");
            }
        }
    }

    private void openMainView(User user) {
        mainView = new MainView(user.getName(), user); // Передаем имя и пользователя
        mainView.setVisible(true);
        mainView.addLogoutListener(new LogoutListener());
        view.dispose(); // Закрываем окно входа/регистрации
    }

    class LogoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainView.dispose();
            view.setVisible(true); // Отображаем окно входа/регистрации снова
        }
    }
}
