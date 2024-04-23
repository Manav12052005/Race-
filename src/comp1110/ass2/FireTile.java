package comp1110.ass2;

import java.util.Random;

import static comp1110.ass2.FireTile.Direction.EAST;
import static comp1110.ass2.FireTile.Direction.FLIP

public class FireTile {
    private char[][] tiles; //Proper implementation

    //private int[][] tiles; Made for testing
    private Boolean isHorizontal;

    public FireTile(char[][] tiles, boolean isHorizontal) {
        this.tiles = tiles;
        this.isHorizontal = isHorizontal;
    }

    enum Direction{
        EAST, WEST, FLIP
    }

    public FireTile actionStringToFT(String string){
        return null;
    }

    public void rotate(Direction direction) {
        char[][] rotatedTiles;
        if (isHorizontal & direction != FLIP) {
            rotatedTiles = new char[4][3];
        } else if(!isHorizontal & direction != FLIP){
            rotatedTiles = new char[3][4];
        } else if(isHorizontal & direction == FLIP){
            rotatedTiles = new char[3][4];
        } else {rotatedTiles = new char[4][3];
        }

        if (direction == Direction.WEST || direction == Direction.WEST) {
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