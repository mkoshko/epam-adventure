package by.koshko.task01.main;

import by.koshko.task01.entity.Plan;
import by.koshko.task01.service.PlanCreatorService;
import by.koshko.task01.service.PlanManagementService;
import by.koshko.task01.service.exception.ServiceException;
import by.koshko.task01.service.factory.ServiceFactory;
import by.koshko.task01.service.specification.FindBySpecification;
import by.koshko.task01.service.specification.FindSpecificationBuilder;
import by.koshko.task01.service.specification.SortBySpecification;
import by.koshko.task01.service.specification.SortSpecificationBuilder;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Scanner;

import static by.koshko.task01.service.specification.FindSpecificationBuilder.SearchType.FindByID;
import static by.koshko.task01.service.specification.FindSpecificationBuilder.SearchType.FindByName;
import static by.koshko.task01.service.specification.FindSpecificationBuilder.SearchType.NameStartWith;
import static by.koshko.task01.service.specification.FindSpecificationBuilder.SearchType.FindByCostAbroad;
import static by.koshko.task01.service.specification.FindSpecificationBuilder.SearchType.FindByCostON;
import static by.koshko.task01.service.specification.FindSpecificationBuilder.SearchType.FindByCostWN;
import static by.koshko.task01.service.specification.FindSpecificationBuilder.SearchType.FindByCostMegabyte;
import static by.koshko.task01.service.specification.FindSpecificationBuilder.SearchType.FindByCostSms;
import static by.koshko.task01.service.specification.SortSpecificationBuilder.SortBy;

