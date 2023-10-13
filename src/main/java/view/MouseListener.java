package view;

import datastruct.Coordinate;
import javafx.scene.input.MouseEvent;

interface MouseListener {

    default void onMouseClicked(MouseEvent event, Coordinate coordinate) {}
    default void onMousePressed(MouseEvent event, Coordinate coordinate) {}
    default void onMouseReleased(MouseEvent event, Coordinate coordinate) {}
    default void onMouseEntered(MouseEvent event, Coordinate coordinate) {};


}
