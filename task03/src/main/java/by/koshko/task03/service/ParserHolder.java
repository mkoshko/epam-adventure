package by.koshko.task03.service;

/**
 * Class creates and holds {@code TextParser}. Contain method for obtain
 * {@code TextParser} instance.
 */
final class ParserHolder {
    /**
     * Text parser.
     */
    private static TextParser textParser;

    /**
     * Private constructor.
     */
    private ParserHolder() {
    }

    /**
     * Returns {@code TextParser} object.
     *
     * @return text parser.
     */
    static TextParser getTextParser() {
        if (textParser != null) {
            return textParser;
        } else {
            initParsers();
            return textParser;
        }
    }

    /**
     * Creates and constructs parsers in chain.
     */
    private static void initParsers() {
        textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        SymbolParser symbolParser = new SymbolParser();
        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(symbolParser);
    }
}
