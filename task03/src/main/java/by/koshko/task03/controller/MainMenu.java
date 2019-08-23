package by.koshko.task03.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Main menu.
 */
public final class MainMenu {
    /**
     * Properties.
     */
    private Properties properties = new Properties();

    {
        loadProperties();
    }

    /**
     * Controller that will handle the requests.
     */
    private Controller controller = Controller.getInstance();
    /**
     * Resource for menu items.
     */
    private ResourceBundle rb = ResourceBundle.getBundle("mainMenu");
    /**
     * Scanner for user input.
     */
    private Scanner in = new Scanner(System.in);
    /**
     * Menu for change language.
     */
    private Menu changeLangMenu = new Menu("menu.lang.title");

    {
        changeLangMenu.add(new MenuItem("menu.rus", "ru_RU", null, false));
        changeLangMenu.add(new MenuItem("menu.eng", "en_US", null, false));
    }

    /**
     * Main menu.
     */
    private Menu mainMenu = new Menu("menu.main.title");

    {
        mainMenu.add(new MenuItem("menu.load", "LOAD_TEXT "
                + properties.getProperty("path"), null, false));
        mainMenu.add(new MenuItem("menu.sortSentenceNumber",
                "SORT_BY_SENTENCE_NUMBER ", null, false));
        mainMenu.add(new MenuItem("menu.sortWordLength",
                "SORT_BY_WORD_LENGTH ", null, false));
        mainMenu.add(new MenuItem("menu.sortWordNumber",
                "SORT_BY_WORD_NUMBER ", null, false));
        mainMenu.add(new MenuItem("menu.sortCharNumber",
                "SORT_BY_CHAR ", null, true));
        mainMenu.add(new MenuItem("menu.changeLang",
                "CHANGE_LANG", changeLangMenu, false));
        mainMenu.add(new MenuItem("menu.exit",
                "EXIT", null, false));
    }

    /**
     * Start point of a menu.
     */
    public void run() {
        var active = true;
        while (active) {
            var request = mainMenu.start();
            if (request.equals("EXIT")) {
                active = false;
            } else {
                System.out.println(controller.executeTask(request));
            }
        }
    }

    /**
     * Class describes menu.
     */
    class Menu {
        /**
         * Title of a menu.
         */
        private String title;
        /**
         * Started value for menu items numeration.
         */
        private int menuNumber = 1;
        /**
         * List of menu items that this menu object have.
         */
        private Map<String, MenuItem> menuItems = new LinkedHashMap<>();

        /**
         * Creates menu with specified title.
         *
         * @param newTitle title of a the menu.
         */
        Menu(final String newTitle) {
            title = newTitle;
        }

        /**
         * Adds {@link MenuItem} to {@link #menuItems} map.
         *
         * @param item to be added.
         */
        public void add(final MenuItem item) {
            menuItems.put(String.valueOf(menuNumber++), item);
        }

        /**
         * Prints menu and read user input.
         *
         * @return response after command execution depends on selected
         * menu item.
         */
        public String start() {
            print();
            var selected = in.nextLine();
            return menuItems.get(selected).select();
        }

        /**
         * Prints menu to a console.
         */
        private void print() {
            rb = ResourceBundle.getBundle("mainMenu");
            System.out.println(rb.getString(title));
            menuItems.forEach((k, v) -> System.out.printf("%s. %s%n", k, v));
        }
    }

    /**
     * Class describes menu item.
     */
    class MenuItem {
        /**
         * Menu item.
         */
        private String field;
        /**
         * Request that returns if this menu item was selected.
         */
        private String request;
        /**
         * Submenu.
         */
        private Menu subMenu;
        /**
         * Indicates if additional arguments must be obtained from user.
         */
        private boolean arg = false;

        /**
         * Constructs the menu item object.
         *
         * @param field0   menu item name.
         * @param request0 request to controller.
         * @param subMenu0 submenu.
         * @param arg0     .
         */
        MenuItem(final String field0,
                 final String request0,
                 final Menu subMenu0,
                 final boolean arg0) {
            field = field0;
            request = request0;
            subMenu = subMenu0;
            arg = arg0;
        }

        /**
         * Checks if this menu item have submenu, or need additional input from
         * user, and returns request string.
         *
         * @return constructed request string.
         */
        public String select() {
            if (subMenu == null) {
                if (arg) {
                    return request + getArg();
                } else {
                    return request;
                }
            } else {
                return String.format("%s %s", request, subMenu.start());
            }
        }

        /**
         * Reads additional argument from standard input if {@link #arg}
         * is {@code true}.
         * @return string argument.
         */
        private String getArg() {
            return in.nextLine();
        }

        @Override
        public String toString() {
            return rb.getString(field);
        }
    }

    /**
     * Loads property file for this class.
     */
    private void loadProperties() {
        try (InputStream in = getClass()
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (in != null) {
                properties.load(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
