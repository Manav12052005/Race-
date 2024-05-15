package comp1110.ass2;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import static comp1110.ass2.Board.*;
import static comp1110.ass2.FireTile.*;
import static comp1110.ass2.PathwayCard.*;

/**
 * This class is for testing purposes only. You should create and use your own objects to solve the tasks below
 * instead of directly using the strings provided. Note that Task 2 is the only task you are expected to use string
 * manipulation to solve.
 */
public class RaceToTheRaft {

    /**
     * Determine whether a boardState string is well-formed.
     * To be well-formed the string must satisfy all the following conditions:
     * <p>
     * 1. Each line is terminated by a newline character `\n`
     * 2. The number of printable (non-newline) characters in each line is equal AND is either 9 or 18.
     * 3. Each character (except the newline character) is one of the following:
     * - 'b' (blue)
     * - 'B' (blue with blue cat)
     * - 'f' (fire)
     * - 'g' (green)
     * - 'G' (green with green cat)
     * - 'n' (none)
     * - 'o' (objective)
     * - 'p' (purple)
     * - 'P' (purple with purple cat)
     * - 'r' (red)
     * - 'R' (red with red cat)
     * - 'w' (wild)
     * - 'W' (wild with a cat of any colour)
     * - 'y' (yellow)
     * - 'Y' (yellow with yellow cat)
     * 4. The number of lines is either 12, 15 or 18.
     * </p>
     *
     * @param boardString A string representing the boardState
     * @return True if the boardState is well-formed, otherwise false.
     */
    //Simon Task
    public static boolean isBoardStringWellFormed(String boardString) {
        if (!boardString.endsWith("\n")) return false;
        if (boardString.lines().count() != 12 && boardString.lines().count() != 15 &&
                boardString.lines().count() != 18) {
            return false;
        }

        AtomicBoolean isValid = new AtomicBoolean(true);
        boardString.lines().forEach(line -> {
            if (line.length() != 9 && line.length() != 18) {
                isValid.set(false);
                return;
            }
            for (char ch : line.toCharArray()) {
                if (ch != 'b' && ch != 'B' && ch != 'f' && ch != 'g' && ch != 'G' && ch != 'n' && ch != 'o' && ch != 'p'
                && ch != 'P' && ch != 'r' && ch != 'R' && ch != 'w' && ch != 'W' && ch != 'y' && ch != 'Y') {
                    isValid.set(false);
                    return;
                }
            }
        });

        return isValid.get(); // FIXME TASK 2
    }
    /**
     * Make Constructors for each of your objects.
     */
    // FIXME TASK 3
    //Manav Task - Done in respective classes

