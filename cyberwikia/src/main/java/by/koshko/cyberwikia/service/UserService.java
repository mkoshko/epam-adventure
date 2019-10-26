package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.User;

public interface UserService {
    /**
     * Returns the {@code User} with specified identifier if one exists,
     * or {@code null}, if no {@code User} found.
     *
     * @param userId User identifier.
     * @return {@code User} entity if exists with specified id,
     * {@code null} otherwise.
     * @throws ServiceException if some error occurred at underlying layer.
     */
    User get(long userId) throws ServiceException;

    /**
     * Returns the {@code User} if one exists with specified login and password,
     * {@code null} otherwise.
     *
     * @param login    User login.
     * @param password User password.
     * @return {@code User} entity if one exists with specified login and
     * password, {@code null} otherwise.
     * @throws ServiceException if some error occurred at underlying layer.
     */
    User signIn(String login, String password) throws ServiceException;

    /**
     * Saves given user, if same user doesn't exists and all properties
     * are correct.
     *
     * @param user {@code User} entity to be saved.
     * @return {@code ServiceResponse} object which contains errors if any
     * occurred while processing.
     * @throws ServiceException if some error occurred at underlying layer.
     */
    ServiceResponse sighUp(User user) throws ServiceException;

    /**
     * Updates password for user with specified identifier, only if given string
     * with old password matches the old password currently saved for user.
     *
     * @param userId      user identifier.
     * @param oldPassword supposedly old user password, but who knows.
     * @param newPassword new password to be set for user.
     * @return {@code ServiceResponse} object which contains errors if any
     * occurred while processing.
     */
    ServiceResponse updatePassword(long userId, String oldPassword,
                                   String newPassword);
}
