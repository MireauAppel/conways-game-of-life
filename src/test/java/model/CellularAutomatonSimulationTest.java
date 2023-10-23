package model;

import datastruct.Coordinate;
import javafx.scene.paint.Color;
import model.automata.GameOfLifeAutomaton;
import static model.automata.GameOfLifeState.*;
import model.automata.GameOfLifeState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;


class CellularAutomatonSimulationTest {

    private final CellularAutomatonSimulation<GameOfLifeState> simulation;
    private final CellularAutomaton<GameOfLifeState> automaton;
    private final Random randomGenerator;

    CellularAutomatonSimulationTest() {
        this.randomGenerator = new Random();
        this.automaton = new GameOfLifeAutomaton(4,3);
        this.simulation = new CellularAutomatonSimulation<>(automaton, randomGenerator);
    }
    @BeforeEach
    public void setUp() {
        this.simulation.clear();
    }

    @Test
    public void testNumberOfColumns() {
        assertEquals(4, simulation.numberOfColumns());
    }

    @Test
    public void testNumberOfRows() {
        assertEquals(3, simulation.numberOfRows());
    }

    @Test
    public void testAt() {
        Coordinate coordinate = Coordinate.of(1, 2);
        Cell<GameOfLifeState> cell = simulation.at(coordinate);
        assertEquals(DEAD,cell.get());
    }

    @Test
    public void testUpdateToNextGeneration() {
        GameOfLifeState[][] input =
                { {ALIVE, ALIVE, ALIVE, DEAD},
                  {ALIVE, DEAD, DEAD, DEAD},
                  {DEAD, ALIVE, DEAD, DEAD}
                };
        GameOfLifeState[][] output =
                { {ALIVE, DEAD, ALIVE, ALIVE},
                  {ALIVE, DEAD, ALIVE, ALIVE},
                  {DEAD, DEAD, ALIVE, ALIVE}
                };
        for (Coordinate coordinate : this.simulation) {
            this.simulation.at(coordinate).set(input[coordinate.y()][coordinate.x()]);
        }
        simulation.updateToNextGeneration();
        for (Coordinate coordinate : this.simulation) {
            assertEquals(
                    output[coordinate.y()][coordinate.x()],
                    this.simulation.at(coordinate).get(),
                    "Generation at " + coordinate
            );
        }
    }

    @Test
    public void testNext() {
        Coordinate coordinate = Coordinate.of(1, 1);
        Cell<GameOfLifeState> cell = simulation.at(coordinate);
        GameOfLifeState oldState = cell.get();
        simulation.cycleThrough(coordinate);
        assertNotEquals(oldState, cell.get());
    }

    @Test
    public void testCopy() {
        Coordinate source = Coordinate.of(1, 1);
        Coordinate destination = Coordinate.of(2, 2);
        this.simulation.at(source).set(ALIVE);
        simulation.copy(source, destination);
        assertEquals(
                ALIVE,
                this.simulation.at(destination).get()
        );
    }

    @Test
    public void testColor() {
        Coordinate coordinate = Coordinate.of(1, 1);
        assertEquals(Color.WHITE, this.simulation.getColor(coordinate));
        this.simulation.at(coordinate).set(ALIVE);
        assertEquals(Color.RED, this.simulation.getColor(coordinate));
    }

    @Test
    public void testSetChangeListener() {
        Coordinate coordinate = Coordinate.of(1, 1);
        Coordinate otherCoordinate = Coordinate.of(0,2);
        Cell<GameOfLifeState> cell = simulation.at(coordinate);
        List<GameOfLifeState> states = new ArrayList<>();
        Runnable listener = () -> states.add(this.simulation.at(coordinate).get());
        simulation.setChangeListener(coordinate, listener);
        this.simulation.at(otherCoordinate).set(ALIVE);
        assertEquals(Collections.emptyList(), states);
        this.simulation.at(coordinate).set(ALIVE);
        this.simulation.at(otherCoordinate).set(DEAD);
        assertEquals(List.of(ALIVE), states);
        this.simulation.at(coordinate).set(ALIVE);
        this.simulation.at(otherCoordinate).set(ALIVE);
        this.simulation.at(coordinate).set(DEAD);
        assertEquals(List.of(ALIVE, ALIVE, DEAD), states);
    }

    @Test
    public void testSetGenerationNumberChangeListener() {
        List<Integer> values = new ArrayList<>();
        OnChangeListener<Integer> listener = (oldValue, newValue) -> values.add(newValue);
        simulation.setGenerationNumberChangeListener(listener);
        assertEquals(Collections.emptyList(), values);
        simulation.clear();
        assertEquals(List.of(0), values);
        simulation.updateToNextGeneration();
        assertEquals(List.of(0,1), values);
        simulation.updateToNextGeneration();
        simulation.updateToNextGeneration();
        assertEquals(List.of(0,1,2,3), values);
    }

    @Test
    public void testClear() {
        for (Coordinate coordinate : this.simulation) {
            this.simulation.at(coordinate).set(ALIVE);
        }
        this.simulation.clear();
        for (Coordinate coordinate : this.simulation) {
            assertEquals(DEAD, this.simulation.at(coordinate).get());
        }
    }

    @Test
    public void testReset() {
        randomGenerator.setSeed(321);
        this.simulation.reset();
        int count = 0;
        for (Coordinate coordinate : this.simulation) {
            if (this.simulation.at(coordinate).get().equals(ALIVE)) {
                count = count + 1;
            }
        }
        assertEquals(7, count);
    }
}