    /**
     * Draws a random fire tile from those remaining in the bag.
     *
     * @param gameState the current state of the game, including the fire tile bag
     * @return a random fire tile from those remaining, in string form. If there are no tiles remaining, return the
     * empty string.
     */
    public static String drawFireTile(String[] gameState) {
        // Tom task
        String fireTileBag = gameState[4]; //gets fireTileBag from gameState

        // Checks if there are any tiles left
        if (fireTileBag.isEmpty()) {
            return "";
        }
        else return pickFire(fireTileBag);


            // FIXME TASK 5
    }
    /**
     * Chooses a random challenge from those available in the Utility class according
     * to the given difficulty.
     *
     * @param difficulty the desired difficulty of the challenge
     * @return a random challenge of the given difficulty
     */
    public static String chooseChallenge(int difficulty) {
        int index = 0; // The index of string in Utility.CHALLENGES that will be returned
        Random rand = new Random();
        switch (difficulty) {
            case 0:
                index = rand.nextInt(4) + 0;
                break;
            case 1:
                index = rand.nextInt(4) + 4;
                break;
            case 2:
                index = rand.nextInt(7) + 8;
                break;
            case 3:
                index = rand.nextInt(7) + 16;
                break;
            case 4:
                index = rand.nextInt(5) + 26;
                break;
            case 5:
                index = rand.nextInt(7) + 32;
                break;
            default:
                break;
        }
        return Utility.CHALLENGES[index]; // FIXME TASK 6
    }
    //Simon task
    /**
     * Draw random cards from the specified decks.
     * The decks string denotes what decks to draw from and how many cards to draw from that deck.
     * <p>
     * For example the drawRequest string "A4B1D1" tells us that we should draw 4 cards from deck A, 1 card from deck B
     * and
     * 1 card from deck D.
     * <p>
     * If I draw cards a, b, d, l, from deck A, card a from deck B and card s from deck D, I would return the string:
     * "AabdlBaCDs".
     * Decks should be listed in alphabetical order, with cards drawn from that deck also listed in alphabetical order.
     * </p>
     * Recall the mapping between deck and char:
     * A -> CIRCLE;
     * B -> CROSS;
     * C -> SQUARE;
     * D -> TRIANGLE;
     *
     * @param gameState   the current state of the game, including the current state of the decks
     * @param drawRequest A string representing the decks to draw from and the number of cards to draw from each respective
     *                    deck.
     * @return The updated gameState array after the cards have     been drawn. (Remove all cards drawn from decks and
     * add them to the Hand string). If it is not possible
     * to draw all the specified cards, you should return the original gameState.
     */
    public static String[] drawHand(String[] gameState, String drawRequest) {
        // Tom task
        String decks = gameState[1]; // Gets deck string representation
        String hand = gameState[2]; // Gets hand string representation

        String[] modCards = cardPickUp(decks, hand, drawRequest);

        String modDecks = modCards[0];
        String modHand = modCards[1];

        String[] updatedGameState = new String[gameState.length];
        for (int i = 0; i < gameState.length; i++) {
            if (i == 1) {
                updatedGameState[i] = modDecks;
            } else if (i == 2) {
                updatedGameState[i] = modHand;
            } else {
                updatedGameState[i] = gameState[i];
            }
        }
        return updatedGameState;// FIXME TASK 7
    }
    /**
     * Place the given card or fire tile as described by the placement string and return the updated gameState array.
     * See the README for details on these two strings.
     * You may assume that the placements given are valid.
     * <p>
     * When placing a card, you should update both the Board string and remove the corresponding card from the Hand
     * string in the gameState array.
     *
     * @param gameState       An array representing the game state.
     * @param placementString A string representing a Fire Tile Placement or a Card Placement.
     * @return the updated gameState array after this placement has been made
     */
    public static String[] applyPlacement(String[] gameState, String placementString) {
        String board = gameState[0];
        String hand = gameState[2];
        char deckID = placementString.charAt(0);
        char cardID = placementString.charAt(1);

        if (Character.isDigit(placementString.charAt(1))) {//FireTile Placement
            FireTile fireTile = actionStringToFT(placementString); // Create FireTile object
            int[] loc = new int[]{Integer.parseInt(placementString.substring(1,3)),
                    Integer.parseInt(placementString.substring(3,5))};
            board = placeOnBoardFT(fireTile, charBoard(board), loc); // Modify the board
        } else { //PathwayCard Placement
            PathwayCard card = actionStringToPWC(placementString); // Create PathwayCard object
            board = placeOnBoardPWC(card, charBoard(board)); // Modify the board
            if (!hand.contains("A")){
                hand = "A" + hand;
            }
            if (!hand.contains("B")){
                hand = hand.substring(0, hand.indexOf("C")) + "B" + hand.substring(hand.indexOf("C"));
            }
            if (!hand.contains("C")){
                hand = hand.substring(0, hand.indexOf("D")) + "C" + hand.substring(hand.indexOf("D"));
            }
            if (!hand.contains("D")){
                hand = hand + "D";
            }
            hand = hand.substring(0, hand.indexOf(deckID))
                    + hand.substring(hand.indexOf(deckID)).replaceFirst(String.valueOf(cardID), "");
            // remove card from hand
        }

        // Update gameState
        gameState[0] = board;
        gameState[2] = hand;
        return gameState;// FIXME TASK 8
    }

