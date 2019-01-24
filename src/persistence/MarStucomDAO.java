
package persistence;

import auxiliar.RankingEntry;
import constants.EGemName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Enemy;
import model.Fighter;
import model.Gem;
import model.Place;
import model.Superhero;
import model.User;

public class MarStucomDAO {

    private Connection connection;

    private MarStucomDAO() {}
    private static MarStucomDAO instance;
    public static MarStucomDAO getInstance() {
        if (instance == null) 
            instance = new MarStucomDAO();
        return instance;
    }

    /* --- USER --- */

    public User validateLogin(String username, String password) throws SQLException {
        User user = getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password))
            throw new NullPointerException("Wrong credentials");

        return user;
    }
    public void validateUser(User user) throws SQLException {
        // Make sure username is not already in use
        if (getUserByUsername(user.getName()) != null)
            throw new NullPointerException("username already exists");
        // Make sure user superhero exists
        if (!findSuperheroByName(user.getSuperhero().getName()))
            throw new NullPointerException("superhero dosnt exist");
    }
    public void insertUser(User user) throws SQLException {
        // Build statement
        PreparedStatement pst = connection.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?,?)");
        pst.setString(1, user.getName());
        pst.setString(2, user.getPassword());
        pst.setInt(3, user.getLevel());
        pst.setString(4, user.getSuperhero().getName());
        pst.setString(5, user.getPlace().getName());
        pst.setInt(6, user.getPoints());

        // Execute insert query (insert User into database)
        pst.executeUpdate();
        // Close resources
        pst.close();
    }
    public void deleteUser(User user) throws SQLException {
        // Delete User from database
        String query = ""
                + "DELETE FROM user "
                + "WHERE username = '"+user.getName()+"'";        
        Statement st = updateByQuery(query);
        
        // Close resources
        st.close();
    }

    public void updateUserPlace(User user, Place destination) throws SQLException {
        // Update User's place in database
        Statement st = updateByQuery(""
                + "UPDATE user "
                + "SET place = '"+destination.getName()+"' "
                + "WHERE username = '"+user.getName()+"'"
        );

        // Close resources
        st.close();
    }
    
    private User getUserByUsername(String username) throws SQLException {
        // Select User by username
        ResultSet rs = selectByQuery(
                "SELECT * FROM user WHERE username='"+username+"'"
        );
        
        // Create new User with SELECT's result, if any
        User user = null;
        if (rs.next()) {
            user = new User(null);
            fillUser(user, rs);
        }

        return user;
    }
    private void fillUser(User user, ResultSet rs) throws SQLException {
        user.setName(rs.getString("username"));
        user.setPassword(rs.getString("pass"));
        user.setLevel(rs.getInt("points"));
        user.setSuperhero(new Superhero(rs.getString("superhero")));
        user.setPlace(new Place(rs.getString("place")));
        user.setPoints(rs.getInt("points"));
    }

    /* --- ENEMY --- */

    public List<Enemy> getEnemiesByPlace(Place place) throws SQLException {
        // Select Enemies from given Place
        ResultSet rs = selectByQuery(
                "SELECT * FROM enemy WHERE place='"+place.getName()+"'"
        );

        // Create new Enemy for every SELECT result's entry
        List<Enemy> enemies = new ArrayList<>();
        while (rs.next()) {
            Enemy enemy = new Enemy("");
            fillEnemy(enemy, rs);
            enemies.add(enemy);
        }

        return enemies;
    }
    private void fillEnemy(Enemy enemy, ResultSet rs) throws SQLException {
        enemy.setName(rs.getString("name"));
        enemy.setWeakness(rs.getString("debility"));
        enemy.setPlace(new Place(rs.getString("name")));
    }

    /* --- SUPERHERO --- */

    private boolean findSuperheroByName(String name) throws SQLException {
        // Select Superhero by name
        ResultSet rs = selectByQuery(
                "SELECT * FROM superhero WHERE name='"+name+"'"
        );
        
        // Close resources
        boolean result = rs.next();
        rs.close();

        return result;
    }

    public List<Superhero> getSuperheroes() throws SQLException {
        // Select every Superhero
        ResultSet rs = selectByQuery("SELECT * FROM superhero");

        // Create new Superhero for every SELECT result's entry
        List<Superhero> superheroes = new ArrayList<>();
        while (rs.next()) {
            Superhero sh = new Superhero("");
            fillSuperhero(sh, rs);
            superheroes.add(sh);
        }

        return superheroes;
    }
    private void fillSuperhero(Superhero sh, ResultSet rs) throws SQLException {
        sh.setName(rs.getString("name"));
        sh.setSuperpower(rs.getString("superpower"));
    }

    /* --- PLACE --- */

    public Place getPlaceByName(String name) throws SQLException {
        // Select Place by name
        ResultSet rs = selectByQuery(
                "SELECT * FROM place WHERE name='"+name+"'"
        );
        
        // Create new Place with SELECT result, if any
        Place place = null;
        if (rs.next()) {
            place = new Place("");
            fillPlace(place, rs);
        }

        return place;
    }
    private void fillPlace(Place place, ResultSet rs) throws SQLException {
        place.setName(rs.getString("name"));
        place.setDescription(rs.getString("description"));
        place.setNorth(new Place(rs.getString("north")));
        place.setSouth(new Place(rs.getString("south")));
        place.setEast(new Place(rs.getString("east")));
        place.setWest(new Place(rs.getString("west")));
    }

    /* --- GEM --- */

    public void updateOwnerGemsPlace(Fighter fighter, Place place) throws SQLException {
        String query = ""
                + "UPDATE gem "
                + "SET place = '"+place.getName()+"'"
                + "WHERE owner = '"+fighter.getName()+"'";
        updateByQuery(query);
    }
    
    public List<Gem> getGemsByUserPlace(User user, Place place) throws SQLException {
        // Select User Gems by given Place
        ResultSet rs = selectByQuery(""
                + "SELECT * FROM gem "
                + "WHERE place='"+place.getName()+"' "
                + "AND user='"+user.getName()+"'"
        );
        
        // Create new Gem for every SELECT result's entry
        List<Gem> gems = new ArrayList<>();
        while (rs.next()) {
            Gem gem = new Gem(null);
            fillGem(gem, rs);
            gems.add(gem);
        }

        return gems;
    }
    private void fillGem(Gem gem, ResultSet rs) throws SQLException {
        gem.setName(EGemName.getGemByFullName(rs.getString("name")));
    }

    /* --- RANKING --- */

    public List<RankingEntry> getRanking() throws SQLException {
        // Select RankingEntry values
        String query = ""
                + "SELECT u.username, u.superhero, u.points, u.level, count(g.owner) as gems "
                + "FROM user as u "
                + "INNER JOIN gem as g ON u.username = g.owner "
                + "GROUP BY u.username";
        ResultSet rs = selectByQuery(query);
        
        // Create new RankingEntry for every SELECT result's entry
        List<RankingEntry> ranking = new ArrayList<>();
        while (rs.next()) {
            RankingEntry entry = new RankingEntry();
            fillRankingEntry(entry, rs);
            ranking.add(entry);
        }
        
        return ranking;
    }
    private void fillRankingEntry(RankingEntry entry, ResultSet rs) throws SQLException {
        entry.setUser(new User(rs.getString("username")));
        entry.setSuperhero(new Superhero(rs.getString("superhero")));
        entry.setPoints(rs.getInt("points"));
        entry.setLevel(rs.getInt("level"));
        entry.setGems(rs.getInt("gems"));
    }

    /* --- DATABASE --- */

    private ResultSet selectByQuery(String query) throws SQLException {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
//        st.close();   // TO DO: Preguntar a Mar why the fuck al cerrar esto me peta el rs
        return rs;
    }
    private Statement updateByQuery(String query) throws SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate(query);
        return st;
    }

    public void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/marvel";
        String user = "root";
        String password = "";

        connection = DriverManager.getConnection(url, user, password);
    }
    public void disconnect() throws SQLException {
        if (connection != null)
            connection.close();
    }
}