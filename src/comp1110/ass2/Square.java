package comp1110.ass2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *determines the possible square conditions
 *colour
 *fire
 *raft
 */
public class Square extends ImageView {
    private double x;
    private double y;
    private enum type {
        BLUE, GREEN, PURPLE, RED, YELLOW,
        blueCAT, greenCAT, purpleCAT, redCAT, yellowCAT,
        FIRE, OBJECTIVE, WILD, wildOCCUPIED, NONE
    }
    private type t;
    private Image img;

    public Square(double x, double y, char t) {
        this.x = x;
        this.y = y;
        switch (t) {
            case 'b':
                this.t = type.BLUE;
                img = new Image("comp1110/ass2/gui/assets/blue.png");
                break;
            case 'g':
                this.t = type.GREEN;
                img = new Image("comp1110/ass2/gui/assets/green.png");
                break;
            case 'p':
                this.t = type.PURPLE;
                img = new Image("comp1110/ass2/gui/assets/purple.png");
                break;
            case 'r':
                this.t = type.RED;
                img = new Image("comp1110/ass2/gui/assets/red.png");
                break;
            case 'y':
                this.t = type.YELLOW;
                img = new Image("comp1110/ass2/gui/assets/yellow.png");
                break;
            case 'B':
                this.t = type.blueCAT;
                img = new Image("comp1110/ass2/gui/assets/blueCat.png");
                break;
            case 'G':
                this.t = type.greenCAT;
                img = new Image("comp1110/ass2/gui/assets/greenCat.png");
                break;
            case 'P':
                this.t = type.purpleCAT;
                img = new Image("comp1110/ass2/gui/assets/purpleCat.png");
                break;
            case 'R':
                this.t = type.redCAT;
                img = new Image("comp1110/ass2/gui/assets/redCat.png");
                break;
            case 'Y':
                this.t = type.yellowCAT;
                img = new Image("comp1110/ass2/gui/assets/yellowCat.png");
                break;
            case 'f':
                this.t = type.FIRE;
                img = new Image("comp1110/ass2/gui/assets/fire.png");
                break;
            case 'o':
            case 'w':
            case 'W':
                this.t = type.OBJECTIVE;
                img = new Image("comp1110/ass2/gui/assets/objective.png");
                break;
            default:
                this.t = type.NONE;
                img = null;
                break;
        };
    }

    public type getT() {
        return this.t;
    }

    public Image getImg() {
        return img;
    }

    public double getValueX() {
        return x;
    }

    public double getValueY() {
        return y;
    }
}
