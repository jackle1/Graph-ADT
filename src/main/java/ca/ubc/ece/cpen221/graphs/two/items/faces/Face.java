package ca.ubc.ece.cpen221.graphs.two.items.faces;

import ca.ubc.ece.cpen221.graphs.two.Actor;
import ca.ubc.ece.cpen221.graphs.two.commands.Command;
import ca.ubc.ece.cpen221.graphs.two.items.Item;
import ca.ubc.ece.cpen221.graphs.two.items.LivingItem;
import ca.ubc.ece.cpen221.graphs.two.items.MoveableItem;

/**
 * A Face is a {@link MoveableItem} that represents the Face of a
 * person we know. The face may or may not move around the world,
 * and a specific type of {@link Item} around the specific Face
 * will be affected in some way (some examples: a certain item
 * might die when the Face is nearby while other items might be
 * creaeted)
 *
 */

public interface Face extends MoveableItem, Actor {
    /**
     * Returns the area around the Face afflicted by its special effect.
     * Each Face should have only 1 special effect
     *
     * @return the area around the Face affected
     */
    int getAffectedRadius();

    /**
     * The Command is executed on the types of item that is affected by
     * the Face. When the result of this method returns 0, the Face
     * should be removed from the world
     *
     * @return the remaining number of commands the face can execute to
     * modify its surrounding items
     *
     */
    int getCommandsRemaining();

}
