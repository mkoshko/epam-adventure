package by.koshko.task03.service;

import java.util.Locale;

/**
 * Service for changing locale settings.
 */
public final class LocaleManagementService {
    /**
     * Private constructor.
     */
    private LocaleManagementService() {
    }

    /**
     * Changes language.
     *
     * @param lang    language.
     * @param country country.
     */
    public static void changeLocale(final String lang, final String country) {
        Locale.setDefault(new Locale(lang, country));
    }

}