@SuppressWarnings("ALL")
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static final String GREEN = "\033[0;32m";
    private static final String RED = "\033[0;31m";
    public static final String RESET = "\033[0m";
    private static final String ERR = " is a number? ¯\\_(ツ)_/¯.";
    private static final String CREATE = "Create plan from file.";
    private static final String FIND = "Find plans.";
    private static final String SORT = "Sort plans.";
    private static final String REMOVE = "Remove plan.";
    private static final String REPORT = "Write report.";
    private static final String ALL = "All avalable plans.";
    private static final String CUSTOMERS = "Total number of customers.";
    private static final String EXIT = RED + "Quit" + RESET;
    private static List<Plan> list;
    private static PlanManagementService managementService
            = ServiceFactory.getInstance().getPlanManagementService();

    public static void main(final String... args) throws InterruptedException {
        mainMenu();
    }

    //print
    private static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    private static void printErr(String msg) {
        println(RED + msg + RESET);
    }

    private static void printSuccess(String msg) {
        println(GREEN + msg + RESET);
    }

    private static void print(String s) {
        System.out.print(s);
    }

    private static void println(String s) {
        System.out.println(s);
    }

    private static void printMenu(String title, String... items) {
        String header = "//===========" + GREEN + title + RESET + "==========//";
        System.out.println(header);
        for (int i = 0; items != null && i < items.length; i++) {
            printf("%-5s", "[" + (i + 1) + "] ");
            println(items[i]);
        }
        print("//");
        for (int i = 0; i < header.length() - (RED.length() + RESET.length() + 4); i++) {
            print("=");
        }
        println("//");
        print("[-->]");
    }

    private static void printMenu(String title, Map<Integer, String> items) {
        String header = "//===========" + GREEN + title + RESET + "==========//";
        System.out.println(header);
        for (int i = 1; items != null && i < items.size() + 1; i++) {
            printf("%-5s", "[" + (i) + "] ");
            println(items.get(i));
        }
        print("//");
        for (int i = 0; i < header.length() - (RED.length() + RESET.length() + 4); i++) {
            print("=");
        }
        println("//");
        print("[-->]");
    }

    //edit
    private static void editItem(int pos, Map<Integer, String> items, String... args) {

        String postfix = args.length == 1
                ? " [" + GREEN + args[0] + RESET + "]"
                : " [min:" + GREEN + args[0] + RESET + ", "
                + "max:" + GREEN + args[1] + RESET + "]";
        items.replace(pos, RED + items.get(pos) + RESET + postfix);
    }

    //check
    private static int readInt() {
        while (true) {
            String s = scanner.next();
            if (s.matches("\\d+")) {
                return Integer.valueOf(s);
            } else {
                println(RED + "[" + s + "]" + ERR + RESET);
                return 0;
            }
        }

    }

    private static double readDouble() {
        while (true) {
            String s = scanner.next();
            if (s.matches("\\d+(\\.\\d+)?")) {
                return Double.valueOf(s);
            } else {
                System.out.println(RED + "[" + s + "]" + ERR + RESET);
                return 0;
            }
        }
    }

    private static String[] readDArgs() {
        scanner.nextLine();
        boolean correct = true;
        while (true) {
            correct = true;
            printSuccess("Enter exact value or in range [min max]");
            String[] args = scanner.nextLine().trim().split("\\s+");
            if (args.length != 0 && args.length <= 2) {
                for (String s : args) {
                    if (!s.matches("^\\d+(\\.\\d+)?")) {
                        printErr("Illegal arguments");
                        printErr("Try again.");
                        correct = false;
                        break;
                    }
                }
                if (correct) {
                    return args;
                }
            }
        }
    }

    private static int[] readIntArgs(final String hint) {
        scanner.nextLine();
        boolean correct = true;
        while (true) {
            correct = true;
            printSuccess(hint);
            String[] args = scanner.nextLine().trim().split("\\s+");
            int[] ints = new int[args.length];
            if (args.length != 0 && args.length <= 2) {
                int i = 0;
                for (String s : args) {
                    if (!s.matches("\\d+")) {
                        printErr("Illegal arguments");
                        printErr("Try again.");
                        correct = false;
                        break;
                    }
                    ints[i] = Integer.valueOf(s);
                    i++;
                }
                if (correct) {
                    return ints;
                }
            }
        }
    }

    private static String readString() {
        scanner.nextLine();
        return scanner.nextLine();
    }

    //menu
    private static void mainMenu() {
        boolean running = true;
        while (running) {
            printMenu("Main menu", CREATE, FIND, SORT, REMOVE, REPORT, ALL, CUSTOMERS, EXIT);
            int a = readInt();
            switch (a) {
                case 1:
                    new CreatePlanMenu().start();
                    break;
                case 2:
                    new FindPlan().start();
                    break;
                case 3:
                    new SortPlans().start();
                    break;
                case 4:
                    new DeletePlan().start();
                    break;
                case 5:
                    new WriteReport().start();
                    break;
                case 6:
                    new AllPlans().start();
                    break;
                case 7:
                    new Customers().start();
                    break;
                case 8:
                    running = false;
                    break;
            }
        }
    }

    static class CreatePlanMenu {
        PlanCreatorService service = ServiceFactory.getInstance().getCreatorService();

        public void start() {
            boolean running = true;
            while (running) {
                printMenu("Create menu", "Enter path to a file",
                        RED + "Back" + RESET);
                int menu = readInt();
                switch (menu) {
                    case 1:
                        printSuccess("Enter path to file");
                        try {
                            service.create(readString());
                            printSuccess("Done.");
                            running = false;
                            break;
                        } catch (ServiceException e) {
                            printErr(e.getMessage());
                            break;
                        }
                    case 2:
                        running = false;
                        break;
                }
            }
        }
    }

    static class FindPlan {
        private String tips = "Tips and tricks: numbers can"
                + " be written in the range [min max]";
        private String s1, s2, s3, s4, s5, s6, s7, s8, find, back;
        private LinkedHashMap<Integer, String> items = new LinkedHashMap<>();
        FindSpecificationBuilder builder = new FindSpecificationBuilder();
        FindBySpecification<Plan> spec;

        {
            s1 = "Find by ID";
            s2 = "Find by name";
            s3 = "Find by name start with";
            s4 = "Find by cost within network";
            s5 = "Find by cost to other networks";
            s6 = "Find by cost abroad";
            s7 = "Find by cost sms";
            s8 = "Find by cost megabyte";
            find = GREEN + "Find" + RESET;
            back = RED + "Back" + RESET;
            items.put(1, s1);
            items.put(2, s2);
            items.put(3, s3);
            items.put(4, s4);
            items.put(5, s5);
            items.put(6, s6);
            items.put(7, s7);
            items.put(8, s8);
            items.put(9, find);
            items.put(10, back);
        }

        public void start() {
            boolean running = true;
            while (running) {
                println(GREEN + tips + RESET);
                printMenu("Find menu", items);
                int i = readInt();
                try {
                    switch (i) {
                        case 1:
                            String[] id = readDArgs();
                            builder.compose(FindByID, id);
                            editItem(i, items, id);
                            break;
                        case 2:
                            printSuccess("Enter plan name");
                            String name = readString();
                            builder.compose(FindByName, name);
                            editItem(i, items, name);
                            break;
                        case 3:
                            printSuccess("Enter plan prefix");
                            String start = readString();
                            builder.compose(NameStartWith, start);
                            editItem(i, items, start);
                            break;
                        case 4:
                            String[] costWN = readDArgs();
                            builder.compose(FindByCostWN, costWN);
                            editItem(i, items, costWN);
                            break;
                        case 5:
                            String[] costON = readDArgs();
                            builder.compose(FindByCostON, costON);
                            editItem(i, items, costON);
                            break;
                        case 6:
                            String[] costAbr = readDArgs();
                            builder.compose(FindByCostAbroad, costAbr);
                            editItem(i, items, costAbr);
                            break;
                        case 7:
                            String[] costSMS = readDArgs();
                            builder.compose(FindByCostSms, costSMS);
                            editItem(i, items, costSMS);
                            break;
                        case 8:
                            String[] costMB = readDArgs();
                            builder.compose(FindByCostMegabyte, costMB);
                            editItem(i, items, costMB);
                            break;
                        case 9:
                            printSuccess("//====================RESULT====================//");
                            spec = builder.build();
                            managementService.queryToPlanRepository(spec).forEach(System.out::println);
                            printSuccess("//====================RESULT====================//");
                        case 10:
                            running = false;
                            break;
                    }
                } catch (ServiceException e) {
                    printErr(e.getMessage());
                }
            }
        }
    }

    static class SortPlans {
        private String hint = "Choose 1 or 2 parameters";
        private String s1, s2, s3, s4, s5, s6, s7, sort, back;
        private LinkedHashMap<Integer, String> items = new LinkedHashMap<>();
        SortSpecificationBuilder builder = new SortSpecificationBuilder();
        SortBySpecification<Plan> spec;
        private List<SortBy> sortByList = new ArrayList<>();

        {
            s1 = "Sort by ID";
            s2 = "Sort by name";
            s3 = "Sort by cost within network";
            s4 = "Sort by cost to other networks";
            s5 = "Sort by cost abroad";
            s6 = "Sort by cost sms";
            s7 = "Sort by cost megabyte";
            sort = GREEN + "Sort" + RESET;
            back = RED + "Back" + RESET;
            items.put(1, s1);
            items.put(2, s2);
            items.put(3, s3);
            items.put(4, s4);
            items.put(5, s5);
            items.put(6, s6);
            items.put(7, s7);
            items.put(8, sort);
            items.put(9, back);
        }

        public void start() {
            boolean running = true;
            int selected = 0;
            while (running) {
                printSuccess("Can be choosen maximum 2 parameters.");
                printMenu("Sort menu", items);
                int menu = readInt();
                if (menu >= 8 || selected < 2) {
                    switch (menu) {
                        case 1:
                            sortByList.add(SortBy.Id);
                            selected++;
                            editItem(menu, selected);
                            break;
                        case 2:
                            sortByList.add(SortBy.Name);
                            selected++;
                            editItem(menu, selected);
                            break;
                        case 3:
                            sortByList.add(SortBy.CostIN);
                            selected++;
                            editItem(menu, selected);
                            break;
                        case 4:
                            sortByList.add(SortBy.CostON);
                            selected++;
                            editItem(menu, selected);
                            break;
                        case 5:
                            sortByList.add(SortBy.CostAbroad);
                            selected++;
                            editItem(menu, selected);
                            break;
                        case 6:
                            sortByList.add(SortBy.CostSMS);
                            selected++;
                            editItem(menu, selected);
                            break;
                        case 7:
                            sortByList.add(SortBy.CostMegabyte);
                            selected++;
                            editItem(menu, selected);
                            break;
                        case 8:
                            try {
                                if (sortByList.size() == 0) {
                                    printErr("No sorting option selected.");
                                } else if (sortByList.size() == 1) {
                                    spec = builder.build(sortByList.get(0));
                                } else {
                                    spec = builder.build(sortByList.get(0), sortByList.get(1));
                                }
                                managementService.queryToPlanRepository(spec);
                                printSuccess("Sorting complete");
                                running = false;
                                break;
                            } catch (ServiceException e) {
                                printErr(e.getMessage());
                            }
                        case 9:
                            running = false;
                            break;

                    }
                } else {
                    printErr("All sort parameters already selected");
                }

            }
        }

        private void editItem(int pos, int num) {
            if (num == 1) {
                items.replace(pos, RED + items.get(pos) + RESET + GREEN + " [main]" + RESET);
            } else {
                items.replace(pos, RED + items.get(pos) + RESET + GREEN + " [secondary]" + RESET);
            }
        }
    }

    static class DeletePlan {
        private Map<Integer, String> items = new LinkedHashMap<>();
        private String hint = "Enter exact index or in range [From(inclusive) To(exclusive)]";

        {
            items.put(1, "Remove by index");
            items.put(2, "Remove all queried");
            items.put(3, RED + "Back" + RESET);
        }

        public void start() {
            boolean running = true;
            while (running) {
                printMenu("Detele plans", items);
                int item = readInt();
                switch (item) {
                    case 1:
                        int[] args = readIntArgs(hint);
                        switch (args.length) {
                            case 1:
                                try {
                                    boolean res = managementService.remove(args[0] - 1);
                                    running = false;
                                    if (res) {
                                        printSuccess("Plans was successfuly removed");
                                    } else {
                                        printErr("Plans wasn't removed");
                                    }
                                    break;
                                } catch (ServiceException e) {
                                    printErr(e.getMessage());
                                }
                                break;
                            case 2:
                                try {
                                    boolean res = managementService.remove(args[0] - 1, args[1] - 1);
                                    running = false;
                                    if (res) {
                                        printSuccess("Plans was successfuly removed");
                                    } else {
                                        printErr("Plans wasn't removed");
                                    }
                                    break;
                                } catch (ServiceException e) {
                                    printErr(e.getMessage());
                                }
                        }
                        break;
                    case 2:
                        try {
                            boolean res = managementService.remove();
                            running = false;
                            if (res) {
                                printSuccess("Plans was successfuly removed");
                            } else {
                                printErr("Plans wasn't removed");
                            }
                            break;
                        } catch (ServiceException e) {
                            printErr(e.getMessage());
                        }
                        break;
                    case 3:
                        running = false;
                        break;
                }

            }
        }
    }

    static class WriteReport {
        public void start() {
            boolean running = true;
            while (running) {
                printMenu("Make report", "Specify file path", RED + "Back" + RESET);
                int menu = readInt();
                switch (menu) {
                    case 1:
                        printSuccess("Enter path to a file");
                        String path = readString();
                        try {
                            managementService.report(path);
                            printSuccess("Done.");
                            running = false;
                            break;
                        } catch (ServiceException e) {
                            printErr(e.getMessage());
                        }
                    case 2:
                        running = false;
                        break;
                }
            }
        }
    }

    static class AllPlans {
        public void start() {
            System.out.println("//==================" + GREEN + "All available plans" + RESET + "==================//");
            try {
                managementService.receiveAllAvailablePlans().forEach(System.out::println);
            } catch (ServiceException e) {
                printErr(e.getMessage());
            }
            System.out.println("//==================" + GREEN + "All available plans" + RESET + "==================//");
        }
    }

    static class Customers {
        public void start() {
            println("Total number of customers - " + managementService.getTotalNumberOfCustomers());
        }
    }
}
