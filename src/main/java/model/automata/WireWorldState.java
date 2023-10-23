package model.automata;

import javafx.scene.paint.Color;
import model.State;

import java.util.List;

public enum WireWorldState implements State<WireWorldState> {
    DEAD, WIRE, BLUE, RED;


    @Override
    public Color getColor() {
        return switch (this) {
            case DEAD -> Color.BLACK;
            case WIRE -> Color.GOLD;
            case BLUE -> Color.BLUE;
            case RED -> Color.RED;
        };
    }

    @Override
    public WireWorldState cycle() {
        return switch (this) {
            case DEAD -> WIRE;
            case WIRE -> BLUE;
            case BLUE -> RED;
            case RED -> DEAD;
        };
    }

    @Override
    public WireWorldState update(List<WireWorldState> neighbours) {
        return switch (this) {
            case DEAD -> DEAD;
            case WIRE -> {
                int countBlues = State.count(BLUE, neighbours);
                yield (countBlues == 1 || countBlues == 2)? BLUE: WIRE;
            }
            case BLUE -> RED;
            case RED -> WIRE;
        };
    }
}
