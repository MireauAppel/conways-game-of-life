package controller;

import datastruct.Coordinate;
import javafx.scene.paint.Color;
import model.OnChangeListener;

/**
 * Represents a simulation of a 2D cellular automaton, such as the Game of Life.
 * Provides methods for updating the simulation, retrieving information, and managing listeners.
 */
public interface Simulation extends Iterable<Coordinate> {

    /**
     * Returns the number of columns in the simulation grid.
     *
     * @return The number of columns in the grid.
     */
    int numberOfColumns();

    /**
     * Returns the number of rows in the simulation grid.
     *
     * @return The number of rows in the grid.
     */
    int numberOfRows();


    /**
     * Updates the simulation to the next generation. This is done by computing, for each
     * coordinate, a new state that depends on the states of its neighbours.
     */
    void updateToNextGeneration();

    /**
     * Changes the state at a given {@link Coordinate}. This is used to edit the grid with the mouse. It
     * is not part of the simulation of the cellular automaton.
     *
     * @param coordinate The {@link Coordinate} to advance to the next state.
     */
    void cycleThrough(Coordinate coordinate);

    /**
     * Copies the state from the source {@link Coordinate} to the destination {@link Coordinate}.
     *
     * @param source      The source {@link Coordinate}.
     * @param destination The destination {@link Coordinate}.
     */
    void copy(Coordinate source, Coordinate destination);

    /**
     * Gets the {@link Color} associated with the state at the specified {@link Coordinate}.
     *
     * @param coordinate The {@link Coordinate} to retrieve the color for.
     * @return The {@link Color} associated with the state at the specified {@link Coordinate}.
     */
    Color getColor(Coordinate coordinate);

    /**
     * Sets a listener to be executed when the state at the specified {@link Coordinate} changes.
     *
     * @param coordinate The {@link Coordinate} to listen for changes.
     * @param listener   The listener to execute when the state changes.
     */
    void setChangeListener(Coordinate coordinate, Runnable listener);

    /**
     * Sets a listener to be executed when the generation number changes.
     *
     * @param listener The listener to execute when the generation number changes.
     */
    void setGenerationNumberChangeListener(OnChangeListener<Integer> listener);

    /**
     * Resets the simulation to random states.
     */
    void reset();

    /**
     * Clears the simulation, setting all states to their default values.
     */
    void clear();
}
