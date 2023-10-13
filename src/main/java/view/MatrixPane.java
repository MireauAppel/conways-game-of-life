package view;

import controller.Controller;
import datastruct.Coordinate;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

/**
 * Created by Arnaud Labourel on 22/11/2018.
 */
public class MatrixPane extends GridPane {
    private static final double CELL_SIZE = 14;

    public Controller getController() {
        return controller;
    }

    private Controller controller;

    public void initialize(Controller controller) {
        this.controller = controller;
        for (Coordinate coordinate : controller.coordinates()) {
            addCellRectangle(coordinate);
        }
    }

    private void addCellRectangle(Coordinate coord) {
        Rectangle rectangleCell = new Rectangle(CELL_SIZE, CELL_SIZE);
        addStatePropertyListener(rectangleCell, coord);
        updateFill(rectangleCell, coord);
        addEventHandler(rectangleCell, coord);
        add(rectangleCell, coord);
    }

    private void add(Rectangle rectangleCell, Coordinate coord) {
        this.add(rectangleCell, coord.x(), coord.y());
    }

    private void addStatePropertyListener(Rectangle cellRectangle, Coordinate coord) {
        controller.getSimulation().setChangeListener(
                coord,
                () -> updateFill(cellRectangle, coord)
        );
    }

    private void updateFill(Rectangle cellRectangle, Coordinate coord) {
        Color color = this.controller.getSimulation().getColor(coord);
        cellRectangle.setFill(color);
    }

    private void addEventHandler(Rectangle cellRectangle, Coordinate coord) {

        cellRectangle.addEventHandler(
                MouseEvent.MOUSE_PRESSED,
                event -> mouseListener.onMousePressed(event, coord)
        );
        cellRectangle.addEventHandler(
                MouseEvent.DRAG_DETECTED,
                event -> this.startFullDrag()
        );
        cellRectangle.addEventHandler(
                MouseEvent.MOUSE_CLICKED,
                event -> mouseListener.onMouseClicked(event, coord)
        );
        cellRectangle.addEventHandler(
                MouseDragEvent.MOUSE_RELEASED,
                event -> mouseListener.onMouseReleased(event, coord)
        );
        cellRectangle.addEventHandler(
                MouseDragEvent.MOUSE_DRAG_ENTERED,
                event -> mouseListener.onMouseEntered(event, coord)
        );
    }

    private MouseListener mouseListener = new WaitingMouseListener(this);

    void setMouseListener(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
    }

    void resetWaitingListener() {
        setMouseListener(new WaitingMouseListener(this));
    }


}
