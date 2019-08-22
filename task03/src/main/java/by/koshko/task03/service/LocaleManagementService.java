package by.koshko.task03.service;

import java.util.Locale;

public class LocaleManagementService {

    public static void changeLocale(final String locale) {
        Locale.setDefault(new Locale(locale));
    }

}
