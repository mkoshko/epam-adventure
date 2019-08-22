package by.koshko.task03.controller;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static final CommandProvider PROVIDER = new CommandProvider();
    private Map<String, Command> commands = new HashMap<>();

    private CommandProvider() {
        commands.put("LOAD_TEXT", new LoadTextCommand());
        commands.put("SORT_BY_WORD_LENGTH", new SortByWordLength());
        commands.put("SORT_BY_WORD_NUMBER", new SortByWordNumber());
        commands.put("SORT_BY_CHAR", new SortByChars());
        commands.put("SORT_BY_SENTENCE_NUMBER", new SortBySentenceNumber());
    }

    public static CommandProvider getInstance() {
        return PROVIDER;
    }

    public Command getCommand(final String name) {
        try {
            return commands.get(name.toUpperCase());
        } catch (NullPointerException e) {
            return null;
        }
    }
}
