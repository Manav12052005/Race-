package comp1110.ass2;

import java.util.Random;

import static comp1110.ass2.Board.charBoardToString;
import static comp1110.ass2.FireTile.rotate;
import static comp1110.ass2.PathwayCard.Direction.*;

public class FireTile {
    private char[][] tiles; // char representation array
    private int[] dim; // tile dimensions
    private Boolean isHorizontal; // y > x ?

    public FireTile(char[][] tiles, int[] dim, boolean isHorizontal) {
        this.tiles = tiles;
        this.dim = dim;
        this.isHorizontal = isHorizontal;
    }

    public static FireTile actionStringToFT(String string){
        char tileID = string.charAt(0);
        int[] xy = new int[]{Integer.parseInt(string.substring(1,2)),Integer.parseInt(string.substring(3,4))};
        char flip = string.charAt(5);
        PathwayCard.Direction direction = PathwayCard.charToDirection(string.charAt(6));
        String card = tileFinder(Utility.FIRE_TILES, tileID);
        int[] intArray = toIntArray(card.substring(1));
        int[] dim = findDimensions(intArray);
        char[][] cardArray = tileBuilder(defualtArray(dim), intArray, dim);
        boolean horiz = dim[1] > dim[0];
        FireTile tile = new FireTile(cardArray, dim, horiz);
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

    public static String placeOnBoard(FireTile tile, char[][] board, int[] loc){
        int x = tile.dim[0];
        int y = tile.dim[1];
        char[][] tiles = tile.tiles;
        for(int i = x; i <= (x+2); i++){
            for(int j = y; j <= (y+2); j++){
                if (tiles[(i-x)][(i-y)] != 'N'){
                board[i][j] = tiles[(i-x)][(i-y)];
            }}
        }
        return charBoardToString(board);
    }
    public static String tileFinder(String[] deck, char c){
        for (String card : deck) {
            if (card.charAt(0) == c) {
                return card;
            }
        }
        return null;
    }

    public static char[][] tileBuilder(char[][] blank, int[] array, int[] dim){
        for(int i = 0; i < array.length; i += 2){
            int x = array[i];
            int y = array[i+1];
            blank[x][y] = 'f';
        }
        return blank;
    }

    public static int[] toIntArray(String intString){
        if (intString.isEmpty()){
            throw new IllegalArgumentException("card to cardBuilder is empty");
        }
        int[] intArray = new int[intString.length()];
        for (int i = 0; i < intString.length(); i++) {
            intArray[i] = Character.getNumericValue(intString.charAt(i));
        }

        return null;
    };

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

    public static char[][] defualtArray(int[] dimension){
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

    public void rotate(PathwayCard.Direction direction) {
        char[][] rotatedTiles;
        if (isHorizontal & direction != FLIP) {
            rotatedTiles = new char[dim[1]][dim[0]];
        } else if(!isHorizontal & direction != FLIP){
            rotatedTiles = new char[dim[0]][dim[1]];
        } else if(isHorizontal & direction == FLIP){
            rotatedTiles = new char[dim[0]][dim[1]];
        } else {rotatedTiles = new char[dim[1]][dim[0]];
        }

        if (direction == WEST || direction == WEST) {
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    if (direction.equals(EAST)) { // rotate 90 degrees clockwise
                        rotatedTiles[j][tiles.length - 1 - i] = tiles[i][j];
                    } else {rotatedTiles[tiles[0].length - 1 - j][i] = tiles[i][j];// rotate 90 degrees counterclockwise
                    }
                }
            }
        } else for (int i = 0; i < tiles.length; i++) { //flips card about the y axis
                    for (int j = 0; j < tiles[i].length; j++) {
                        rotatedTiles[i][tiles[i].length - 1 - j] = tiles[i][j];
                    }}
        tiles = rotatedTiles;
        isHorizontal = !isHorizontal;
    }

    public boolean isEmpty (String thing){
            return thing == "";
        }

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
}