
package model;

public abstract class Fighter extends MovingEntity {
    
    protected String name;
    protected int level;

    public Fighter(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return this.level;
    }

    public void fight() {
        // TO DO: Fight logic
    }
}