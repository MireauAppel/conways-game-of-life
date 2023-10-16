package model.automata;

import model.CellularAutomaton;

import java.util.Random;

public class WireWorldAutomata implements CellularAutomaton<WireWorldState> {

    private final int numberOfColumns;
    private final int numberOfRows;

    public WireWorldAutomata(int numberOfColumns, int numberOfRows) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
    }

    @Override
    public int numberOfColumns() {
        return this.numberOfColumns;
    }

    @Override
    public int numberOfRows() {
        return this.numberOfRows;
    }

    @Override
    public WireWorldState defaultState() {
        return WireWorldState.DEAD;
    }

    @Override
    public WireWorldState randomState(Random generator) {
        int r = generator.nextInt(18);
        return r < 5 ? WireWorldState.CONDUCTOR:
               r == 5 ? WireWorldState.HEAD:
                 WireWorldState.DEAD;
    }
}
