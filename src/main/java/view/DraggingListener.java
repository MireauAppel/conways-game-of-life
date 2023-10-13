package view;

import datastruct.Coordinate;
import javafx.scene.input.MouseEvent;

public class DraggingListener implements MouseListener {
    private final MatrixPane matrix;
    private final Coordinate source;


    public DraggingListener(MatrixPane matrix, Coordinate source) {
        this.matrix = matrix;
        this.source = source;
    }

    @Override
    public void onMouseReleased(MouseEvent event, Coordinate coordinate) {
        this.matrix.resetWaitingListener();
    }

    @Override
    public void onMouseEntered(MouseEvent event, Coordinate destination) {
        if (!event.isPrimaryButtonDown()) {
            this.matrix.resetWaitingListener();
            return;
        }
        this.matrix.getController().getSimulation().copy(source, destination);
    }

    @Override
    public void onMousePressed(MouseEvent event, Coordinate coordinate) {
        this.matrix.setMouseListener(
                new MousePressedListener(this.matrix, coordinate)
        );
    }

}
