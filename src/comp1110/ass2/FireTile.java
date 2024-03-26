package comp1110.ass2;

import java.util.Random;

public class FireTile {

    private String bag;

    public boolean isEmpty(String thing){
        return thing == "";
    }
    public void parseFromString(String){
    }
    public static String pickFire(String stringBag){
        Random random = new Random();
        int randomIndex = random.nextInt(stringBag.length());
        char randomChar = stringBag.charAt(randomIndex); // Gets a random char from our bag

        for (String fireTile : Utility.FIRE_TILES){
            if(fireTile.charAt(0)==randomChar){
                return fireTile; // returns the unique fireTile based on the ID
            }
            else return null; // invalid character?
        }
        return null;
    }


}
