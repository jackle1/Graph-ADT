package ca.ubc.ece.cpen221.graphs.two.items.vehicles;

import ca.ubc.ece.cpen221.graphs.two.Location;
import ca.ubc.ece.cpen221.graphs.two.World;
import ca.ubc.ece.cpen221.graphs.two.commands.Command;
import ca.ubc.ece.cpen221.graphs.two.commands.WaitCommand;

import javax.swing.ImageIcon;

public abstract class AbstractVehicle implements Vehicle{
    /**
     * RI:
     * - Initial location is a valid location
     * - Energy lost is not null
     * - The world it is being spawned in is a valid world
     * - Strength lost is not null
     *
     * AF
     * -Represents a vehicle item that is able to move around the world. The vehicle picks
     * up speed the longer it moves in one given direction and begins to slow down if it
     * wants to turn. A vehicle is also able to run over and kill anything that has a lower strength
     * than itself but will be destroyed if the item being run over has a higher strength than itself.
     * It will also
     */
    private int INITIAL_STRENGTH;
    private int MAX_ENERGY;
    private int INITIAL_SPEED;

    private ImageIcon image;

    private boolean isDead;
    private int speed;
    private int energy;
    private int strength;
    private Location location;

    protected void setSTRENGTH(int i) { this.INITIAL_STRENGTH = i;}
    protected void setCOOLDOWN(int i) { this.INITIAL_SPEED = i; }
    protected void setENERGY(int i) { this.MAX_ENERGY = i; }

    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
    }

    @Override
    public int getMovingRange() {
        return 1;
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public String getName() {
        return "Vehicle";
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void loseEnergy(int energy) {
        this.energy -= energy;
    }

    @Override
    public boolean isDead() {
        return strength <= 0;
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
        return speed;
    }

    @Override
    public Command getNextAction(World world) {
       return new WaitCommand();
    }

    @Override
    public void loseStrength(int strength){
        this.strength -= strength;
    }

    @Override
    public void controlEnergy(){
        loseEnergy(speed);
    }

}
