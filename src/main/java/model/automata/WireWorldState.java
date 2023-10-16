package model.automata;

import javafx.scene.paint.Color;
import model.State;

import java.util.List;

public enum WireWorldState implements State<WireWorldState> {
    DEAD, CONDUCTOR, HEAD, TAIL;

    @Override
    public Color getColor() {
        return switch (this) {
            case DEAD -> Color.BLACK;
            case CONDUCTOR -> Color.GOLD;
            case HEAD -> Color.BLUE;
            case TAIL -> Color.RED;
        };
    }

    @Override
    public WireWorldState next() {
        return switch (this) {
            case DEAD -> CONDUCTOR;
            case CONDUCTOR -> HEAD;
            case HEAD -> TAIL;
            case TAIL -> DEAD;
        };
    }

    @Override
    public WireWorldState update(List<WireWorldState> neighbours) {
        return switch (this) {
            case DEAD -> DEAD;
            case CONDUCTOR -> {
                int heads = State.count(HEAD,neighbours);
                yield heads == 1 || heads == 2 ? HEAD: CONDUCTOR;
            }
            case HEAD -> TAIL;
            case TAIL -> CONDUCTOR;
        };
    }
}
