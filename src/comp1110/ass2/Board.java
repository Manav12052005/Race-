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
            int x = 0, y = (squares.size() / line.length()) * 38;
            int index = 0;
            for (char ch : line.toCharArray()) {
                Square current = new Square(x, y, line.charAt(index));
                squares.add(current);
                index ++;
                x += 38;
            }
        });
        this.squares = squares;
    }
}
