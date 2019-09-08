package by.koshko.task04.service;

import by.koshko.task04.entity.Banks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public final class BankDOMBuilder implements BanksBuilder {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger(getClass());
    /**
     * Object for parsing XML.
     */
    private DocumentBuilder builder;

    /**
     * Constructs {@link #builder}.
     *
     * @param pathToSchema Path to schema file.
     * @throws BankBuilderException Throws if path to schema file is null or
     *                              string argument is empty, or if some errors
     *                              occurred while {@link DocumentBuilder}
     *                              configuration process.
     */
    public BankDOMBuilder(final String pathToSchema)
            throws BankBuilderException {
        if (pathToSchema == null || pathToSchema.isEmpty()) {
            throw new BankBuilderException("Path to schema is empty.");
        }
        try {
            logger.debug("Configuring DOM builder...");
            DocumentBuilderFactory factory
                    = DocumentBuilderFactory.newDefaultInstance();
            factory.setNamespaceAware(true);
            logger.info("Setting schema '{}'", pathToSchema);
            factory.setSchema(SchemaFactory.newDefaultInstance()
                    .newSchema(new File(pathToSchema)));
            builder = factory.newDocumentBuilder();
            logger.info("DOM builder configuration complete.");
        } catch (ParserConfigurationException e) {
            throw new BankBuilderException("DOMParser configuration error.", e);
        } catch (SAXException e) {
            throw new BankBuilderException("DOMParser creation error.", e);
        }

    }

    /**
     * Parses xml file and creates {@link Banks} object.
     *
     * @param xmlFilePath Path to xml file.
     * @return Constructed after parsing xml file {@link Banks} object.
     * @throws BankBuilderException Throws if path to xml file is empty, or
     *                              some errors occurred while paring.
     */
    public Banks buildBanks(final String xmlFilePath)
            throws BankBuilderException {
        logger.info("Path to xml file: '{}'", xmlFilePath);
        if (xmlFilePath == null || xmlFilePath.isEmpty()) {
            throw new BankBuilderException("Path to xml file is null or empty.");
        }
        try {
            Document document = builder.parse(new File(xmlFilePath));
            return BankDOMHandler.build(document);
        } catch (SAXException newE) {
            throw new BankBuilderException(
                    String.format("Error while parsing xml file '%s'.",
                            xmlFilePath));
        } catch (IOException newE) {
            throw new BankBuilderException(
                    String.format("Error while reading from file '%s'.",
                            xmlFilePath));
        }
    }
}
