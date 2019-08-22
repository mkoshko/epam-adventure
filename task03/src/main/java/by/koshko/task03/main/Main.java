package by.koshko.task03.main;

import by.koshko.task03.controller.MainMenu;
import by.koshko.task03.dao.DaoException;
import by.koshko.task03.dao.TextReader;
import by.koshko.task03.entity.ComponentType;
import by.koshko.task03.entity.TextComposite;
import by.koshko.task03.service.MonkeyService;
import by.koshko.task03.service.ParserHolder;
import by.koshko.task03.service.TextParser;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class Main {
    public static void main(final String[] args) throws IOException, DaoException {
//        Locale.setDefault(new Locale("ru", "RU"));
//        MainMenu menu = new MainMenu();
//        menu.start();

        var text = TextReader.read("data/text.txt");
        var textComposite = new TextComposite();
        TextParser parser = ParserHolder.getTextParser();
        parser.parse(text, textComposite);
        var list = MonkeyService.obtain(textComposite, ComponentType.SENTENCE);
        System.out.println(list.size());
    }
}
