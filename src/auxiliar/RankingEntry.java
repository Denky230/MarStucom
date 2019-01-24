
package auxiliar;

import model.Superhero;
import model.User;

public class RankingEntry {
    
    private User user;
    private Superhero superhero;
    private int points;
    private int level;
    private int gems;

    public RankingEntry(User user, Superhero superhero, int points, int level, int gems) {
        this.user = user;
        this.superhero = superhero;
        this.points = points;
        this.level = level;
        this.gems = gems;
    }
    public RankingEntry() {
        this(null, null, 0, 0, 0);
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Superhero getSuperhero() {
        return superhero;
    }
    public void setSuperhero(Superhero superhero) {
        this.superhero = superhero;
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public int getGems() {
        return gems;
    }
    public void setGems(int gems) {
        this.gems = gems;
    }
}