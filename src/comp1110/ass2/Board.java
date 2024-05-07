package comp1110.ass2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;




/**
 * holds whole board state (in an ArrayList of squares)
 */
public class Board {
    private static final double SQUARE_WIDTH = Square.SQUARE_WIDTH;
    private ArrayList<Square> squares;

    // Enum to represent each board and its orientation
    public enum BoardName {
        SQUARE_BOARD_1, SQUARE_BOARD_2, SQUARE_BOARD_3, SQUARE_BOARD_4,
        RECTANGLE_BOARD_1, RECTANGLE_BOARD_2, RECTANGLE_BOARD_3, RECTANGLE_BOARD_4
    }

    // Method to retrieve board strings based on board name and orientation

    public static String getBoard(BoardName boardName, boolean withFire) {
        String[][] boardArray;

        // Select the board array based on the input board name
        switch (boardName) {
            case SQUARE_BOARD_1:
                boardArray = SQUARE_BOARDS;
                break;
            case SQUARE_BOARD_2:
                boardArray = SQUARE_BOARDS;
                break;
            case SQUARE_BOARD_3:
                boardArray = SQUARE_BOARDS;
                break;
            case SQUARE_BOARD_4:
                boardArray = SQUARE_BOARDS;
                break;
            case RECTANGLE_BOARD_1:
                boardArray = RECTANGLE_BOARDS;
                break;
            case RECTANGLE_BOARD_2:
                boardArray = RECTANGLE_BOARDS;
                break;
            case RECTANGLE_BOARD_3:
                boardArray = RECTANGLE_BOARDS;
                break;
            case RECTANGLE_BOARD_4:
                boardArray = RECTANGLE_BOARDS;
                break;
            default:
                throw new IllegalArgumentException("Invalid board name: " + boardName);
        }

        // Get the chosen board
        String[] board = boardArray[boardName.ordinal() % 4]; // Adjust the index based on the number of boards

        // Return the appropriate part of the board based on whether it's with fire or not
        return withFire ? board[0] : board[1];
    }

    // String representations of square boards
    private static final String[][] SQUARE_BOARDS = {
            // Board 1
            {
                    """ 
        fffgygbyr
        fffgygpby
        fffrrbrgp
        fffbgypbr
        fffpbrpgy
        fffyrygyp
        fffgbbrpb
        fffpggbyg
        fffpypgrr
        """ ,
                    """ 
        gbygygbyr
        brpgygpby
        ygbrrbrgp
        bypbgypbr
        gprpbrpgy
        rbgyrygyp
        ybygbbrpb
        ygrpggbyg
        bbypypgrr
        """
            },
            // Board 2
            {
                    """ 
        fffyggybp
        fffpyyrgy
        fffrgbpgb
        fffbrgyrp
        fffppbgrb
        fffbyrpby
        fffbygbrp
        fffprgryg
        fffybyppy
        """
            ,
                    """ 
        gbbyggybp
        rbgpyyrgy
        ygyrgbpgb
        bprbrgyrp
        gbrppbgrb
        pygbyrpby
        gbybygbrp
        pygprgryg
        gbrybyppy
        """
            },
            // Board 3
            {
                    """ 
        fffpybygr
        fffrpgbyb
        fffbrbgyr
        fffgpypbg
        fffrbgpry
        fffpyrygb
        fffrggbpg
        fffpbyryb
        fffgybrgy
        """
            ,
                    """ 
        grgpybygr
        gyprpgbyb
        brbbrbgyr
        gypgpypbg
        pbrrbgpry
        ygppyrygb
        bpyrggbpg
        brypbyryb
        gybgybrgy
        """
            },
            // Board 4
            {
                    """ 
        fffpgyyrb
        fffpbbryp
        fffbgypgb
        fffyybyrg
        fffbgrggy
        fffgpgbgp
        fffbgybpb
        fffpyrgyg
        fffgppbrr
        """
            ,
                    """ 
        rrbpgyyrb
        ygypbbryp
        bprbgypgb
        rgbyybyrg
        pypbgrggy
        ygygpgbgp
        bprbgybpb
        rybpyrgyg
        bgygppbrr
        """
            }
    };

