package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.PlayerTeamDao;
import by.koshko.cyberwikia.dao.Transaction;
import by.koshko.cyberwikia.service.PlayerTeamService;
import by.koshko.cyberwikia.service.ServiceException;

import java.util.List;

public class PlayerTeamServiceImpl extends AbstractService implements PlayerTeamService {

    public PlayerTeamServiceImpl() throws ServiceException {
        super();
    }

    public PlayerTeamServiceImpl(final Transaction transaction) {
        super(transaction);
    }

    public List<PlayerTeam> loadPlayerTeams(final Player player) throws ServiceException {
        try {
            PlayerTeamDao ptd = getTransaction().getDao(DaoTypes.PLAYERTEAMDAO);
            return ptd.findPlayerTeam(player);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        } finally {
            close();
        }
    }
}
