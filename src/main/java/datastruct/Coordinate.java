package datastruct;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a 2D integer coordinate used to specify positions in a grid.
 */
public record Coordinate(int x, int y) {

    /**
     * Creates a new {@link Coordinate} instance with the given {@code x} and {@code y} values.
     *
     * @param x The x-coordinate value.
     * @param y The y-coordinate value.
     * @return A new {@link Coordinate} instance.
     */
    public static Coordinate of(int x, int y) {
        return new Coordinate(x,y);
    }

    /**
     * Computes and returns the {@link Coordinate} to the left of this one.
     *
     * @return The left adjacent {@link Coordinate}.
     */
    public Coordinate left() {
        return new Coordinate(this.x - 1, this.y);
    }

    /**
     * Computes and returns the {@link Coordinate} to the right of this one.
     *
     * @return The right adjacent {@link Coordinate}.
     */
    public Coordinate right() {
        return new Coordinate(this.x + 1, this.y);
    }

    /**
     * Computes and returns the {@link Coordinate} above this one.
     *
     * @return The above adjacent {@link Coordinate}.
     */
    public Coordinate above() {
        return new Coordinate(this.x, this.y + 1);
    }

    /**
     * Computes and returns the {@link Coordinate} below this one.
     *
     * @return The below adjacent {@link Coordinate}.
     */
    public Coordinate below() {
        return new Coordinate(this.x, this.y - 1);
    }

    /**
     * Computes and returns a list of orthogonal (adjacent in horizontal or vertical direction) neighbors.
     *
     * @return A list of orthogonal neighboring {@link Coordinate}s.
     */
    public List<Coordinate> orthogonalNeighbours() {
        return List.of(
                this.right(),
                this.above(),
                this.left(),
                this.below()
        );
    }

    /**
     * Computes and returns a list of diagonal (adjacent in diagonal direction) neighbors.
     *
     * @return A list of diagonal neighboring {@link Coordinate}s.
     */
    public List<Coordinate> diagonalNeighbours() {
        return List.of(
                this.right().above(),
                this.left().above(),
                this.left().below(),
                this.right().below()
        );
    }

    /**
     * Computes and returns a list of all orthogonal and diagonal neighbors.
     *
     * @return A list of all neighboring {@link Coordinate}s.
     */
    public List<Coordinate> orthodiagonalNeighbours() {
        List<Coordinate> neighbours = new ArrayList<>();
        neighbours.addAll(this.orthogonalNeighbours());
        neighbours.addAll(this.diagonalNeighbours());
        return neighbours;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}