package by.koshko.task03.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public final class MainMenu {
    private static Controller controller = Controller.getInstance();
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, String> request = new HashMap<>();
    private static Map<String, String> menuItems = new LinkedHashMap<>();
    private static final String PROP_FILE = "config.properties";
    private static final String RESOURCE = "mainMenu";
    private Properties properties = new Properties();
    private ResourceBundle resource
            = ResourceBundle.getBundle("mainMenu");
    private final int menuSize = 6;

    public MainMenu() {
        loadProperties();
        request.put("1", "LOAD_TEXT " + properties.getProperty("path"));
        request.put("2", "SORT_BY_SENTENCE_NUMBER ");
        request.put("3", "SORT_BY_WORD_LENGTH ");
        request.put("4", "SORT_BY_WORD_NUMBER ");
        request.put("5", "SORT_BY_CHAR ");
        loadMenu();
    }

    public void start() {
        var running = true;
        while (running) {
            printMenu();
            var menu = scanner.nextLine();
            var response = controller.executeTask(request.get(menu));
            System.out.println(response);
        }
    }

    private void printMenu() {
        loadMenu();
        for (int i = 1; i <= menuSize; i++) {
            System.out.printf("%d. %s%n", i, menuItems.get(String.valueOf(i)));
        }
    }

    private void loadMenu() {
        menuItems.clear();
        resource = ResourceBundle.getBundle(RESOURCE, Locale.getDefault());
        menuItems.put("1", resource.getString("menu.load"));
        menuItems.put("2", resource.getString("menu.sort1"));
        menuItems.put("3", resource.getString("menu.sort2"));
        menuItems.put("4", resource.getString("menu.sort3"));
        menuItems.put("5", resource.getString("menu.sort4"));
        menuItems.put("6", resource.getString("menu.exit"));
    }

    private void loadProperties() {
        try (InputStream in = getClass()
                .getClassLoader()
                .getResourceAsStream(PROP_FILE)) {
            if (in != null) {
                properties.load(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
