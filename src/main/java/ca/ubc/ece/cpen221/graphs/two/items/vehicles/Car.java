package ca.ubc.ece.cpen221.graphs.two.items.vehicles;

import ca.ubc.ece.cpen221.graphs.two.Direction;
import ca.ubc.ece.cpen221.graphs.two.Location;
import ca.ubc.ece.cpen221.graphs.two.Util;
import ca.ubc.ece.cpen221.graphs.two.World;
import ca.ubc.ece.cpen221.graphs.two.commands.Command;
import ca.ubc.ece.cpen221.graphs.two.commands.MoveCommand;
import ca.ubc.ece.cpen221.graphs.two.commands.WaitCommand;
import ca.ubc.ece.cpen221.graphs.two.items.Item;

import javax.swing.ImageIcon;

/**
 * Implementation of a car. A car is able to run over items that have a lower strength than itself.
 * Each time a car hits an item, it will lose 1/10th of the strength of the item it ran over. If
 * a car hits an item with a larger strength than itself, it will be destroyed (die). A car will
 * not move anymore if its energy reaches 0.
 */

public class Car extends AbstractVehicle implements Vehicle {
    private final static ImageIcon carImage = Util.loadImage("cars.gif");
    private static final int INITIAL_STRENGTH = 500;
    private static final int MAX_ENERGY = 1000;
    private static final int INITIAL_SPEED = 4;

    private boolean isDead;
    private int speed;
    private int energy;
    private int strength;
    private Location location;
    private int countCommands;
    private int commandLimit;
    private Direction direction;

    public Car(Location location) {
        this.location = location;
        isDead = false;
        this.energy = MAX_ENERGY;
        this.strength = INITIAL_STRENGTH;
        this.speed = INITIAL_SPEED;
        this.countCommands = 0;
        commandLimit = randomNum();
    }

    /**
     *
     * @param targetLocation the location that this item is moving to
     */
    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
        if(((double)countCommands/(double)commandLimit)*100 <= (25)){
            if(speed <= INITIAL_SPEED){
                speed++;
            }
        }
        else {
            if (targetLocation.getX() == location.getX() ||
                targetLocation.getY() == location.getY()) {
                if (speed != 1 && speed >= 1) {
                    speed--;
                }
            }
        }
    }

    @Override
    public int getMovingRange() {
        return 1;
    }

    @Override
    public ImageIcon getImage() {
        return carImage;
    }

    @Override
    public String getName() {
        return "Car";
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    /**
     * A car will lose energy proportional to how fast it is going
     *
     * @param energy the amount of energy lost
     */
    @Override
    public void loseEnergy(int energy) {
        energy -= energy;
    }

    @Override
    public boolean isDead() {
        return strength <= 0;
    }

    @Override //Cars aren't very fibrous either
    public int getPlantCalories() {
        return 0;
    }

    @Override //Cars aren't very meaty, quite crunchy
    public int getMeatCalories() {
        return 0;
    }

    /**
     * Controls how fast the car is able to move
     *
     * @return the speed of the car at that instance
     */
    @Override
    public int getCoolDownPeriod() {
        return speed;
    }

    /**
     * Execute the next command
     *
     * @param world the current world
     * @return the next command to execute
     */
    @Override
    public Command getNextAction(World world) {
        Direction dir = Util.getRandomDirection();
        if( countCommands == 0){
            direction = dir;
        }
        Location targetLocation = new Location(this.getLocation(), direction);

        if (Util.isValidLocation(world, targetLocation)) {
            if (!Util.isLocationEmpty(world, targetLocation)) {
                for (Item item : world.searchSurroundings(location, 1)) {
                    if (item.getLocation().equals(targetLocation)) {
                        if (item.getStrength() >= strength) {
                            this.loseStrength(Integer.MAX_VALUE);
                        } else {
                            item.loseEnergy(Integer.MAX_VALUE);
                            loseStrength(item.getStrength() /
                                10);
                        }
                        countCommands++;
                        if( countCommands == commandLimit){
                            countCommands = 0;
                            commandLimit = randomNum();
                        }
                        return new MoveCommand(this, targetLocation);
                    }
                }
            }else{
                countCommands++;
                if( countCommands == commandLimit){
                    countCommands = 0;
                    commandLimit = randomNum();
                }
                return new MoveCommand(this, targetLocation);
            }
        }else{
            countCommands = 0;
        }
        return new WaitCommand();
    }

    /**
     * A car will lose strength every time it runs over an item
     *
     * @param strength the amount of strength to lose
     */
    @Override
    public void loseStrength(int strength) {
        this.strength -= strength;
    }

    /**
     * A car will lose energy proportional to how fast it is moving
     */
    @Override
    public void controlEnergy() {
        switch(speed){
            case 1: loseEnergy(4);
            case 2: loseEnergy(3);
            case 3: loseEnergy(2);
            case 4: loseEnergy(1);
            default: loseEnergy(0);
        }
    }

    public int randomNum(){
        return (int)Math.random() * (40 - 0 + 1) + 40;
    }

}