    // String representations of rectangle boards
    private static final String[][] RECTANGLE_BOARDS = {
            // Board 1
            {
                    """ 
        fffbrgprg
        fffrbpyby
        fffpgybrr
        fffbybgyp
        fffbrgygg
        fffybpbry
        """
            ,
                    """ 
        yypbrgprg
        bpgrbpyby
        gbypgybrr
        grgbybgyp
        rypbrgygg
        bpgybpbry
        """
            },
            // Board 2
            {
                    """ 
        fffgrybbr
        fffybgryp
        fffbpbygg
        fffyprypy
        fffrygpbb
        ffbpygyr
        """
            ,
                    """ 
        bbggrybbr
        grpybgryp
        pgrbpbygg
        gpbyprypy
        bygrygpbb
        grybpygyr
        """
            },
            // Board 3
            {
                    """ 
        fffygybgy
        fffgyrpbg
        fffpbyyrp
        fffgbrggr
        fffpgbypb
        fffgrpryb
        """
            ,
                    """ 
        brpygybgy
        grbgyrpbg
        gyppbyyrp
        ybygbrggr
        gyrpgbypb
        pbbgrpryb
        """
            },
            // Board 4
            {
                    """ 
        fffbgpgpb
        fffyrgbyr
        fffgybpgy
        fffbrrbgb
        fffpygybp
        fffgpbyry
        """
            ,
                    """ 
        gyybgpgpb
        prbyrgbyr
        yprgybpgy
        ggybrrbgb
        brgpygybp
        bprgpbyry
        """
            }
    };


    // Constructor
    public Board(String boardString) {
        ArrayList<Square> squares = new ArrayList<>();
        boardString.lines().forEach(line -> {
            double x = 0, y = (squares.size() / line.length()) * SQUARE_WIDTH;
            for (char ch : line.toCharArray()) {
                Square current = new Square(x, y, ch);
                squares.add(current);
                x += 38;
            }
        });
        this.squares = squares;
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }


