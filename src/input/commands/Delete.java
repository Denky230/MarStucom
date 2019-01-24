
package input.commands;

import java.io.IOException;
import java.sql.SQLException;
import management.Manager;
import management.ViewManager;
import model.User;

public class Delete extends Command {

    public Delete(int arguments, String callCode) {
        super(arguments, callCode);
    }

    @Override
    public void call(String[] args) throws IOException, SQLException {
        String password = args[0];

        // Use given password to validate logged User's credentials
        User user = Manager.getInstance().unregister(password);
        // Say goodbye
        ViewManager.getInstance().farewellUser(user);
    }
}