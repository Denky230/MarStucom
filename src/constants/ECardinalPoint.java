
package constants;

public enum ECardinalPoint {

    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W");
    
    private final String cardinalLetter;
    
    private ECardinalPoint(String cardinalLetter) {
        this.cardinalLetter = cardinalLetter;
    }
    
    public String getCardinalLetter() {
        return this.cardinalLetter;
    }
    public static ECardinalPoint getCardinalPointFromLetter(String cardinalLetter) {
        ECardinalPoint[] values = ECardinalPoint.values();
        for (ECardinalPoint value : values) {
            if (value.cardinalLetter.equals(cardinalLetter))
                return value;
        }
        return null;
    }
}