package matrix;

public class ConstantMatrixInitializer<T> implements MatrixInitializer<T> {

  public final T constant;

  public ConstantMatrixInitializer(T constant) {
    this.constant = constant;
  }

  @Override
  public T initialValueAt(Coordinate coordinate) {
    return constant;
  }
}