
package model;

import constants.EGemName;
import java.sql.SQLException;

public class Gem extends MovingEntity {

    private EGemName name;
    private User user;
    private Fighter owner;
    private Place place;

    public Gem(EGemName name, User user, Fighter owner, Place place) {
        this.name = name;
        this.user = user;
        this.owner = owner;
        this.place = place;
    }
    public Gem(EGemName name) {
        this(name, null, null, null);
    }

    public EGemName getName() {
        return name;
    }
    public void setName(EGemName name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Fighter getOwner() {
        return owner;
    }
    public void setOwner(Fighter owner) {
        this.owner = owner;
    }

    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    protected void move(Place destination) throws SQLException {
        super.move(destination); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void land(Place landingPlace) {
        // TO DO: if theres enemies, assign this to one of them
    }
}