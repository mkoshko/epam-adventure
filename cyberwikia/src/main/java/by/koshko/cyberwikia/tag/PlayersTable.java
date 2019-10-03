package by.koshko.cyberwikia.tag;

import by.koshko.cyberwikia.bean.Player;
import by.koshko.cyberwikia.bean.Team;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PlayersTable extends TagSupport {
    private List<Player> players;
    private Iterator<Player> iterator;

    public void setPlayers(final List<Player> players) {
        this.players = players;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        iterator = players.iterator();
        try {
            out.write("<table class=table>");
            out.write("<thead>");
            out.write("<tr>");
            out.write("<td>Player</td>");
            out.write("</tr>");
            out.write("<tbody>");
            while (iterator.hasNext()) {
                Player player = iterator.next();
                out.write("<tr>");
                out.write("<td>" + player.getFirstName() + " "
                + player.getNickname() + " " + player.getLastName() + "</td>");
                out.write("</tr>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("</tbody>");
            out.write("</table>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
}
