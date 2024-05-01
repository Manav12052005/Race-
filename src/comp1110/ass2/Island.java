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

        // Process each island substring
        for (String s : sb) {
            char size = s.charAt(0);
            char rotation = s.charAt(1);

            // Handle island size and rotation
            switch (size) {
                case 'S':
                    // Handle small island
                    break;
                case 'L':
                    // Handle large island
                    break;
                default:
                    throw new IllegalArgumentException("Invalid island size!");
            }

            switch (rotation) {
                case 'N':
                    // Handle north rotation
                    break;
                case 'E':
                    // Handle east rotation
                    break;
                case 'S':
                    // Handle south rotation
                    break;
                case 'W':
                    // Handle west rotation
                    break;
                case 'A':
                    // Handle any rotation
                    break;
                default:
                    throw new IllegalArgumentException("Invalid rotation!");
            }
        }

        this.island = island;
    }
}

