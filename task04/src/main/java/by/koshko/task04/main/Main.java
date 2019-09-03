package by.koshko.task04.main;

import by.koshko.task04.service.BankBuilderException;
import by.koshko.task04.service.BankDOMBuilder;
import by.koshko.task04.service.BankSAXBuilder;

public class Main {

    private static final String SCHEMA = "data/banks.xsd";
    private static final String XML = "data/banks.xml";

    public static void main(final String[] args) throws BankBuilderException {
        BankSAXBuilder builder = new BankSAXBuilder(SCHEMA);
        var banks = builder.buildBanks(XML);
        System.out.println(banks);
        System.out.println("========================================");
        BankDOMBuilder builder0 = new BankDOMBuilder(SCHEMA);
        var banks0 = builder0.buildBanks(XML);
        System.out.println(banks0);
    }

}
