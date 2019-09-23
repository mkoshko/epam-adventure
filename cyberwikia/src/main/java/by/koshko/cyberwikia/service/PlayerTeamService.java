package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.DaoTypes;
import by.koshko.cyberwikia.dao.PlayerTeamDao;

import java.util.List;

public class PlayerTeamService extends AbstractService {

    public PlayerTeamService() throws ServiceException {
        super();
    }

    public List<PlayerTeam> loadPlayerTeams(final Player player) throws ServiceException {
        try {
            PlayerTeamDao ptd = getTransaction().getDao(DaoTypes.PLAYERTEAMDAO);
            close();
            return ptd.findPlayerTeam(player);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
