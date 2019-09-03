package by.koshko.task04.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BanksBuilderFactory {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger(getClass());
    private enum ParserType {
        SAX,
        DOM,
        STAX
    }
    public BanksBuilder createBanksBuilder(final String parserType,
                                           final String schema) {
        ParserType type = ParserType.valueOf(parserType.toUpperCase());
        try {
            switch (type) {
                case DOM:
                    return new BankDOMBuilder(schema);
                case SAX:
                    return new BankSAXBuilder(schema);
                case STAX:
                default:
                    return null;
            }
        } catch (BankBuilderException e) {
            logger.error("Can't create BanksBuilder. {}", e.getMessage());
            return null;
        }
    }
}
