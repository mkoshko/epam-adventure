package by.koshko.task04.service;

import by.koshko.task04.entity.Banks;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class BankDOMBuilder {
    private Banks banks;
    private DocumentBuilder builder;

    public BankDOMBuilder(final String schema) throws SAXException,
            ParserConfigurationException {
        DocumentBuilderFactory factory
                = DocumentBuilderFactory.newDefaultInstance();
        factory.setNamespaceAware(true);
        factory.setSchema(SchemaFactory.newDefaultInstance()
                .newSchema(new File(schema)));
        builder = factory.newDocumentBuilder();
    }
}
