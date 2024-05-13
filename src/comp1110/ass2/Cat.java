package comp1110.ass2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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
        // If the destination is a cat, turn it to uppercase
        // If the destination is 'w', turn it to 'W'
        if (destRow[destX] == 'w') {
            destRow[destX] = 'W';
        } else {
            destRow[destX] = Character.toUpperCase(cat);
        }
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

    public static boolean checkMovementValid(String[] gameState, String movementString) {
        char cat = movementString.charAt(0);

        int startY = Integer.parseInt(movementString.substring(1, 3));
        int startX = Integer.parseInt(movementString.substring(3, 5));
        int destY = Integer.parseInt(movementString.substring(5, 7));
        int destX = Integer.parseInt(movementString.substring(7, 9));

        // for debugging
//        System.out.println(destY+ " " + destX);

        String[] rows = gameState[0].split("\n");

        int width = rows[0].length();
        int height = rows.length;

        if (startY < 0 || startY >= height || startX < 0 || startX >= width ||
                destY < 0 || destY >= height || destX < 0 || destX >= width) {
            return false;
        }

        char[] destRow = rows[destY].toCharArray();

        char dest = destRow[destX];
        if (dest != Character.toLowerCase(cat) && dest != 'w') {
            return false;
        }

        // Check if discarded card exists in hand
        boolean flag = false;
        String hand = gameState[2];
        char discardedDeck = movementString.charAt(9);
        char discardedCard = movementString.charAt(10);
        int indexDeck = hand.indexOf(discardedDeck);
        int indexNextDeck = hand.length();
        for (int i = indexDeck; i < hand.length(); i++) {
            if (Character.isUpperCase(hand.charAt(i))) {
                indexNextDeck = i;
            }
        }
        for (int i = indexDeck; i < indexNextDeck; i++) {
            if (hand.charAt(i) == discardedCard) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return false;
        }

        // Check if the cat can move to the destination
        char[][] tiles = Board.charBoard(gameState[0]);
        boolean[][] visited = new boolean[tiles.length][tiles[0].length];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startY, startX});
        visited[startY][startX] = true;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[0] == destY && current[1] == destX) {
                return true;
            }

            for (int[] direction : directions) {
                int newY = current[0] + direction[0];
                int newX = current[1] + direction[1];

                if (newY >= 0 && newY < height && newX >= 0 && newX < width
                        && !visited[newY][newX] &&
                        (tiles[newY][newX] == Character.toLowerCase(cat) || tiles[newY][newX] == Character.toUpperCase(cat)
                || tiles[newY][newX] == 'w' || tiles[newY][newX] == 'W')) {

                    visited[newY][newX] = true;

//                    System.out.println(newY + " " + newX);

                    queue.add(new int[]{newY, newX});
                }
            }
        }


        return false;
    }

    public static boolean isCatOnRaft(String[] gamestate, int x, int y) {
        char[][] board = Board.charBoard(gamestate[0]);
        // the cat is on the raft if the squares around it contains an 'o'
        return (x > 0 && board[y][x - 1] == 'o') || (x < board[0].length - 1 && board[y][x + 1] == 'o') ||
                (y > 0 && board[y - 1][x] == 'o') || (y < board.length - 1 && board[y + 1][x] == 'o') ||
                (x > 0 && y > 0 && board[y - 1][x - 1] == 'o') ||
                (x < board[0].length - 1 && y < board.length - 1 && board[y + 1][x + 1] == 'o') ||
                (x > 0 && y < board.length - 1 && board[y + 1][x - 1] == 'o') ||
                (x < board[0].length - 1 && y > 0 && board[y - 1][x + 1] == 'o');

    }
}
