package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Team;
import by.koshko.cyberwikia.dao.*;
import by.koshko.cyberwikia.dao.mysql.TransactionImpl;

public class TeamServiceImpl extends AbstractService {

    public TeamServiceImpl() throws ServiceException {
        super();
    }

    public TeamServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    public Team findTeamById(final long id) throws ServiceException {
        try {
            Transaction transaction = new TransactionImpl();
            TeamDao teamDao = transaction.getDao(DaoTypes.TEAMDAO);
            return teamDao.get(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
