package comp1110.ass2;

import java.util.Random;

public class PathwayCard {
    public static String[] cardPickUp(String decks, String hand, String drawRequest){

        String deckA = decks.substring(decks.indexOf('A')+1, decks.indexOf('B'));
        String deckB = decks.substring(decks.indexOf('B')+1, decks.indexOf('C'));
        String deckC = decks.substring(decks.indexOf('C')+1, decks.indexOf('D'));
        String deckD = decks.substring(decks.indexOf('D')+1);
        String[] bag = {"thing", "stuff"};
        return bag;
    }

}
