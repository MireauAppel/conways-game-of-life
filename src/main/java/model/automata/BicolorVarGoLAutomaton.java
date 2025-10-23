package model.automata;

import model.CellularAutomaton;

import java.util.Random;
import java.util.random.RandomGenerator;

public class BicolorVarGoLAutomaton implements CellularAutomaton<BicolorVarGoLState> {
    private final int numberOfColumns;
    private final int numberOfRows;

    public BicolorVarGoLAutomaton (int numberOfColumns, int numberOfRows) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
    }

    @Override
    public int numberOfColumns() {
        return numberOfColumns;
    }

    @Override
    public int numberOfRows() {
        return numberOfRows;
    }

    @Override
    public BicolorVarGoLState  defaultState() {
        return BicolorVarGoLState.DEAD;
    }

    @Override
    public BicolorVarGoLState randomState(Random generator) {
        return generator.nextBoolean() ? BicolorVarGoLState.randomState1(generator) : BicolorVarGoLState.DEAD;
    }
}
