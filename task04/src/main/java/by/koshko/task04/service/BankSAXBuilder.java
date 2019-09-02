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

public class BankSAXBuilder {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger(getClass());
    /**
     * SAX event handler.
     */
    private BankHandler handler;
    /**
     * XML reader.
     */
    private XMLReader reader;

    public BankSAXBuilder(final String schemaFile) throws SAXException,
            ParserConfigurationException {
        logger.info("Configuring SAXBuilder...");
        SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
        factory.setNamespaceAware(true);
        logger.info("Setting schema '{}'", schemaFile);
        factory.setSchema(SchemaFactory.
                newDefaultInstance().newSchema(new File(schemaFile)));
        handler = new BankHandler();
        reader = factory.newSAXParser().getXMLReader();
        reader.setContentHandler(handler);
        logger.info("SAXBuilder configuration complete.");
    }

    public Banks buildBanks(final String xmlFilePath)
            throws BankBuilderException {
        try {
            logger.info("Parsing xml file {} is started.", xmlFilePath);
            reader.parse(xmlFilePath);
            logger.info("Parsing process is finished.");
            return handler.getBanks();
        } catch (IOException e) {
            throw new BankBuilderException(
                String.format("Error while reading from file %s", xmlFilePath));
        } catch (SAXException e) {
            throw new BankBuilderException(
                String.format("Error while parsing xml file %s", xmlFilePath));
        }
    }
}
