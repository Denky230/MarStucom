
package management;

import auxiliar.Context;
import auxiliar.RankingEntry;
import constants.ECardinalPoint;
import java.util.List;
import model.Enemy;
import model.Gem;
import model.Place;
import model.Superhero;
import model.User;

public class ViewManager {

    private ViewManager() {}
    private static ViewManager instance;
    public static ViewManager getInstance() {
        if (instance == null)
            instance = new ViewManager();
        return instance;
    }

    public void welcomeUser(User user) {
        System.out.println("Welcome, " + user.getName());
    }
    public void welcomeNewUser(User user) {
        welcomeUser(user);
        System.out.println("Log in to begin your adventure!");
    }
    public void farewellUser(User user) {
        System.out.println(""
                + "User "+user.getName()+" is no longer part of the system.\n"
                + "Farewell soldier, may we meet again."
        );
    }

    public void soutSuperheroes(List<Superhero> superheroes) {
        String message = ">> SuperHeroes <<\n";
        StringBuilder sb = new StringBuilder(message);
        int indent = 15;

        // Append superheroes
        for (Superhero superhero : superheroes) {
            String name = superhero.getName();
            String superpower = superhero.getSuperpower();

            // Set indentation
            name = String.format("%-"+indent+"s", name);

            sb.append(name).
                    append(superpower).
                    append("\n");
        }

        System.out.println(sb);
    }
    public void soutRanking(List<RankingEntry> ranking) {
        String message = "";
        StringBuilder sb = new StringBuilder(message);
        int indent = 15;

        if (!ranking.isEmpty()) {
            sb.append(">> Ranking <<\n");

            // TO DO: order by gems > lvl > points
            // Append ranking entries
            for (RankingEntry entry : ranking) {
                // Get every ranking column into an array
                String[] columns = new String[] {
                    entry.getUser().getName(),
                    entry.getSuperhero().getName(),
                    String.valueOf(entry.getPoints()),
                    String.valueOf(entry.getLevel()),
                    String.valueOf(entry.getGems())
                };

                // Set indentation
                for (String column : columns) {
                    column = String.format("%-"+indent+"s", column);
                    sb.append(column);
                }
                sb.append("\n");
            }
        } else {
            sb.append("There are no users for the ranking (nobody has gems)");
        }

        System.out.println(sb);
    }

    public void soutContext(Context context) {
        // Line separator
        String separator = "---";
        
        // Sout Place info
        soutPlace(context.getPlace());
        System.out.println(separator);

        // Sout Enemies info
        System.out.println(">> Enemies <<");
        soutEnemies(context.getEnemies());
        System.out.println(separator);

        // Sout Gems info
        System.out.println(">> Gems <<");
        soutGems(context.getGems());
        System.out.println(separator);

        // Sout Places to go
        System.out.println("You can go to:");
        soutAdjacentPlaces(context.getCardinalPoints());
    }
    private void soutPlace(Place place) {
        // Place name + description
        System.out.println(
                "Place: " + place.getName() + "\n"
                + place.getDescription()
        );
    }
    private void soutEnemies(List<Enemy> enemies) {
        String message = "";
        StringBuilder sb = new StringBuilder(message);

        if (!enemies.isEmpty()) {
            // Append enemies
            for (Enemy enemy : enemies) {
                String name = enemy.getName();
                String weakness = enemy.getWeakness();
                int level = enemy.getLevel();
                sb.append(name).
                        append(" - Weakness: ").append(weakness).
                        append(" - Level: ").append(level).
                        append("\n");
            }
            // Remove last "\n"
            sb.delete(sb.length() - 1, sb.length());
        } else {
            sb.append("There is nobody here");
        }

        System.out.println(sb);
    }
    private void soutGems(List<Gem> gems) {
        String message = "";
        StringBuilder sb = new StringBuilder(message);

        if (!gems.isEmpty()) {
            // Add gems
            for (Gem gem : gems) {
                String name = gem.getName().name();
                sb.append(name).append("\n");
            }
            // Remove last "\n"
            sb.delete(sb.length() - 1, sb.length());
        } else {
            sb.append("There are no gems here");
        }

        System.out.println(sb);
    }
    private void soutAdjacentPlaces(List<ECardinalPoint> cardinalPoints) {
        String message = "";
        StringBuilder sb = new StringBuilder(message);

        // Append cardinal letters from cardinal points
        for (ECardinalPoint cardinalPoint : cardinalPoints) {
            String cardinalLetter = cardinalPoint.getCardinalLetter();
            sb.append(cardinalLetter).append(" ");
        }

        System.out.println(sb);
    }
}