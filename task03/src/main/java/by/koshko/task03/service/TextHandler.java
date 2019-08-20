package by.koshko.task03.service;

import by.koshko.task03.dao.DaoException;
import by.koshko.task03.dao.TextReader;
import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.TextComposite;

public class TextHandler {
    private static TextHandler instance = new TextHandler();
    private TextParser textParser = ParserHolder.getTextParser();
    private Component textComposite;
    private TextHandler() {
    }

    public static TextHandler access() {
        return instance;
    }

    public void makeComposite(final String path) throws ServiceException {
        if (path == null || path.isEmpty()) {
            throw new ServiceException("Path to file is empty.");
        }
        try {
            var currentText = TextReader.read(path);
            textComposite = new TextComposite();
            textParser.parse(currentText, textComposite);
        } catch (DaoException e) {
            throw new ServiceException(
                    String.format("Can't read text from '%s'.", path));
        }
    }

    public String showText() throws ServiceException {
        if (textComposite != null) {
            return textComposite.compose();
        } else {
            throw new ServiceException("Text is empty. Load text first.");
        }
    }

    public Component getTextComposite() {
        return textComposite;
    }
}
