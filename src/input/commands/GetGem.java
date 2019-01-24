
package input.commands;

import constants.EGemName;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import management.Manager;
import model.Gem;
import model.User;

public class GetGem extends Command {

    public GetGem(int arguments, String callCode) {
        super(arguments, callCode);
    }

    @Override
    public void call(String[] args) throws IOException, SQLException {
        Manager manager = Manager.getInstance();
        // Make sure User is logged in
        User user = manager.getUserLogged();

        // Get User input as a Gem
        String input = args[0]+" "+args[1];
        EGemName gemName = EGemName.getGemByFullName(input);
        // Get current Place gems so we know which gems User can pick up
        List<Gem> gems = manager.getContext().getGems();

        // Check if given Gem is valid
        for (Gem gem : gems) {
            if (gem.getName() == gemName) {
                // Update Gem owner to User
            }
        }

        throw new RuntimeException("There is no gem with that name here");
    }
}