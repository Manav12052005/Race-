package comp1110.ass2;

import java.util.ArrayList;

public class Hand {
    private static final double SQUARE_WIDTH = Square.SQUARE_WIDTH;

    private ArrayList<Square> squares;
    private ArrayList<String> cards;

    public Hand(String handString) {
        cards = handToCards(handString);
        ArrayList<Square> squares = new ArrayList<>();

        for (int i = 0; i < cards.size(); i++) {
            char[] chars = cards.get(i).toCharArray();
            double outerX = (i % 2) * 3 * SQUARE_WIDTH + (i % 2) * 10,
                    outerY = (i / 2) * 3 * SQUARE_WIDTH + (i / 2) * 10;
            for (int j = 0; j < chars.length; j++) {
                double x = (j % 3) * SQUARE_WIDTH + outerX;
                double y = (j / 3) * SQUARE_WIDTH + outerY;
                Square current = new Square(x, y, chars[j]);
                System.out.println(chars[j]);
                squares.add(current);
            }
        }

        this.squares = squares;
    }

    /**
     *
     * @param hand given hand string (see the STRING_REPRESENTATION.md)
     * @return ret an ArrayList of Strings with each card in hand represented as a String element
     */
    public static ArrayList<String> handToCards(String hand) {
        ArrayList<String> ret = new ArrayList<>();
        char[] handChar = hand.toCharArray();
        String[] deck;

        for (int i = 0; i < handChar.length; i++) {
            if (handChar[i] == 'A' || handChar[i] == 'B' || handChar[i] == 'C' || handChar[i] == 'D') {
                deck = switch (handChar[i]) {
                    case 'A' -> Utility.DECK_A;
                    case 'B' -> Utility.DECK_B;
                    case 'C' -> Utility.DECK_C;
                    case 'D' -> Utility.DECK_D;
                    default -> throw new IllegalStateException("Unexpected value: " + handChar[i]);
                };
                int j = i + 1;
                while (j < handChar.length && handChar[j] != 'A' && handChar[j] != 'B' &&
                        handChar[j] != 'C' && handChar[j] != 'D') {
                    for (String str : deck) {
                        if (str.charAt(0) == handChar[j]) {
                            ret.add(str.substring(1));
                        }
                    }
                    j++;
                }
            }
        }
        return ret;
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }

    // for debugging use
//    public static void main(String[] args) {
//        String hand = "AfhkDahw";
//        ArrayList<String> list = new ArrayList<>();
//        list = handToCards(hand);
//        for (String str : list) {
//            System.out.println(str);
//        }
//    }
}
