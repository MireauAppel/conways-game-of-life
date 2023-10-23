package model.automata;

import javafx.scene.paint.Color;
import model.State;

import java.util.List;

/**
 * {@link GameOfLifeState} instances represent the possible states of a {@link GameOfLifeState}.
 */
public enum GameOfLifeState implements State<GameOfLifeState> {
    ALIVE, DEAD;


    @Override
    public Color getColor() {
        return switch (this) {
            case ALIVE -> Color.RED;
            case DEAD -> Color.WHITE;
        };
    }

    @Override
    public GameOfLifeState cycle() {
        return switch (this) {
            case ALIVE -> DEAD;
            case DEAD -> ALIVE;
        };
    }

    @Override
    public GameOfLifeState update(List<GameOfLifeState> neighbours) {
        int countAlive = State.count(ALIVE, neighbours);
        return (countAlive == 3 || (countAlive == 2 && this == ALIVE))?
                ALIVE:
                DEAD;
    }

}
