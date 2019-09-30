package by.koshko.cyberwikia.bean;

import java.util.NoSuchElementException;

public enum Role {
    ADMINISTRATOR,
    EVENT_MODERATOR,
    USER;

    public static Role valueOf(final int role) {
        if (role <= values().length - 1 && role >= 0) {
            return values()[role];
        }
        throw new NoSuchElementException();
    }
}
