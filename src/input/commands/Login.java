
package input.commands;

import auxiliar.Context;
import java.io.IOException;
import java.sql.SQLException;
import management.Manager;
import management.ViewManager;
import model.User;

public class Login extends Command {

    public Login(int arguments, String callCode) {
        super(arguments, callCode);
    }

    @Override
    public void call(String[] args) throws IOException, SQLException {
        String username = args[0];
        String password = args[1];

        Manager manager = Manager.getInstance();
        ViewManager view = ViewManager.getInstance();
        
        // Validate credentials, login if correct
        User user = manager.login(username, password);
        view.welcomeUser(user);
        
        // Show User posible actions
        Context context = manager.getContext();
        view.soutContext(context);
    }
}