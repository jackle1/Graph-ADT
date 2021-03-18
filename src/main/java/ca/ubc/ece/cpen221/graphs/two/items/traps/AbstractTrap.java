package ca.ubc.ece.cpen221.graphs.two.items.traps;

import ca.ubc.ece.cpen221.graphs.two.Location;
import ca.ubc.ece.cpen221.graphs.two.World;
import ca.ubc.ece.cpen221.graphs.two.commands.Command;
import ca.ubc.ece.cpen221.graphs.two.commands.WaitCommand;
import ca.ubc.ece.cpen221.graphs.two.items.Item;
import ca.ubc.ece.cpen221.graphs.two.items.MoveableItem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class AbstractTrap implements Trap {

    /**
     *  RI:
     *  - Initial location is a valid location
     *  - Energy lost is not null
     *  - The world it is being spawned in is a valid world
     *
     * AF: Represents an non-movable item that is able to cause destruction over a certain
     * set radius if activated. Once it has been used, it will be removed from the world
     */

    private int SENSITIVITY;
    private int ACTIVATION_RADIUS;
    private int DESTRUCTION_RADIUS;
    private int INITIAL_USES;
    private int COOLDOWN;

    private int STRENGTH;
    private ImageIcon image;
    private Location location;

    private boolean isDead = false;
    private int REMAINING_USES = INITIAL_USES;

    protected void setSENSITIVITY(int i) {
        SENSITIVITY = i;
    }

    protected void setACTIVATION_RADIUS(int i) {
        ACTIVATION_RADIUS = i;
    }

    protected void setDESTRUCTION_RADIUS(int i) {
        DESTRUCTION_RADIUS = i;
    }

    protected void setINITIAL_USES(int i) {
        INITIAL_USES = i;
    }

    protected void setCOOLDOWN(int i) {
        COOLDOWN = i;
    }

    protected void setSTRENGTH(int i) {
        STRENGTH = i;
    }

    protected void setLocation(Location i) {
        location = i;
    }

    @Override
    public int getRemainingTriggers() {
        return REMAINING_USES;
    }

    @Override
    public int getSensitivity() {
        return this.SENSITIVITY;
    }

    @Override
    public int getActivationRadius() {
        return this.ACTIVATION_RADIUS;
    }

    @Override
    public int getDestructionRadius() {
        return this.DESTRUCTION_RADIUS;
    }

    @Override
    public ImageIcon getImage() {
        return this.image;
    }

    @Override
    public String getName() {
        return "generic trap";
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public int getStrength() {
        return this.STRENGTH;
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

    @Override
    public int getCoolDownPeriod() {
        return COOLDOWN;
    }

    @Override
    public abstract Command getNextAction(World world);
}
