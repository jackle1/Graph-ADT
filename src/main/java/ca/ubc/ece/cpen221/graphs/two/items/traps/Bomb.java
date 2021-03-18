package ca.ubc.ece.cpen221.graphs.two.items.traps;

import ca.ubc.ece.cpen221.graphs.two.Location;
import ca.ubc.ece.cpen221.graphs.two.Util;
import ca.ubc.ece.cpen221.graphs.two.World;
import ca.ubc.ece.cpen221.graphs.two.commands.Command;
import ca.ubc.ece.cpen221.graphs.two.commands.WaitCommand;
import ca.ubc.ece.cpen221.graphs.two.items.Item;
import ca.ubc.ece.cpen221.graphs.two.items.MoveableItem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A bomb is a trap that has a small trigger radius, but a large destructive radius.
 * It is very sensitive and is only used once. It has very high strength meaning it
 * can destroy almost anything that gets caught in its destructive radius
 */

public class Bomb extends AbstractTrap implements Trap{
    private static final int SENSITIVITY = 2;
    private static final int ACTIVATION_RADIUS = 1;
    private static final int DESTRUCTION_RADIUS = 6;
    private static final int INITIAL_USES = 1;
    private static final int COOLDOWN = 30;

    private static final int STRENGTH = 1000;
    private static final ImageIcon bombImage = Util.loadImage("bomb.gif");
    private final Location location;

    private boolean isDead;
    private int REMAINING_USES;

    public Bomb(Location initialLocation) {
        REMAINING_USES = INITIAL_USES;
        isDead = false;
        location = initialLocation;

        setSENSITIVITY(SENSITIVITY);
        setACTIVATION_RADIUS(ACTIVATION_RADIUS);
        setDESTRUCTION_RADIUS(DESTRUCTION_RADIUS);
        setINITIAL_USES(INITIAL_USES);
        setCOOLDOWN(COOLDOWN);
        setSTRENGTH(STRENGTH);
        setLocation(location);

    }
    @Override
    public String getName() {
        return "bomb trap";
    }

    @Override
    public ImageIcon getImage() {
        return bombImage;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public Command getNextAction(World world) {
        Set<Item> surroundings = world.searchSurroundings(location, ACTIVATION_RADIUS);
        List<Item> toRemove = new ArrayList<>();
        boolean trapActivate = false;
        if (!surroundings.isEmpty()) {
            for (Item item : surroundings) {
                if (item instanceof MoveableItem && item.getStrength() >= SENSITIVITY) {
                    trapActivate = true;
                    break;
                }
            }
        }

        if (trapActivate) {
            REMAINING_USES--;
            if (REMAINING_USES == 0) {
                isDead = true;
            }
            Set<Item> toKill = world.searchSurroundings(location, DESTRUCTION_RADIUS);
            for (Item item : toKill) {
                if (STRENGTH > item.getStrength()) {
                    toRemove.add(item);
                }
            }
            return new Command(){
                @Override
                public void execute(World world) {
                    for (Item item : toRemove) {
                        item.loseEnergy(Integer.MAX_VALUE);
                    }
                }
            };
        }
        return new WaitCommand();
    }

}
