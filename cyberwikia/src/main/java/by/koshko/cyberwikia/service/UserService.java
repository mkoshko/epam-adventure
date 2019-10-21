package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.ServiceResponse;
import by.koshko.cyberwikia.bean.User;

public interface UserService {
    User get(long userId) throws ServiceException;
    User signIn(String login, String password) throws ServiceException;
    ServiceResponse sighUp(User user) throws ServiceException;
    int update(User user) throws ServiceException;
    ServiceResponse updatePassword(long userId, String oldPassword,
                                   String newPassword);
}
