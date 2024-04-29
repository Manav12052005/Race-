package comp1110.ass2;

import javafx.scene.Group;
import javafx.scene.image.Image;
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
}
