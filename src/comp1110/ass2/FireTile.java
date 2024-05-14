package comp1110.ass2;

import java.util.Arrays;
import java.util.Random;

import static comp1110.ass2.Board.charBoardToString;
import static comp1110.ass2.PathwayCard.Direction.*;

/**
 * This class constructs fire tiles, and handles rotation and placement in the game.
 * Authored primarily by Tom.
 */
public class FireTile {
    private char[][] tiles; // char representation array
    private Boolean isHorizontal; // y > x ?

    public FireTile(char[][] tiles, boolean isHorizontal) {
        this.tiles = tiles;
        this.isHorizontal = isHorizontal;
    }

    // Converts an action string to a FireTile object
    public static FireTile actionStringToFT(String string){
        char tileID = string.charAt(0);
        char flip = 'F'; // Default flip value
        PathwayCard.Direction direction = PathwayCard.Direction.NORTH; // Default direction

        if (string.length() > 5) {
            flip = string.charAt(5);
        }
        if (string.length() > 6 && PathwayCard.Direction.isValidDirection(string.charAt(6))) {
            direction = PathwayCard.charToDirection(string.charAt(6));
        }

        String card = tileFinder(Utility.FIRE_TILES, tileID);
        int[] intArray = toIntArray(card.substring(1));
        int[] dim = findDimensions(intArray);
        char[][] cardArray = tileBuilder(defaultArray(dim), intArray);

        boolean horiz = dim[1] > dim[0];
        FireTile tile = new FireTile(cardArray, horiz);

        if (flip == 'T'){
            tile.rotate(FLIP);
        }
        if (direction == PathwayCard.Direction.EAST){
            tile.rotate(EAST);
        }
        if (direction == WEST){
            tile.rotate(WEST);
        }
        if (direction == SOUTH) {
            tile.rotate(EAST);
            tile.rotate(EAST);
        }
        return tile;
    }

    // Places a FireTile on the game board (string)
    public static String placeOnBoardFT(FireTile tile, char[][] board, int[] loc) {
        int startX = loc[0];
        int startY = loc[1];
        char[][] tiles = tile.tiles;
        for (int tileRow = 0; tileRow < tiles.length; tileRow++) {
            for (int tileCol = 0; tileCol < tiles[0].length; tileCol++) {
                int boardRow = startX + tileRow;
                int boardCol = startY + tileCol;
                if (tiles[tileRow][tileCol] != 'N') {
                    board[boardRow][boardCol] = tiles[tileRow][tileCol];
                }}
        }
        return charBoardToString(board);
    }

    // Finds a tile in the deck based on its ID
    public static String tileFinder(String[] deck, char c){
        for (String card : deck) {
            if (card.charAt(0) == c) {
                return card;
            }
        }
        return null;
    }

    // Builds a tile from an array of coordinates
    public static char[][] tileBuilder(char[][] blank, int[] array){
        for(int i = 0; i < array.length; i += 2){
            int x = array[i];
            int y = array[i+1];
            blank[x][y] = 'f';
        }
        return blank;
    }

    // Converts a string of integers to an int array
    public static int[] toIntArray(String intString){
        if (intString.isEmpty()){
            throw new IllegalArgumentException("card to cardBuilder is empty");
        }
        int[] intArray = new int[intString.length()];
        for (int i = 0; i < intString.length(); i++) {
            intArray[i] = Character.getNumericValue(intString.charAt(i));
        }

        return intArray;
    };

    // Finds the dimensions of a tile based on its coordinates
    public static int[] findDimensions(int[] coordinates) {
        int maxX = 0;
        int maxY = 0;
        for (int i = 0; i < coordinates.length; i += 2) {
            int x = coordinates[i];
            int y = coordinates[i + 1];
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }
        return new int[] {maxX + 1, maxY + 1};
    }

