package main;

import controllers.UserController;
import models.UserModel;
import views.UserView;

public class Main {
    public static void main(String[] args) {
        UserModel model = new UserModel();
        UserView view = new UserView();

        new UserController(model, view);
        view.setVisible(true);
    }
}
