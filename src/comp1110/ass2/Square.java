package comp1110.ass2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


/**
 *determines the possible square conditions
 *colour
 *fire
 *raft
 */
public class Square extends ImageView {
    public static final double SQUARE_WIDTH = 40;
    private double x;
    private double y;
    private double mouseX, mouseY;
    public enum type {
        BLUE, GREEN, PURPLE, RED, YELLOW,
        blueCAT, greenCAT, purpleCAT, redCAT, yellowCAT,
        FIRE, OBJECTIVE, WILD, wildOCCUPIED, NONE
    }
    private final type t;
    private final Image img;

    public static Square selectedSquare = null;

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
        }

        setOnMousePressed((MouseEvent event) -> {
            if (isCat()) {
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
                toFront(); // bring the square to the front
            }
        });

        setOnMouseDragged((MouseEvent event) -> {
            if (isCat()) {
                double deltaX = event.getSceneX() - mouseX;
                double deltaY = event.getSceneY() - mouseY;
                setLayoutX(getLayoutX() + deltaX);
                setLayoutY(getLayoutY() + deltaY);
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();
            }
        });

        setOnMouseReleased((MouseEvent event) -> {
            if (isCat()) {
                double newX = Math.round(getLayoutX() / SQUARE_WIDTH) * SQUARE_WIDTH;
                double newY = Math.round(getLayoutY() / SQUARE_WIDTH) * SQUARE_WIDTH;
                setLayoutX(newX);
                setLayoutY(newY);
            }
        });

//        setOnMousePressed((MouseEvent event) -> {
//            if (Square.selectedSquare != null) {
//                Square.selectedSquare.setEffect(null); // remove highlight from previously selected square
//            }
//            Square.selectedSquare = this;
//            this.setEffect(new DropShadow(20, Color.WHITE)); // highlight the selected square
//        });
//
//        setOnMouseClicked(event -> {
//            if (Square.selectedSquare != null) {
//                double newX = Math.floor((event.getSceneX() / Square.SQUARE_WIDTH)) * Square.SQUARE_WIDTH;
//                double newY = Math.floor((event.getSceneY() / Square.SQUARE_WIDTH)) * Square.SQUARE_WIDTH;
//                Square.selectedSquare.setLayoutX(newX);
//                Square.selectedSquare.setLayoutY(newY);
//                Square.selectedSquare.setEffect(null); // remove highlight
//                Square.selectedSquare = null; // deselect the square
//            }
//        });
    }

    public static type charToType(char t){
        return switch (t) {
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
            case 'o', 'w', 'W' -> type.OBJECTIVE;
            case 'N' -> type.NONE;
            default -> throw new IllegalArgumentException("type char ID not valid");
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

    private boolean isCat() {
        return t == type.blueCAT || t == type.greenCAT || t == type.purpleCAT || t == type.redCAT || t == type.yellowCAT;
    }
}
