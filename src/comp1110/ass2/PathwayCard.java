package comp1110.ass2;

import java.util.Arrays;
import java.util.Random;

import static comp1110.ass2.Board.charBoardToString;
import static comp1110.ass2.PathwayCard.Direction.*;

/**
 * PathwayCard class represents a pathway card in the game, and handles placement and rotation.
 * Authored by Tom.
 */
public class PathwayCard {
    private char[][] tiles;
    private int[] rowcol;

    public PathwayCard(char[][] tiles, int[] xy) {
        this.tiles = tiles;
        this.rowcol = xy;
    }

    public char[][] getTiles() {
        return tiles;
    }

    /**
     * Enum representing the possible directions of a pathway card.
     */
    public enum Direction {
        NORTH, EAST, SOUTH, WEST, FLIP;

        public static boolean isValidDirection(char c) {
            return c == 'N' || c == 'E' || c == 'S' || c == 'W';
        }
    }

    // Converts a character to its corresponding Direction enum value
    public static Direction charToDirection(char c) {
        switch (c) {
            case 'N' -> {
                return NORTH;
            }
            case 'E' -> {
                return EAST;
            }
            case 'S' -> {
                return SOUTH;
            }
            case 'W' -> {
                return WEST;
            }
            case 'F' -> {
                return FLIP;
            }
            default -> throw new IllegalArgumentException("charToDirection input invalid");
        }
    }

    // Converts an action string to a PathwayCard object
    public static PathwayCard actionStringToPWC(String string) {
        char deckID = string.charAt(0);
        char cardID = string.charAt(1);
        int[] rowcol = new int[]{Integer.parseInt(string.substring(2, 4)), Integer.parseInt(string.substring(4, 6))};
        Direction direction = charToDirection(string.charAt(6));
        String[] deck;
        deck = switch (deckID) {
            case 'A' -> Utility.DECK_A;
            case 'B' -> Utility.DECK_B;
            case 'C' -> Utility.DECK_C;
            case 'D' -> Utility.DECK_D;
            default -> throw new IllegalStateException("Unexpected DECK ID");
        };
        String card = cardFinder(deck, cardID);
        char[][] cardArray = cardBuilder(card);
        PathwayCard wayCard = new PathwayCard(cardArray, rowcol);
        if (direction == EAST) {
            wayCard.rotate(EAST);
        }
        if (direction == WEST) {
            wayCard.rotate(WEST);
        }
        if (direction == SOUTH) {
            wayCard.rotate(EAST);
            wayCard.rotate(EAST);
        }
        return wayCard;
    }

    // Places a PathwayCard on the game board
    public static String placeOnBoardPWC(PathwayCard card, char[][] board) {
        int row = card.rowcol[0];
        int col = card.rowcol[1];
        char[][] tiles = card.tiles;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[row + i][col + j] = tiles[i][j];
            }
        }
        return charBoardToString(board);
    }

    // Finds a card in the deck based on its ID
    public static String cardFinder(String[] deck, char c) {
        for (String card : deck) {
            if (card.charAt(0) == c) {
                return card.substring(1);
            }
        }
        return null;
    }

    // Builds a card from a string representation
    public static char[][] cardBuilder(String string) {
        if (string.isEmpty()) {
            throw new IllegalArgumentException("card to cardBuilder is empty");
        }
        char[] row1 = {string.charAt(0), string.charAt(1), string.charAt(2)};
        char[] row2 = {string.charAt(3), string.charAt(4), string.charAt(5)};
        char[] row3 = {string.charAt(6), string.charAt(7), string.charAt(8)};
        char[][] card = new char[][]{row1, row2, row3};
        return card;
    }

    // Rotates the PathwayCard based on the given direction
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

    // Checks if the PathwayCard is placed off the game board
    public static boolean isOffBoard(char[][] board, char[][] tile, int x, int y) {
        return x < 0 || y < 0 ||
                y + tile[0].length > board[0].length ||
                x + tile.length > board.length;
    }

    // Checks if the PathwayCard overlaps with fire on the game board
    public static boolean isOverlappingFirePWC(char[][] board) {
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == 'f') {
                    return true;
                }
            }
        }
        return false;
    }

    // Checks if the PathwayCard overlaps with a cat on the game board
    public static boolean isOverlappingCatPWC(char[][] board) {
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (Character.isUpperCase(aChar)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Checks if the PathwayCard overlaps with a raft on the game board
    public static boolean isOverlappingRaftPWC(char[][] board) {
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == 'o') {
                    return true;
                }
            }
        }
        return false;
    }

    // Extracts a sub-board from the game board based on the PathwayCard's position
    public static char[][] extractSubBoard(char[][] board, char[][] card, int x, int y) {
        int cardRows = card.length;
        int cardCols = card[0].length;
        System.out.println("board dimensions: " + board.length + "x" + board[0].length);
        System.out.println("card dimensions: " + cardRows + "x" + cardCols);
        System.out.println("placement coordinates (x, y): " + x + ", " + y);

        // Creates a new char array to hold the extracted sub-board
        char[][] subBoard = new char[cardRows][cardCols];
        for (int row = 0; row < cardRows; row++) {
            for (int col = 0; col < cardCols; col++) {
                int boardRow = x + row;
                int boardCol = y + col;
                subBoard[row][col] = board[boardRow][boardCol];
            }
        }
        System.out.println("");
        return subBoard;
    }

    // Picks up cards from the decks based on the draw request
    public static String[] cardPickUp(String decks, String hand, String drawRequest) {
        // Creating individual deck strings
        String deckA = decks.substring(decks.indexOf('A'), decks.indexOf('B'));
        String deckB = decks.substring(decks.indexOf('B'), decks.indexOf('C'));
        String deckC = decks.substring(decks.indexOf('C'), decks.indexOf('D'));
        String deckD = decks.substring(decks.indexOf('D'));

        int[] requests = new int[]{0, 0, 0, 0};

        for (int i = 0; i < drawRequest.length(); i++) {
            char currentChar = drawRequest.charAt(i);

            if (Character.isDigit(currentChar)) {
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

            i = nextIndex - 1;
        }

        if ((deckA.length() - 1) < requests[0] || (deckB.length() - 1) < requests[1] || (deckC.length() - 1) < requests[2] ||
                (deckD.length() - 1) < requests[3]) {
            return new String[]{decks, hand};
        }

        String newHand = "";
        String modifiedDecks = "";

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

    // Picks cards from a specific deck based on the request
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