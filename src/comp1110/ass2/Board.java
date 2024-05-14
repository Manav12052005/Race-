package comp1110.ass2;

import java.util.ArrayList;
import java.util.Random;

import static comp1110.ass2.Utility.RECTANGLE_BOARDS;
import static comp1110.ass2.Utility.SQUARE_BOARDS;


/**
 * holds whole board state (in an ArrayList of squares)
 */
public class Board {
    private static final double SQUARE_WIDTH = Square.SQUARE_WIDTH;
    private ArrayList<Square> squares;

    // Enum to represent each board and its orientation
    public enum BoardName {
        SQUARE_BOARD_1, SQUARE_BOARD_2, SQUARE_BOARD_3, SQUARE_BOARD_4,
        RECTANGLE_BOARD_1, RECTANGLE_BOARD_2, RECTANGLE_BOARD_3, RECTANGLE_BOARD_4
    }

    // Method to retrieve board strings based on board name and orientation

    public static String getBoard(BoardName boardName, boolean withFire) {
        String[][] boardArray;

        // Select the board array based on the input board name
        switch (boardName) {
            case SQUARE_BOARD_1:
                boardArray = SQUARE_BOARDS;
                break;
            case SQUARE_BOARD_2:
                boardArray = SQUARE_BOARDS;
                break;
            case SQUARE_BOARD_3:
                boardArray = SQUARE_BOARDS;
                break;
            case SQUARE_BOARD_4:
                boardArray = SQUARE_BOARDS;
                break;
            case RECTANGLE_BOARD_1:
                boardArray = RECTANGLE_BOARDS;
                break;
            case RECTANGLE_BOARD_2:
                boardArray = RECTANGLE_BOARDS;
                break;
            case RECTANGLE_BOARD_3:
                boardArray = RECTANGLE_BOARDS;
                break;
            case RECTANGLE_BOARD_4:
                boardArray = RECTANGLE_BOARDS;
                break;
            default:
                throw new IllegalArgumentException("Invalid board name: " + boardName);
        }

        // Get the chosen board
        String[] board = boardArray[boardName.ordinal() % 4]; // Adjust the index based on the number of boards

        // Return the appropriate part of the board based on whether it's with fire or not
        return withFire ? board[0] : board[1];
    }


    // Constructor
    public Board(String boardString) {
        ArrayList<Square> squares = new ArrayList<>();
        boardString.lines().forEach(line -> {
            double x = 0, y = ((double) squares.size() / line.length()) * SQUARE_WIDTH;
            for (char ch : line.toCharArray()) {
                Square current = new Square(x, y, ch);
                squares.add(current);
                x += SQUARE_WIDTH;
            }
        });
        this.squares = squares;
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }


    public static char[][] charBoard(String boardGameState) { // A simple character representation of board
        String[] lines = boardGameState.split("\n");
        int rows = lines.length;
        int cols = lines[0].length();
        char[][] tiles = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            tiles[i] = lines[i].toCharArray();
        }
        return tiles;
    }

    public static String charBoardToString(char[][] tiles) {
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < tiles.length; i++) {
            boardString.append(new String(tiles[i]));
            boardString.append("\n");
        }
        return boardString.toString();
    }

}

