package ru.naumen.core.game.impl;

import java.util.Random;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

/**
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public class XOGame implements Game {


    public transient static final char COMPUTER_CHAR = 'O';
    public transient static final char USER_CHAR = 'X';
    public transient static final char UNUSED_CHAR = '*';
    public transient static final int[][] WINS = {
            {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
            {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
            {1, 5, 9}, {3, 5, 7}
    };
    private static final long serialVersionUID = 1l;
    public transient static Random RND = new Random();
    /**
     * У нас есть набор отображаемых символов
     * XO* -> мы должны уметь представлять их в виде некоторого вывода
     */
    public static String format(char[] input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result.append(input[(3 * i + j)]);
                if (j != 2) {
                    result.append(" | ");
                }
            }

            if (i != 2) {
                result.append("<br>");
            }
        }
        return result.toString();
    }
    public static String unformat(String input) {
        return input.replace("<br>", "").replace(" | ", "");
    }

    char matrix[] = {'*', '*', '*', '*', '*', '*', '*', '*', '*'};

    GameState victory = GameState.IN_PROGRESS;

    /**
     * @return -1 если игрок победил или текущий ход компьютера
     */
    public int calculateComputerStep() {

        return -1;
    }

    @Override
    public String getStateRepresentation() {
        return format(matrix);
    }

    public int getComputerWin() {
        return winStep(COMPUTER_CHAR);
    }

    @Override
    public String getDescription() {
        return "You have a 3x3 board. You can put X symbols anywhere, one per turn. Your task is to put 3 X'es in a line.";
    }

    public int getExpectedUserWin() {
        return winStep(USER_CHAR);
    }

    @Override
    public String getId() {
        return "xo";
    }

    @Override
    public String getShortDescription() {
        return "Серьёзное испытание для интеллекта";
    }

    @Override
    public String getShortName() {
        return "Крестики-нолики";
    }

    /**
     * 1 | 2 | 3
     * _________
     * 4 | 5 | 6
     * _________
     * 7 | 8 | 9
     */
    @Override
    public void input(String userInput) {
        try {
            int value = Integer.parseInt(userInput);
            if (value < 1 || value > 9 || matrix[value - 1] != UNUSED_CHAR) {
                throw new IllegalArgumentException();
            }

            matrix[value - 1] = USER_CHAR;

            if (isUserWin()) {
                victory = GameState.VICTORY;
                return;
            }

            int winStep = getComputerWin();

            if (winStep != -1) {
                matrix[winStep - 1] = COMPUTER_CHAR;
                victory = GameState.FAILURE;
                return;
            }

            int expectedUserWinStep = getExpectedUserWin();

            if (expectedUserWinStep != -1) {
                matrix[expectedUserWinStep - 1] = COMPUTER_CHAR;
                if (isFieldFilled()) {
                    victory = GameState.VICTORY;
                }
                return;
            }

            int acceptedRandomStep = getAcceptedRandomStep();
            matrix[acceptedRandomStep - 1] = COMPUTER_CHAR;

            if (isFieldFilled()) {
                victory = GameState.VICTORY;
                return;
            }

        } catch (IllegalArgumentException e) {
            //Некорректный ввод
        }
    }

    public boolean isUserWin() {
        for (int[] line : WINS) {
            if (isWin(line, USER_CHAR)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String output() {
        return null;
    }

    @Override
    public Game resetState() {
        return new XOGame();
    }

    @Override
    public GameState state() {
        return victory;
    }

    public int winStep(char turn) {
        for (int[] line : WINS) {
            if (canWin(line, turn)) {
                for (int index : line) {
                    if (matrix[index - 1] == UNUSED_CHAR) {
                        return index;
                    }
                }
            }
        }
        return -1;
    }

    void setMatrix(char[] newState) {
        matrix = newState;
    }

    private boolean canWin(int[] line, char turn) {
        return countOfState(line, UNUSED_CHAR) == 1
                && countOfState(line, turn) == 2;
    }

    private int countOfState(int[] indexes, char expectedState) {
        int count = 0;
        for (int index : indexes) {
            if (matrix[index - 1] == expectedState) {
                count++;
            }
        }
        return count;
    }

    private int getAcceptedRandomStep() {
        int randomStep = RND.nextInt(9) + 1;

        for (int i = randomStep; i < 10; i++) {
            if (matrix[i - 1] == UNUSED_CHAR) {
                return i;
            }
        }

        for (int i = randomStep; i > 0; i--) {
            if (matrix[i - 1] == UNUSED_CHAR) {
                return i;
            }
        }

        throw new IllegalStateException();
    }

    private boolean isFieldFilled() {
        for (char turn : matrix) {
            if (turn == UNUSED_CHAR) {
                return false;
            }
        }

        return true;
    }

    private boolean isWin(int[] line, char turn) {
        return countOfState(line, turn) == 3;
    }
}
