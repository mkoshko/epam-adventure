package by.koshko.task04.service;

import by.koshko.task04.entity.Banks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public final class BankSAXBuilder implements BanksBuilder {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger(getClass());
    /**
     * SAX event handler.
     */
    private BankHandler handler;
    /**
     * XML reader.
     */
    private XMLReader reader;

    /**
     * Construct {@link XMLReader} using {@link javax.xml.parsers.SAXParser},
     * and setting {@link javax.xml.validation.Schema} for xml validation.
     *
     * @param pathToSchema Path to schema for xml validation.
     * @throws BankBuilderException Throws if string argument is null or empty,
     *                              or if some errors occurred while SAXParser
     *                              configuration.
     */
    public BankSAXBuilder(final String pathToSchema) throws BankBuilderException {
        if (pathToSchema == null || pathToSchema.isEmpty()) {
            throw new BankBuilderException("Empty path to schema.");
        }
        try {
            logger.info("Configuring SAXBuilder...");
            SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
            factory.setNamespaceAware(true);
            logger.info("Setting schema '{}'", pathToSchema);
            factory.setSchema(SchemaFactory.
                    newDefaultInstance().newSchema(new File(pathToSchema)));
            handler = new BankHandler();
            reader = factory.newSAXParser().getXMLReader();
            reader.setContentHandler(handler);
            logger.info("SAXBuilder configuration complete.");
        } catch (SAXException e) {
            throw new BankBuilderException("SAXParser creation error.", e);
        } catch (ParserConfigurationException e) {
            throw new BankBuilderException("SAXParser configuration error.", e);
        }

    }

    /**
     * Starts parse process of given xml file.
     *
     * @param xmlFilePath path to xml file.
     * @return {@link Banks} object built from xml file.
     * @throws BankBuilderException Throws if file can't be read, or some error
     *                              occurred while parsing.
     */
    public Banks buildBanks(final String xmlFilePath)
            throws BankBuilderException {
        if (xmlFilePath == null || xmlFilePath.isEmpty()) {
            throw new BankBuilderException("Path to xml file is empty.");
        }
        try {
            logger.info("Parsing xml file '{}' is started.", xmlFilePath);
            reader.parse(xmlFilePath);
            Banks banks = handler.getBanks();
            logger.info("Parsing process is finished.");
            return banks;
        } catch (IOException e) {
            throw new BankBuilderException(
                String.format("Error while reading from file %s", xmlFilePath));
        } catch (SAXException e) {
            throw new BankBuilderException(
                String.format("Error while parsing xml file %s", xmlFilePath));
        }
    }
}
