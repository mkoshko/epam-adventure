package by.koshko.task03.controller;

import java.util.*;

public final class Runner {
    private ResourceBundle rb
            = ResourceBundle.getBundle("mainMenu", Locale.getDefault());
    private static Controller controller = new Controller();
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, String> menuItems = new HashMap<>();
    Properties properties = new Properties();
    static {
        menuItems.put("1", "LOAD_TEXT data/text.txt");
        menuItems.put("2", "SORT_BY_WORD_LENGTH ");
        menuItems.put("3", "SORT_BY_WORD_NUMBER ");
        menuItems.put("4", "SORT_BY_SENTENCE_NUMBER ");
        menuItems.put("5", "SORT_BY_CHAR ");
    }

    public static void start() {
        var running = true;
        while (running) {
            printMenu();
            var menu = scanner.nextLine();
            var response = controller.executeTask(menuItems.get(menu));
            System.out.println(response);
        }
    }

    private static void printMenu() {
        System.out.println("1.LoadText");
        System.out.println("2.Sort by word length");
        System.out.println("3.Sort by word number");
        System.out.println("4.Sort by sentence number");
        System.out.println("5.Sort by char number");
        System.out.println("6.Exit");
    }
}
