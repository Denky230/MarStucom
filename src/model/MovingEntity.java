
package model;

import java.sql.SQLException;

public abstract class MovingEntity {

    protected void move(Place destination) throws SQLException {
        land(destination);
    }
    /**
     * Handle post-movement
     * @param landingPlace destination Place
     * @throws SQLException 
     */
    protected void land(Place landingPlace) throws SQLException {}
}