package comp1110.ass2;

import java.util.ArrayList;

/**
 * represents the game board
 * converted from Island substring from challenge string
 *
 */
public class Island {
    private String island;

    public Island(String island){
        if (!(island.length() == 4 || island.length() == 8)) {
            throw new IllegalArgumentException("Island length invalid!");
        }

        ArrayList<String> sb = new ArrayList<>();
        for (int i = 0; i < island.length() / 2; i++) {
            String board = island.substring(i * 2, i * 2 + 2);
            sb.add(board);
        }

        // Will need further instructions to continue
        for (String s : sb) {
            switch (s.charAt(0)) {
                case 'L':
                    break;
                case 'S':
                    break;
            }
        }

        this.island = island;
    }

}
