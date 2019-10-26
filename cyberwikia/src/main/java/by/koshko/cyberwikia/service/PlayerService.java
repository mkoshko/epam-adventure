package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.ServiceResponse;

import java.util.List;

public interface PlayerService {

    /**
     * Finds player with specified identifier.
     *
     * @param id player identifier.
     * @return {@code Player} object if one exists, {@code null} otherwise.
     * @throws ServiceException if some errors occurred at underlying layer.
     */
    Player findById(long id) throws ServiceException;

    /**
     * Finds players whose nicknames contains an entry of a given string.
     *
     * @param searchString string using for searching players.
     * @return List with {@code Player} objects which was found.
     */
    List<Player> findPlayersByNickname(String searchString);

    /**
     * Saves {@code Player} object if all parameters are correct and user with
     * specified id has no player profile.
     *
     * @param userId User identifier, used to check if the user already have
     *               player profile.
     * @param player {@code Player} object to be saved.
     * @return {@code ServiceResponse} object which contains errors if some
     * occurred while processing.
     * @throws ServiceException if some errors occurred at underlying layer.
     */
    ServiceResponse createPlayer(long userId, Player player)
            throws ServiceException;

    /**
     * Replaces old users player profile with one given as parameter, only if
     * given {@code Player} object has valid parameters and user already have a
     * profile.
     *
     * @param userId User identifier whose player profile will be replaced with
     *               the given one.
     * @param player {@code Player} object to be replaced.
     * @return {@code ServiceResponse} object which contains errors if some
     * occurred while processing.
     * @throws ServiceException if some errors occurred at underlying layer.
     */
    ServiceResponse editPlayer(long userId, Player player)
            throws ServiceException;

    /**
     * Deletes {@code Player} profile for given user.
     *
     * @param userId identifier of the user which profile will be deleted.
     * @return {@code true} if player profile was successfully deleted,
     * {@code false} otherwise.
     * @throws ServiceException if some errors occurred at underlying layer.
     */
    boolean deletePlayer(long userId) throws ServiceException;

    /**
     * Obtains list of {@code Player} objects with offset and limit.
     *
     * @param offset index of the record from which list starts.
     * @param limit  number of records to be returned.
     * @return List with {@code Player} objects.
     * @throws ServiceException if some errors occurred at underlying layer.
     */
    List<Player> findAll(int offset, int limit) throws ServiceException;

    /**
     * @param id {@code Player} profile identifier.
     * @return {@code Player} object in which all fields are loaded.
     * @throws ServiceException if some errors occurred at underlying layer.
     */
    Player loadProfile(long id) throws ServiceException;

    /**
     * Returns number of all stored {@code Player} objects.
     *
     * @return number of all stored {@code Player} objects.
     * @throws ServiceException if some errors occurred at underlying layer.
     */
    int getRowsNumber() throws ServiceException;
}
