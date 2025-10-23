package matrix;
import java.util.ArrayList;
import java.util.List;

public class SubMatrixInitializer<T> implements MatrixInitializer<T>{

    private final Matrix<T> matrix;
    private final Coordinate corner;

    public SubMatrixInitializer(Matrix<T> matrix, Coordinate corner){
        this.matrix = matrix;
        this.corner = corner;
    }

    @Override
    public T initialValueAt(Coordinate coordinate) {
        return matrix.get(coordinate.plus(corner));
    }
}