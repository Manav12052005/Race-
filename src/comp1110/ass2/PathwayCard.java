package comp1110.ass2;

import java.util.Random;

public class PathwayCard {

    private Square[][] tiles;

    public void rotate(String direction) {

        Square[][] rotatedCard = new Square[3][3];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (direction.equals("east")) {
                    // Rotate 90 degrees clockwise
                    rotatedCard[col][2 - row] = tiles[row][col];
                } else {
                    // Rotate 90 degrees counter-clockwise
                    rotatedCard[2 - col][row] = tiles[row][col];
                }
            }
        }

        tiles = rotatedCard; // Update the card's tiles
    }
    public static String[] cardPickUp(String decks, String hand, String drawRequest) {

        //Creating individual deck strings
        String deckA = decks.substring(decks.indexOf('A'), decks.indexOf('B'));
        String deckB = decks.substring(decks.indexOf('B'), decks.indexOf('C'));
        String deckC = decks.substring(decks.indexOf('C'), decks.indexOf('D'));
        String deckD = decks.substring(decks.indexOf('D'));

        //Creating corresponding individual draw requests
        int reqA = Integer.parseInt(drawRequest.substring(drawRequest.indexOf('A') + 1, drawRequest.indexOf('B')));
        int reqB = Integer.parseInt(drawRequest.substring(drawRequest.indexOf('B') + 1, drawRequest.indexOf('C')));
        int reqC = Integer.parseInt(drawRequest.substring(drawRequest.indexOf('C') + 1, drawRequest.indexOf('D')));
        int reqD = Integer.parseInt(drawRequest.substring(drawRequest.indexOf('D') + 1));

        if((deckA.length()-1) < reqA || (deckB.length()-1) < reqB || (deckC.length()-1) < reqC ||
                (deckD.length()-1) < reqD){
            return new String[]{decks, hand};
        }

        String newHand = "";
        String modifiedDecks = ""; // Will hold all modified decks

        // Get the results for each deck
        String[] deckA_results = cardPick(reqA, deckA);
        String[] deckB_results = cardPick(reqB, deckB);
        String[] deckC_results = cardPick(reqC, deckC);
        String[] deckD_results = cardPick(reqD, deckD);

        // Add picked cards to newHand
        newHand += deckA_results[1];
        newHand += deckB_results[1];
        newHand += deckC_results[1];
        newHand += deckD_results[1];

        // Concatenate the modified decks
        modifiedDecks += deckA_results[0];
        modifiedDecks += deckB_results[0];
        modifiedDecks += deckC_results[0];
        modifiedDecks += deckD_results[0];

        return new String[]{modifiedDecks, newHand};

    }

    private static String[] cardPick(int request, String deck) {
        StringBuilder pickedCards = new StringBuilder();
        pickedCards.append(deck.charAt(0));
        StringBuilder modDeck = new StringBuilder(deck);
        Random random = new Random();

        for (int i = 0; i < request; i++) {
            int randomIndex = random.nextInt(modDeck.length() - 1) + 1;
            char pickedCard = modDeck.charAt(randomIndex);
            pickedCards.append(pickedCard);

            modDeck.deleteCharAt(randomIndex);
        }

        return new String[]{modDeck.toString(), pickedCards.toString()};

    }
}
