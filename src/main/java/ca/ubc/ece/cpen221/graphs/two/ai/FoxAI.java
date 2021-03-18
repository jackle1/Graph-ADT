package ca.ubc.ece.cpen221.graphs.two.ai;

import ca.ubc.ece.cpen221.graphs.two.ArenaWorld;
import ca.ubc.ece.cpen221.graphs.two.Direction;
import ca.ubc.ece.cpen221.graphs.two.Location;
import ca.ubc.ece.cpen221.graphs.two.Util;
import ca.ubc.ece.cpen221.graphs.two.commands.*;
import ca.ubc.ece.cpen221.graphs.two.items.Item;
import ca.ubc.ece.cpen221.graphs.two.items.animals.ArenaAnimal;

/**
 * Your Fox AI.
 */
public class FoxAI extends AbstractAI {

    private int breedCoolDown;
    private int countCommands;
    private int movementCoolDown;
    private int countMovement;
    private Direction currentDirection;

    public FoxAI() {
        breedCoolDown = (int) Math.random() * (4 - 0 + 1) + 0;
        countCommands = 0;
        movementCoolDown = (int) Math.random() * (11 - 2 + 1) + 2;
        countMovement = 0;
        currentDirection = Util.getRandomDirection();
    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        Location FoxLoc = animal.getLocation();

        if (animal.getEnergy() > ((double)animal.getMaxEnergy()) * (3 / 4)) {
            if (countMovement >= movementCoolDown) {
                Direction random = Util.getRandomDirection();
                Location targetLoc3 = new Location(FoxLoc, random);
                if (Util.isValidLocation(world, targetLoc3)) {
                    if (isLocationEmpty(world, animal, targetLoc3)) {
                        countCommands++;
                        currentDirection = random;
                        countMovement = 0;
                        movementCoolDown = (int) Math.random() * (11 - 2 + 1) + 2;
                        return new MoveCommand(animal, targetLoc3);
                    }
                }
            }
            else {
                Location forward = new Location(FoxLoc, currentDirection);
                if (Util.isValidLocation(world, forward)) {
                    if(isLocationEmpty(world, animal, forward)) {
                        countCommands++;
                        countMovement++;
                        return new MoveCommand(animal, forward);
                    }
                }
            }
        }

        for (Item item : world.searchSurroundings(animal)) {
            if (item.getName().equals("Rabbit")) {
                if (item.getLocation().getDistance(FoxLoc) == 1) {
                    countCommands++;
                    return new EatCommand(animal, item);
                } else {
                    Direction toFood = Util.getDirectionTowards(FoxLoc, item.getLocation());
                    Location targetLoc1 = new Location(FoxLoc, toFood);

                    if (Util.isValidLocation(world, targetLoc1)) {
                        if (isLocationEmpty(world, animal, targetLoc1)) {
                            countCommands++;
                            return new MoveCommand(animal, targetLoc1);
                        }
                    }
                }
            }
        }

        if (countCommands >= breedCoolDown) {
            Direction breedDir = Util.getRandomDirection();
            Location breedLoc = new Location(FoxLoc, breedDir);

            if (Util.isValidLocation(world, breedLoc)) {
                if (isLocationEmpty(world, animal, breedLoc)) {
                    if (animal.getEnergy() >= animal.getMinimumBreedingEnergy()) {
                        countCommands = 0;
                        breedCoolDown = (int) Math.random() * (4 - 0 + 1) + 0;;
                        return new BreedCommand(animal, breedLoc);
                    }
                }
            }
        }

        Direction random = Util.getRandomDirection();
        Location targetLoc2 = new Location(FoxLoc, random);
        if (Util.isValidLocation(world, targetLoc2)) {
            if (isLocationEmpty(world, animal, targetLoc2)) {
                countCommands++;
                return new MoveCommand(animal, targetLoc2);
            }
        }

        countCommands++;
        return new WaitCommand();
    }

}
