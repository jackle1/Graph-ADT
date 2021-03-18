package ca.ubc.ece.cpen221.graphs.two.ai;

import ca.ubc.ece.cpen221.graphs.two.ArenaWorld;
import ca.ubc.ece.cpen221.graphs.two.commands.Command;
import ca.ubc.ece.cpen221.graphs.two.commands.WaitCommand;
import ca.ubc.ece.cpen221.graphs.two.commands.EatCommand;
import ca.ubc.ece.cpen221.graphs.two.commands.BreedCommand;
import ca.ubc.ece.cpen221.graphs.two.items.animals.ArenaAnimal;
import ca.ubc.ece.cpen221.graphs.two.Location;
import ca.ubc.ece.cpen221.graphs.two.Util;

import ca.ubc.ece.cpen221.graphs.two.Direction;
import ca.ubc.ece.cpen221.graphs.two.commands.MoveCommand;
import ca.ubc.ece.cpen221.graphs.two.items.Item;


/**
 * Your Rabbit AI.
 */
public class RabbitAI extends AbstractAI {

    private int breedCoolDown;
    private int countCommands;
    private int movementCooldown;
    private int countMovement;
    private Direction currentDirection;

    public RabbitAI() {
        breedCoolDown = (int) Math.random() * (4 - 0 + 1) + 0;
        countCommands = 0;
        movementCooldown = (int) Math.random() * (11 - 2 + 1) + 2;
        countMovement = 0;
        currentDirection = Util.getRandomDirection();
    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        Location rabbitLoc = animal.getLocation();

        if (animal.getEnergy() > ((double)animal.getMaxEnergy()) * (3 / 4)) {
            if(countMovement >= movementCooldown) {
                Direction random = Util.getRandomDirection();
                Location targetLoc3 = new Location(rabbitLoc, random);
                if (Util.isValidLocation(world, targetLoc3)) {
                    if (isLocationEmpty(world, animal, targetLoc3)) {
                        countCommands++;
                        currentDirection = random;
                        countMovement = 0;
                        movementCooldown = (int) Math.random() * (11 - 2 + 1) + 2;
                        return new MoveCommand(animal, targetLoc3);
                    }
                }
            }else{//else we keep going forward
                Location forward = new Location(rabbitLoc, currentDirection);
                if (Util.isValidLocation(world, forward)) {
                    if (isLocationEmpty(world, animal, forward)) {
                        countCommands++;
                        countMovement++;
                        return new MoveCommand(animal, forward);
                    }
                }
            }
        }
        for (Item item : world.searchSurroundings(animal)) {
            if (item.getName().equals("grass")) {
                if (item.getLocation().getDistance(animal.getLocation()) == 1) {
                    countCommands++;
                    return new EatCommand(animal, item);
                } else {
                    Direction toFood = Util.getDirectionTowards(rabbitLoc, item.getLocation());
                    Location targetLoc1 = new Location(rabbitLoc, toFood);

                    if (Util.isValidLocation(world, targetLoc1)) {
                        if (isLocationEmpty(world, animal, targetLoc1)) {
                            countCommands++;
                            currentDirection = toFood;
                            return new MoveCommand(animal, targetLoc1);
                        }
                    }
                }

            }
        }

        if (countCommands >= breedCoolDown) {
            Direction breedDir = Util.getRandomDirection();
            Location breedLoc =
                new Location(rabbitLoc, breedDir);

            if (Util.isValidLocation(world, breedLoc)) {
                if (isLocationEmpty(world, animal, breedLoc)) {
                    if (animal.getEnergy() >= animal.getMinimumBreedingEnergy()) {
                        countCommands = 0;
                        breedCoolDown = (int) Math.random() *  (4 - 0 + 1) + 0;
                        return new BreedCommand(animal, breedLoc);
                    }
                }
            }
        }

        Direction random = Util.getRandomDirection();
        Location targetLoc3 = new Location(rabbitLoc, random);
        if (Util.isValidLocation(world, targetLoc3)) {
            if (isLocationEmpty(world, animal, targetLoc3)) {
                countCommands++;
                currentDirection = random;
                return new MoveCommand(animal, targetLoc3);
            }
        }
        countCommands++;
        return new WaitCommand();
    }
}
