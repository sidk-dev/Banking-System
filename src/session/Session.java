package session;

import models.User;

public final class Session {
    private static User currentUser = null;
    private Session() {}

    public static void saveCurrentUser(User user) {
        Session.currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
