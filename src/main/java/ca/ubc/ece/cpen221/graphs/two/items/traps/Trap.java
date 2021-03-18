package ca.ubc.ece.cpen221.graphs.two.items.traps;

import ca.ubc.ece.cpen221.graphs.two.Actor;
import ca.ubc.ece.cpen221.graphs.two.items.Item;
import ca.ubc.ece.cpen221.graphs.two.items.MoveableItem;

/**
 * A Trap represent an item the can cause damage to its surroundings.
 * When a suitable {@link MoveableItem } moves too close to it, all {@link Item} with a
 * lower strength than the Trap will be destroyed in a given radius around the trap. A
 * trap can be triggered for only a finite number of times before it is destroyed.
 *
 */

public interface Trap extends Item, Actor {

    /**
     * Returns the remaining number of times a trap can be used before it is removed from the world.
     * Certain traps be reusable while others can be single use.
     *
     * @return the remaining number of times the trap can be triggered before it gets destroyed
     */
    int getRemainingTriggers();

    /**
     * Returns the sensitivity of the trap. The sensitivity is the minimum amount of Strength
     * needed for a {@link MoveableItem} to trigger the trap
     *
     * @return the sensitivity of the trap
     */
    int getSensitivity();

    /**
     * Returns the activation radius of the trap. The activation radius is
     * the area where if a {@link MoveableItem} enters within, the trap will
     * be triggered.
     *
     * @return the area in which the trap will trigger
     */
    int getActivationRadius();

    /**
     * Returns the radius in which weaker items will be destroyed, should be trap be
     * triggered
     *
     * @return the area around the Trap that will be affected
     *
     */
    int getDestructionRadius();


}
