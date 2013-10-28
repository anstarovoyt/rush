package ru.naumen.core.game.impl;

import ru.naumen.core.game.Game;
import ru.naumen.core.game.GameState;

import java.util.Random;

/**
 * @author Andrey Hitrin
 * @since 21.10.13
 */
public class XOGame implements Game {

    public static final char COMPUTER_CHAR = 'O';
    public static final char USER_CHAR = 'X';
    public static final char UNUSED_CHAR = '*';
    public static final int[][] WINS = {
            {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
            {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
            {1, 5, 9}, {3, 5, 7}
    };
    public static Random RND = new Random();
    char matrix[] = {'*', '*', '*', '*', '*', '*', '*', '*', '*'};
    GameState victory = GameState.IN_PROGRESS;

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
                    result.append("|");
                }
            }

            if (i != 2) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    public static String unformat(String input) {
        return input.replace("\n", "").replace("|", "");
    }

    /**
     * @return -1 если игрок победил или текущий ход компьютера
     */
    public int calculateComputerStep() {

        return -1;
    }

    @Override
    public String computerOutput() {
        return format(matrix);
    }

    @Override
    public String getDescription() {
        return "You have a 3x3 board. You can put X symbols anywhere, one per turn. Your task is to put 3 X'es in a line.";
    }

    @Override
    public String getId() {
        return "xo";
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

    private boolean isFieldFilled() {
        for (char turn : matrix) {
            if (turn == UNUSED_CHAR) {
                return false;
            }
        }

        return true;
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

    public boolean isUserWin() {
        for (int[] line : WINS) {
            if (isWin(line, USER_CHAR)) {
                return true;
            }
        }
        return false;
    }

    public int getExpectedUserWin() {
        return winStep(USER_CHAR);
    }

    public int getComputerWin() {
        return winStep(COMPUTER_CHAR);
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

    @Override
    public String output() {
        return null;
    }

    @Override
    public GameState state() {
        return victory;
    }

    void setMatrix(char[] newState) {
        matrix = newState;
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

    private boolean canWin(int[] line, char turn) {
        return countOfState(line, UNUSED_CHAR) == 1
                && countOfState(line, turn) == 2;
    }

    private boolean isWin(int[] line, char turn) {
        return countOfState(line, turn) == 3;
    }
}
