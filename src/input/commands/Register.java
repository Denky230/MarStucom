
package input.commands;

import java.io.IOException;
import java.sql.SQLException;
import management.Manager;
import management.ViewManager;
import model.Superhero;
import model.User;

public class Register extends Command {
    
    public Register(int arguments, String callCode) {
        super(arguments, callCode);
    }

    @Override
    public void call(String[] args) throws IOException, SQLException {
        String username = args[0];
        String password = args[1];
        Superhero sh = new Superhero(args[2]);
        
        // Create new User
        User user = new User(username, password, sh);
        // Register User
        Manager.getInstance().registerUser(user);
        ViewManager.getInstance().welcomeNewUser(user); 
    }
}