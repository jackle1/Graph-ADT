package ca.ubc.ece.cpen221.graphs.two.ai;

import ca.ubc.ece.cpen221.graphs.two.ArenaWorld;
import ca.ubc.ece.cpen221.graphs.two.commands.Command;
import ca.ubc.ece.cpen221.graphs.two.commands.WaitCommand;
import ca.ubc.ece.cpen221.graphs.two.items.animals.ArenaAnimal;

public class BearAI extends AbstractAI implements AI{
    //UNUSED, bears just use the default AreanAnimalAI
    public BearAI() {

    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        return new WaitCommand();
    }
}
