package model;

import datastruct.Coordinate;
import datastruct.MatrixInitializer;
import datastruct.Matrix;
import controller.Simulation;
import java.util.ArrayList;
import java.util.List;

/**
 * An initializer for a {@link Matrix} of states, where each state is computed based on the value
 * of its neighbours in a {@link Simulation} of a cellular automaton.
 *
 * @param <S> the type of states in the simulation.
 */
public class NextGenerationInitializer<S extends State<S>> implements MatrixInitializer<S> {

    private final CellularAutomatonSimulation<S> simulation;

    /** Create a {@link MatrixInitializer} to compute the next generation in
     * a 2D cellular automaton.
     *
     * @param simulation the {@link Simulation} representing the cellular automaton.
     */
    public NextGenerationInitializer(CellularAutomatonSimulation<S> simulation) {
        this.simulation = simulation;
    }

    @Override
    public S initialValueAt(Coordinate coordinate) {
        List<S> neighbours = new ArrayList<>();
        for (Coordinate neighbourCoord : coordinate.orthodiagonalNeighbours()) {
            Coordinate wrapped = wrap(neighbourCoord);
            neighbours.add(this.simulation.at(wrapped).get());
        }
        S state = this.simulation.at(coordinate).get();
        return state.update(neighbours);
    }

    /** Computes the grid {@link Coordinate} for an arbitrary {@link Coordinate}, even outside
     * the grid. This is done by considering that the grid wraps over its edges, connecting the left side to the right
     * side, and the top side to the bottom side. This way, every cell has 4 orthogonal
     * neighbours and 4 diagonal neighbours.
     *
     * @param coordinate a {@link Coordinate} that may be outside the grid.
     * @return a corresponding {@link Coordinate}, that is inside the grid.
     */
    Coordinate wrap(Coordinate coordinate) {
        return new Coordinate(
                modulo(coordinate.x(),this.simulation.numberOfColumns()),
                modulo(coordinate.y(),this.simulation.numberOfRows())
        );
    }

    /** The non-negative remainder of n divided by d.
     *
     * @param n an arbitrary integer.
     * @param d a non-zero integer.
     * @return the remainder of {@code n/d}, between {@code 0} and {@code n-1}.
     */
    static int modulo(int n, int d) {
        int result = n % d;
        return n < 0 ? result + d : result;
    }
}
