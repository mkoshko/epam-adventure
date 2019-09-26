package by.koshko.cyberwikia.bean;

public enum Role {
    ADMINISTRATOR(1),
    EVENT_MODERATOR(2),
    USER(3);

    private int userRole;

    Role(final int role) {
        userRole = role;
    }
}
