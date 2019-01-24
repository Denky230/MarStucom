
package constants;

public enum EGemName {

    MIND,
    POWER,
    REALITY,
    SOUL,
    SPACE,
    TIME;

    public static EGemName getGemByFullName(String fullName) {
        try {
            String name = fullName.split(" ")[0].toUpperCase();
            return EGemName.valueOf(name);
            
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("There is no gem with that name");
        }
    }
}