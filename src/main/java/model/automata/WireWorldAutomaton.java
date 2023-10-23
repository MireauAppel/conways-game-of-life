package model.automata;

import model.CellularAutomaton;

import java.util.Random;

import static model.automata.WireWorldState.WIRE;

public class WireWorldAutomaton implements CellularAutomaton<WireWorldState> {

    private final int numberOfColumns;
    private final int numberOfRows;

    public WireWorldAutomaton(int numberOfColumns, int numberOfRows) {
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
    public WireWorldState defaultState() {
        return WireWorldState.DEAD;
    }

    @Override
    public WireWorldState randomState(Random generator) {
        int r = generator.nextInt(10);
        return r < 3 ? WireWorldState.WIRE: WireWorldState.DEAD;
    }
}
