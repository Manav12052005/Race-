package comp1110.ass2;

import java.util.ArrayList;

/**
 * holds whole board state (in an ArrayList of squares)
 */
public class Board {
    private ArrayList<Square> squares;

    public Board(String boardString) {
        ArrayList<Square> squares = new ArrayList<>();
        boardString.lines().forEach(line -> {
            double x = 0, y = (squares.size() / line.length()) * 38;
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
}
