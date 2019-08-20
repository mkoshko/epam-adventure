package by.koshko.task03.controller;

import java.util.Scanner;

public class Runner {
    private static Controller controller = new Controller();
    private static Scanner scanner = new Scanner(System.in);
    public static void start() {
        var running  = true;
        while (running) {
            printMenu();
            var menu = scanner.nextLine();
            String response = "";
            switch (menu) {
                case "1":
                    response = controller.executeTask("LOAD_TEXT data/text.txt");
                    break;
                case "2":
                    response = controller.executeTask("SORT_BY_WORD_LENGTH ");
                    break;
                case "3":
                    response = controller.executeTask("SORT_BY_WORD_NUMBER ");
                    break;
                case "4":
                    response = controller.executeTask("SORT_BY_SENTENCE_NUMBER ");
                    break;
                case "5":
                    System.out.println("Enter a char ");
                    var ch = scanner.nextLine();
                    response = controller.executeTask("SORT_BY_CHAR " + ch);
                    break;
                case "6":
                    running = false;
                    break;
                default:
                    response = "Missed!";
                    break;
            }
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
