package comp1110.ass2;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Card extends ImageView {
    private static final double SQUARE_WIDTH = Square.SQUARE_WIDTH;
    private double cardX;
    private double cardY;
    private Group img;

    private ArrayList<Square> card;

    public Card(String str) {
        ArrayList<Square> card = new ArrayList<>();
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            double x = (i % 3) * SQUARE_WIDTH;
            double y = (i / 3) * SQUARE_WIDTH;
            Square current = new Square(x, y, chars[i]);

//            System.out.println(x);
//            System.out.println(y);
//            System.out.println();

//            System.out.println(chars[i]);
            card.add(current);
        }

        this.card = card;
    }

    public double getCardX() {
        return cardX;
    }

    public double getCardY() {
        return cardY;
    }

    public Group getImg() {
        return img;
    }

    public ArrayList<Square> getCard() {
        return card;
    }

    public static String discardCard(String handString, String movementString) {
        String discardString = movementString.substring(9);
        char[] hand = handString.toCharArray();
        for (int i = 0; i < discardString.length(); i += 2) {
            String discard = discardString.substring(i, i + 2);
            char deck = discard.charAt(0);
            char[] newHand = new char[hand.length - 1];
            for (int j = 0; j < newHand.length; j++) {
                if (hand[j] != deck) {
                    newHand[j] = hand[j];
                } else {
                    for (int k = j; k < hand.length - 1; k++) {
                        newHand[k] = hand[k + 1];
                    }
                    break;
                }
            }
            hand = newHand;
        }

        return hand.toString();
    }
}
