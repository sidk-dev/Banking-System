package models;

import session.Session;

public class User {
    private final String fullName;
    private final String email;
    private final String UUID;

    public User(String fullName, String email, String UUID) {
        this.fullName = fullName;
        this.email = email;
        this.UUID = UUID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUUID() {
        return UUID;
    }

    public String getEmail() {
        return email;
    }
}
