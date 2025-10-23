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
        return switch(this){
            case ALIVE -> Color.RED;
            case DEAD -> Color.WHITE;
        };
    }

    @Override
    public GameOfLifeState next() {
        return switch(this){
            case ALIVE -> DEAD;
            case DEAD -> ALIVE;
        };
    }

    @Override
    public GameOfLifeState update(List<GameOfLifeState> neighbours) {
        int counter = State.count(ALIVE, neighbours);
            if (this.equals(ALIVE) && (counter == 2 || counter == 3)) {
                return ALIVE;
            }
            if (this.equals(DEAD) && counter == 3) {
                return ALIVE;
            }
        return DEAD;
    }

}
