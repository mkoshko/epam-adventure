package by.koshko.task04.main;

import by.koshko.task04.entity.Banks;
import by.koshko.task04.service.BankBuilderException;
import by.koshko.task04.service.BanksBuilder;
import by.koshko.task04.service.BanksBuilderFactory;

public class Main {

    private static final String SCHEMA = "data/banks.xsd";
    private static final String XML = "data/banks.xml";

    public static void main(final String[] args) throws BankBuilderException {
        BanksBuilderFactory factory = new BanksBuilderFactory();
        BanksBuilder builder = factory.createBanksBuilder("sax", SCHEMA);
        Banks banks = builder.buildBanks(XML);
        System.out.println(banks);
    }

}
