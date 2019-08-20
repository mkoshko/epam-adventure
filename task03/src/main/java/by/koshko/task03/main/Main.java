package by.koshko.task03.main;


import by.koshko.task03.dao.DaoException;
import by.koshko.task03.dao.TextReader;
import by.koshko.task03.entity.TextComposite;
import by.koshko.task03.service.*;

public class Main {
    public static void main(final String[] args) throws DaoException, ServiceException {
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
        SortingService sortingService = new SortingService(textComposite);
        sortingService.sortBySentencesNumber();
        System.out.println();
        System.out.println(textComposite.compose());
    }
}
