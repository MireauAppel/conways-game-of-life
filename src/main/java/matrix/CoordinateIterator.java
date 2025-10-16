package matrix;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An {@link Iterator} for generating 2D {@link Coordinate}s within a specified width and
 * height range.
 */
class CoordinateIterator implements Iterator<Coordinate> {
    private Coordinate current = new Coordinate(0,0);
    private int width;
    private int height;

    /**
     * Creates a new {@link CoordinateIterator} with the specified width and height.
     *
     * @param width  The width of the coordinate range.
     * @param height The height of the coordinate range.
     */
    public CoordinateIterator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Checks if there are more {@link Coordinate}s to iterate over.
     *
     * @return true if there are more {@link Coordinate}s; otherwise, false.
     */
    @Override
    public boolean hasNext() {
        return current.x() < width && current.y() < height;
    }

    /**
     * Returns the next {@link Coordinate} in the iteration.
     *
     * @return The next {@link Coordinate} in the iteration.
     * @throws NoSuchElementException if there are no more {@link Coordinate}s to iterate over.
     */
    @Override
    public Coordinate next() {
        if (!hasNext()) throw new NoSuchElementException(); {}
        Coordinate next = new Coordinate(current.x(), current.y());
        current = current.right();
        if (current.x()==width)
            current = new  Coordinate(0,current.y()+1);

        return next;
    }
}
