package ca.ubc.ece.cpen221.graphs.two;

import ca.ubc.ece.cpen221.graphs.two.ai.BearAI;
import ca.ubc.ece.cpen221.graphs.two.ai.FoxAI;
import ca.ubc.ece.cpen221.graphs.two.ai.RabbitAI;
import ca.ubc.ece.cpen221.graphs.two.core.WorldImpl;
import ca.ubc.ece.cpen221.graphs.two.core.WorldUI;
import ca.ubc.ece.cpen221.graphs.two.items.Gardener;
import ca.ubc.ece.cpen221.graphs.two.items.Grass;
import ca.ubc.ece.cpen221.graphs.two.items.animals.Bear;
import ca.ubc.ece.cpen221.graphs.two.items.animals.Fox;
import ca.ubc.ece.cpen221.graphs.two.items.animals.Gnat;
import ca.ubc.ece.cpen221.graphs.two.items.animals.Rabbit;
import ca.ubc.ece.cpen221.graphs.two.items.faces.SpiritedAway;
import ca.ubc.ece.cpen221.graphs.two.items.traps.AcidSpray;
import ca.ubc.ece.cpen221.graphs.two.items.traps.Bomb;
import ca.ubc.ece.cpen221.graphs.two.items.vehicles.Car;

import javax.swing.SwingUtilities;

/**
 * The Main class initializes a world with some {@link Grass}, {@link Rabbit}s,
 * {@link Fox}es, {@link Gnat}s, {@link Gardener}, etc.
 * <p>
 * You may modify or add Items/Actors to the World.
 */
public class Main {

    static final int X_DIM = 40;
    static final int Y_DIM = 40;
    static final int SPACES_PER_GRASS = 7;
    static final int INITIAL_GRASS = X_DIM * Y_DIM / SPACES_PER_GRASS;
    static final int INITIAL_GNATS = INITIAL_GRASS / 4;
    static final int INITIAL_RABBITS = INITIAL_GRASS / 4;
    static final int INITIAL_FOXES = INITIAL_GRASS / 32;
    static final int INITIAL_TIGERS = INITIAL_GRASS / 32;
    static final int INITIAL_BEARS = INITIAL_GRASS / 36;
    static final int INITIAL_BOMBS = 6;
    static final int INITIAL_ACID_SPRAYS = 8;
    static final int INITIAL_SPIRITS = INITIAL_GRASS / 80;
    static final int INITIAL_HYENAS = INITIAL_GRASS / 32;
    static final int INITIAL_CARS = INITIAL_GRASS / 100;
    static final int INITIAL_TRUCKS = INITIAL_GRASS / 150;
    static final int INITIAL_MOTORCYCLES = INITIAL_GRASS / 64;
    static final int INITIAL_MEN = INITIAL_GRASS / 150;
    static final int INITIAL_WOMEN = INITIAL_GRASS / 100;
    static final int INITIAL_HUNTERS = INITIAL_GRASS / 150;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().createAndShowWorld();
            }
        });
    }

    public void createAndShowWorld() {
        World world = new WorldImpl(X_DIM, Y_DIM);
        initialize(world);
        new WorldUI(world).show();
    }

    public void initialize(World world) {
        addGrass(world);
        world.addActor(new Gardener());

        addGnats(world);
        addRabbits(world);
        addFoxes(world);
      //  addBears(world);
     //   addBombs(world);
      //  addAcidSprays(world);
     //   addCars(world);
      //  addSpirits(world);
    }

  /*  private void addSpirits(World world){
        for(int i = 0; i < INITIAL_SPIRITS; i++){
            Location loc = Util.getRandomEmptyLocation(world);
            SpiritedAway spirit = new SpiritedAway(loc);
            world.addItem(spirit);
            world.addActor(spirit);
        }
    }

    private void addCars(World world){
        for(int i = 0; i < INITIAL_CARS; i++){
            Location loc = Util.getRandomEmptyLocation(world);
            Car car = new Car(loc);
            world.addItem(car);
            world.addActor(car);
        }
    }

    private void addAcidSprays(World world) {
        for (int i = 0; i < INITIAL_ACID_SPRAYS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            AcidSpray acidspray = new AcidSpray(loc);
            world.addItem(acidspray);
            world.addActor(acidspray);
        }
    }

    private void addBombs(World world) {
        for (int i = 0; i < INITIAL_BOMBS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Bomb bomb = new Bomb(loc);
            world.addItem(bomb);
            world.addActor(bomb);
        }
    }

    private void addBears(World world) {
        BearAI bearAI = new BearAI();
        for (int i = 0; i < INITIAL_BEARS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Bear bear = new Bear(bearAI, loc);
            world.addItem(bear);
            world.addActor(bear);
        }
    }*/

    private void addGrass(World world) {
        for (int i = 0; i < INITIAL_GRASS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            world.addItem(new Grass(loc));
        }
    }

    private void addGnats(World world) {
        for (int i = 0; i < INITIAL_GNATS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Gnat gnat = new Gnat(loc);
            world.addItem(gnat);
            world.addActor(gnat);
        }
    }

    private void addFoxes(World world) {
        FoxAI foxAI = new FoxAI();
        for (int i = 0; i < INITIAL_FOXES; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Fox fox = new Fox(foxAI, loc);
            world.addItem(fox);
            world.addActor(fox);
        }
    }

    private void addRabbits(World world) {
        RabbitAI rabbitAI = new RabbitAI();
        for (int i = 0; i < INITIAL_RABBITS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Rabbit rabbit = new Rabbit(rabbitAI, loc);
            world.addItem(rabbit);
            world.addActor(rabbit);
        }
    }
}