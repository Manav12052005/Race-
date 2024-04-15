package comp1110.ass2;

import java.util.Random;

public class PathwayCard {

    private Square[][] tiles;

    public void rotate(String direction) {

        Square[][] rotatedCard = new Square[3][3];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (direction.equals("east")) {
                    // Rotate card 90 degrees clockwise
                    rotatedCard[col][2 - row] = tiles[row][col];
                } else {
                    // Rotate card 90 degrees counter-clockwise
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

        int[] requests = new int[]{0, 0, 0, 0}; // Default: 0 for each deck (A, B, C, D)

        for (int i = 0; i < drawRequest.length(); i++) {
            char currentChar = drawRequest.charAt(i);

            if (Character.isDigit(currentChar)) { // Skip any non-digit characters
                continue;
            }

            int nextIndex = i + 1;
            while (nextIndex < drawRequest.length() && Character.isDigit(drawRequest.charAt(nextIndex))) {
                nextIndex++;
            }

            String valueStr = drawRequest.substring(i + 1, nextIndex);
            int value = Integer.parseInt(valueStr);

            switch (currentChar) {
                case 'A':
                    requests[0] = value;
                    break;
                case 'B':
                    requests[1] = value;
                    break;
                case 'C':
                    requests[2] = value;
                    break;
                case 'D':
                    requests[3] = value;
                    break;
            }

            i = nextIndex - 1; // Move the index to the last digit parsed
        }

        if((deckA.length()-1) < requests[0] || (deckB.length()-1) < requests[1] || (deckC.length()-1) < requests[2] ||
                (deckD.length()-1) < requests[3]){
            return new String[]{decks, hand};
        }

        String newHand = "";
        String modifiedDecks = ""; // Will hold all modified decks

        // Sends each deck and its associated request to our cardPick function
        String[] deckA_results = cardPick(requests[0], deckA);
        String[] deckB_results = cardPick(requests[1], deckB);
        String[] deckC_results = cardPick(requests[2], deckC);
        String[] deckD_results = cardPick(requests[3], deckD);

        // Adds picked cards to complete hand
        newHand += deckA_results[1];
        newHand += deckB_results[1];
        newHand += deckC_results[1];
        newHand += deckD_results[1];

        // Reform the full decks string with removed cards
        modifiedDecks += deckA_results[0];
        modifiedDecks += deckB_results[0];
        modifiedDecks += deckC_results[0];
        modifiedDecks += deckD_results[0];

        return new String[]{modifiedDecks, newHand};

    }

    private static String[] cardPick(int request, String deck) { //receives seperated decks and requests
        StringBuilder pickedCards = new StringBuilder();
        pickedCards.append(deck.charAt(0)); //adds deck ID to our picked cards
        StringBuilder modDeck = new StringBuilder(deck);
        Random random = new Random();

        for (int i = 0; i < request; i++) {
            int randomIndex = random.nextInt(modDeck.length() - 1) + 1;
            char pickedCard = modDeck.charAt(randomIndex); //Chooses random card from deck
            pickedCards.append(pickedCard); //adds chosen card to picked cards

            modDeck.deleteCharAt(randomIndex); //removes chosen card from deck
        }

        return new String[]{modDeck.toString(), pickedCards.toString()}; //returns modified deck and full hand.

    }
}
