package ca.ubc.ece.cpen221.graphs.two.items.animals;

import ca.ubc.ece.cpen221.graphs.two.Food;
import ca.ubc.ece.cpen221.graphs.two.Location;
import ca.ubc.ece.cpen221.graphs.two.Util;
import ca.ubc.ece.cpen221.graphs.two.World;
import ca.ubc.ece.cpen221.graphs.two.ai.AI;
import ca.ubc.ece.cpen221.graphs.two.ai.ArenaAnimalAI;
import ca.ubc.ece.cpen221.graphs.two.commands.Command;
import ca.ubc.ece.cpen221.graphs.two.items.LivingItem;

import javax.swing.*;

public class Bear extends AbstractArenaAnimal implements ArenaAnimal {

    private static final int INITIAL_ENERGY = 250;
    private static final int MAX_ENERGY = 300;
    private static final int STRENGTH = 200;
    private static final int VIEW_RANGE = 7;
    private static final int MIN_BREEDING_ENERGY = 60;
    private static final int COOLDOWN = 6;
    private static final ImageIcon bearImage = Util.loadImage("bear.gif");

    private final AI ai;

    private Location location;
    private int energy;

    public Bear(AI bearAI, Location initialLocation) {
        this.ai = new ArenaAnimalAI(energy);
        this.location = initialLocation;

        this.energy = INITIAL_ENERGY;
        setINITIAL_ENERGY(INITIAL_ENERGY);
        setMAX_ENERGY(MAX_ENERGY);
        setSTRENGTH(STRENGTH);
        setVIEW_RANGE(VIEW_RANGE);
        setCOOLDOWN(COOLDOWN);
        setMIN_BREEDING_ENERGY(MIN_BREEDING_ENERGY);
        setLocation(location);
        setEnergy(energy);
    }

    @Override
    public LivingItem breed() {
        Bear child = new Bear(ai, location);
        child.energy = energy / 3;
        this.energy = energy / 2;
        return child;
    }

    public Command getNextAction(World world) {
        Command nextAction = ai.getNextAction(world, this);
        this.energy--; // Loses 1 energy regardless of action.

        return nextAction;
    }

    @Override
    public ImageIcon getImage() {
        return bearImage;
    }

    @Override
    public String getName() {
        return "bear";
    }

    @Override
    public void loseEnergy(int energyLoss) {
        this.energy = this.energy - energyLoss;
    }

    @Override
    public boolean isDead() {
        return this.energy <= 0;
    }

}
