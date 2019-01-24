
package input.commands;

import auxiliar.RankingEntry;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import management.ViewManager;
import persistence.MarStucomDAO;

public class ListRanking extends Command {
    
    public ListRanking(int arguments, String callCode) {
        super(arguments, callCode);
    }

    @Override
    public void call(String[] args) throws IOException, SQLException {
        // Get ranking from database
        List<RankingEntry> ranking = MarStucomDAO.getInstance().getRanking();
        // List ranking
        ViewManager.getInstance().soutRanking(ranking);
    }
}