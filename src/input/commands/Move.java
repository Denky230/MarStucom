
package input.commands;

import auxiliar.Context;
import constants.ECardinalPoint;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import management.Manager;
import management.ViewManager;
import model.Place;
import model.User;

public class Move extends Command {
    
    public Move(int arguments, String callCode) {
        super(arguments, callCode);
    }

    @Override
    public void call(String[] args) throws IOException, SQLException {
        Manager manager = Manager.getInstance();
        // Make sure User is logged in
        User user = manager.getUserLogged();
        // Get User current Place info
        Context context = manager.getContext();

        // Get User input (command call) as a cardinalPoint
        String input = getCallCode();
        ECardinalPoint cardinalPoint = ECardinalPoint.getCardinalPointFromLetter(input);
        // Get current Place cardinalPoints so we know where User can move
        List<ECardinalPoint> cardinalPoints = context.getCardinalPoints();

        // Check if cardinalPoint given is valid
        if (cardinalPoints.contains(cardinalPoint)) {
            // Get User's current Place
            Place destination = context.getPlace();
            // Get Place in destination's cardinalPoint
            destination = destination.getAdjacentPlaces().getPlaceByCardinalPoint(cardinalPoint);

            // Move User + give feedback
            user.move(destination);
            ViewManager.getInstance().soutContext(context);
        } else {
            throw new RuntimeException("You can't move in that direction");
        }
    }
}