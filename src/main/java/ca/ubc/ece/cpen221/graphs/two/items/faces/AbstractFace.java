package ca.ubc.ece.cpen221.graphs.two.items.faces;

import ca.ubc.ece.cpen221.graphs.two.Location;
import ca.ubc.ece.cpen221.graphs.two.World;
import ca.ubc.ece.cpen221.graphs.two.commands.Command;

import javax.swing.*;

public abstract class AbstractFace implements Face {

    /**
     * RI:
     * - Initial location is a valid location
     * - Energy lost is not null
     * - The world it is being spawned in is a valid world
     *
     * AF:
     * - Face represents an item that has a special effect. The effect can be used a
     * finite amount of times before the item is removed
     */
    private int AFFECTED_RADIUS;
    private int INITIAL_COMMANDS;
    private int COOLDOWN;

    private int STRENGTH;
    private ImageIcon image;
    private Location location;
    private int MOVING_RANGE;

    private boolean isDead;
    private int REMAINING_COMMANDS = INITIAL_COMMANDS;

    protected void setAFFECTED_RADIUS (int i) {
        AFFECTED_RADIUS = i;
    }

    protected void setINITIAL_COMMANDS(int i) {
        INITIAL_COMMANDS = i;
    }

    protected void setCOOLDOWN(int i) {
        COOLDOWN = i;
    }

    protected void setSTRENGTH(int i) {
        STRENGTH = i;
    }

    protected void setMOVING_RANGE(int i) {
        MOVING_RANGE = i;
    }

    protected void setLocation(Location i) {
        location = i;
    }

    @Override
    public int getAffectedRadius() {
        return AFFECTED_RADIUS;
    }

    @Override
    public int getCommandsRemaining() {
        return REMAINING_COMMANDS;
    }

    @Override
    public int getCoolDownPeriod() {
        return COOLDOWN;
    }

    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
    }

    @Override
    public int getMovingRange() {
        return MOVING_RANGE;
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public String getName() {
        return "generic face";
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public void loseEnergy(int energy) {
        isDead = true;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public int getPlantCalories() {
        return 0;
    }

    @Override
    public int getMeatCalories() {
        return 0;
    }

    //Implement this in the actual representation, look at AbstractTrap for example
    @Override
    public abstract Command getNextAction(World world);
}
