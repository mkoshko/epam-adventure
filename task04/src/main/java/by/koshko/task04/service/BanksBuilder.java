package by.koshko.task04.service;

import by.koshko.task04.entity.Banks;

public interface BanksBuilder {
    Banks buildBanks(String xmlFilePath) throws BankBuilderException;
}
