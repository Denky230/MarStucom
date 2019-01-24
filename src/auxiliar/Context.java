
package auxiliar;

import constants.ECardinalPoint;
import java.util.List;
import model.Enemy;
import model.Gem;
import model.Place;

public class Context {
    
    private Place place;
    private List<Gem> gems;
    private List<Enemy> enemies;

    public Context(Place place, List<Gem> gems, List<Enemy> enemies) {
        this.place = place;
        this.gems = gems;
        this.enemies = enemies;
    }
    public Context() {
        this(null, null, null);
    }
    
    public Place getPlace() {
        return this.place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }
    
    public List<Gem> getGems() {
        return gems;
    }
    public void setGems(List<Gem> gems) {
        this.gems = gems;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<ECardinalPoint> getCardinalPoints() {
        return this.place.getAdjacentPlaces().getCardinalPoints();
    }
}