    public static char[][] charBoard(String boardGameState) { // A simple character representation of board
        String[] lines = boardGameState.split("\n");
        int rows = lines.length;
        int cols = lines[0].length();
        char[][] tiles = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            tiles[i] = lines[i].toCharArray();
        }
        return tiles;
    }

    public static String charBoardToString(char[][] tiles) {
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < tiles.length; i++) {
            boardString.append(new String(tiles[i]));
            boardString.append("\n");
        }
        return boardString.toString();
    }

    public static String generateIslandBoard(String islandString) {
        StringBuilder islandBoard = new StringBuilder();
        Random random = new Random();
        Set<Integer> chosenIndices = new HashSet<>(); // Set to keep track of chosen board indices

        // Check if the length of the island string is 8 characters
        if (islandString.length() == 8) {
            String firstBoard = generateSingleBoard(islandString.substring(0, 2), random, chosenIndices);
            String thirdBoard = generateSingleBoard(islandString.substring(4, 6), random, chosenIndices);

            String secondBoard = generateSingleBoard(islandString.substring(2, 4), random, chosenIndices);
            String fourthBoard = generateSingleBoard(islandString.substring(6, 8), random, chosenIndices);

            String[] firstLines = firstBoard.split("\n");
            String[] thirdLines = thirdBoard.split("\n");
            String[] secondLines = secondBoard.split("\n");
            String[] fourthLines = fourthBoard.split("\n");

            for (int i = 0; i < firstLines.length; i++) {
                islandBoard.append(firstLines[i]).append(thirdLines[i]).append("\n");
            }
            for (int i = 0; i < secondLines.length; i++) {
                islandBoard.append(secondLines[i]).append(fourthLines[i]).append("\n");
            }
        } else {
            // Handle the case when the length of the island string is not 8 characters
            for (int i = 0; i < islandString.length(); i += 2) {
                String singleBoard = generateSingleBoard(islandString.substring(i, i + 2), random, chosenIndices);
                islandBoard.append(singleBoard);
            }
        }

        return islandBoard.toString();
    }

    private static String generateSingleBoard(String islandSubstring, Random random, Set<Integer> chosenIndices) {
        char size = islandSubstring.charAt(0);
        char rotation = islandSubstring.charAt(1);

        // Determine the board array based on the size
        String[][] boards;
        if (size == 'L') {
            boards = SQUARE_BOARDS;
        } else if (size == 'S') {
            boards = RECTANGLE_BOARDS;
        } else {
            throw new IllegalArgumentException("Invalid island string: " + islandSubstring);
        }

        // Choose the board index randomly from the available options
        int boardIndex;
        if (size == 'L') {
            // Choose randomly from SQUARE_BOARDS
            boardIndex = random.nextInt(SQUARE_BOARDS.length);
        } else if (size == 'S') {
            // Choose randomly from RECTANGLE_BOARDS
            boardIndex = random.nextInt(RECTANGLE_BOARDS.length);
        } else {
            throw new IllegalArgumentException("Invalid island string: " + islandSubstring);
        }

        // Ensure that the selected board index hasn't been chosen before
        while (chosenIndices.contains(boardIndex)) {
            if (size == 'L') {
                boardIndex = random.nextInt(SQUARE_BOARDS.length);
            } else if (size == 'S') {
                boardIndex = random.nextInt(RECTANGLE_BOARDS.length);
            }
        }

        chosenIndices.add(boardIndex); // Add the chosen index to the set

        // Determine if withFire should be true based on rotation
        boolean withFire = rotation != 'A';

        // Get the board based on rotation
        String boardString;
        if (size == 'L') {
            // Choose randomly from SQUARE_BOARDS
            boardString = getBoard(BoardName.values()[BoardName.SQUARE_BOARD_1.ordinal() + boardIndex], withFire);
        } else if (size == 'S') {
            // Choose randomly from RECTANGLE_BOARDS
            boardString = getBoard(BoardName.values()[BoardName.RECTANGLE_BOARD_1.ordinal() + boardIndex], withFire);
        } else {
            throw new IllegalArgumentException("Invalid island string: " + islandSubstring);
        }

        // Rotate the board string according to the orientation if not 'A'
        String rotatedBoardString;
        if (rotation == 'A') {
            rotatedBoardString = boardString;
        } else {
            rotatedBoardString = rotateString(boardString, rotation);
        }

        return rotatedBoardString;
    }

    public static String addFireCards(String islandBoard, String fireSubstring) {
        StringBuilder islandWithFire = new StringBuilder(islandBoard);

        // Parse the fire substring and add fire cards to the island board
        int index = 0;
        while (index < fireSubstring.length()) {
            // Extract the coordinates of the top-left square of the fire card
            int row = Integer.parseInt(fireSubstring.substring(index, index + 2));
            int col = Integer.parseInt(fireSubstring.substring(index + 2, index + 4));

            // Replace the square and its neighbors with fire tiles
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    // Ensure the index is within the bounds of the board
                    if (i >= 0 && i < islandBoard.length() / (islandBoard.indexOf('\n') + 1) &&
                            j >= 0 && j < islandBoard.indexOf('\n')) {
                        int position = (i * (islandBoard.indexOf('\n') + 1)) + j;
                        islandWithFire.setCharAt(position, 'f'); // Replace the square with fire tile
                    }
                }
            }

            // Move to the next fire location substring
            index += 4; // Each fire location substring has 4 characters (2 for row, 2 for column)
        }

        return islandWithFire.toString();
    }


    public static String rotateString(String str, char orientation) {
        switch (orientation) {
            case 'N':
                return str; // No rotation needed for North orientation
            case 'E':
                return rotate(str, 1); // Rotate clockwise for East orientation
            case 'S':
                return rotate(str, 2); // Rotate 180 degrees for South orientation
            case 'W':
                return rotate(str, 3); // Rotate counterclockwise for West orientation
            default:
                throw new IllegalArgumentException("Invalid orientation character: " + orientation);
        }
    }
    private static String rotate(String str, int numRotations) {
        // Split the string into individual lines
        String[] lines = str.split("\n");
        int numLines = lines.length;
        int lineLength = lines[0].length();

        StringBuilder rotatedString = new StringBuilder();
        // Rotate each line based on the number of rotations
        for (int r = 0; r < numRotations; r++) {
            StringBuilder rotatedLine = new StringBuilder();
            for (int i = 0; i < lineLength; i++) {
                for (int j = numLines - 1; j >= 0; j--) {
                    rotatedLine.append(lines[j].charAt(i));
                }
                rotatedString.append(rotatedLine.reverse()).append("\n");
                rotatedLine.setLength(0); // Reset StringBuilder for the next iteration
            }
            lines = rotatedString.toString().split("\n"); // Update lines for next rotation
            rotatedString.setLength(0); // Reset StringBuilder for the next iteration
        }
        return lines[0].isEmpty() ? "" : String.join("\n", lines);
    }

}

