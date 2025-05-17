package com.quizzo.service;

public class AuthService {
    private static final UserService userService = new UserService();

    /** Returns true if (email,password) matches; you can also hard‑code an “admin” here. */
    public static boolean authenticate(String email, String password) {
        // you could special‑case an admin email:
        if ("admin@quizzo.com".equals(email) && "admin123".equals(password)) {
            return true;
        }
        // otherwise check in user collection
        return userService.login(email, password);
    }
}
