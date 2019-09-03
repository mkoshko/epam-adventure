package by.koshko.task04.service;

import by.koshko.task04.entity.Banks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public final class BankStAXBuilder implements BanksBuilder {
    private Logger logger = LogManager.getLogger(getClass());
    private InputStream in;
    private XMLStreamReader reader;

    @Override
    public Banks buildBanks(final String xmlFilePath)
            throws BankBuilderException {
        logger.info("Configuring StAX builder...");
        try {
            in = new FileInputStream(new File(xmlFilePath));
            XMLInputFactory factory = XMLInputFactory.newDefaultFactory();
            reader = factory.createXMLStreamReader(in);
        } catch (FileNotFoundException e) {
            throw new BankBuilderException(String.format("File '%s' not found",
                    xmlFilePath));
        } catch (XMLStreamException e) {
            throw new BankBuilderException("SAXParser configuration error.", e);
        }
        logger.info("StAX builder configuration complete.");
        return null;
    }
}
