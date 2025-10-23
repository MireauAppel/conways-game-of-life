package model;

import controller.Simulation;
import matrix.Coordinate;
import matrix.ListMatrix;
import javafx.scene.paint.Color;

import java.util.Iterator;
import java.util.Random;



/**
 * {@link CellularAutomatonSimulation} instances run <i>The Game of Life</i>.
 *
 *  @param <S> The type of state used in the simulation.
 */
public class CellularAutomatonSimulation<S extends State<S>>
        implements Simulation {

    private final ListMatrix<Cell<S>> grid;
    private final Cell<Integer> generationNumber = new Cell<>(0);
    private final CellularAutomaton<S> automaton;
    private final Random generator;

    /**
     * Creates a new {@link CellularAutomatonSimulation} instance for a given automaton.
     *
     * @param automaton  A description of the {@link CellularAutomaton}.
     * @param generator  The {@link Random} instance used for random state generation.
     */
    public CellularAutomatonSimulation(CellularAutomaton<S> automaton, Random generator) {
        this.automaton = automaton;
        this.grid = new ListMatrix<>(
                automaton.numberOfColumns(),
                automaton.numberOfRows(),
                new ConstantCellInitializer<>(automaton.defaultState())
        );
        this.generator = generator;
    }


    @Override
    public int numberOfColumns() {
        //TODO: à compléter
        return 0;
    }

    @Override
    public int numberOfRows() {
        //TODO: à compléter
        return 0;
    }

    /**
     * Returns the {@link Cell} at the specified coordinate.
     *
     * @param coordinate The coordinate of the cell to retrieve.
     * @return The cell at the specified coordinate.
     */
    public Cell<S> at(Coordinate coordinate) {
        //TODO: à compléter
        return null;
    }

    @Override
    public void updateToNextGeneration() {
        //TODO: à compléter, en utilisant nextGenerationMatrix()
    }

    /** Computes the {@link ListMatrix} of states obtained after a single step of updates
     * of the simulation.
     *
     * @return the states of each cell after one generation
     */
    private ListMatrix<S> nextGenerationMatrix() {
        //TODO: à compléter
        return null;
    }
    @Override
    public void next(Coordinate coordinate) {
        //TODO: à compléter
    }

    @Override
    public void copy(Coordinate source, Coordinate destination) {
        //TODO: à compléter
    }

    @Override
    public Color getColor(Coordinate coordinate) {
        //TODO: à compléter
        return null;
    }

    @Override
    public void setChangeListener(Coordinate coordinate, Runnable listener) {
        this.at(coordinate).addOnChangeListener(
                (oldValue, newValue) -> listener.run()
        );
    }

    @Override
    public void setGenerationNumberChangeListener(OnChangeListener<Integer> listener){
        this.generationNumber.addOnChangeListener(listener);
    }


    @Override
    public void clear() {
        //TODO: à compléter (penser à remettre le nombre de génération à 0)
    }


    @Override
    public void reset() {
        //TODO: à compléter (penser à remettre le nombre de génération à 0)
    }

    @Override
    public Iterator<Coordinate> iterator() {
        return this.grid.coordinates().iterator();
    }
}