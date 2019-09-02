package by.koshko.task04.main;

import by.koshko.task04.service.BanksHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(final String[] args) throws SAXException, ParserConfigurationException, IOException, DatatypeConfigurationException {

        BanksHandler handler = new BanksHandler();
        String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(constant);
        Schema schema = schemaFactory.newSchema(new File("data/banks.xsd"));
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(false);
        factory.setSchema(schema);
        XMLReader reader = factory.newSAXParser().getXMLReader();
        reader.setContentHandler(handler);
        reader.parse("data/banks.xml");
        handler.getBanks().getBanks().forEach(System.out::println);
    }

}
