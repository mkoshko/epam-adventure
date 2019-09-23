package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.PlayerTeam;
import org.testng.annotations.Test;

import java.util.List;

public class PlayerServiceImplTest {

    @Test
    public void testFindByNickname() throws ServiceException {
        PlayerService playerService = new PlayerServiceImpl();
        List<Player> players = playerService.findAll();
        players.forEach(player -> System.out.println(player.getId() + " " + player.getFirstName() + " " + player.getNickname() + " " + player.getLastName()));
    }

    @Test
    public void testLoadProfile() throws ServiceException {
        PlayerService ps = new PlayerServiceImpl();
        Player player = ps.loadProfile(18);
        if (player != null) {
            System.out.printf("Nickname: %s\nFull name: %s\nBirth: %s\nCountry: %s '%s'\nOverview: %s\n", player.getNickname(), player.getFirstName() + " " + player.getLastName(), player.getBirth().toString(), player.getCountry().getName(), player.getCountry().getCode(), player.getOverview());
            System.out.println("Current team:");
            player.getPlayerTeams().stream().filter(PlayerTeam::isActive).forEach(playerTeam -> System.out.println(playerTeam.getTeam().getName()));
            System.out.println("Team history:");
            player.getPlayerTeams().stream().filter(playerTeam -> !playerTeam.isActive()).forEach(playerTeam -> System.out.println(playerTeam.getTeam().getName()));
        }
    }

    @Test
    public void testFindAll() throws ServiceException {
        PlayerService ps = new PlayerServiceImpl();
        List<Player> players = ps.findAll();
        players.forEach(System.out::println);
    }
}