    /**
     * Move the given cat as described by the cat movement string and return the updated gameState array. You may
     * assume that the cat movement is valid.
     * <p>
     * You should both move the cat (updating the Board string) and also add the cat to the Exhausted Cats string, or
     * update that cat's reference in the Exhausted Cats string if it was already exhausted.
     *
     * @param gameState      An array representing the game state.
     * @param movementString A string representing the movement of a cat and the cards discarded to allow this move.
     * @return the updated gameState array after this movement has been made.
     */
    public static String[] moveCat(String[] gameState, String movementString) {
        String[] ret = gameState;
        // Cat move operation
        ret[0] = Cat.catMove(ret[0], movementString);
        // Card discard operation
        ret[2] = Card.discardCard(ret[2], movementString);
        // Cat exhaust operation
        ret[3] = Cat.catExhauste(ret[3], movementString);

        return ret; // FIXME TASK 9
    }

    /**
     * Given a challengeString, construct a board string that satisfies the challenge requirements.
     * <p>
     * Each board in the `squareBoard` or `rectangleBoard` arrays may only be used once. For example: if the
     * challenge requires 4 Large (square) boards, you must use all 4 square boards. You may not use the same board
     * twice, even in different orientations.
     * The cat, fire card and raft card placements should all match the challenge string.
     *
     * @param challengeString A string representing the challenge to initialise
     * @return A board string for this challenge.
     */
    public static String initialiseChallenge(String challengeString) {
        return Challenge.getBoardSubstring(challengeString);  // FIXME 10
    }


    /**
     * Given a card placement string or a fire tile placement string, check if that placement is valid.
     * <p>
     * A card placement is valid if all the following conditions are met:
     * <p>
     * 1. No part of the card is off-board
     * 2. No part of the card is overlapping fire.
     * 3. No part of the card is overlapping a cat.
     * 4. No part of the card is overlapping part of a Raft card (any of the 8 squares surrounding the `o`
     * state)
     * </p>
     * A fire tile placement is valid if all the following conditions are met:
     * <p>
     * 1. No part of the fire tile is off-board
     * 2. No part of the fire tile overlaps fire
     * 3. No part of the fire tile overlaps a cat
     * 4. No part of the fire tile overlaps part of a Raft card (any of the 8 squares surrounding the `o` state)
     * 5. The Fire tile is orthogonally adjacent to another fire square.
     * </p>
     *
     * @param gameState       An array representing the gameState
     * @param placementString A string representing a card placement or a fire tile placement
     * @return True if the placement is valid, otherwise false.
     */
    public static boolean isPlacementValid(String[] gameState, String placementString) {
        char[][] tile;
        char[][] board = charBoard(gameState[0]);
        if (Character.isDigit(placementString.charAt(1))) {
            FireTile fireTile = FireTile.actionStringToFT(placementString);
            tile = fireTile.getTiles();
            for (char[] chars : tile) {
                System.out.println(chars);
            }
            int x = Integer.parseInt(placementString.substring(1, 3));
            int y = Integer.parseInt(placementString.substring(3, 5));
//            System.out.println(isOffBoard(board, tile, x, y));
            if (isOffBoard(board, tile, x, y)) {
                return false;
            }
            char[][] subBoard = extractSubBoard(board, tile, x, y);
            if (isOverlappingFireFT(subBoard, tile)) {
                return false;
            } else if (isOverlappingCatFT(subBoard, tile)) {
                return false;
            } else if (isOverlappingRaftFT(subBoard, tile)){
                return false;
            } else return isAdjacentToFire(board, tile, x, y);
        } else {
            PathwayCard card = PathwayCard.actionStringToPWC(placementString);
            tile = card.getTiles();
            int x = Integer.parseInt(placementString.substring(2,4));
            int y = Integer.parseInt(placementString.substring(4,6));
            if (isOffBoard(board, tile, x, y)) {
                return false;
            }
            char[][] subBoard = extractSubBoard(board, tile, x, y);
            if (isOverlappingFirePWC(subBoard)){
                return false;
            } else if (isOverlappingCatPWC(subBoard)){
                return false;
            } else return !isOverlappingRaftPWC(subBoard);
        }

    } // FIXME TASK 12
//    public static void main(String[] args) {
//        // Example usage to create a Challenge object and access its substrings
//        String placementString = "l0003TW";
//        String board = """
//            fffpgyyrbgrgpybygr
//            fffpBbrypgyprpgbyb
//            fffbgypgbbrbbrbgyr
//            fffyybyrggypgpypbg
//            fffbgrggypbrrbgpry
//            fffgpgbgpygppyRygb
//            fffbgybpbbpyrggbpg
//            fffpyrgygbrypbyryb
//            fffgppbRrgybgybrgy
//            fffygybgygyybgpgpb
//            fffgyrpbgprbyrgbyr
//            fffPbyyrpyprgybpgy
//            fffgbrggrggybrrwww
//            fffpgbypbbrgpygwow
//            fffgrprybbprgpbwww
//            """;
//        String[] gameState = new String[]{board, "AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "", "abCDE"};
//        System.out.println("isPlacementValid: " + isPlacementValid(gameState, placementString));
//    }



