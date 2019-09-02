package by.koshko.task04.main;

import by.koshko.task04.service.BankBuilderException;
import by.koshko.task04.service.BankSAXBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(final String[] args) throws SAXException, ParserConfigurationException, IOException, BankBuilderException {
        BankSAXBuilder builder = new BankSAXBuilder("data/banks.xsd");
        var banks = builder.buildBanks("data/banks.xml");
        System.out.println(banks);
    }

}
