
package model;

import java.sql.SQLException;

public class Enemy extends Fighter {

    private String weakness;
    private Place place;

    public Enemy(String name, String weakness, int level, Place place) {
        super(name, level);

        this.weakness = weakness;
        this.place = place;
    }
    public Enemy(String name) {
        this(name, null, 0, null);
    }
    
    public String getWeakness() {
        return weakness;
    }
    public Place getPlace() {
        return place;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }
    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    protected void move(Place destination) throws SQLException {
        super.move(destination); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    protected void land(Place landingPlace) throws SQLException {
        super.land(landingPlace); //To change body of generated methods, choose Tools | Templates.
    }
}