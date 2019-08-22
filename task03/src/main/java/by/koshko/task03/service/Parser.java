package by.koshko.task03.service;

import by.koshko.task03.entity.Component;

/**
 * Parser which follows chain of responsibility pattern. Parse text and creates
 * a tree structure using {@code Component} classes.
 *
 * @see by.koshko.task03.entity.TextComposite
 * @see by.koshko.task03.entity.ParagraphComposite
 * @see by.koshko.task03.entity.SentenceComposite
 * @see by.koshko.task03.entity.LexemeComposite
 * @see by.koshko.task03.entity.WordComposite
 * @see by.koshko.task03.entity.MarkComposite
 * @see by.koshko.task03.entity.Symbol
 */
public interface Parser {
    /**
     * Sets the next parser in chain.
     *
     * @param nextParser next parser in chain.
     */
    void setNext(Parser nextParser);

    /**
     * Parse text argument, and put them into component argument.
     *
     * @param text string to be parsed.
     * @param component in which parsed parts will be stored.
     */
    void parse(String text, Component component);
}
