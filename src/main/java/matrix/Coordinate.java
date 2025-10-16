package matrix;

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
        return new Coordinate(x, y) ;
    }

    /**
     * Computes and returns the {@link Coordinate} to the left of this one.
     *
     * @return The left adjacent {@link Coordinate}.
     */
    public Coordinate left() {
        return new  Coordinate(x-1, y) ;
    }

    /**
     * Computes and returns the {@link Coordinate} to the right of this one.
     *
     * @return The right adjacent {@link Coordinate}.
     */
    public Coordinate right() {
        return new Coordinate(x+1, y) ;
    }

    /**
     * Computes and returns the {@link Coordinate} above this one.
     *
     * @return The above adjacent {@link Coordinate}.
     */
    public Coordinate above() {
        return new Coordinate(x, y+1) ;
    }

    /**
     * Computes and returns the {@link Coordinate} below this one.
     *
     * @return The below adjacent {@link Coordinate}.
     */
    public Coordinate below() {
        return new Coordinate(x, y-1) ;
    }

    /**
     * Computes and returns a list of orthogonal (adjacent in horizontal or vertical direction) neighbors.
     *  | | | |
     * ---------
     *  | |X| |
     * ---------
     *  |X|O|X|
     * ---------
     *  | |X| |
     * ---------
     * | | | |
     * @return A list of orthogonal neighboring {@link Coordinate}s.
     */
    public List<Coordinate> orthogonalNeighbours() {

        return List.of(this.above(), this.left(), this.right(), this.below());
    }

    /**
     * Computes and returns a list of diagonal (adjacent in diagonal direction) neighbors.
     *  | | | |
     * ---------
     *  |X| |X|
     * ---------
     *  | |O| |
     * ---------
     *  |X| |X|
     * ---------
     * | | | |
     *
     * @return A list of diagonal neighboring {@link Coordinate}s.
     */
    public List<Coordinate> diagonalNeighbours() {
        return List.of(this.left().above(), this.right().above(), this.left().below(), this.right().below());
    }

    /**
     * Computes and returns a list of all orthogonal and diagonal neighbors.
     *      *  | | | |
     *      * ---------
     *      *  |X|X|X|
     *      * ---------
     *      *  |X|O|X|
     *      * ---------
     *      *  |X|X|X|
     *      * ---------
     *      * | | | |
     *
     * @return A list of all neighboring {@link Coordinate}s.
     */
    public List<Coordinate> orthodiagonalNeighbours() {
        return List.of(this.left().above(),this.above(), this.right().above(),this.left(), this.right(), this.left().below(), this.below(), this.right().below());
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

  public Coordinate minus(Coordinate corner) {
    return new Coordinate(this.x - corner.x, this.y - corner.y);
  }

  public Coordinate plus(Coordinate corner) {
      return new Coordinate(this.x + corner.x, this.y + corner.y);
  }
}