    // Creates a default char array filled with 'N'
    public static char[][] defaultArray(int[] dimension){
        int x = dimension[0];
        int y = dimension[1];
        char[][] array = new char[x][y];
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                array[i][j] = 'N';
            }
        }
        return array;
    }

    // Rotates the FireTile based on the given direction
    public void rotate(PathwayCard.Direction direction) {
        char[][] rotatedTiles;
        if (direction != FLIP) {
            rotatedTiles = new char[tiles[0].length][tiles.length];
        } else {
            rotatedTiles = new char[tiles.length][tiles[0].length];
        }
        if(direction == FLIP) for (int i = 0; i < tiles.length; i++) { //flips card about the y axis
            for (int j = 0; j < tiles[i].length; j++) {
                rotatedTiles[i][tiles[i].length - 1 - j] = tiles[i][j];
            }
        }
        if (direction == WEST || direction == EAST) {
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    if (direction.equals(EAST)) { // rotate 90 degrees clockwise
                        rotatedTiles[j][tiles.length - 1 - i] = tiles[i][j];
                    } else {
                        rotatedTiles[tiles[0].length - 1 - j][i] = tiles[i][j];// rotate 90 degrees counterclockwise
                    }
                }
            }
            isHorizontal = !isHorizontal;
        }
        tiles = rotatedTiles;
    }

    // Checks if the FireTile overlaps with fire on the game board
    public static boolean isOverlappingFireFT(char[][] subBoard, char[][] tile){
        for (int row = 0; row < subBoard.length; row++) {
            for (int col = 0; col < subBoard[row].length; col++) {
                if (subBoard[row][col] == 'f' && tile[row][col] == 'f') {
                    return true;
                }
            }
        }
        return false;
    }

    // Checks if the FireTile overlaps with a cat on the game board
    public static boolean isOverlappingCatFT(char[][] subBoard, char[][] tile){
        for (int row = 0; row < subBoard.length; row++) {
            for (int col = 0; col < subBoard[row].length; col++) {
                if (Character.isUpperCase(subBoard[row][col]) && tile[row][col] == 'f') {
                    return true;
                }
            }
        }
        return false;
    }

    // Checks if the FireTile overlaps with a raft on the game board
    public static boolean isOverlappingRaftFT(char[][] subBoard, char[][] tile){
        for (int row = 0; row < subBoard.length; row++) {
            for (int col = 0; col < subBoard[row].length; col++) {
                if (subBoard[row][col] == 'o' && tile[row][col] == 'f') {
                    return true;
                }
            }
        }
        for (int row = 0; row < subBoard.length; row++) {
            for (int col = 0; col < subBoard[row].length; col++) {
                if (subBoard[row][col] == 'w' && tile[row][col] == 'f') {
                    return true; // 'f' found at the same index
                }
            }
        }
        return false;
    }

    // Checks if the FireTile will be adjacent to fire on the game board.
    public static boolean isAdjacentToFire(char[][] board, char[][] tile, int x, int y){
        int cardRows = tile.length;
        int cardCols = tile[0].length;

        char[][] tempBoard = new char[cardRows][cardCols];
        for (int row = 0; row < cardRows; row++) {
            System.arraycopy(board[x + row], y, tempBoard[row], 0, cardCols);
        }
        for (int row = 0; row < cardRows; row++) {
            for (int col = 0; col < cardCols; col++) {
                tempBoard[row][col] = tile[row][col];
            }
        }
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int row = 0; row < cardRows; row++) {
            for (int col = 0; col < cardCols; col++) {
                if (tempBoard[row][col] == 'f') {
                    for (int[] offset : offsets) {
                        int adjRow = row + offset[0];
                        int adjCol = col + offset[1];

                        if (adjRow >= 0 && adjRow < cardRows &&
                                adjCol >= 0 && adjCol < cardCols &&
                                tempBoard[adjRow][adjCol] == 'f') {
                            return true; //
                        }
                    }
                }
            }
        }

        return false; //
    }

    // Picks a random FireTile from the string bag
    public static String pickFire (String stringBag){
        Random random = new Random();
        int randomIndex = random.nextInt(stringBag.length());
        char randomChar = stringBag.charAt(randomIndex); // Gets a random char from our bag

        for (String fireTile : Utility.FIRE_TILES) {
            if (fireTile.charAt(0) == randomChar) {
                return fireTile; // returns the unique fireTile based on the ID
            }
        }
        return null;
    }

    public char[][] getTiles () {
        return tiles;
    }

    public boolean getIsHorizontal () {
        return isHorizontal;
    }

    public static void main(String[] args) {
        FireTile tile = actionStringToFT("D");
        char[][] board = tile.tiles;
        // print board
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}