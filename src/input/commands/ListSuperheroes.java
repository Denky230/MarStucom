
package input.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import management.ViewManager;
import model.Superhero;
import persistence.MarStucomDAO;

public class ListSuperheroes extends Command {

    public ListSuperheroes(int arguments, String callCode) {
        super(arguments, callCode);
    }

    @Override
    public void call(String[] args) throws IOException, SQLException {
        // Get all Superheroes from database
        List<Superhero> superheroes = MarStucomDAO.getInstance().getSuperheroes();
        // List superheroes
        ViewManager.getInstance().soutSuperheroes(superheroes);
    }
}