
package auxiliar;

import constants.ECardinalPoint;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import model.Place;

public class CardinalPoints {
    
    private final LinkedHashMap<ECardinalPoint, Place> places;

    public CardinalPoints(Place north, Place south, Place east, Place west) {
        this.places = new LinkedHashMap<>();
        this.places.put(ECardinalPoint.NORTH, north);
        this.places.put(ECardinalPoint.SOUTH, south);
        this.places.put(ECardinalPoint.EAST, east);
        this.places.put(ECardinalPoint.WEST, west);
    }

    public LinkedHashMap<ECardinalPoint, Place> getPlaces() {
        return places;
    }
    
    public Place getPlaceByCardinalPoint(ECardinalPoint cardinalPoint) {
        return this.places.get(cardinalPoint);
    }
    public void setPlaceAtCardinalPoint(Place place, ECardinalPoint cardinalPoint) {
        this.places.put(cardinalPoint, place);
    }
    public ArrayList<ECardinalPoint> getCardinalPoints() {
        ArrayList<ECardinalPoint> cardinalPoints = new ArrayList<>();
        
        // Loop cardinal points looking for not nulls
        for (Map.Entry<ECardinalPoint, Place> entry : places.entrySet()) {
            if (entry.getValue().getName() != null) {
                // Add key (NORTH, SOUTH, etc.)
                ECardinalPoint cardinalPoint = entry.getKey();
                cardinalPoints.add(cardinalPoint);
            }
        }
        
        return cardinalPoints;
    }
}