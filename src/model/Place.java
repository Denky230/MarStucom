
package model;

import auxiliar.CardinalPoints;
import constants.ECardinalPoint;
import java.util.ArrayList;
import java.util.Map;

public class Place {

    private String name;
    private String description;
    private CardinalPoints adjacentPlaces;

    public Place(String name, String description, Place north, Place south, Place east, Place west) {
        this.name = name;
        this.description = description;
        this.adjacentPlaces = new CardinalPoints(north, south, east, west);
    }
    public Place(String name) {
        this(name, "", null, null, null, null);
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Place getNorth() {
        return adjacentPlaces.getPlaceByCardinalPoint(ECardinalPoint.NORTH);
    }
    public Place getSouth() {
        return adjacentPlaces.getPlaceByCardinalPoint(ECardinalPoint.SOUTH);
    }
    public Place getEast() {
        return adjacentPlaces.getPlaceByCardinalPoint(ECardinalPoint.EAST);
    }
    public Place getWest() {
        return adjacentPlaces.getPlaceByCardinalPoint(ECardinalPoint.WEST);
    }
    public CardinalPoints getAdjacentPlaces() {
        return this.adjacentPlaces;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setNorth(Place north) {
        this.adjacentPlaces.setPlaceAtCardinalPoint(north, ECardinalPoint.NORTH);
    }
    public void setSouth(Place south) {
        this.adjacentPlaces.setPlaceAtCardinalPoint(south, ECardinalPoint.SOUTH);
    }
    public void setEast(Place east) {
        this.adjacentPlaces.setPlaceAtCardinalPoint(east, ECardinalPoint.EAST);
    }
    public void setWest(Place west) {
        this.adjacentPlaces.setPlaceAtCardinalPoint(west, ECardinalPoint.WEST);
    }
}