
package management;

import auxiliar.Context;
import java.sql.SQLException;
import java.util.List;
import model.Enemy;
import model.Gem;
import model.Place;
import model.User;
import persistence.MarStucomDAO;

public class Manager {
    
    /**
     * User currently logged in the system
     */
    private User userLogged;
    /**
     * Stores info about the current Place and the User options
     */
    private final Context context;

    private final MarStucomDAO dao;

    private Manager() {
        dao = MarStucomDAO.getInstance();
        context = new Context();
    }
    private static Manager instance;
    public static Manager getInstance() {
        if (instance == null)
            instance = new Manager();
        return instance;
    }

    public User login(String username, String password) throws SQLException {
        // Validate User credentials
        User user = dao.validateLogin(username, password);
        userLogged = user;
        
        // Get User current Place info
        updateContext(userLogged.getPlace());
        
        return user;
    }
    public void registerUser(User user) throws SQLException {
        // Check if User is valid for registration
        dao.validateUser(user);        
        // Insert User into database
        dao.insertUser(user);
    }
    public User unregister(String password) throws SQLException {
        // Make sure there is User logged in
        String username = getUserLogged().getName();
        // Validate User credentials
        User user = dao.validateLogin(username, password);

        // Unregister User + log out
        dao.deleteUser(user);
        userLogged = null;
        
        return user;
    }

    public void moveUser(Place destination) throws SQLException {
        // Update User's Place in database + memory
        dao.updateUserPlace(userLogged, destination);
        userLogged.setPlace(destination);
        
        // Update User Gems' Place in database
        dao.updateOwnerGemsPlace(userLogged, destination);
    }
    public void updateContext(Place newPlace) throws SQLException {
        // Get new Place Context values
        Place place = dao.getPlaceByName(newPlace.getName());
        List<Enemy> enemies = dao.getEnemiesByPlace(newPlace);
        List <Gem> gems = dao.getGemsByUserPlace(userLogged, newPlace);

        // Update Context values
        context.setPlace(place);
        context.setGems(gems);
        context.setEnemies(enemies);
    }

    /**
     * Makes sure there is a User logged in the system.
     * @return User currently logged in the system
     * @throws RuntimeException if no User is logged in
     */
    public User getUserLogged() {
        if (userLogged == null)
            throw new RuntimeException("No user logged in");

        return this.userLogged;
    }
    public Context getContext() { return context; }
}