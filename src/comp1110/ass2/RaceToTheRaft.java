package comp1110.ass2;

import java.util.*;
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
        return "";  // FIXME 10
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
        int x = Integer.parseInt(placementString.substring(1, 2));
        int y = Integer.parseInt(placementString.substring(3, 4));

        // 3. Create Tile Representation
        char[][] tile;
        char[][] board = charBoard(gameState[0]);
        if (placementString.charAt(0) == 'f') {
            FireTile fireTile = FireTile.actionStringToFT(placementString);
            tile = fireTile.getTiles();
            return isOffBoard(board, tile, x, y) ||
                    isOverlappingFireFT(board, tile, x, y) ||
                    isOverlappingCatFT(board, tile, x, y) ||
                    isOverlappingRaftFT(board, tile, x, y) ||
                    !isAdjacentToFire(board, tile, x, y);
        } else {
            PathwayCard card = PathwayCard.actionStringToPWC(placementString);
            tile = card.getTiles();
            return isOffBoard(board, tile, x, y) ||
                    isOverlappingFirePWC(board, tile, x, y) ||
                    isOverlappingCatPWC(board, tile, x, y) ||
                    isOverlappingRaftPWC(board, tile, x, y);
        }

    } // FIXME TASK 12

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
//        return false; // FIXME TASK 14
        char catColor = catMovementString.charAt(0);
        int startY = Integer.parseInt(catMovementString.substring(1, 3));
        int startX = Integer.parseInt(catMovementString.substring(3, 5));
        int endY = Integer.parseInt(catMovementString.substring(5, 7));
        int endX = Integer.parseInt(catMovementString.substring(7, 9));

        System.out.println(catMovementString);
        System.out.println(startY);
        System.out.println(startX);
        System.out.println(endY);
        System.out.println(endX);

        // Check if the end position color matches the cat color
        String[] rows = gameState[0].split("\n");
        System.out.println(rows[endY].charAt(endX));
        if (rows[endY].charAt(endX) != Character.toLowerCase(catColor)) {
            return false;
        }

        // Check if there is a possible way to move cat




        return false;
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
        return false;     // FIXME TASK 15
    }


}
