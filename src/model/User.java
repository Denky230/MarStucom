
package model;

import java.sql.SQLException;
import management.Manager;

public class User extends Fighter {

    private final static int STARTING_LEVEL = 1;
    private final static Place STARTING_PLACE = new Place("New York");
    
    private String password;
    private Superhero superhero;
    private Place place;
    private int points;

    public User(String username, String password, Superhero superhero) {
        super(username, STARTING_LEVEL);

        this.password = password;
        this.superhero = superhero;
        this.place = STARTING_PLACE;
        this.points = 0;
    }
    public User(String username) {
        super(username, 0);
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public Superhero getSuperhero() {
        return superhero;
    }
    public void setSuperhero(Superhero superhero) {
        this.superhero = superhero;
    }

    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public void move(Place destination) throws SQLException {
        Manager.getInstance().moveUser(destination);
        super.move(destination);
    }
    @Override
    protected void land(Place landingPlace) throws SQLException {
        // Update new Place values
        Manager.getInstance().updateContext(landingPlace);
    }
}