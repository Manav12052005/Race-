package comp1110.ass2;

import java.util.ArrayList;
import java.util.Random;


/**
 * holds whole board state (in an ArrayList of squares)
 */
public class Board {
    private static final double SQUARE_WIDTH = Square.SQUARE_WIDTH;
    private ArrayList<Square> squares;

    // Enum to represent each board and its orientation
    public enum BoardName {
        BOARD_1, BOARD_2, BOARD_3, BOARD_4,
        RECTANGLE_BOARD_1, RECTANGLE_BOARD_2, RECTANGLE_BOARD_3, RECTANGLE_BOARD_4
    }

    // Method to retrieve board strings based on board name and orientation
    public static String getBoard(BoardName boardName, boolean withFire) {
        String[][] boardArray;
        switch (boardName) {
            case BOARD_1:
            case BOARD_2:
            case BOARD_3:
            case BOARD_4:
                boardArray = SQUARE_BOARDS;
                break;
            default:
                boardArray = RECTANGLE_BOARDS;
                break;
        }

        int index = boardName.ordinal();
        return boardArray[index][withFire ? 0 : 1];
    }

    // String representations of square boards
    private static final String[][] SQUARE_BOARDS = {
            // Board 1
            {
                    "fffgygbyr",
                    "fffgygpby",
                    "fffrrbrgp",
                    "fffbgypbr",
                    "fffpbrpgy",
                    "fffyrygyp",
                    "fffgbbrpb",
                    "fffpggbyg",
                    "fffpypgrr"
            },
            {
                    "gbygygbyr",
                    "brpgygpby",
                    "ygbrrbrgp",
                    "bypbgypbr",
                    "gprpbrpgy",
                    "rbgyrygyp",
                    "ybygbbrpb",
                    "ygrpggbyg",
                    "bbypypgrr"
            },
            // Board 2
            {
                    "fffyggybp",
                    "fffpyyrgy",
                    "fffrgbpgb",
                    "fffbrgyrp",
                    "fffppbgrb",
                    "fffbyrpby",
                    "fffbygbrp",
                    "fffprgryg",
                    "fffybyppy"
            },
            {
                    "gbbyggybp",
                    "rbgpyyrgy",
                    "ygyrgbpgb",
                    "bprbrgyrp",
                    "gbrppbgrb",
                    "pygbyrpby",
                    "gbybygbrp",
                    "pygprgryg",
                    "gbrybyppy"
            },
            // Board 3
            {
                    "fffpybygr",
                    "fffrpgbyb",
                    "fffbrbgyr",
                    "fffgpypbg",
                    "fffrbgpry",
                    "fffpyrygb",
                    "fffrggbpg",
                    "fffpbyryb",
                    "fffgybrgy"
            },
            {
                    "grgpybygr",
                    "gyprpgbyb",
                    "brbbrbgyr",
                    "gypgpypbg",
                    "pbrrbgpry",
                    "ygppyrygb",
                    "bpyrggbpg",
                    "brypbyryb",
                    "gybgybrgy"
            },
            // Board 4
            {
                    "fffpgyyrb",
                    "fffpbbryp",
                    "fffbgypgb",
                    "fffyybyrg",
                    "fffbgrggy",
                    "fffgpgbgp",
                    "fffbgybpb",
                    "fffpyrgyg",
                    "fffgppbrr"
            },
            {
                    "rrbpgyyrb",
                    "ygypbbryp",
                    "bprbgypgb",
                    "rgbyybyrg",
                    "pypbgrggy",
                    "ygygpgbgp",
                    "bprbgybpb",
                    "rybpyrgyg",
                    "bgygppbrr"
            }
    };

    // String representations of rectangle boards
    private static final String[][] RECTANGLE_BOARDS = {
            // Board 1
            {
                    "fffbrgprg",
                    "fffrbpyby",
                    "fffpgybrr",
                    "fffbybgyp",
                    "fffbrgygg",
                    "fffybpbry"
            },
            {
                    "yypbrgprg",
                    "bpgrbpyby",
                    "gbypgybrr",
                    "grgbybgyp",
                    "rypbrgygg",
                    "bpgybpbry"
            },
            // Board 2
            {
                    "fffgrybbr",
                    "fffybgryp",
                    "fffbpbygg",
                    "fffyprypy",
                    "fffrygpbb",
                    "ffbpygyr"
            },
            {
                    "bbggrybbr",
                    "grpybgryp",
                    "pgrbpbygg",
                    "gpbyprypy",
                    "bygrygpbb",
                    "grybpygyr"
            },
            // Board 3
            {
                    "fffygybgy",
                    "fffgyrpbg",
                    "fffpbyyrp",
                    "fffgbrggr",
                    "fffpgbypb",
                    "fffgrpryb"
            },
            {
                    "brpygybgy",
                    "grbgyrpbg",
                    "gyppbyyrp",
                    "ybygbrggr",
                    "gyrpgbypb",
                    "pbbgrpryb"
            },
            // Board 4
            {
                    "fffbgpgpb",
                    "fffyrgbyr",
                    "fffgybpgy",
                    "fffbrrbgb",
                    "fffpygybp",
                    "fffgpbyry"
            },
            {
                    "gyybgpgpb",
                    "prbyrgbyr",
                    "yprgybpgy",
                    "ggybrrbgb",
                    "brgpygybp",
                    "bprgpbyry"
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
    // Method to generate island board based on island string
    public static String generateIslandBoard(String islandString) {
        StringBuilder islandBoard = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < islandString.length(); i += 2) {
            char size = islandString.charAt(i);
            char rotation = islandString.charAt(i + 1);

            // Determine the board size
            String[][] boards;
            if (size == 'S') {
                boards = RECTANGLE_BOARDS;
            } else {
                boards = SQUARE_BOARDS;
            }

            // Choose the board index randomly
            int boardIndex = random.nextInt(boards.length);

            // Get the board based on rotation
            String[] selectedBoard = boards[boardIndex];
            int orientationIndex;
            if (rotation == 'A') {
                // If rotation is 'A', choose randomly between the two orientations
                orientationIndex = random.nextInt(2);
            } else {
                // Map rotation characters to index (N->0, E->1, S->2, W->3)
                orientationIndex = "NESW".indexOf(rotation);
            }

            // Append the selected part of the board to the island board string
            islandBoard.append(selectedBoard[orientationIndex]);
        }

        return islandBoard.toString();
    }
    public class Main {
        public static void main(String[] args) {
            // Retrieve board strings and print them

            // Example 1: Retrieve the board string for BOARD_1 without fire
            String board1WithoutFire = Board.getBoard(Board.BoardName.BOARD_1, false);
            System.out.println("Board 1 without fire:");
            System.out.println(board1WithoutFire);

            // Example 2: Retrieve the board string for RECTANGLE_BOARD_3 with fire
            String rectangleBoard3WithFire = Board.getBoard(Board.BoardName.RECTANGLE_BOARD_3, true);
            System.out.println("\nRectangle Board 3 with fire:");
            System.out.println(rectangleBoard3WithFire);

            // Example 3: Retrieve the board string for BOARD_4 with fire
            String board4WithFire = Board.getBoard(Board.BoardName.BOARD_4, true);
            System.out.println("\nBoard 4 with fire:");
            System.out.println(board4WithFire);
        }
    }

}