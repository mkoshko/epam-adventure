package by.koshko.task03.main;


import by.koshko.task03.dao.DaoException;
import by.koshko.task03.dao.TextReader;
import by.koshko.task03.entity.ComponentType;
import by.koshko.task03.entity.TextComposite;
import by.koshko.task03.service.*;

public class Main {
    public static void main(final String[] args) throws DaoException {
        String text = TextReader.read("data/text.txt");
        //===============================//
        TextParser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        SymbolParser symbolParser = new SymbolParser();
        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(symbolParser);
        //===============================//
        TextComposite textComposite = new TextComposite();
        textParser.parse(text, textComposite);
        System.out.println(textComposite.compose());
        //===============================//
//        SortingService sortingService = new SortingService(textComposite);
        System.out.println();
//        sortingService.sort2();
//        System.out.println(textComposite.compose());
        var list = MonkeyService.obtain(textComposite, ComponentType.WORD);
        list.forEach(component -> System.out.println(component.compose()));
    }
}
