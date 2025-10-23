package model.automata;

import javafx.scene.paint.Color;
import model.State;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.List;

/**
 * {@link GameOfLifeState} instances represent the possible states of a {@link GameOfLifeState}.
 */
public enum BicolorVarGoLState implements State<BicolorVarGoLState> {
    ALIVE1, ALIVE2, DEAD;


    @Override
    public Color getColor() {
        return switch(this){
            case ALIVE1 -> Color.RED;
            case ALIVE2 -> Color.BLUE;
            case DEAD -> Color.WHITE;
        };
    }
    public static BicolorVarGoLState randomState1(Random generator) {
        return generator.nextBoolean() ? BicolorVarGoLState.ALIVE1 : BicolorVarGoLState.ALIVE2;
    }
    @Override
    public BicolorVarGoLState next() {
        return switch(this){
            case ALIVE1 -> DEAD;
            case ALIVE2 -> DEAD;
            case DEAD -> randomState1(new Random());
        };
    }

    @Override
    public BicolorVarGoLState update(List<BicolorVarGoLState> neighbours) {
        int counter1 = State.count(ALIVE1, neighbours);
        int counter2 = State.count(ALIVE2, neighbours);
        int counter = counter1 + counter2;
        if (this.equals(ALIVE1) && (counter == 2 || counter == 3)) {
            return ALIVE1;
        }
        if (this.equals(ALIVE2) && (counter == 2 || counter == 3)) {
            return ALIVE2;
        }
        if (counter1 > counter2) {
            if (this.equals(DEAD) && counter == 3) {
                return ALIVE1;
            }
        } else if (counter2 > counter1) {
            if (this.equals(DEAD) && counter == 3) {
                return ALIVE2;
            }
        }
        return DEAD;
    }

}
