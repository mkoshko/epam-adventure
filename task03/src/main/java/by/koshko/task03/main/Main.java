package by.koshko.task03.main;

import by.koshko.task03.controller.MainMenu;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class Main {
    public static void main(final String[] args) throws IOException {
        Locale.setDefault(new Locale("ru", "RU"));
        MainMenu menu = new MainMenu();
        menu.start();
//        var in = Main.class.getClassLoader().getResourceAsStream("config.properties");
//        Properties properties = new Properties();
//        properties.load(in);
//        System.out.println(properties.getProperty("path"));
    }
}
