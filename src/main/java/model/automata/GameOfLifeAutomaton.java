package model.automata;

import model.CellularAutomaton;

import java.util.Random;
import java.util.random.RandomGenerator;

public class GameOfLifeAutomaton implements CellularAutomaton<GameOfLifeState> {
    private final int numberOfColumns;
    private final int numberOfRows;

    public GameOfLifeAutomaton(int numberOfColumns, int numberOfRows) {
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
    public GameOfLifeState defaultState() {
        return GameOfLifeState.DEAD;
    }

    @Override
    public GameOfLifeState randomState(Random generator) {
        return generator.nextBoolean() ? GameOfLifeState.ALIVE : GameOfLifeState.DEAD;
    }
}
