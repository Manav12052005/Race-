package comp1110.ass2;

import java.util.Random;

public class FireTile {
//    the final implementation will be Square[][] but to test rotate we will use int[][].
//    private Square[][] tiles;
    private int[][] tiles;
    private Boolean isHorizontal;

    public FireTile(int[][] tiles, boolean isHorizontal){
        this.tiles = tiles;
        this.isHorizontal = isHorizontal;
    }

    public void rotate(String direction){

        int[][] rotatedTiles;
        boolean newIsHorizontal = isHorizontal;

        if(isHorizontal){
            rotatedTiles = new int[3][4];
        }
        else {
            rotatedTiles = new int[4][3];

        }
        for(int row = 0; row < tiles.length; row++){
            for(int col = 0; col < tiles[0].length; col++){
                if(direction.equals("east")){ //flip 90 degrees clockwise
                    rotatedTiles[col][tiles.length - 1 - row] = tiles[row][col];
                }
                else if (direction.equals("west")){ //flip 90 degrees counterclockwise
                    rotatedTiles[tiles[0].length - 1 - col][row] = tiles[row][col];
                }
                else{ //flip tile across the vertical axis
                    rotatedTiles[row][tiles[0].length - 1 - col] = tiles[row][col];
                }
            }
            tiles = rotatedTiles;
            isHorizontal = newIsHorizontal;
        }
    }

    public boolean isEmpty(String thing){
        return thing == "";
    }

    public static String pickFire(String stringBag){
        Random random = new Random();
        int randomIndex = random.nextInt(stringBag.length());
        char randomChar = stringBag.charAt(randomIndex); // Gets a random char from our bag

        for (String fireTile : Utility.FIRE_TILES){
            if(fireTile.charAt(0)==randomChar){
                return fireTile; // returns the unique fireTile based on the ID
            }
        }
        return null;
    }

    public int[][] getTiles() {
        return tiles;
    }

    public boolean getIsHorizontal() {
        return isHorizontal;
    }
}