    /**
     * Given a cat movement string, check if the cat movement is valid.
     * <p>
     * A cat movement is valid if:
     * 1. The end location is the same colour as the cat.
     * 2. There is a path from start location to the end location, consisting only of squares the same colour as the
     * cat.
     * 3. The path does not include diagonal movements.
     *
     * @param gameState         An array representing the gameState
     * @param catMovementString A string representing a cat movement.
     * @return True if the cat movement is valid, otherwise false
     */
    public static boolean isCatMovementValid(String[] gameState, String catMovementString) {
        return Cat.checkMovementValid(gameState, catMovementString);    // FIXME TASK 14
    }


    /**
     * Determine whether the game is over. The game ends if any of the following conditions are met:
     * <p>
     * Fire tile placement:
     * 1. If this placement action is not valid AND there is no other position this tile could be placed validly
     * (the game is lost).
     * 2. If placing this fire tile makes it impossible for any one cat to reach the raft (the game is lost).
     * <p>
     * Cat movement:
     * 1. If after moving this cat, all cats have safely reached the raft (the game is won).
     * <p>
     * Card placement:
     * 1. If after placing this card, there are no more fire tiles left in the bag (the game is lost).
     * </p>
     *
     * @param gameState An array of strings representing the game state
     * @param action    A string representing a fire tile placement, cat movement or card placement action.
     * @return True if the game is over (regardless of whether it is won or lost), otherwise False.
     */
    public static boolean isGameOver(String[] gameState, String action) {
        char firstChar = action.charAt(0);
        char secondChar = action.charAt(1);
        String fireTileBag = gameState[4]; // Gets fireTileBag from gameState
        String boardState = gameState[0]; // Gets the current state of the game board

        if ((firstChar == 'Y' || firstChar == 'B' || firstChar == 'R' || firstChar == 'G' || firstChar == 'P')
                && Character.isDigit(secondChar) && action.length() > 8) { // the action is a movement

            if (RaceToTheRaft.isCatMovementValid(gameState, action)) {
                gameState = RaceToTheRaft.moveCat(gameState, action);
                // if every cat is on the raft, the game is won
                // the cats are on the raft if it's around 'o' character
                String[] rows = gameState[0].split("\n");
                boolean catAllOnRaft = true;
                for (int y = 0; y < rows.length; y++) {
                    for (int x = 0; x < rows[0].length(); x++) {
                        if (Challenge.isCharCat(rows[y].charAt(x))) {
                            if (!Cat.isCatOnRaft(gameState, x, y)) {
                                System.out.println("Not on raft");
                                catAllOnRaft = false;
                            }
                        }
                    }
                }
                if (!catAllOnRaft) {
                    return false; // Game is won, return immediately
                }
            }
        } else if (fireTileBag.isEmpty()) {
            return true; // No tiles left, game is lost, return immediately
        } else if (isFireTile(action)) {
            // Iterate over all possible coordinates and rotations

            String[] rows = boardState.split("\n");

            if (isPlacementValid(gameState, action)) {


                System.out.println(gameState[0]);
                System.out.println(action + " true!!!");

                String[] newGameState = applyPlacement(gameState, action);

                System.out.println("after placement");
                System.out.println(newGameState[0]);

                String[] afterRows = newGameState[0].split("\n");

                for (int r = 0; r < rows.length; r++) {
                    for (int c = 0; c < rows[0].length(); c++) {
                        if (Challenge.isCharCat(rows[r].charAt(c))) {
                            char cat = rows[r].charAt(c);

                            System.out.println("cat: " + cat);

                            boolean catToRaft = false;

                            for (int row = 0; row < rows.length; row++) {
                                for (int col = 0; col < rows[0].length(); col++) {

                                    if (rows[row].charAt(col) == 'o' || rows[row].charAt(col) == 'w' ||
                                        rows[row].charAt(col) == 'W') {

                                        StringBuilder sb = new StringBuilder();
                                        sb.append(cat);
                                        sb.append(String.format("%02d%02d", r, c));
                                        sb.append(String.format("%02d%02d", row, col));

                                        if (Cat.checkIfBlocked(newGameState, sb.toString())) {

                                            for (int n = r; n < rows.length; n++) {
                                                for (int m = rows[0].length() - 3; m < rows[0].length() - 1; m++) {
                                                    System.out.println("Reached here: " + rows[n].charAt(m) + rows[n].charAt(m + 1));
                                                    if (afterRows[n].charAt(m) == 'f' && afterRows[n].charAt(m + 1) != 'f'
                                                    && afterRows[n].charAt(m + 1) != Character.toLowerCase(cat)
                                                    && !afterRows[n].contains("w")) {
                                                                return true;
                                                    }
                                                }
                                            }

//                                          if (Cat.checkMovementValid(newGameState, sb.toString())) {
                                                System.out.println("cat can move to the raft!");
                                                System.out.println(sb.toString());
                                                catToRaft = true;
                                                break;
                                        }


                                    }

                                }
                                if (catToRaft) {
                                    break;
                                }
                            }

                            if (!catToRaft) {
                                return true;
                            } else {
                                return false;
                            }
                        }

                    }
                }
            }

            for (int row = 0; row < rows.length; row++) {
                for (int col = 0; col < rows[0].length(); col++) {
                    for (char orient : new char[] {'N', 'S', 'E', 'W'}) {
                        for (char flippe : new char[] {'T', 'F'}) {
                            StringBuilder currentActionBuilder = new StringBuilder();
                            currentActionBuilder.append(action.charAt(0));

                            String digits = String.format("%02d%02d", row, col);
                            currentActionBuilder.append(digits);
//                            currentActionBuilder.setCharAt(2, (char) (row % 10)); // units digit of row
//                            currentActionBuilder.setCharAt(3, (char) (col / 10)); // tens digit of col
//                            currentActionBuilder.setCharAt(4, (char) (col % 10)); // units digit of col
                            currentActionBuilder.append(flippe);
                            currentActionBuilder.append(orient);

//                            System.out.println("flip: " + flippe);
//                            System.out.println("orient: " + orient);

                            System.out.println(currentActionBuilder.toString());

                            String currentAction = currentActionBuilder.toString();
                            // Check if the current placement is valid


                            if (isPlacementValid(gameState, currentAction)) {
                                return false; // At least one valid placement, game is not over
                            }
                        }
                    }
                }
            }
        } else {
            return false;
        }

        return true;
    }



    public static boolean isFireTile(String action) {
        if (action.length() == 7 && Character.isDigit(action.charAt(1))) {
            System.out.println("FireTile" + action);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String[] gamestate = new String[]{
            """
fffbfffff
ffffgffff
fffffffff
ffffffyff
ffffffrrf
fffffffff
fffbfyfff
ffffffgff
fffgpffff
ffffffbfy
ffffyrfff
ffffffyRp
ffffffwww
ffffgbwow
fffffpwww
"""
            , "AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "" , "abCDE"};
        boolean test = isPlacementValid(gamestate, "b1203FN");

        System.out.println("placement: " + test);


    }


}
// FIXME TASK 15
