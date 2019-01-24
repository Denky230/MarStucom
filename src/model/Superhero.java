
package model;

public class Superhero {

    private String name;
    private String superpower;

    public Superhero(String name, String superpower) {
        this.name = name;
        this.superpower = superpower;
    }
    public Superhero(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }
    public String getSuperpower() {
        return superpower;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }
}