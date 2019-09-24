package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Country;

import java.util.List;

public interface CountryService {

    Country getCountryById(long id) throws ServiceException;
    List<Country> getAll() throws ServiceException;
}
