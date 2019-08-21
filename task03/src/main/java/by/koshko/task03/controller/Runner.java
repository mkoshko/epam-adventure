package by.koshko.task03.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Runner {
    private static Controller controller = new Controller();
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, String> menuItems = new HashMap<>(){
        {
            this.put("1", "LOAD_TEXT data/text.txt");
            this.put("2", "SORT_BY_WORD_LENGTH ");
            this.put("3", "SORT_BY_WORD_NUMBER ");
            this.put("4", "SORT_BY_SENTENCE_NUMBER ");
            this.put("5", "SORT_BY_CHAR  ");
        }
    };
    public static void start() {
        var running  = true;
        while (running) {
            printMenu();
            var menu = scanner.nextLine();
            String response = "";
            response = controller.executeTask(menuItems.get(menu));
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
