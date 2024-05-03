package comp1110.ass2;

import java.util.HashMap;
import java.util.Map;

public class Cat {
    private String exhausted;

    public Cat(String exhausted) {
        this.exhausted = exhausted;
    }

    public static String catMove(String boardState, String movementString) {
        char cat = movementString.charAt(0);

        int startY = Integer.parseInt(movementString.substring(1, 3));
        int startX = Integer.parseInt(movementString.substring(3, 5));
        int destY = Integer.parseInt(movementString.substring(5, 7));
        int destX = Integer.parseInt(movementString.substring(7, 9));

        String[] rows = boardState.split("\n");
        char[] startRow = rows[startY].toCharArray();
        startRow[startX] = Character.toLowerCase(cat);
        rows[startY] = new String(startRow);
        char[] destRow = rows[destY].toCharArray();
        destRow[destX] = Character.toUpperCase(cat);
        rows[destY] = new String(destRow);
        boardState = String.join("\n", rows) + "\n";

        return boardState;
    }

    public static String catExhauste(String exhaustedCats, String movementString) {
        StringBuilder sb = new StringBuilder();
        Map<Character, String> exhaustedMap = new HashMap<>();

        // Populate the map with characters and their coordinates
        for (int i = 0; i < exhaustedCats.length(); i += 5) {
            exhaustedMap.put(exhaustedCats.charAt(i), exhaustedCats.substring(i + 1, i + 5));
        }

        // Iterate over the characters in the desired order
        for (char c : new char[]{'B', 'G', 'P', 'R', 'Y'}) {
            if (c == movementString.charAt(0)) {
                // If the character is the one in the movementString, use the new coordinate
                sb.append(c);
                sb.append(movementString.substring(5, 9));
            } else if (exhaustedMap.containsKey(c)) {
                // If the character exists in ret[3], use the old coordinate
                sb.append(c);
                sb.append(exhaustedMap.get(c));
            }
        }
        return sb.toString();
    }


}