class Main {
    public static void main(String[] args) {
        // Retrieve board strings and print them

        // Example 1: Retrieve the board string for BOARD_1 without fire
        String board1WithoutFire = Board.getBoard(Board.BoardName.SQUARE_BOARD_1, false);
        System.out.println("Square Board 1 without fire:");
        System.out.println(board1WithoutFire);

        // Example 2: Retrieve the board string for RECTANGLE_BOARD_3 with fire
        String rectangleBoard3WithFire = Board.getBoard(Board.BoardName.RECTANGLE_BOARD_3, true);
        System.out.println("\nRectangle Board 3 with fire:");
        System.out.println(rectangleBoard3WithFire);

        // Example 3: Retrieve the board string for BOARD_4 with fire
        String board4WithFire = Board.getBoard(Board.BoardName.SQUARE_BOARD_4, true);
        System.out.println("\n Square Board 4 with fire:");
        System.out.println(board4WithFire);



        // Test the rotateString function
        String testString = """
        fffpgyyrb
        fffpbbryp
        fffbgypgb
        fffyybyrg
        fffbgrggy
        fffgpgbgp
        fffbgybpb
        fffpyrgyg
        fffgppbrr""";
        System.out.println("\nOriginal String:");
        System.out.println(testString);
        System.out.println("\nRotated String (Clockwise):");
        System.out.println(Board.rotateString(testString, 'E')); // Rotate clockwise (East)
        System.out.println("\nRotated String (180 degrees):");
        System.out.println(Board.rotateString(testString, 'S')); // Rotate 180 degrees (South)
        System.out.println("\nRotated String (Counterclockwise):");
        System.out.println(Board.rotateString(testString, 'W')); // Rotate counterclockwise (West)

        String islandString = "LASN";
        String generatedIslandBoard = Board.generateIslandBoard(islandString);
        System.out.println("\nGenerated Island Board:");
        System.out.println(generatedIslandBoard);

        // Test the addFireCards function
        String islandBoard = """
            gyybgpgpb
            prbyrgbyr
            yprgybpgy
            ggybrrbgb
            brgpygybp
            bprgpbyry
            """;
        String fireSubstring = "04010107";
        System.out.println("\nIsland Board before adding fire cards:");
        System.out.println(islandBoard);
        String islandWithFire = Board.addFireCards(islandBoard, fireSubstring);
        System.out.println("\nIsland Board after adding fire cards:");
        System.out.println(islandWithFire);

    }


}

