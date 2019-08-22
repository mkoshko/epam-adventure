package by.koshko.task03.service;

import by.koshko.task03.dao.DaoException;
import by.koshko.task03.dao.TextReader;
import by.koshko.task03.entity.Component;
import by.koshko.task03.entity.TextComposite;

/**
 * Singleton class which load text from file and construct a
 * {@code TextComposite} tree.
 */
public final class TextHandler {
    /**
     * Instance of this class.
     */
    private static TextHandler instance = new TextHandler();
    /**
     * Parser for {@code TextComposite}.
     */
    private TextParser textParser = ParserHolder.getTextParser();
    /**
     * Component that is a root of a text.
     */
    private Component textComposite;

    /**
     * Private constructor.
     */
    private TextHandler() {
    }

    /**
     * Returns instance of this class.
     *
     * @return instance of this class.
     */
    public static TextHandler access() {
        return instance;
    }

    /**
     * Reads text from a specified path argument, and pass it into a
     * {@code TextParser}, which creates a tree with root at
     * {@code TextComposite} object.
     *
     * @param path Path to a file with a text.
     * @throws ServiceException if can't read text from a file.
     */
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

    /**
     * Returns a text composed from a {@code TextComposite}.
     *
     * @return string representation of a {@code TextComposite}.
     * @throws ServiceException if {@code TextComposite} is null.
     */
    public String showText() throws ServiceException {
        if (textComposite != null) {
            return textComposite.compose();
        } else {
            throw new ServiceException("Text is empty. Load text first.");
        }
    }

    /**
     * Returns {@code TextComposite}.
     *
     * @return {@code TextComposite} object.
     */
    public Component getTextComposite() {
        return textComposite;
    }
}
