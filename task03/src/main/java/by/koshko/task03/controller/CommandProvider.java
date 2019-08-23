package by.koshko.task03.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Class holds all available commands that can be execute.
 */
public final class CommandProvider {
    /**
     * Instance of this class.
     */
    private static final CommandProvider PROVIDER = new CommandProvider();
    /**
     * Hash map that holds commands.
     */
    private Map<String, Command> commands = new HashMap<>();

    /**
     * Constructor that adds commands to hash map.
     */
    private CommandProvider() {
        commands.put("LOAD_TEXT", new LoadTextCommand());
        commands.put("SORT_BY_WORD_LENGTH", new SortByWordLength());
        commands.put("SORT_BY_WORD_NUMBER", new SortByWordNumber());
        commands.put("SORT_BY_CHAR", new SortByChars());
        commands.put("SORT_BY_SENTENCE_NUMBER", new SortBySentenceNumber());
        commands.put("CHANGE_LANG", new ChangeLanguageCommand());
    }

    /**
     * Returns instance of this class.
     *
     * @return instance of this class.
     */
    public static CommandProvider getInstance() {
        return PROVIDER;
    }

    /**
     * Returns command object if that exists in {@link #commands} hash map.
     *
     * @param name Name of the command.
     * @return {@code Command} object or {@code Null} if such object doesn't
     * exists.
     */
    public Command getCommand(final String name) {
        try {
            return commands.get(name.toUpperCase());
        } catch (NullPointerException e) {
            return null;
        }
    }
}
