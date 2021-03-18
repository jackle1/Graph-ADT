package ca.ubc.ece.cpen221.graphs.two.items.vehicles;

import ca.ubc.ece.cpen221.graphs.two.Actor;
import ca.ubc.ece.cpen221.graphs.two.items.MoveableItem;

/**
 * A vehicle represents an Moveable Item who's speed depends on the direction it is going. If
 * it is going straight, the vehicle will speed up until the max speed. If the vehicle turns, then
 * it will begin to slow down. The speed also determines how much energy a vehicle loses. When out
 * of energy, the vehicle will stay in place. A Vehicle is able to run over items that has a lower
 * strength value than itself but will be destroyed if the item has a larger strength than itself.
 */
public interface Vehicle extends MoveableItem, Actor {

    /**
     * Cause the vehicle to lose strength everytime it successfully runs over another item
     */
    void loseStrength(int strength);

    /**
     * Controls when the vehicle gains and loses energy. A vehicle gains energy if it runs over
     * gas and loses energy proportional to how fast it is going
     */
    void controlEnergy();

}
