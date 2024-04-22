package comp1110.ass2;

import java.util.Random;

public class FireTile {
    //    the final implementation will be Square[][] but to test rotate we will use int[][].
//    private Square[][] tiles;
    private int[][] tiles;
    private Boolean isHorizontal;

    public FireTile(int[][] tiles, boolean isHorizontal) {
        this.tiles = tiles;
        this.isHorizontal = isHorizontal;
    }

    public void rotate(String direction) {
        int[][] rotatedTiles;
        if (isHorizontal & direction != "flip") {
            rotatedTiles = new int[4][3];
        } else if(!isHorizontal & direction != "flip"){
            rotatedTiles = new int[3][4];
        } else if(isHorizontal & direction == "flip"){
            rotatedTiles = new int[3][4];
        } else {rotatedTiles = new int[4][3];
        }

        if (direction == "east" || direction == "west") {
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    if (direction.equals("east")) { // rotate 90 degrees clockwise
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

        public int[][] getTiles () {
            return tiles;
        }

        public boolean getIsHorizontal () {
            return isHorizontal;
        }
}