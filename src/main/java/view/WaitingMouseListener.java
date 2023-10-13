package view;

import datastruct.Coordinate;
import javafx.scene.input.MouseEvent;

class WaitingMouseListener implements MouseListener {


    private final MatrixPane matrix;

    WaitingMouseListener(MatrixPane matrix) {
        this.matrix = matrix;
    }

    @Override
    public void onMousePressed(MouseEvent event, Coordinate coordinate) {
        this.matrix.setMouseListener(new MousePressedListener(this.matrix, coordinate));
    }


}
