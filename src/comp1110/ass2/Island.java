package comp1110.ass2;

import java.util.ArrayList;

/**
 * represents the game board
 * converted from Island substring from challenge string
 *
 */
public class Island {
    private String island;

    // Constructor
    public Island(String island){
        if (!(island.length() == 4 || island.length() == 8)) {
            throw new IllegalArgumentException("Island length invalid!");
        }

        ArrayList<String> sb = new ArrayList<>();
        for (int i = 0; i < island.length() / 2; i++) {
            String board = island.substring(i * 2, i * 2 + 2);
            sb.add(board);
        }

        for (String s : sb) {
            
        }

        this.island = island;
    }
}
