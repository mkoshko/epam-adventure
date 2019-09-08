package by.koshko.task04.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class BanksBuilderFactory {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger(getClass());
    private HashMap<String, BanksBuilder> cache = new HashMap<>();

    private enum ParserType {
        SAX,
        DOM,
        STAX
    }

    public BanksBuilder createBanksBuilder(final String parserType,
                                           final String schema)
            throws BankBuilderException {
        logger.info("Cache size: {}", cache.size());
        ParserType type = ParserType.valueOf(parserType.toUpperCase());
        String key = parserType + schema;
        switch (type) {
            case DOM:
                if (isInCache(key)) {
                    logger.info("Returning DOMBuilder from cache.");
                    return cache.get(key);
                } else {
                    logger.info("Caching DOMBuilder.");
                    BanksBuilder builder = new BankDOMBuilder(schema);
                    cache.put(key, builder);
                    return builder;
                }
            case SAX:
                if (isInCache(key)) {
                    logger.info("Returning SAXBuilder from cache.");
                    return cache.get(key);
                } else {
                    logger.info("Caching SAXBuilder.");
                    BanksBuilder builder = new BankSAXBuilder(schema);
                    cache.put(key, builder);
                    return builder;
                }
            case STAX:
                if (isInCache(key)) {
                    logger.info("Returning StAXBuilder from cache.");
                    return cache.get(key);
                } else {
                    logger.info("Caching StAXBuilder.");
                    BanksBuilder builder = new BankStAXBuilder();
                    cache.put(key, builder);
                    return builder;
                }
            default:
                return null;
        }
    }

    private boolean isInCache(final String key) {
        return cache.containsKey(key);
    }
}
