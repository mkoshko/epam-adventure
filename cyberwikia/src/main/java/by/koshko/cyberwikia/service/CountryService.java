package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Country;

import java.util.List;

public interface CountryService {

    /**
     * Finds country by specified id.
     *
     * @param id Identifier of the country.
     * @return {@code Country} object if one was found, {@code null} otherwise.
     * @throws ServiceException throws if some exceptions occurs in dao layer.
     */
    Country getCountryById(long id) throws ServiceException;

    /**
     * Returns list of all available countries.
     *
     * @return list of all available countries.
     * @throws ServiceException if some errors occurs in dao layer.
     */
    List<Country> getAll() throws ServiceException;
}
