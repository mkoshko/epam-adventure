package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Country;

public interface CountryService {

    Country getCountryById(long id) throws ServiceException;
}
