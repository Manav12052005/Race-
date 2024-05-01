package comp1110.ass2;

import java.util.Arrays;
import java.util.Random;

import static comp1110.ass2.Board.charBoardToString;
import static comp1110.ass2.PathwayCard.Direction.*;


public class PathwayCard { ;
    private char[][] tiles;
    private int[] xy;
    public PathwayCard(char[][] tiles, int[] xy){
        this.tiles = tiles;
        this.xy = xy;
    }
    public char[][] getTiles() {
        return tiles;
    }

    enum Direction{
        NORTH, EAST, SOUTH, WEST, FLIP
    }

    public static Direction charToDirection(char c){
        switch(c){
            case 'N' -> {return NORTH;}
            case 'E' -> {return EAST;}
            case 'S' -> {return SOUTH;}
            case 'W' -> {return WEST;}
            case 'F' -> {return FLIP;}
            default -> throw new IllegalArgumentException("charToDirection input invalid");
        }
    }
    public static PathwayCard actionStringToPWC(String string){
        char deckID = string.charAt(0);
        char cardID = string.charAt(1);
        int[] xy = new int[]{Integer.parseInt(string.substring(2,3)),Integer.parseInt(string.substring(4,5))};
        Direction direction = charToDirection(string.charAt(6));
        String[] deck;
        deck = switch (deckID){
            case 'A' -> Utility.DECK_A;
            case 'B' -> Utility.DECK_B;
            case 'C' -> Utility.DECK_C;
            case 'D' -> Utility.DECK_D;
            default -> throw new IllegalStateException("Unexpected DECK ID");
        };
        String card = cardFinder(deck, cardID);
        char[][] cardArray = cardBuilder(card);
        PathwayCard wayCard = new PathwayCard(cardArray, xy);
        if (direction == EAST){
            wayCard.rotate(EAST);
        }
        if (direction == WEST){
            wayCard.rotate(WEST);
        }
        if (direction == SOUTH) {
            wayCard.rotate(EAST);
            wayCard.rotate(EAST);
        }
        return new PathwayCard(cardArray, xy);
    }

    public static String placeOnBoard(PathwayCard card, char[][] board){
        int x = card.xy[0];
        int y = card.xy[1];
        char[][] tiles = card.tiles;
        for(int i = x; i <= (x+2); i++){
            for(int j = y; j <= (y+2); j++){
                board[i][j] = tiles[(i-x)][(i-y)];
            }
        }
        return charBoardToString(board);
    }
    public static String cardFinder(String[] deck, char c){
        for (String card : deck) {
            if (card.charAt(0) == c) {
                return card;
            }
        }
        return null;
    }

    public static char[][] cardBuilder(String string){
        if (string.isEmpty()){
            throw new IllegalArgumentException("card to cardBuilder is empty");
        }
        char[] row1 = {string.charAt(0), string.charAt(1), string.charAt(2)};
        char[] row2 = {string.charAt(3), string.charAt(4), string.charAt(5)};
        char[] row3 = {string.charAt(6), string.charAt(7), string.charAt(8)};
        return new char[][]{row1, row2, row3};
    };

    public void rotate(Direction direction) {

        char[][] rotatedCard = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (direction.equals(EAST)) {
                    // Rotate card 90 degrees clockwise
                    rotatedCard[j][2 - i] = tiles[i][j];
                } else {
                    // Rotate card 90 degrees counter-clockwise
                    rotatedCard[2 - j][i] = tiles[i][j];
                }
            }
        }

        tiles = rotatedCard; // Update the card's tiles
    }

    public static boolean isOffBoard(char[][] board, char[][] tile, int x, int y){
        if(board.length - y > tile.length || board[0].length - x > tile[0].length){
            return true;
        }
        return false;
    }
    public static boolean isOverlappingFirePWC(char[][] board, char[][] card, int x, int y){
        char[][] subBoard = extractSubBoard(board, card, x, y);
        for (char[] chars : subBoard) {
            for (char aChar : chars) {
                if (aChar == 'f') {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isOverlappingCatPWC(char[][] board, char[][] card, int x, int y){
        char[][] subBoard = extractSubBoard(board, card, x, y);
        for (char[] chars : subBoard) {
            for (char aChar : chars) {
                if (Character.isUpperCase(aChar)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isOverlappingRaftPWC(char[][] board, char[][] card, int x, int y){
        char[][] subBoard = extractSubBoard(board, card, x, y);
        for (char[] chars : subBoard) {
            for (char aChar : chars) {
                if (aChar == 'o') {
                    return true;
                }
            }
        }
        return false;
    }

    public static char[][] extractSubBoard(char[][] board, char[][] card, int x, int y) {
        // Get dimensions of the smaller card
        int cardRows = card.length;
        int cardCols = card[0].length;

        // Create a new char array to hold the extracted sub-board
        char[][] subBoard = new char[cardRows][cardCols];

        // Iterate through the sub-board area
        for (int row = 0; row < cardRows; row++) {
            for (int col = 0; col < cardCols; col++) {
                // Calculate the corresponding index in the larger board
                int boardRow = y + row;
                int boardCol = x + col;

                // Extract the character from the larger board
                subBoard[row][col] = board[boardRow][boardCol];
            }
        }

        return subBoard;
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
