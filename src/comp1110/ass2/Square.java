package comp1110.ass2;

/**
 *determines the possible square conditions
 *colour
 *fire
 *raft
 */
public class Square {
    private int x;
    private int y;
    private enum type {
        BLUE, GREEN, PURPLE, RED, YELLOW,
        blueCAT, greenCAT, purpleCAT, redCAT, yellowCAT,
        FIRE, OBJECTIVE, WILD, wildOCCUPIED, NONE
    }
    private type t;

    public Square(int x, int y, char t) {
        this.x = x;
        this.y = y;
        this.t = switch (t) {
            case 'b' -> type.BLUE;
            case 'g' -> type.GREEN;
            case 'p' -> type.PURPLE;
            case 'r' -> type.RED;
            case 'y' -> type.YELLOW;
            case 'B' -> type.blueCAT;
            case 'G' -> type.greenCAT;
            case 'P' -> type.purpleCAT;
            case 'R' -> type.redCAT;
            case 'Y' -> type.yellowCAT;
            case 'f' -> type.FIRE;
            case 'o' -> type.OBJECTIVE;
            case 'w' -> type.WILD;
            case 'W' -> type.wildOCCUPIED;
            default -> type.NONE;
        };
    }
}
