package ca.ubc.ece.cpen221.graphs.two.items.faces;

import ca.ubc.ece.cpen221.graphs.two.Direction;
import ca.ubc.ece.cpen221.graphs.two.Location;
import ca.ubc.ece.cpen221.graphs.two.Util;
import ca.ubc.ece.cpen221.graphs.two.World;
import ca.ubc.ece.cpen221.graphs.two.commands.Command;
import ca.ubc.ece.cpen221.graphs.two.commands.MoveCommand;
import ca.ubc.ece.cpen221.graphs.two.commands.WaitCommand;
import ca.ubc.ece.cpen221.graphs.two.items.Item;
import ca.ubc.ece.cpen221.graphs.two.items.animals.Gnat;
import ca.ubc.ece.cpen221.graphs.two.items.traps.Bomb;

import javax.swing.*;
import java.util.Set;

import static ca.ubc.ece.cpen221.graphs.two.Util.getRandomEmptyLocation;

/**
 * Implementation of the SpiritedAway face. Wanders around aimlessly. If it
 * sees a {@link Gnat} in its radius of effect it will spawn a {@link Bomb}
 *
 */

public class SpiritedAway extends AbstractFace implements Face{
    private static final int AFFECTED_RADIUS = 3;
    private static final int INITIAL_COMMANDS = 2;
    private static final int COOLDOWN = 20;

    private static final int STRENGTH = 9999;
    private static final ImageIcon spiritedAwayImage = Util.loadImage("spiritedaway.png");
    private Location location;
    private static final int MOVING_RANGE = 0;

    private boolean isDead;
    private int REMAINING_COMMANDS;

    public SpiritedAway(Location initialLocation) {
        REMAINING_COMMANDS = INITIAL_COMMANDS;
        isDead = false;
        location = initialLocation;

        setAFFECTED_RADIUS(AFFECTED_RADIUS);
        setINITIAL_COMMANDS(INITIAL_COMMANDS);
        setCOOLDOWN(COOLDOWN);
        setSTRENGTH(STRENGTH);
        setMOVING_RANGE(MOVING_RANGE);
        setLocation(location);
    }

    @Override
    public int getCommandsRemaining() {
        return REMAINING_COMMANDS;
    }

    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
    }

    @Override
    public ImageIcon getImage() {
        return spiritedAwayImage;
    }

    @Override
    public String getName() {
        return "Spirited Away";
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public Command getNextAction(World world) {
        Set<Item> surroundings = world.searchSurroundings(location, AFFECTED_RADIUS);
        boolean seesGnat = false;
        if (!(surroundings.isEmpty())) {
            for (Item item : surroundings) {
                if (item instanceof Gnat) {
                    seesGnat = true;
                    break;
                }
            }
        }

        if (seesGnat) {
            REMAINING_COMMANDS--;
            if (REMAINING_COMMANDS == 0) {
                isDead = true;
            }
            Location targetLocation = getRandomEmptyLocation(world,
                    location.getX() - AFFECTED_RADIUS, location.getY() - AFFECTED_RADIUS,
                    location.getX() + AFFECTED_RADIUS, location.getY() + AFFECTED_RADIUS);
            return new Command(){
                @Override
                public void execute(World world) {
                    Bomb bomb = new Bomb(targetLocation);
                    world.addItem(bomb);
                    world.addActor(bomb);
                }
            };
        } else {
            return new WaitCommand();
        }
    }
}
