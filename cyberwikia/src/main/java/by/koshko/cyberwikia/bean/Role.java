package by.koshko.cyberwikia.bean;

import java.io.Serializable;
import java.util.NoSuchElementException;

public enum Role implements Serializable {
    ADMINISTRATOR,
    EVENT_MODERATOR,
    USER;

    private static final long serialVersionUID = 1948680934600846753L;

    public static Role valueOf(final int role) {
        if (role <= values().length - 1 && role >= 0) {
            return values()[role];
        }
        throw new NoSuchElementException();
    }
}
