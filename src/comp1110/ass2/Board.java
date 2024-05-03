package comp1110.ass2;

import java.util.ArrayList;

/**
 * holds whole board state (in an ArrayList of squares)
 */
public class Board {
    private static final double SQUARE_WIDTH = Square.SQUARE_WIDTH;
    private ArrayList<Square> squares;

    // Constructor
    public Board(String boardString) {
        ArrayList<Square> squares = new ArrayList<>();
        boardString.lines().forEach(line -> {
            double x = 0, y = (squares.size() / line.length()) * SQUARE_WIDTH;
            for (char ch : line.toCharArray()) {
                Square current = new Square(x, y, ch);
                squares.add(current);
                x += 38;
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