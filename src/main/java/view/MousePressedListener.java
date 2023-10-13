package view;

import datastruct.Coordinate;
import javafx.scene.input.MouseEvent;

public class MousePressedListener implements MouseListener {

    private final Coordinate pressedCoordinate;
    private final MatrixPane matrix;
    public MousePressedListener(MatrixPane matrix, Coordinate coordinate) {
        this.matrix = matrix;
        this.pressedCoordinate = coordinate;
    }

    @Override
    public void onMouseEntered(MouseEvent event, Coordinate coordinate) {
        this.matrix.getController().getSimulation().copy(pressedCoordinate,coordinate);
        this.matrix.setMouseListener(new DraggingListener(this.matrix, pressedCoordinate));
    }

    @Override
    public void onMousePressed(MouseEvent event, Coordinate coordinate) {
        this.matrix.setMouseListener(new MousePressedListener(this.matrix, coordinate));
    }

    @Override
    public void onMouseReleased(MouseEvent event, Coordinate coordinate) {
        if (coordinate.equals(pressedCoordinate)) {
            this.matrix.getController().getSimulation().next(coordinate);
        } else {
            this.matrix.getController().getSimulation().copy(pressedCoordinate,coordinate);
        }
        this.matrix.resetWaitingListener();
    }
}
