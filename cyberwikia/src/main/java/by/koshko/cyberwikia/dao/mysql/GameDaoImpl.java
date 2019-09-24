package by.koshko.cyberwikia.dao.mysql;

import by.koshko.cyberwikia.bean.Game;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.GameDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDaoImpl extends AbstractDao implements GameDao {
    private static final String GET
            = "SELECT id, title, icon_file FROM game WHERE id=?;";
    private static final String GET_ALL
            = "SELECT id, title, icon_file FROM game;";
    private static final String SAVE
            = "INSERT INTO game (title, icon_file) VALUES (?, ?)";
    private static final String UPDATE
            = "UPDATE game SET title=?, icon_file=? WHERE id=?;";
    @Override
    public Game get(final long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(GET);
            rs = statement.executeQuery();
            return buildSingleInstance(rs);
        } catch (SQLException e) {
            logger.error("Cannot find game by ID. SQL state: {}."
                         + " Message: {}", e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot find game.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public List<Game> getAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = getConnection().prepareStatement(GET_ALL);
            rs = statement.executeQuery();
            return buildMultipleInstances(rs);
        } catch (SQLException e) {
            logger.error("Cannot fetch all games. SQL state: {}."
                         + " Message: {}", e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot fetch games.");
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
        }
    }

    @Override
    public void save(final Game entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(SAVE);
            setUpStatement(statement, entity);
            if (statement.executeUpdate() == 1) {
                logger.info("Game '{}' has been saved.", entity.getTitle());
            }
        } catch (SQLException e) {
            logger.error("Cannot save game. SQL state: {}."
                         + " Message: {}", e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot save game.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void update(final Game entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(UPDATE);
            setUpStatement(statement, entity);
            statement.setLong(3, entity.getId());
            if (statement.executeUpdate() == 1) {
                logger.info("Game '{}' has been updated.", entity.getTitle());
            }
        } catch (SQLException e) {
            logger.error("Cannot update game. SQL state: {}."
                         + " Message: {}", e.getSQLState(), e.getMessage());
            throw new DaoException("Cannot update game.");
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void delete(final Game entity) throws DaoException {
        throw new DaoException("Unsupported operation.");
    }

    private Game buildSingleInstance(final ResultSet rs) throws SQLException {
        if (rs.next()) {
            return buildGame(rs);
        }
        return null;
    }

    private List<Game> buildMultipleInstances(final ResultSet rs)
            throws SQLException {
        List<Game> games = new ArrayList<>();
        while (rs.next()) {
            games.add(buildGame(rs));
        }
        return games;
    }

    private Game buildGame(final ResultSet rs) throws SQLException {
        Game game = new Game();
        game.setId(rs.getLong("id"));
        game.setTitle(rs.getString("title"));
        game.setIconFile(rs.getString("icon_file"));
        return game;
    }

    private void setUpStatement(final PreparedStatement st, final Game game)
            throws SQLException {
        st.setString(1, game.getTitle());
        st.setString(2, game.getIconFile());
    }
}
