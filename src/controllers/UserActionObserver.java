package controllers;

public interface UserActionObserver {
    void onLogin(String email, String password);
    void onRegister(String email, String password, String name);
}
