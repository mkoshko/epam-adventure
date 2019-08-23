package by.koshko.task03.main;

import by.koshko.task03.controller.MainMenu;

/**
 * Main.
 */
public final class Main {
    /**
     * Private constructor.
     */
    private Main() {
    }
    /**
     * Main.
     *
     * @param args args.
     */
    public static void main(final String[] args) {
        MainMenu menu = new MainMenu();
        menu.run();
    }
}
