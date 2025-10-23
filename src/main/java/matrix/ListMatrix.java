package matrix;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a matrix, a rectangular array, with generic values in each cell.
 *
 * @param <T> The type of values stored in the matrix cells.
 */
public class ListMatrix<T> implements Matrix<T> {

  private final List<List<T>> matrix;
  private final int width;
  private final int height;

  /**
   * Creates a new {@link ListMatrix} with the specified width, height, and an initializer to set
   * values.
   *
   * @param width       The width of the {@link ListMatrix}.
   * @param height      The height of the {@link ListMatrix}.
   * @param initializer A matrix initializer to set values in the {@link ListMatrix}.
   */
  public ListMatrix(int width, int height, MatrixInitializer<T> initializer) {
    this.width = width;
    this.height = height;
    this.matrix = new ArrayList<>(height);
    this.initializeWith(initializer); // fills the matrix using initializer
  }

  public ListMatrix(int width, int height, T constant) {
    this(width, height, new ConstantMatrixInitializer<>(constant));
  }

  private void initializeWith(MatrixInitializer<T> initializer) {
    for(int i = 0; i<width; i++){
      List<T> line = new ArrayList<>(width);

      for(int j = 0; j<height; j++) {
        line.add(initializer.initialValueAt(new Coordinate(i, j)));
      }
      matrix.add(line);
    }
  }

  public int width() {
    return this.width;
  }

  public int height() {
    return this.height;
  }

  @Override
  public T get(int x, int y) {
    return matrix.get(x).get(y);
  }

  @Override
  public void set(int x, int y, T newValue) {
    matrix.get(x).set(y, newValue);
  }

  public Matrix<T> subMatrix(Coordinate corner, int width, int height) {
    return new ListMatrix<>(width, height, new SubMatrixInitializer<T>(this,corner));
  }

}